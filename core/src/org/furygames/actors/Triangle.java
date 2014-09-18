package org.furygames.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

/**
 * Created by scanevaro on 11/09/2014.
 */
public class Triangle extends GenericFigure {

    private PolygonShape tri;

    public Triangle(World world, float posX, float posY, float angleRadians) {
        super("actors/figures/elementMetal000.png", world);

        sprite.setSize(1, 1);

        super.setPosition(posX, posY);
        bodyDef.position.set(posX, posY);
        bodyDef.angle = angleRadians;
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.6f, 0);
        vertices[1] = new Vector2(0, 0.8f);
        vertices[2] = new Vector2(0.6f, 0);
        tri = new PolygonShape();
        tri.set(vertices);
        fixtureDef.shape = tri;

        body = world.createBody(bodyDef);

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Triangle");

        body.setUserData(new Box2DSprite(sprite));

        tri.dispose();
    }
}