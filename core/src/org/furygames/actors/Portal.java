package org.furygames.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by scanevaro on 17/09/2014.
 */
public class Portal extends GenericFigure {

    private CircleShape shape;

    public Portal(World world, float posX, float posY, float radius) {
        super("actors/figures/wall-column.png", world);

        sprite.setSize(radius * 2, radius * 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        super.setPosition(posX, posY);
        bodyDef.position.set(posX, posY);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        shape = new CircleShape();
        shape.setRadius(radius);

        fixtureDef.shape = shape;
        fixtureDef.density = .5f;
        fixtureDef.restitution = .5f;
        fixtureDef.friction = .5f;
        fixtureDef.isSensor = true;

        body = world.createBody(bodyDef);

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Portal");

//        body.setUserData(new Box2DSprite(sprite));

        shape.dispose();
    }
}
