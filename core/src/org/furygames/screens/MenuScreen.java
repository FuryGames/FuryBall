package org.furygames.screens;

import org.furygames.actors.Box2DCreator;
import org.furygames.actors.CreditsButton;
import org.furygames.actors.ExitButton;
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
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import org.furygames.screens.BuoyancyController;
import org.furygames.screens.Box2DFactory;
import org.furygames.timer.ScreenSwitchTask;
public class MenuScreen extends GenericScreen implements ContactListener {
	private static final int MAX_SPAWNED_BODIES = 20;	

	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camara;
	private Array<Body> worldBodies;
	
	private StartButton startButton;
	private CreditsButton creditsButton;
	private ExitButton exitButton;
	
	private BuoyancyController buoyancyController;
	private int spawnedBodies;	
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		//checkInput();
		
		mundo.step(delta, 8, 6);

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
		
		camara.update();
		debugRenderer.render(mundo, camara.combined);	
		
		buoyancyController.step();
	}

	@Override
	public void resize(int width, int height) {
		camara.update();
		System.out.println("MenuScreen");
	}

	@Override
	public void show() {
		mundo = new World(new Vector2(0,-1),true);
		debugRenderer = new Box2DDebugRenderer();
		
		camara = new OrthographicCamera(WIDTH,HEIGHT);
		camara.position.set(WIDTH / 2, HEIGHT/ 2, 0);
		
		worldBodies = new Array <Body> ();
		
		Gdx.input.setInputProcessor(new MenuScreenInputController(){
			public boolean keyDown(int keycode) {
				switch(keycode) {
					case Keys.CONTROL_LEFT:
						
						if (spawnedBodies < MAX_SPAWNED_BODIES) {
							spawnedBodies++;

							/* Translate camera point to world point */
							Vector3 unprojectedVector = new Vector3();
							camara.unproject(unprojectedVector.set(-12f, -6f, 0));

							/* Create a new box */
							if (MathUtils.randomBoolean()) {
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
						
						camara.position.y += 3f;
						//movimiento.y = speed;
					break;
					case Keys.A:
						//movimiento.x = -speed;	
						camara.position.x -= 3f;
					break;
					case Keys.S:
						//movimiento.y = -speed;	
						camara.position.y -= 3f;
					break;
					case Keys.D:
						//movimiento.x = speed;	
						camara.position.x += 3f;
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
		
		// Crear start button
		
		startButton = new StartButton(mundo, 
				MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f),
				MathUtils.random(1.5f, 3f));
		creditsButton = new CreditsButton(mundo, 
				MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f),
				MathUtils.random(1.5f, 3f));
		exitButton = new ExitButton (mundo,
				MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f),
				2.5f, 2.5f);
		
		Box2DCreator.createLimits(mundo);
		
		Shape shape = Box2DFactory.createBoxShape(WIDTH - 6.6f, HEIGHT / 3 - 1f, new Vector2((camara.viewportWidth/2)-(0.1f), 1.5f), 0);
		
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
		} 
		
		else if (fixtureB.isSensor()
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
		} 
		
		else if (fixtureB.isSensor()
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
		
	private void checkInput () {
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camara.unproject(touchPos);
        
        Sprite spStartButton = startButton.getSprite();
        Sprite spCreditsButton = creditsButton.getSprite();
        Sprite spExitButton = exitButton.getSprite();
        
        if (touchPos.x > spStartButton.getX() 
        		&& touchPos.x < spStartButton.getX() + spStartButton.getWidth() 
        		&& touchPos.y > spStartButton.getY() 
        		&& touchPos.y < spStartButton.getY() + spStartButton.getHeight()
        		&& Gdx.input.justTouched()) {
        	
        	System.out.println("startButton");
        }
        
        else if (touchPos.x > spCreditsButton.getX() 
        		&& touchPos.x < spCreditsButton.getX() + spCreditsButton.getWidth() 
        		&& touchPos.y > spCreditsButton.getY() 
        		&& touchPos.y < spCreditsButton.getY() + spCreditsButton.getHeight()
        		&& Gdx.input.justTouched()) {
        	
        	Timer.schedule(new ScreenSwitchTask(EScreen.CREDITS), 2f);
        }
        
        else if (touchPos.x > spExitButton.getX() 
        		&& touchPos.x < spExitButton.getX() + spExitButton.getWidth() 
        		&& touchPos.y > spExitButton.getY() 
        		&& touchPos.y < spExitButton.getY() + spExitButton.getHeight()
        		&& Gdx.input.justTouched()) {
        	
        	System.out.println("exitButton");
        }
  	}
}
