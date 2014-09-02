package org.furygames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MenuScreen extends GenericScreen {
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camara;
	
	private float speed = 500;
	private Vector2 movimiento = new Vector2(0,0);
	private Body cubo;
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	

		
		mundo.step(1/60f, 8, 3);
		cubo.applyForceToCenter(movimiento, true);
		
		camara.position.set(cubo.getPosition().x,cubo.getPosition().y,0);
		camara.update();
		debugRenderer.render(mundo, camara.combined);		
	}

	@Override
	public void resize(int width, int height) {
		camara.viewportWidth = width/25;
		camara.viewportHeight = height/25;
		camara.update();
	}

	@Override
	public void show() {
		mundo = new World(new Vector2(0,-9.81f),true);
		debugRenderer = new Box2DDebugRenderer();
		camara = new OrthographicCamera(Gdx.graphics.getWidth()/25,Gdx.graphics.getHeight()/25);
		
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
		
		// pelota
		
		BodyDef cuerpo = new BodyDef();
		cuerpo.type = BodyType.DynamicBody;
		cuerpo.position.set(0,3);
		
		CircleShape circulo = new CircleShape();
		circulo.setRadius(.25f);
		
		FixtureDef propiedades = new FixtureDef();
		propiedades.density = 2.5f;
		propiedades.friction = .25f;
		propiedades.restitution = .75f;
		propiedades.shape = circulo;
		
		mundo.createBody(cuerpo).createFixture(propiedades);
		circulo.dispose();
		
		//linea
		
		cuerpo.type = BodyType.StaticBody;
		cuerpo.position.set(0,0);
		
		ChainShape FormaLinea = new ChainShape();
		FormaLinea.createChain(new Vector2[] {new Vector2(-50,0),new Vector2(50,0)});
		
		propiedades.shape = FormaLinea;
		mundo.createBody(cuerpo).createFixture(propiedades);
		FormaLinea.dispose();
		
		// Caja
		
		cuerpo.type = BodyType.DynamicBody;
		cuerpo.position.set(2.25f,10);
		
		PolygonShape FormaCuadrado = new PolygonShape();
		FormaCuadrado.setAsBox(.5f, 1);
		
		propiedades.shape = FormaCuadrado;
		propiedades.friction = .75f;
		propiedades.restitution = .1f;
		propiedades.density = 5;
		
		cubo = mundo.createBody(cuerpo);
		cubo.createFixture(propiedades);
		FormaCuadrado.dispose();
		
		cubo.applyAngularImpulse(.5f, true);
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
