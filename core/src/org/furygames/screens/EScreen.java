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
    },
 
    CREDITS {
    	@Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new CreditsScreen();
        }
    },
    
    LEVELS {
    	@Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new LevelsScreen();
        }
    },
    
    GAME {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new GameScreen();
        }
    },
    
    SCORE {
    	@Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
             return new ScoreScreen();
        }
    };
 
    protected abstract com.badlogic.gdx.Screen getScreenInstance();
 
}