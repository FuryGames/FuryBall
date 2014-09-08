package org.furygames.screens;

import org.furygames.actors.Box2DCreator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class LevelsScreen extends GenericScreen {
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private World world;
	private Array<Body> worldBodies;
	private Vector2 gravity;
	
	public LevelsScreen () {
		debug = new Box2DDebugRenderer();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.position.set(WIDTH / 2f, HEIGHT / 2f, 0);
		
		gravity = new Vector2 (0, -1);
		world = new World (gravity, false);
		
		worldBodies = new Array <Body> ();
		
		// Crear Limites
		Box2DCreator.createLimits(world);
	}
}
