package org.furygames.furyball;

import org.furygames.levels.ELevels;
import org.furygames.manager.AssetsManager;
import org.furygames.screens.EScreen;
import org.furygames.screens.GameScreen;
import org.furygames.screens.ScreenManager;

import com.badlogic.gdx.Game;

public class FuryBall extends Game {
	
	public static final AssetsManager assets = new AssetsManager();
	
	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
//        ScreenManager.getInstance().show(EScreen.SPLASH);
        GameScreen.eLevels = ELevels.LEVEL1;
        ScreenManager.getInstance().show(EScreen.GAME);
	}

	@Override
	public void dispose() {
		super.dispose();
        ScreenManager.getInstance().dispose();
	}
}
