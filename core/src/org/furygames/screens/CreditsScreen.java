package org.furygames.screens;

import org.furygames.actors.AuthorsCreator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class CreditsScreen extends GenericScreen {

	private final float SCREEN_WIDTH = 12.8f;
	private final float SCREEN_HEIGHT = 7.2f;
	
	private Vector2 gravity;
	private World world;
	private OrthographicCamera camera;
	private Box2DDebugRenderer worldRender;
	
	public CreditsScreen () {
		gravity = new Vector2 (MathUtils.random(-10f, 10f), MathUtils.random(-10f, 10f));
		world = new World (gravity, true);
		
		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
		
		// Crear cuerpo de ejemplo
		AuthorsCreator.Matias(world);
		
		// Crear Limites
		
		
		worldRender = new Box2DDebugRenderer();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		world.step(delta, 8, 6);
		camera.update();
		worldRender.render(world, camera.combined);
	}
}
