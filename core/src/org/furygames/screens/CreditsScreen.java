package org.furygames.screens;

import org.furygames.actors.AuthorMatias;
import org.furygames.actors.AuthorsCreator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class CreditsScreen extends GenericScreen {

	private final static float SCREEN_WIDTH = 12.8f;
	private final static float SCREEN_HEIGHT = 7.2f;
	
	private Body box;
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private World world;
	private Array<Body> worldBodies;
	private Vector2 gravity;
	
	private AuthorMatias matias;
	//private AuthorMatias jrf;
	
	public CreditsScreen () {
		gravity = new Vector2 (MathUtils.random(-10f, 10f), MathUtils.random(-10f, 10f));
		world = new World (gravity, true);
		
		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
		
		worldBodies = new Array <Body> ();
		
		// Crear cuerpo de ejemplo
		matias = new AuthorMatias(5, 5, world);
		//jrf = new AuthorMatias(3, 3, world);
		
		// Crear Limites
		createLimits(world);
		
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
	public void show() {
		super.show();
		
		
	}
	
	private static void createLimits (World world) {
		
		final float SEPARATOR = .2f;
		
		// Bottom
		//
		
		BodyDef bottom = new BodyDef();
		bottom.type = BodyType.StaticBody;
		 
		EdgeShape shape = new EdgeShape();
		shape.set(SEPARATOR, SEPARATOR, SCREEN_WIDTH - SEPARATOR, SEPARATOR);
		 
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		 
		Body oBody = world.createBody(bottom);
		oBody.createFixture(fixDef);
		 
		shape.dispose();
		
		// Up
		//
		
		BodyDef up = new BodyDef();
		up.type = BodyType.StaticBody;
		 
		EdgeShape shape2 = new EdgeShape();
		shape2.set(SEPARATOR, SCREEN_HEIGHT - SEPARATOR , SCREEN_WIDTH - SEPARATOR, SCREEN_HEIGHT - SEPARATOR);
		 
		FixtureDef fixDef2 = new FixtureDef();
		fixDef2.shape = shape2;
		 
		Body body = world.createBody(up);
		body.createFixture(fixDef2);
		 
		shape2.dispose();
		
		// Left
		//
		
		BodyDef left = new BodyDef();
		left.type = BodyType.StaticBody;
		 
		EdgeShape leftShape = new EdgeShape();
		shape2.set(SEPARATOR, SEPARATOR, SEPARATOR, SCREEN_HEIGHT - SEPARATOR);
		 
		FixtureDef leftFix = new FixtureDef();
		leftFix.shape = leftShape;
		 
		Body leftBody = world.createBody(left);
		leftBody.createFixture(leftFix);
		 
		leftShape.dispose();
		
		// Right
		//
		
		BodyDef right = new BodyDef();
		right.type = BodyType.StaticBody;
		 
		EdgeShape rightShape = new EdgeShape();
		shape2.set(SCREEN_WIDTH - SEPARATOR, SEPARATOR, SCREEN_WIDTH - SEPARATOR, SCREEN_HEIGHT - SEPARATOR);
		 
		FixtureDef rightFix = new FixtureDef();
		rightFix.shape = rightShape;
		 
		Body rightBody = world.createBody(right);
		rightBody.createFixture(rightFix);
		 
		rightShape.dispose();
	}

}
