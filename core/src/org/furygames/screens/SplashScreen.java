package org.furygames.screens;

import org.furygames.timer.ScreenSwitchTask;

import com.badlogic.gdx.utils.Timer;

public class SplashScreen extends GenericScreen {

	public SplashScreen () {

	}

	@Override
	public void render(float delta) {
		System.out.println("Soy splash!");
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		/* schedule to show main menu screen after 2 seconds */
		Timer.schedule(new ScreenSwitchTask(EScreen.MENU), 3f);
	}

	@Override
	public void hide() {
		/* dispose intro screen because it won't be needed anymore */
		ScreenManager.getInstance().dispose(EScreen.SPLASH);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	} 

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	
	}

}