package org.furygames.screens;

import org.furygames.actors.Author;
import org.furygames.actors.Author.EAuthors;
import org.furygames.actors.Box2DCreator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer.Task;

public class CreditsScreen extends GenericScreen {
	
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private World world;
	private Array<Body> worldBodies;
	private Vector2 gravity;
	private Task task;

	private Array <Author> authors;
	
	public CreditsScreen () {
		gravity = new Vector2 (MathUtils.random(-10f, 10f), MathUtils.random(-10f, 10f));
		world = new World (gravity, true);
		
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.position.set(WIDTH / 2f, HEIGHT / 2f, 0);
				
		worldBodies = new Array <Body> ();
		authors = new Array <Author> ();
		
		authors.add(new Author(MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f), 
				world, 
				MathUtils.random(1.5f, 3f),
				EAuthors.WRITKAS));
		authors.add(new Author(MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f), 
				world, 
				MathUtils.random(1.5f, 3f),
				EAuthors.JRF));
		authors.add(new Author(MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f), 
				world, 
				MathUtils.random(1.5f, 3f),
				EAuthors.ENKI));
		authors.add(new Author(MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f), 
				world, 
				MathUtils.random(1.5f, 3f),
				EAuthors.GUITAURICIO));
		
		// Crear Limites
		Box2DCreator.createLimits(world);
		
		debug = new Box2DDebugRenderer();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		camera.update();
		debug.render(world, camera.combined);
		
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
		world.step(delta, 8, 6);
	}

	@Override
	public void dispose() {
		System.out.println("Dispose CreditScreen");
		
		// Cancela todas las tareas
		task.cancel();
		
		world.dispose();
		debug.dispose();
		
		super.dispose();
	}
}
