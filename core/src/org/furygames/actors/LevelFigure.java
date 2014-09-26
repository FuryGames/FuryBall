package org.furygames.actors;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

/**
 * Created by writkas on 25/09/14.
 */
public class LevelFigure extends GenericFigure {

    private PolygonShape rect;

    public LevelFigure (World world, int numLevel, int posX, int posY, int size) {
        super(world);

        switch (numLevel) {
            case 1:
                super.setImg("buttons/levels/level-1.png");
                break;
            case 2:
                super.setImg("buttons/levels/level-2.png");
                break;
            case 3:
                super.setImg("buttons/levels/level-3.png");
                break;
            case 4:
                super.setImg("buttons/levels/level-4.png");
                break;
            case 5:
                super.setImg("buttons/levels/level-5.png");
                break;
            case 6:
                super.setImg("buttons/levels/level-6.png");
                break;
            case 7:
                super.setImg("buttons/levels/level-7.png");
                break;
            case 8:
                super.setImg("buttons/levels/level-8.png");
                break;
        }

        bodyDef.position.set(posX, posY);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        rect = new PolygonShape();
        rect.setAsBox(size, size);
        fixtureDef.shape = rect;

        body = world.createBody(bodyDef);

        body.createFixture(fixtureDef);
        body.setUserData(new Box2DSprite(sprite));

        rect.dispose();
    }
}
