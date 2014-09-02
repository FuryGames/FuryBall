package org.furygames.timer;

import org.furygames.screens.EScreen;
import org.furygames.screens.ScreenManager;

import com.badlogic.gdx.utils.Timer.Task;

public class ScreenSwitchTask extends Task {
	
	private EScreen screen = null;
	
	public ScreenSwitchTask(EScreen screen) {
		this.screen = screen;
	}

	@Override
	public void run() {
		/* easily implemented screen switching thanks to singleton pattern */
		ScreenManager.getInstance().show(screen);
	}

}
