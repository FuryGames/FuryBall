package org.furygames.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends GenericFigure {

    private PolygonShape wall;

    public Wall(World world, float posX, float posY, float sizeX, float sizeY) {
        super("actors/figures/wall-square.png", world);

        spImg.setSize(sizeX * 2, sizeY * 2);
        spImg.setOrigin(sizeX / 2, sizeY / 2);

        //super.setPosition(posX, posY);
        bd.position.set(posX, posY);
        bd.type = BodyDef.BodyType.StaticBody;

        wall = new PolygonShape();
        wall.setAsBox(sizeX, sizeY);
        fixDef.shape = wall;

        body = world.createBody(bd);

        body.createFixture(fixDef);
        body.setUserData(spImg);

        wall.dispose();
    }
}
