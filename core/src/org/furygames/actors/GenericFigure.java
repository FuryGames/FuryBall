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

    private float x, y;
    private float width, height;

    public GenericFigure(String imgPath, World world) {
        this.world = world;

        setImg(imgPath);

        bodyDef = new BodyDef();

        fixtureDef = new FixtureDef();
    }

    public GenericFigure (World world) {
        this.world = world;

        bodyDef = new BodyDef();

        fixtureDef = new FixtureDef();
    }

    public void setImg (String imgPath) {
        texture = FuryBall.assets.manager.get(imgPath, Texture.class);
        sprite = new Sprite(texture);
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

    public Sprite getSprite () {
        return this.sprite;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
