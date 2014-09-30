package org.furygames.screens;

import org.furygames.actors.Box2DCreator;
import org.furygames.actors.CreditsButton;
import org.furygames.actors.ExitButton;
import org.furygames.actors.StartButton;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.Timer;

import org.furygames.timer.ScreenSwitchTask;

public class MenuScreen extends GenericScreen implements ContactListener {

	private static World world;
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private Array<Body> worldBodies;
	
	private StartButton startButton;
	private CreditsButton creditsButton;
	private ExitButton exitButton;
	
	private BuoyancyController buoyancyController;

	@Override
	public void render(float delta) {
		super.render(delta);
		
		checkInput();
		
		world.step(delta, 8, 6);

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
		world.step(1 / 60f, 8, 6);

		buoyancyController.step();
	}

	@Override
	public void resize(int width, int height) {
		camera.update();
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -1), true);
		debug = new Box2DDebugRenderer();
		
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.position.set(WIDTH / 2, HEIGHT/ 2, 0);
		
		worldBodies = new Array <Body> ();

        // Crear botones

		startButton = new StartButton(world,
				MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f),
				MathUtils.random(1.5f, 3f));
		creditsButton = new CreditsButton(world,
				MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f),
				MathUtils.random(1.5f, 3f));
		exitButton = new ExitButton (world,
				MathUtils.random(.5f, WIDTH - .5f),
				MathUtils.random(.5f, HEIGHT - .5f),
				2.5f, 2.5f);
		
		Box2DCreator.createLimits(world);
		
		Shape shape = Box2DFactory.createBoxShape(WIDTH - 6.2f, HEIGHT / 3 - .945f,
                new Vector2((camera.viewportWidth/2)-(0.1f), 1.5f), 0);
		
		FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 5, 0.1f, 0, true);
		Body water = Box2DFactory.createBody(world, BodyType.StaticBody, fixtureDef, new Vector2(0.1f, 0.1f));
		buoyancyController = new BuoyancyController(world, water.getFixtureList().first());
		world.setContactListener(this);
	}

	@Override
	public void hide() {

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
        super.dispose();

		world.dispose();
		debug.dispose();
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
        camera.unproject(touchPos);
        
        Sprite spStartButton = startButton.getSprite();
        Sprite spCreditsButton = creditsButton.getSprite();
        Sprite spExitButton = exitButton.getSprite();

        if (touchPos.x > spStartButton.getX() 
        		&& touchPos.x < spStartButton.getX() + spStartButton.getWidth() 
        		&& touchPos.y > spStartButton.getY() 
        		&& touchPos.y < spStartButton.getY() + spStartButton.getHeight()
        		&& Gdx.input.justTouched()) {
        	
        	startButton.getBody().applyLinearImpulse(
        			new Vector2(MathUtils.random(-1f, 1f), 1.5f),
        			startButton.getBody().getWorldCenter(),
    				true);
        	
        	Timer.schedule(new ScreenSwitchTask(EScreen.LEVELS), 1f);
            // Temp
            //Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 1f);
        }
        
        else if (touchPos.x > spCreditsButton.getX() 
        		&& touchPos.x < spCreditsButton.getX() + spCreditsButton.getWidth() 
        		&& touchPos.y > spCreditsButton.getY() 
        		&& touchPos.y < spCreditsButton.getY() + spCreditsButton.getHeight()
        		&& Gdx.input.justTouched()) {
        	
        	creditsButton.getBody().applyLinearImpulse(
        			new Vector2(MathUtils.random(-1f, 1f), 1.5f),
        			creditsButton.getBody().getWorldCenter(),
    				true);
        	
        	Timer.schedule(new ScreenSwitchTask(EScreen.CREDITS), 1f);
        }
        
        else if (touchPos.x > spExitButton.getX() 
        		&& touchPos.x < spExitButton.getX() + spExitButton.getWidth() 
        		&& touchPos.y > spExitButton.getY() 
        		&& touchPos.y < spExitButton.getY() + spExitButton.getHeight()
        		&& Gdx.input.justTouched()) {
        	
        	exitButton.getBody().applyLinearImpulse(
        			new Vector2(MathUtils.random(-1f, 1f), 1.5f),
        			exitButton.getBody().getWorldCenter(),
    				true);
        }
  	}
}
