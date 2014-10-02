package org.furygames.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.furygames.furyball.FuryBall;
import org.furygames.screens.GenericScreen;

/**
 * Created by scanevaro on 29/09/2014.
 */
public class LevelCompleteActor extends Actor {
    private Sprite sprite;

    public LevelCompleteActor() {
        sprite = new Sprite(FuryBall.assets.manager.get("levelComplete.png", Texture.class));
        sprite.setSize(GenericScreen.WIDTH / 2, GenericScreen.HEIGHT / 2);
        sprite.setPosition(GenericScreen.WIDTH / 2 - sprite.getWidth() / 2, GenericScreen.HEIGHT / 2 - sprite.getHeight() / 3);

        setActions();
    }

    private void setActions() {
        //addAction(Actions.Popup);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = new Color(getColor().r, getColor().g,
                getColor().b, getColor().a * parentAlpha);

        batch.setColor(color);
        sprite.setColor(color);

        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
