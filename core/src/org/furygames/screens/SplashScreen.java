package org.furygames.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen extends GenericScreen {
	
	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	private Texture  logoTexture;
	
	public SplashScreen () {

	}

	@Override
	public void render(float delta) {
		manager.update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		sprite.draw(batcher);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		logoTexture = new Texture(Gdx.files.internal("Fondo-logo2.png")); 
		sprite = new Sprite(logoTexture );
		sprite.setColor(1, 1, 1, 0);
			
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
	
		sprite.setSize(sprite.getWidth(), sprite.getHeight() );
		sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
				- (sprite.getHeight() / 2));
		
		
		setupTween();
	  	batcher = new SpriteBatch();
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