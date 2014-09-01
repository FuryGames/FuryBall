package org.furygames.handler;

import org.furygames.gui.Button.ButtonHandler;
import org.furygames.screens.EScreen;
import org.furygames.screens.ScreenManager;

public class ScreenSwitchHandler implements ButtonHandler {
	
	private EScreen screen = null;
	
	public ScreenSwitchHandler(EScreen screen) {
		this.screen = screen;
	}

	@Override
	public void onClick() {
		/* easily implemented screen switching thanks to singleton pattern */
		ScreenManager.getInstance().show(screen);
	}

}