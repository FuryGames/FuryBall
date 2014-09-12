package org.furygames.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FillViewport;
import org.furygames.actors.BackgroundActor;
import org.furygames.furyball.FuryBall;

public class SplashScreen extends GenericScreen {

    private Sprite sprite;

    public SplashScreen() {
        FuryBall.assets.cargarAssets();
        FuryBall.assets.manager.finishLoading();
        sprite = new Sprite(FuryBall.assets.manager.get("backgrounds/splash.jpg", Texture.class));

        BackgroundActor bgActor = new BackgroundActor(sprite);
        bgActor.setCenterPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        bgActor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage.addActor(bgActor);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stage.draw();
        stage.act();
    }

    @Override
    public void show() {
        Timer.schedule(new Task() {
            @Override
            public void run() {
                ScreenManager.getInstance().show(EScreen.MENU);
            }
        }, 4.0f);

        Music music = FuryBall.assets.manager.get("sounds/music/Mauricio Vera - Ausencia.mp3", Music.class);
        music.play();
        music.setLooping(true);
    }

    @Override
    public void hide() {
        /* dispose intro screen because it won't be needed anymore */
        ScreenManager.getInstance().dispose(EScreen.SPLASH);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}