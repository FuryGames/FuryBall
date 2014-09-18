package org.furygames.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import org.furygames.furyball.FuryBall;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

public class Ball extends GenericFigure {

    private CircleShape circle;

    public Ball(World world, float posX, float posY, float radius) {
        super("actors/figures/ball/alien_1.png", world);

        sprite.setSize(radius * 2, radius * 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        bodyDef.position.set(posX, posY);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        circle = new CircleShape();
        circle.setRadius(radius);

        fixtureDef.shape = circle;
        fixtureDef.density = .5f;
        fixtureDef.restitution = .5f;
        fixtureDef.friction = .5f;

        body = world.createBody(bodyDef);

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Ball");

        body.setUserData(new Box2DSprite(sprite));

        circle.dispose();
    }

    public void changeImage (int numImg) {
        String img = null;

        switch (numImg) {
            case 1:
                img = "actors/figures/ball/alien_1.png";
                break;
            case 2:
                img = "actors/figures/ball/alien_2.png";
                break;
            case 3:
                img = "actors/figures/ball/alien_3.png";
                break;
            case 4:
                img = "actors/figures/ball/alien_4.png";
                break;
        }

        Texture tt = FuryBall.assets.manager.get(img, Texture.class);
        sprite.setTexture(tt);
    }
}
