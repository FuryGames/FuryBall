package org.furygames.screens;

import org.furygames.actors.Box2DCreator;
import org.furygames.levels.ELevels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends GenericScreen {
	
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private World world;
	private Array<Body> worldBodies;
	private Vector2 gravity;
	
	public static boolean isLoaded = false;
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
		
		// Crear Limites (Test)
		Box2DCreator.createLimits(world);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		camera.update();
        debug.render(world, camera.combined);
        world.step(delta, 8, 3);
		
        if (!isLoaded) {
			switch (eLevels) {
				case LEVEL1:
					// Crear el mundo
					System.out.println("Nivel 1");
					break;
				case LEVEL2:
					System.out.println("Nivel 2");
					break;
				case LEVEL3:
					System.out.println("Nivel 3");
					break;
				case LEVEL4:
					System.out.println("Nivel 4");
					break;
				case LEVEL5:
					System.out.println("Nivel 5");
					break;
				default:
					break;
			}
			
			isLoaded = true;
        }
	}
}
