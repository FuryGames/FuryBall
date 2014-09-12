package org.furygames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import org.furygames.actors.Box2DCreator;
import org.furygames.inputs.GravityInput;
import org.furygames.inputs.VirtualController;
import org.furygames.levels.ELevels;
import org.furygames.levels.ILevel;
import org.furygames.levels.Level1;
import org.furygames.levels.Level2;
import org.furygames.levels.Level3;
import org.furygames.levels.Level4;
import org.furygames.levels.Level5;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends GenericScreen {

    private final float GRAVITY_FORCE = 3f;
	
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private World world;
	private Array<Body> worldBodies;
	private Vector2 gravity;
	private ILevel currentLevel; // Nivel actual
    private GravityInput gravityInput;
	
	// Si el nivel esta cargado
	public static boolean isLoaded = false;
	// Cuando se necesita elimiar un nivel
	public static boolean needsToBeCleaned = false;
	public static ELevels eLevels;
	
	public GameScreen () {}
	
	@Override
	public void show() {
		super.show();
		
		gravity = new Vector2 (0, 0);
		world = new World (gravity, false);
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.position.set(WIDTH / 2f, HEIGHT / 2f, 0);

		worldBodies = new Array <Body> ();
		debug = new Box2DDebugRenderer();
		
		// En el caso que el nivel no haya sido asignado previamente
		if (eLevels == null)
			eLevels = ELevels.LEVEL1;
		
		// Crear Limites
		Box2DCreator.createLimits(world);

        gravityInput = new GravityInput();
        Gdx.input.setInputProcessor(gravityInput);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

        inputGravity();

		camera.update();
        debug.render(world, camera.combined);
        world.step(delta, 8, 3);
		
        if (!isLoaded) {
			switch (eLevels) {
				case LEVEL1:
					Level1 level1 = new Level1(world);
					currentLevel = level1;
					break;
				case LEVEL2:
					Level2 level2 = new Level2(world);
					currentLevel = level2;
					break;
				case LEVEL3:
					Level3 level3 = new Level3(world);
					currentLevel = level3;
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

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        world.getBodies(worldBodies);

        for (Body body : worldBodies) {
            if (body.getUserData() instanceof Sprite) {
                Sprite sprite = (Sprite) body.getUserData();

				/*
				 * Set body position equals to box position. We also need to
				 * center it in the box (measures are relative to body center).
				 */
                Vector2 position = body.getPosition();
                sprite.setPosition(position.x - sprite.getWidth() / 2,
                        position.y - sprite.getWidth() / 2);

				/* Set sprite rotation equals to body rotation */
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);

				/* Draw the sprite on screen */
                sprite.draw(batch);
            }
        }

        batch.end();
	}

    private void inputGravity() {
        if (VirtualController.isGUp()) {
            gravity.set(0f, GRAVITY_FORCE);
            world.setGravity(gravity);
        }

        else if (VirtualController.isGDown()) {
            gravity.set(0f, -GRAVITY_FORCE);
            world.setGravity(gravity);
        }

        else if (VirtualController.isGLeft()) {
            gravity.set(-GRAVITY_FORCE, 0f);
            world.setGravity(gravity);
        }

        else if (VirtualController.isGRight()) {
            gravity.set(GRAVITY_FORCE, 0f);
            world.setGravity(gravity);
        }

        else if (VirtualController.isgNeutral()) {
            gravity.set(0f, 0f);
            world.setGravity(gravity);
        }
    }
}
