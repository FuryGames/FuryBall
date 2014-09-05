package org.furygames.screens;

import org.furygames.actors.Box2DCreator;
import org.furygames.actors.StartButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

import org.furygames.screens.BuoyancyController;
import org.furygames.screens.Box2DFactory;
public class MenuScreen extends GenericScreen implements ContactListener {
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camara;
	private Array<Body> worldBodies;
	
	private StartButton startButton;
	
	//--
	private static final int MAX_SPAWNED_BODIES = 20;	
	private BuoyancyController buoyancyController;
	private int spawnedBodies;	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
		
		mundo.step(delta, 8, 6);
<<<<<<< HEAD
=======

		camara.update();
		debugRenderer.render(mundo, camara.combined);
		
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		
		mundo.getBodies(worldBodies);
		
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
		mundo.step(1/60f, 8, 6);
>>>>>>> 246e140e0cda43caa44d81f3c754e7c7d6928e63
		//cubo.applyForceToCenter(movimiento, true);
		
		//camara.position.set(cubo.getPosition().x,cubo.getPosition().y,0);
		//camara.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
		camara.update();
		debugRenderer.render(mundo, camara.combined);	
		
		buoyancyController.step();
	}

	@Override
	public void resize(int width, int height) {
<<<<<<< HEAD
		//camara.viewportWidth = WIDTH*20;
		//camara.viewportHeight = HEIGHT*20;
		//camara.update();
=======
		//camara.viewportWidth = width;
		//camara.viewportHeight = height;
		camara.update();
>>>>>>> 246e140e0cda43caa44d81f3c754e7c7d6928e63
		System.out.println("MenuScreen");
	}

	@Override
	public void show() {
		mundo = new World(new Vector2(0,-9),true);
		debugRenderer = new Box2DDebugRenderer();
		
		camara = new OrthographicCamera(WIDTH,HEIGHT);
		camara.position.set(WIDTH / 2, HEIGHT/ 2, 0);
		
		worldBodies = new Array <Body> ();
		
		Gdx.input.setInputProcessor(new MenuScreenInputController(){
			public boolean keyDown(int keycode) {
				switch(keycode) {
					case Keys.W:
						
						
						if (spawnedBodies < MAX_SPAWNED_BODIES) {
							spawnedBodies++;

							/* Translate camera point to world point */
							Vector3 unprojectedVector = new Vector3();
							camara.unproject(unprojectedVector.set(-12f, -6f, 0));

							/* Create a new box */
							if (Math.random() >= 0.5) {
								Shape shape = Box2DFactory.createBoxShape(0.2f, 0.2f, new Vector2(6f,-0.8f), 0);
								FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 1,
										0.5f, 0.5f, false);
								Box2DFactory.createBody(mundo, BodyType.DynamicBody,
										fixtureDef, new Vector2(unprojectedVector.x,
												unprojectedVector.y));
							} else {
								/* Create a new triangle */
								Shape shape = Box2DFactory.createTriangleShape(0.2f, 0.2f);
								FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 1,
										0.5f, 0.5f, false);
								Box2DFactory.createBody(mundo, BodyType.DynamicBody,
										fixtureDef, new Vector2(unprojectedVector.x,unprojectedVector.y));
							}
						}						
						
						camara.position.y += 5f;
						//movimiento.y = speed;
					break;
					case Keys.A:
						//movimiento.x = -speed;	
						camara.position.x -= 5f;
					break;
					case Keys.S:
						//movimiento.y = -speed;	
						camara.position.y -= 5f;
					break;
					case Keys.D:
						//movimiento.x = speed;	
						camara.position.x += 5f;
					break;
				}
				return true;
			}
			public boolean keyUp(int keycode) {
				switch(keycode) {
				case Keys.W:
				case Keys.S:
				//	movimiento.y = 0;						
					break;
				case Keys.A:
				case Keys.D:
					//movimiento.x = 0;											
				}
				return true;
			}			
		});
<<<<<<< HEAD
		
		Box2DFactory.createWalls(mundo,0.1f,0.1f,camara.viewportWidth-(0.2f),camara.viewportHeight-(0.2f));	
=======

		// Crear start button
		
		startButton = new StartButton(mundo, 
				MathUtils.random(.5f, WIDTH - 3.5f),
				MathUtils.random(.5f, HEIGHT - 3.5f),
				MathUtils.random(2f, 3f));
		
		Box2DCreator.createLimits(mundo);
		
		
		//---------------------------------
		//---------------------------------
		//Box2DFactory.createWalls(mundo,50,50,2);	
>>>>>>> 246e140e0cda43caa44d81f3c754e7c7d6928e63
		
		Shape shape = Box2DFactory.createBoxShape(((camara.viewportWidth/2)-0.1f),(0.8f), new Vector2((camara.viewportWidth/2)-(0.1f), 0.8f), 0);		
		
		FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 5, 0.1f, 0,true);		
		Body water = Box2DFactory.createBody(mundo, BodyType.StaticBody,fixtureDef, new Vector2(0.1f,0.1f));		
		buoyancyController = new BuoyancyController(mundo, water.getFixtureList().first());	
		mundo.setContactListener(this);

	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		mundo.dispose();
		debugRenderer.dispose();
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.isSensor()
				&& fixtureB.getBody().getType() == BodyType.DynamicBody) {
			buoyancyController.addBody(fixtureB);
		} else if (fixtureB.isSensor()
				&& fixtureA.getBody().getType() == BodyType.DynamicBody) {
			buoyancyController.addBody(fixtureA);
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.isSensor()
				&& fixtureB.getBody().getType() == BodyType.DynamicBody) {
			buoyancyController.removeBody(fixtureB);
		} else if (fixtureB.isSensor()
				&& fixtureA.getBody().getType() == BodyType.DynamicBody) {
			if (fixtureA.getBody().getWorldCenter().y > -1) {
				buoyancyController.removeBody(fixtureA);
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
