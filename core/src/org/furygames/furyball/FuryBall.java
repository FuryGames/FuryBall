package org.furygames.furyball;

import org.furygames.screens.EScreen;
import org.furygames.screens.ScreenManager;

import com.badlogic.gdx.Game;

public class FuryBall extends Game {

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().show(EScreen.MENU);
	}

	@Override
	public void dispose() {
		super.dispose();
        ScreenManager.getInstance().dispose();
	}
}
