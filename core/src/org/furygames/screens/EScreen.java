package org.furygames.screens;

public enum EScreen {
	 
    SPLASH {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new SplashScreen();
        }
    },
 
    MENU {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new MenuScreen();
        }
    }/*,
 
    GAME {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new GameScreen();
        }
    }*/;
 
    protected abstract com.badlogic.gdx.Screen getScreenInstance();
 
}