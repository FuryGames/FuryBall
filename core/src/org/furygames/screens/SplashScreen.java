package org.furygames.screens;


import org.furygames.furyball.FuryBall;
import org.furygames.timer.ScreenSwitchTask;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class SplashScreen extends GenericScreen {
	
	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	
	public SplashScreen () {
		
		
		FuryBall.assets.cargarAssets();
		FuryBall.assets.manager.finishLoading();
		sprite = new Sprite(FuryBall.assets.manager.get("Fondo-logo2.png",Texture.class));
	  	batcher = new SpriteBatch();

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
	
		
		
		
		manager.update(delta);
		batcher.begin();
		sprite.draw(batcher);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		
		
		
		
       
		sprite.setColor(1, 1, 1, 0);
			
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
	
		sprite.setSize(sprite.getWidth(), sprite.getHeight() );
		sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
				- (sprite.getHeight() / 2));
	
		setupTween();
	  	
	 
	
        Timer.schedule(new Task() {
            @Override
            public void run() {
            	
            	FuryBall.assets.manager.get("sounds/intro/Movie Theater Intro.mp3",Sound.class).stop();
            	ScreenManager.getInstance().show(EScreen.MENU);
            }
        }, 3.0f);
    
        FuryBall.assets.manager.get("sounds/intro/Movie Theater Intro.mp3",Sound.class).play();
    }
	  	
	  	
	  	
	  	
	
	private void setupTween() {
	   Tween.registerAccessor(Sprite.class, new SpriteAccessor());
	   manager = new TweenManager();
	
	   TweenCallback cb = new TweenCallback() {
	       @Override
	       public void onEvent(int type, BaseTween<?> source) {
	          
	       }
	   };

	   Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
           .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .7f)
           .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
           .start(manager);
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