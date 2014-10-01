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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;
import org.furygames.actors.Box2DCreator;
import org.furygames.actors.LevelCompleteActor;
import org.furygames.actors.WinMenuButton;
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

    // Si el nivel esta cargado
    public static boolean isLoaded = false;
    // Cuando se necesita elimiar un nivel
    public static boolean needsToBeCleaned = false;
    public static ELevels eLevels;

    public static Sound boing;

    private Sprite background;

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

        // Setear procesador de comandos a la aplicación
        Gdx.input.setInputProcessor(new GestureDetector(new GestureInput()));

        prepareAudio();

        background = new Sprite(FuryBall.assets.manager.get("backgrounds/spaceBackground.png", Texture.class));
        background.setSize(WIDTH, HEIGHT);

        // en el futuro se puede crear un Dialog (Scene2D) y reutilizarlo para cada pantalla que lo necesite (ejemplos: juego Pocket Kingdom, o Bike Race)
        prepareLevelCompleteStage();
    }

    private void prepareAudio() {
        Music music = FuryBall.assets.manager.get("sounds/music/prototipe.mp3", Music.class);
        music.play();
        music.setLooping(true);

        boing = FuryBall.assets.manager.get("sounds/boing.mp3", Sound.class);
    }

    private void prepareLevelCompleteStage() {
        stage.addActor(new LevelCompleteActor());

        //instantiate actors
        WinMenuButton backButton = new WinMenuButton(currentLevel, WinMenuButton.Type.BACK);
        WinMenuButton replayButton = new WinMenuButton(currentLevel, WinMenuButton.Type.REPLAY);
        WinMenuButton nextButton = new WinMenuButton(currentLevel, WinMenuButton.Type.NEXT);

        //set actors position
        backButton.getSprite().setPosition(0, 0);
        backButton.setPosition(0, 0);
        replayButton.getSprite().setPosition(WIDTH / 2 - replayButton.getSprite().getWidth() / 2, 0);
        replayButton.setPosition(WIDTH / 2 - replayButton.getSprite().getWidth() / 2, 0);
        nextButton.getSprite().setPosition(WIDTH - replayButton.getSprite().getWidth(), 0);
        nextButton.setPosition(WIDTH - replayButton.getSprite().getWidth(), 0);

        //add click listeners
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().show(EScreen.LEVELS);
                System.out.println("levelScreen");
            }
        });
        replayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                show();
                isLoaded = false;
            }
        });
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                needsToBeCleaned = true;
                eLevels = eLevels.next();
                isLoaded = false;
                show();
            }
        });

        stage.addActor(backButton);
        stage.addActor(replayButton);
        stage.addActor(nextButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        updateInput();

        camera.update();

        world.step(delta, 8, 3);

        setScreen();

        // Interacciones del nivel
        if (currentLevel != null)
            currentLevel.act();

        // Eliminar nivel si lo necesita
        if (needsToBeCleaned) {
            currentLevel.destroyLevel();
            needsToBeCleaned = false;
        }

        debug.render(world, camera.combined);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        background.draw(batch);

        Box2DSprite.draw(batch, world);

        if (currentLevel.isWin()) {
            stage.draw();
            stage.act();
        }

        batch.end();
    }

    private void updateInput() {
        if (VirtualController.isForce()) {
            gravity.set(GRAVITY_FORCE * (VirtualController.getgForce().x * 0.5f),
                    GRAVITY_FORCE * (VirtualController.getgForce().y * 0.5f));
            world.setGravity(gravity);
        } else if (VirtualController.isgNeutral()) {
            gravity.set(0f, 0f);
            world.setGravity(gravity);
        }
    }

    private void setScreen() {
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


                            if (fixtureA.getUserData().equals("Portal")) {
                                currentLevel.setCollidingPortal(true);
                                Gdx.input.setInputProcessor(stage);
                            } else if (fixtureB.getUserData().equals("Portal")) {
                                currentLevel.setCollidingPortal(true);
                                Gdx.input.setInputProcessor(stage);
                            }

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
    }
}