package org.furygames.furyball;

import org.furygames.manager.AssetsManager;
import org.furygames.screens.EScreen;
import org.furygames.screens.ScreenManager;

import com.badlogic.gdx.Game;

public class FuryBall extends Game {
	
	public static final AssetsManager assets = new AssetsManager();
	
	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().show(EScreen.SPLASH);
	}

	@Override
	public void dispose() {
		super.dispose();
        ScreenManager.getInstance().dispose();
	}
}
