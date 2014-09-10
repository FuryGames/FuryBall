package org.furygames.screens;

import org.furygames.levels.ELevels;
import org.furygames.timer.ScreenSwitchTask;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.utils.Timer;

public class LevelsScreen extends GenericScreen {
    private OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer debugRenderBox2D;

    Body levelall;
    Body level_A1,level_A2,level_A3;
    Body level_B1,level_B2,level_B3;
    Body level_C1,level_C2,level_C3;

    public void render(float delta) {
        super.render(delta);
        
        checkInput();
        
        camera.update();
        debugRenderBox2D.render(world,camera.combined);
        world.step(delta, 8, 3);

        // Esto es solo por el momento
        if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
        	GameScreen.eLevels = ELevels.LEVEL1;
        	Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }
        
        else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
        	GameScreen.eLevels = ELevels.LEVEL2;
        	Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }
        
        else if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
        	GameScreen.eLevels = ELevels.LEVEL3;
        	Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }
        
        else if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
        	GameScreen.eLevels = ELevels.LEVEL4;
        	Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }
        
        else if (Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
        	GameScreen.eLevels = ELevels.LEVEL5;
        	Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }
    }
    
    public void show()
    {
        debugRenderBox2D = new Box2DDebugRenderer();
        world = new World(new Vector2(0,-9.81f),true);
        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.position.set(WIDTH/2,HEIGHT/2,0);

        BodyDef bodydeflevel = new BodyDef();
        FixtureDef fixture = new FixtureDef();


        levelall = setLevelall(levelall,bodydeflevel,fixture,6,7);

        //Line A 2f
        level_A1 = addlevel(level_A1,bodydeflevel,fixture,1.8f,5);
        level_A2 = addlevel(level_A2,bodydeflevel,fixture,1.8f,3);
        level_A3 = addlevel(level_A3,bodydeflevel,fixture,1.8f,2);

        adddistanciajoint(levelall,level_A1,1.5f,true,-4);
        adddistanciajoint(level_A1,level_A2,2.2f,false,0);
        adddistanciajoint(level_A2,level_A3,2.2f,false,0);

        //Line B 6f
        level_B1 = addlevel(level_B1,bodydeflevel,fixture,6.2f,5);
        level_B2 = addlevel(level_B2,bodydeflevel,fixture,6.2f,3);
        level_B3 = addlevel(level_B3,bodydeflevel,fixture,6.2f,2);

        adddistanciajoint(levelall,level_B1,1.5f,true,0);
        adddistanciajoint(level_B1,level_B2,2.2f,false,0);
        adddistanciajoint(level_B2,level_B3,2.2f,false,0);

        //Line C 10f
        level_C1 = addlevel(level_C1,bodydeflevel,fixture,9.8f,5);
        level_C2 = addlevel(level_C2,bodydeflevel,fixture,9.8f,3);
        level_C3 = addlevel(level_C3,bodydeflevel,fixture,9.8f,2);

        adddistanciajoint(levelall,level_C1,1.5f,true,4);
        adddistanciajoint(level_C1,level_C2,2.2f,false,0);
        adddistanciajoint(level_C2,level_C3,2.2f,false,0);
    }
    
    public Body setLevelall(Body body, BodyDef bodydeflevel,FixtureDef fixture,float x, float y)
    {
        bodydeflevel.type = BodyType.StaticBody;
        bodydeflevel.position.set(x,y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6f,0.02f);


        fixture.shape = shape;
        body = world.createBody(bodydeflevel);
        body.createFixture(fixture);
        shape.dispose();
        return body;
    }
    public Body addlevel(Body body, BodyDef bodydeflevel,FixtureDef fixture, float x, float y)
    {
        bodydeflevel.type = BodyType.DynamicBody;
        bodydeflevel.position.set(x,y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1.6f,0.8f);

        fixture.shape = shape;
        fixture.density = 2.5f;
        fixture.friction = 0.25f;
        fixture.restitution = 0.5f;
        body = world.createBody(bodydeflevel);
        body.createFixture(fixture);
        shape.dispose();
        return body;

    }
    public void adddistanciajoint(Body A, Body B,float distance,boolean first,float X)
    {
        DistanceJointDef distanceJointDef = new DistanceJointDef();
        distanceJointDef.bodyA = A;
        distanceJointDef.bodyB = B;
        distanceJointDef.length = distance;
        if(first)
        {
            distanceJointDef.localAnchorA.set(X,0);
        }
        world.createJoint(distanceJointDef);
    }
    public void hide()
    {
        dispose();
    }

    public void dispose()
    {
        //world.dispose();
        debugRenderBox2D.dispose();
    }
    
    public void checkInput () {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Timer.schedule(new ScreenSwitchTask(EScreen.MENU), 0f);
			System.out.println("esc-");
		}
	}
}
