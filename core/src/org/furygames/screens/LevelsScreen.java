package org.furygames.screens;

import org.furygames.levels.ELevels;
import org.furygames.timer.ScreenSwitchTask;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Timer;

public class LevelsScreen extends GenericScreen {
    private OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer debug;

    public void render(float delta) {
        super.render(delta);
        
        checkInput();
        
        camera.update();
        debug.render(world, camera.combined);
        world.step(delta, 8, 3);
    }
    
    public void show() {
        debug = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH / 2,HEIGHT / 2, 0);


    }

    public void dispose() {
        debug.dispose();
    }
    
    public void checkInput () {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)
                || Gdx.input.isKeyJustPressed(Keys.BACK)) {
			Timer.schedule(new ScreenSwitchTask(EScreen.MENU), 0f);
		}

        // Esto es solo por el momento
        if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
            GameScreen.eLevels = ELevels.LEVEL1;
            Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }

        else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
            GameScreen.eLevels = ELevels.LEVEL2;
            Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }

        else if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
            GameScreen.eLevels = ELevels.LEVEL3;
            Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }

        else if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
            GameScreen.eLevels = ELevels.LEVEL4;
            Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }

        else if (Gdx.input.isKeyJustPressed(Keys.NUM_5)) {
            GameScreen.eLevels = ELevels.LEVEL5;
            Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
        }
	}
}
