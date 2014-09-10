package org.furygames.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import org.furygames.furyball.FuryBall;

/**
 * Created by writkas on 10/09/14.
 */

public abstract class GenericFigure implements Disposable {

    protected Sprite spImg;
    protected Texture ttImg;
    protected Body body;
    protected BodyDef bd;
    protected FixtureDef fixDef;
    protected World world;
    protected float posX, posY;

    public GenericFigure (String imgPath, World world) {
        this.world = world;

        ttImg = FuryBall.assets.manager.get(imgPath, Texture.class);
        spImg = new Sprite(ttImg);

        bd = new BodyDef();

        fixDef = new FixtureDef();
    }

    @Override
    public void dispose() {
        ttImg.dispose();
    }

    public void setPosition (float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
