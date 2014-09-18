package org.furygames.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import org.furygames.furyball.FuryBall;

public abstract class GenericFigure implements Disposable {

    protected Sprite sprite;
    protected Texture texture;
    protected Body body;
    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    protected World world;

    public GenericFigure(String imgPath, World world) {
        this.world = world;

        texture = FuryBall.assets.manager.get(imgPath, Texture.class);
        sprite = new Sprite(texture);

        bodyDef = new BodyDef();

        fixtureDef = new FixtureDef();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
