package org.furygames.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

public class Wall extends GenericFigure {

    private PolygonShape wall;

    public Wall(World world, float posX, float posY, float sizeX, float sizeY) {
        super("actors/figures/wall-square.png", world);

        bodyDef.position.set(posX, posY);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        wall = new PolygonShape();
        wall.setAsBox(sizeX, sizeY);
        fixtureDef.shape = wall;

        body = world.createBody(bodyDef);

        body.createFixture(fixtureDef);
        body.setUserData(new Box2DSprite(sprite));

        wall.dispose();
    }
}
