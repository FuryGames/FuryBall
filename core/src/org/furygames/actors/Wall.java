package org.furygames.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

public class Wall extends GenericFigure {

    private PolygonShape wall;

    public Wall(World world, float posX, float posY, float sizeX, float sizeY) {
        super("actors/figures/wall-square.png", world);

        bd.position.set(posX, posY);
        bd.type = BodyDef.BodyType.StaticBody;

        wall = new PolygonShape();
        wall.setAsBox(sizeX, sizeY);
        fixDef.shape = wall;

        body = world.createBody(bd);

        body.createFixture(fixDef);
        body.setUserData(new Box2DSprite(spImg));

        wall.dispose();
    }
}
