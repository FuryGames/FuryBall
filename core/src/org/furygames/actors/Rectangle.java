package org.furygames.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import org.furygames.screens.GenericScreen;

public class Rectangle extends GenericFigure {

    private PolygonShape rect;

    public Rectangle(World world, float posX, float posY, float sizeX, float sizeY, float angleRad) {
        super("actors/figures/wall-column.png", world);

        sprite.setSize(GenericScreen.WUNIT, GenericScreen.HUNIT);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        bodyDef.position.set(posX, posY);
        bodyDef.angle = angleRad;
        bodyDef.type = BodyDef.BodyType.StaticBody;

        rect = new PolygonShape();
        rect.setAsBox(sizeX, sizeY);
        fixtureDef.shape = rect;

        body = world.createBody(bodyDef);

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Rectangle");

        body.setUserData(sprite);

        rect.dispose();
    }
}
