package org.furygames.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

/**
 * Created by scanevaro on 11/09/2014.
 */
public class Triangle extends GenericFigure {

    private PolygonShape tri;

    public Triangle(World world, float posX, float posY) {
        super("actors/figures/wall-column.png", world);

        spImg.setSize(1, 1);

        bd.position.set(posX, posY);
        bd.type = BodyDef.BodyType.StaticBody;

        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, 0);
        vertices[1] = new Vector2(0, 1);
        vertices[2] = new Vector2(0.5f, 0);
        tri = new PolygonShape();
        tri.set(vertices);
        fixDef.shape = tri;

        body = world.createBody(bd);

        body.createFixture(fixDef);
        body.setUserData(new Box2DSprite(spImg));

        tri.dispose();
    }
}
