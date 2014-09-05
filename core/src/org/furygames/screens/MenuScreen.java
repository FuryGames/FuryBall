package org.furygames.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import org.furygames.screens.BuoyancyController;
import org.furygames.screens.Box2DFactory;
public class MenuScreen extends GenericScreen implements ContactListener {
	private World mundo;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camara;
	
	private float speed = 500000;
	private Vector2 movimiento = new Vector2(0,0);
	private Body cubo;
	
	//--
	private static final int MAX_SPAWNED_BODIES = 20;	
	private BuoyancyController buoyancyController;
	private int spawnedBodies;	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
		
		mundo.step(1/60f, 8, 6);
		//cubo.applyForceToCenter(movimiento, true);
		
		//camara.position.set(cubo.getPosition().x,cubo.getPosition().y,0);
		camara.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
		camara.update();
		debugRenderer.render(mundo, camara.combined);	
		
		buoyancyController.step();
	}

	@Override
	public void resize(int width, int height) {
		camara.viewportWidth = width;
		camara.viewportHeight = height;
		camara.update();
		System.out.println("MenuScreen");
	}

	@Override
	public void show() {
		mundo = new World(new Vector2(0,-30),true);
		debugRenderer = new Box2DDebugRenderer();
		
		camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camara.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		
		Gdx.input.setInputProcessor(new MenuScreenInputController(){
			public boolean keyDown(int keycode) {
				switch(keycode) {
					case Keys.W:
						
						
						if (spawnedBodies < MAX_SPAWNED_BODIES) {
							spawnedBodies++;

							/* Translate camera point to world point */
							Vector3 unprojectedVector = new Vector3();
							camara.unproject(unprojectedVector.set(500, 500, 0));

							/* Create a new box */
							if (Math.random() >= 0.5) {
								Shape shape = Box2DFactory.createBoxShape(20, 20, new Vector2(0,
										0), 0);
								FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 0.5f,
										0.5f, 0.5f, false);
								Box2DFactory.createBody(mundo, BodyType.DynamicBody,
										fixtureDef, new Vector2(unprojectedVector.x,
												unprojectedVector.y));
							} else {
								/* Create a new triangle */
								Shape shape = Box2DFactory.createTriangleShape(20, 20);
								FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 0.5f,
										0.5f, 0.5f, false);
								Box2DFactory.createBody(mundo, BodyType.DynamicBody,
										fixtureDef, new Vector2(unprojectedVector.x,
												unprojectedVector.y));
							}
						}						
						
						
						//movimiento.y = speed;
					break;
					case Keys.A:
						//movimiento.x = -speed;						
					break;
					case Keys.S:
						//movimiento.y = -speed;						
					break;
					case Keys.D:
						//movimiento.x = speed;						
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
		
		// pelota
		

		
		/*BodyDef cuerpo = new BodyDef();
		cuerpo.type = BodyType.DynamicBody;
		cuerpo.position.set(300,300);
		
		CircleShape circulo = new CircleShape();
		circulo.setRadius(30);
		
		FixtureDef propiedades = new FixtureDef();
		propiedades.density = 2.5f;
		propiedades.friction = .25f;
		propiedades.restitution = .75f;
		propiedades.shape = circulo;
		
		mundo.createBody(cuerpo).createFixture(propiedades);
		circulo.dispose();
		*/
		
		
		//linea
		
		/*cuerpo.type = BodyType.StaticBody;
		cuerpo.position.set(0,0);
		
		ChainShape FormaLinea = new ChainShape();
		FormaLinea.createChain(new Vector2[] {new Vector2(0,10),new Vector2(1280,10)});
		
		propiedades.shape = FormaLinea;
		mundo.createBody(cuerpo).createFixture(propiedades);
		FormaLinea.dispose();*/
		
		//Box2DCreator.createLimits(mundo);

		
		// Caja
		
	/*	cuerpo.type = BodyType.DynamicBody;
		cuerpo.position.set(400,400);
		
		PolygonShape FormaCuadrado = new PolygonShape();
		FormaCuadrado.setAsBox(25, 50);
		
		propiedades.shape = FormaCuadrado;
		propiedades.friction = .75f;
		propiedades.restitution = .1f;
		propiedades.density = 5;
		
		cubo = mundo.createBody(cuerpo);
		cubo.createFixture(propiedades);
		FormaCuadrado.dispose();
		
		cubo.applyAngularImpulse(5, true);*/
		
		
		//---------------------------------
		//---------------------------------
		//Box2DFactory.createWalls(mundo,50,50,2);	
		
		Shape shape = Box2DFactory.createBoxShape(((camara.viewportWidth/2)-10),(100), new Vector2((camara.viewportWidth/2)-10, 100), 0);		
		
		FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 5, 0.1f, 0,true);		
		Body water = Box2DFactory.createBody(mundo, BodyType.StaticBody,fixtureDef, new Vector2(10,10));		
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
