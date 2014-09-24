package org.furygames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;
import org.furygames.actors.BackgroundActor;
import org.furygames.actors.Box2DCreator;
import org.furygames.furyball.FuryBall;
import org.furygames.inputs.GestureInput;
import org.furygames.inputs.VirtualController;
import org.furygames.levels.*;

public class GameScreen extends GenericScreen {

    private final float GRAVITY_FORCE = 5f;

    private Box2DDebugRenderer debug;
    private OrthographicCamera camera;
    private World world;
    private Array<Body> worldBodies;
    private Vector2 gravity;
    private ILevel currentLevel; // Nivel actual
    private Sprite background;

    // Si el nivel esta cargado
    public static boolean isLoaded = false;
    // Cuando se necesita elimiar un nivel
    public static boolean needsToBeCleaned = false;
    public static ELevels eLevels;

    public static Sound boing;

    public GameScreen() {
        FuryBall.assets.cargarAssets();
        FuryBall.assets.manager.finishLoading();
    }

    @Override
    public void show() {
        super.show();

        gravity = new Vector2(0, 0);
        world = new World(gravity, false);
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH / 2f, HEIGHT / 2f, 0);

        worldBodies = new Array<Body>();
        debug = new Box2DDebugRenderer();

        // En el caso que el nivel no haya sido asignado previamente
        if (eLevels == null)
            eLevels = ELevels.LEVEL1;

        // Crear Limites (Test)
        Box2DCreator.createLimits(world);

        BackgroundActor bgActor = new BackgroundActor(new Sprite(FuryBall.assets.manager.get("backgrounds/spaceBackground.png", Texture.class)));
        bgActor.setColor(1, 1, 1, 1);
        stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage.addActor(bgActor);

        Gdx.input.setInputProcessor(new GestureDetector(new GestureInput()));

        Music music = FuryBall.assets.manager.get("sounds/music/prototipe.mp3", Music.class);
        music.play();
        music.setLooping(true);

        boing = FuryBall.assets.manager.get("sounds/boing.mp3", Sound.class);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        inputGravity();

        camera.update();

        world.step(delta, 8, 3);

        if (!isLoaded) {
            switch (eLevels) {
                case LEVEL1:
                    Level1 level1 = new Level1(world);
                    currentLevel = level1;
                    world.setContactListener(new ContactListener() {
                        @Override
                        public void beginContact(Contact contact) {
                            final Fixture fixtureA = contact.getFixtureA();
                            final Fixture fixtureB = contact.getFixtureB();

                            if (fixtureA.getUserData() == null) {
                                boing.play();
                                return;
                            }


                            if (fixtureB.getUserData() == null) {
                                boing.play();
                                return;
                            }


                            if (fixtureA.getUserData().equals("Portal"))
                                currentLevel.setCollidingPortal(true);
                            else if (fixtureB.getUserData().equals("Portal"))
                                currentLevel.setCollidingPortal(true);
                        }

                        @Override
                        public void endContact(Contact contact) {
                            currentLevel.setCollidingPortal(false);
                        }

                        @Override
                        public void preSolve(Contact contact, Manifold oldManifold) {
                        }

                        @Override
                        public void postSolve(Contact contact, ContactImpulse impulse) {
                        }
                    });
                    break;
                case LEVEL2:
                    Level2 level2 = new Level2(world);
                    currentLevel = level2;
                    break;
                case LEVEL3:
                    Level3 level3 = new Level3(world);
                    currentLevel = level3;
                    world.setContactListener(new ContactListener() {
                        @Override
                        public void beginContact(Contact contact) {
                            final Fixture fixtureA = contact.getFixtureA();
                            final Fixture fixtureB = contact.getFixtureB();

                            if (fixtureA.getUserData() == null) {
                                boing.play();
                                return;
                            }


                            if (fixtureB.getUserData() == null) {
                                boing.play();
                                return;
                            }


                            if (fixtureA.getUserData().equals("Portal"))
                                currentLevel.setCollidingPortal(true);
                            else if (fixtureB.getUserData().equals("Portal"))
                                currentLevel.setCollidingPortal(true);
                            else if (fixtureA.getUserData().equals("BlackHole"))
                                ((Level3) currentLevel).setCollidingBlackHole(true);
                            else if (fixtureB.getUserData().equals("BlackHole"))
                                ((Level3) currentLevel).setCollidingBlackHole(true);
                        }

                        @Override
                        public void endContact(Contact contact) {
                            currentLevel.setCollidingPortal(false);
                            ((Level3) currentLevel).setCollidingBlackHole(false);
                        }

                        @Override
                        public void preSolve(Contact contact, Manifold oldManifold) {
                        }

                        @Override
                        public void postSolve(Contact contact, ContactImpulse impulse) {
                        }
                    });
                    break;
                case LEVEL4:
                    Level4 level4 = new Level4(world);
                    currentLevel = level4;
                    break;
                case LEVEL5:
                    Level5 level5 = new Level5(world);
                    currentLevel = level5;
                    break;
                default:
                    break;
            }

            isLoaded = true;
        }

        // Interacciones del nivel
        if (currentLevel != null)
            currentLevel.act();

        // Eliminar nivel si lo necesita
        if (needsToBeCleaned) {
            currentLevel.destroyLevel();
            needsToBeCleaned = false;
        }

        stage.draw();

        debug.render(world, camera.combined);

        batch.setProjectionMatrix(camera.combined);


        batch.begin();

        Box2DSprite.draw(batch, world);

        /*
        if (((Level3) currentLevel).isWin()) {
            batch.draw(FuryBall.assets.manager.get("levelComplete.png", Texture.class), 100, 100, 100, 100);
        }*/

        batch.end();
    }

    private void inputGravity() {
        if (VirtualController.isForce()) {
            gravity.set(GRAVITY_FORCE * (VirtualController.getgForce().x * 0.5f),
                    GRAVITY_FORCE * (VirtualController.getgForce().y * 0.5f));
            world.setGravity(gravity);
        } else if (VirtualController.isgNeutral()) {
            gravity.set(0f, 0f);
            world.setGravity(gravity);
        }
    }
}
