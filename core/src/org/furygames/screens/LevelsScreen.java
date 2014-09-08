package org.furygames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;

public class LevelsScreen extends GenericScreen {
    private OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer debugRenderBox2D;
    Body levelall,level1,level2;
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        debugRenderBox2D.render(world,camera.combined);
        world.step(delta,8,3);

    }
    public void show()
    {
        debugRenderBox2D = new Box2DDebugRenderer();
        world = new World(new Vector2(0,-9.81f),true);
        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.position.set(WIDTH/2,HEIGHT/2,0);

        BodyDef bodydeflevel = new BodyDef();
        FixtureDef fixture = new FixtureDef();

        levelall = setLevelall(levelall,bodydeflevel,fixture,5,5);
        level1 = addlevel(level1,bodydeflevel,fixture,5,3);
        adddistanciajoint(levelall,level1,3f);

    }
    public Body setLevelall(Body body, BodyDef bodydeflevel,FixtureDef fixture,float x, float y)
    {
        bodydeflevel.type = BodyType.StaticBody;
        bodydeflevel.position.set(x,y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2f,0.2f);


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
        shape.setAsBox(0.3f,0.2f);

        fixture.shape = shape;
        fixture.density = 2.5f;
        fixture.friction = 0.25f;
        fixture.restitution = 0.5f;
        body = world.createBody(bodydeflevel);
        body.createFixture(fixture);
        shape.dispose();
        return body;

    }
    public void adddistanciajoint(Body A, Body B,float distance)
    {
        DistanceJointDef distanceJointDef = new DistanceJointDef();
        distanceJointDef.bodyA = A;
        distanceJointDef.bodyB = B;
        distanceJointDef.length = distance;
        world.createJoint(distanceJointDef);
    }
    public void hide()
    {
        dispose();
    }
    public void dispose()
    {
        world.dispose();
        debugRenderBox2D.dispose();
    }
}
