package org.furygames.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Created by scanevaro on 09/09/2014.
 */
public class BackgroundActor extends Actor {
    private Sprite bgSprite;

    public BackgroundActor(Sprite bgSprite) {
        this.bgSprite = bgSprite;

        configureActor();

        setActions();
    }

    private void configureActor() {
        setColor(1, 1, 1, 0);
        setPosition((Gdx.graphics.getWidth() / 2) - bgSprite.getWidth() / 2, (Gdx.graphics.getHeight() / 2)
                - (bgSprite.getHeight() / 2));
    }

    private void setActions() {
        SequenceAction secAction = new SequenceAction();
        secAction.addAction(Actions.fadeIn(0.5f));
        secAction.addAction(Actions.delay(2.5f));
        secAction.addAction(Actions.fadeOut(0.5f));
        addAction(secAction);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = new Color(getColor().r, getColor().g,
                getColor().b, getColor().a * parentAlpha);

        batch.setColor(color);
        bgSprite.setColor(color);

        bgSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}