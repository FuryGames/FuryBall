package org.furygames.screens;

import org.furygames.actors.Box2DCreator;
import org.furygames.actors.StartButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

public class MenuScreen extends GenericScreen {
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camara;
	private Array<Body> worldBodies;
	
	private float speed = 500000;
	private Vector2 movimiento = new Vector2(0,0);
	private Body cubo;
	
	private StartButton startButton;
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
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
	}

	@Override
	public void resize(int width, int height) {
		//camara.viewportWidth = width;
		//camara.viewportHeight = height;
		//camara.update();
		System.out.println("MenuScreen");
	}

	@Override
	public void show() {
		mundo = new World(new Vector2(0,20),true);
		debugRenderer = new Box2DDebugRenderer();
		
		camara = new OrthographicCamera(WIDTH, HEIGHT);
		camara.position.set(WIDTH / 2f, HEIGHT / 2f, 0);
		
		worldBodies = new Array <Body> ();
		
		Gdx.input.setInputProcessor(new MenuScreenInputController(){
			public boolean keyDown(int keycode) {
				switch(keycode) {
					case Keys.W:
						movimiento.y = speed;
					break;
					case Keys.A:
						movimiento.x = -speed;						
					break;
					case Keys.S:
						movimiento.y = -speed;						
					break;
					case Keys.D:
						movimiento.x = speed;						
					break;					
				}
				return true;
			}
			public boolean keyUp(int keycode) {
				switch(keycode) {
				case Keys.W:
				case Keys.S:
					movimiento.y = 0;						
					break;
				case Keys.A:
				case Keys.D:
					movimiento.x = 0;											
				}
				return true;
			}			
		});

		// Crear start button
		
		startButton = new StartButton(mundo, 
				MathUtils.random(.5f, WIDTH - 3.5f),
				MathUtils.random(.5f, HEIGHT - 3.5f),
				MathUtils.random(2f, 3f));
		
		Box2DCreator.createLimits(mundo);
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

}
