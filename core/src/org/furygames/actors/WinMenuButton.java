package org.furygames.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.furygames.furyball.FuryBall;
import org.furygames.levels.ILevel;
import org.furygames.screens.GenericScreen;

/**
 * Created by scanevaro on 30/09/2014.
 */
public class WinMenuButton extends Actor {

    private ILevel currentLevel;
    private Sprite sprite;

    public enum Type {
        BACK, REPLAY, NEXT
    }

    public WinMenuButton(ILevel currentLevel, WinMenuButton.Type type) {
        this.currentLevel = currentLevel;

        switch (type) {
            case BACK:
                sprite = new Sprite(FuryBall.assets.manager.get("backButton.png", Texture.class));
                break;
            case REPLAY:
                sprite = new Sprite(FuryBall.assets.manager.get("replayButton.png", Texture.class));
                break;
            case NEXT:
                sprite = new Sprite(FuryBall.assets.manager.get("nextButton.png", Texture.class));
                break;
        }

        sprite.setSize(GenericScreen.WIDTH / 4.5f, GenericScreen.HEIGHT / 4.5f);
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = new Color(getColor().r, getColor().g,
                getColor().b, getColor().a * parentAlpha);

        batch.setColor(color);
        sprite.setColor(color);

        sprite.draw(batch);
    }
}