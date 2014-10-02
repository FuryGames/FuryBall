package org.furygames.inputs;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import org.furygames.levels.ELevels;
import org.furygames.screens.EScreen;
import org.furygames.screens.GameScreen;
import org.furygames.timer.ScreenSwitchTask;

/**
 * Created by writkas on 1/10/14.
 */
public class InputLevelScreen implements InputProcessor {
    private Body hitBody = null;
    private World world;
    private Camera camera;

    public InputLevelScreen (World world, Camera camera) {
        this.world = world;
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    Vector3 testPoint = new Vector3();
    QueryCallback callback = new QueryCallback() {
        @Override
        public boolean reportFixture (Fixture fixture) {
            // if the hit point is inside the fixture of the body
            // we report it
            if (fixture.testPoint(testPoint.x, testPoint.y)) {
                hitBody = fixture.getBody();

                int opt = Integer.parseInt(hitBody.getFixtureList().first().getUserData().toString());

                switch (opt) {
                    case 1:
                        GameScreen.eLevels = ELevels.LEVEL1;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                    case 2:
                        GameScreen.eLevels = ELevels.LEVEL2;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                    case 3:
                        GameScreen.eLevels = ELevels.LEVEL3;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                    case 4:
                        GameScreen.eLevels = ELevels.LEVEL4;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                    case 5:
                        GameScreen.eLevels = ELevels.LEVEL5;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                    case 6:
                        GameScreen.eLevels = ELevels.LEVEL6;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                    case 7:
                        GameScreen.eLevels = ELevels.LEVEL7;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                    case 8:
                        GameScreen.eLevels = ELevels.LEVEL8;
                        Timer.schedule(new ScreenSwitchTask(EScreen.GAME), 0f);
                        break;
                }

                return false;
            } else
                return true;
        }
    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // translate the mouse coordinates to world coordinates
        if (camera != null)
            camera.unproject(testPoint.set(screenX, screenY, 0));
        // ask the world which bodies are within the given
        // bounding box around the mouse pointer
        hitBody = null;
        world.QueryAABB(callback, testPoint.x - 0.0001f, testPoint.y - 0.0001f,
                testPoint.x + 0.0001f, testPoint.y + 0.0001f);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
