package org.furygames.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import org.furygames.screens.GenericScreen;

/**
 * Created by writkas on 10/09/14.
 */
public class Rectangle extends GenericFigure {

    private PolygonShape rect;

    public Rectangle(World world, float posX, float posY) {
        super("actors/figures/wall-square.png", world);

        spImg.setSize(GenericScreen.WUNIT, GenericScreen.HUNIT);
        spImg.setOrigin(spImg.getWidth() / 2, spImg.getHeight() / 2);

        super.setPosition(posX, posY);
        bd.position.set(posX, posY);
        bd.type = BodyDef.BodyType.StaticBody;

        rect = new PolygonShape();
        rect.setAsBox(GenericScreen.WUNIT, GenericScreen.HUNIT);

        body = world.createBody(bd);
        body.createFixture(fixDef);
        body.setUserData(spImg);

        rect.dispose();
    }

}
