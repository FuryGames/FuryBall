package org.furygames.furyball;

import org.furyfames.assets.Manager;
import org.furygames.screens.EScreen;
import org.furygames.screens.ScreenManager;

import com.badlogic.gdx.Game;



public class FuryBall extends Game {
	
	
	public static final Manager assets = new Manager();
	
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
