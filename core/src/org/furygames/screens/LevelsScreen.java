package org.furygames.screens;

import com.badlogic.gdx.InputMultiplexer;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;
import org.furygames.actors.LevelFigure;
import org.furygames.inputs.InputLevelScreen;
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
    private final int NUM_LEVELS = 8;

    private OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer debug;
    private LevelFigure [] levelFigures;
    private InputLevelScreen inputLevelScreen;

    public void show() {
        super.show();

        debug = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);

        levelFigures = new LevelFigure [NUM_LEVELS];

        // Level1
        levelFigures[0] = new LevelFigure(world, 1, WUNIT, HUNIT * 7, 1f);
        // Level2
        levelFigures[1] = new LevelFigure(world, 2, (WUNIT * 3) + WUNIT / 2, HUNIT * 7, 1f);
        // Level3
        levelFigures[2] = new LevelFigure(world, 3, (WUNIT * 7) - WUNIT / 2, HUNIT * 7, 1f);
        // Level4
        levelFigures[3] = new LevelFigure(world, 4, WUNIT * 9, HUNIT * 7, 1f);
        // Level5
        levelFigures[4] = new LevelFigure(world, 5, WUNIT, HUNIT * 3, 1f);
        // Level6
        levelFigures[5] = new LevelFigure(world, 6, (WUNIT * 3) + WUNIT / 2, HUNIT * 3, 1f);
        // Level7
        levelFigures[6] = new LevelFigure(world, 7, (WUNIT * 7) - WUNIT / 2, HUNIT * 3, 1f);
        // Level8
        levelFigures[7] = new LevelFigure(world, 8, WUNIT * 9, HUNIT * 3, 1f);

        // Le asignamos camara a todos los LevelScreen, esto es para que funcione el touchDown
        for (LevelFigure lf : levelFigures)
            lf.setCamera(camera);

        // Input
        inputLevelScreen = new InputLevelScreen(world, camera);
        Gdx.input.setInputProcessor(inputLevelScreen);
    }

    public void render(float delta) {
        super.render(delta);

        checkInput();

        camera.update();
        world.step(delta, 8, 3);
        debug.render(world, camera.combined);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
            Box2DSprite.draw(batch, world);
        batch.end();
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
