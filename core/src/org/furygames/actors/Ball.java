package org.furygames.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import org.furygames.furyball.FuryBall;

public class Ball extends GenericFigure {
    private CircleShape circle;

    public Ball (World world, float posX, float posY, float radius) {
        super("actors/figures/ball/alien_1.png", world);

        spImg.setSize(radius * 2, radius * 2);
        spImg.setOrigin(spImg.getWidth() / 2, spImg.getHeight() / 2);

        super.setPosition(posX, posY);
        bd.position.set(posX, posY);
        bd.type = BodyDef.BodyType.DynamicBody;

        circle = new CircleShape();
        circle.setRadius(radius);

        fixDef.shape = circle;
        fixDef.density = .5f;
        fixDef.restitution = .5f;
        fixDef.friction = .5f;

        body = world.createBody(bd);
        body.createFixture(fixDef);
        body.setUserData(spImg);

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
        spImg.setTexture(tt);
    }
}
