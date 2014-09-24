package org.furygames.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import net.dermetfan.utils.libgdx.graphics.AnimatedBox2DSprite;
import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;
import org.furygames.furyball.FuryBall;

/**
 * Created by scanevaro on 17/09/2014.
 */
public class BlackHole extends GenericFigure {

    private CircleShape shape;

    public BlackHole(World world, float posX, float posY, float radius) {
        super("actors/figures/wall-column.png", world);

        sprite.setSize(radius * 2, radius * 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

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
        fixture.setUserData("BlackHole");

        // Set animation
        TextureRegion blackHole1, blackHole2, blackHole3, blackHole4;
        blackHole1 = new TextureRegion(FuryBall.assets.manager.get("actors/figures/BlackHole/BlackHole0000.png", Texture.class));
        blackHole2 = new TextureRegion(FuryBall.assets.manager.get("actors/figures/BlackHole/BlackHole0001.png", Texture.class));
        blackHole3 = new TextureRegion(FuryBall.assets.manager.get("actors/figures/BlackHole/BlackHole0002.png", Texture.class));
        blackHole4 = new TextureRegion(FuryBall.assets.manager.get("actors/figures/BlackHole/BlackHole0003.png", Texture.class));
        Animation animation = new Animation(0.3f, blackHole1, blackHole2, blackHole3, blackHole4);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        AnimatedSprite animatedSprite = new AnimatedSprite(animation);
        AnimatedBox2DSprite animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
        body.setUserData(animatedBox2DSprite);

        shape.dispose();
    }

    public CircleShape getShape() {
        return shape;
    }

    public void setShape(CircleShape shape) {
        this.shape = shape;
    }
}