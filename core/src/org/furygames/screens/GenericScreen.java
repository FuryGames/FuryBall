package org.furygames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

public abstract class GenericScreen implements Screen {

	public static final float WIDTH = Gdx.graphics.getWidth() / 100;
	public static final float HEIGHT = Gdx.graphics.getHeight() / 100;
	
	// Unidades de mediada que podemos utilizar para posicionar las figuras
	public static final float WUNIT = WIDTH / 10;
	public static final float HUNIT = HEIGHT / 10;

	protected Stage stage;
	protected SpriteBatch batch;
	
	private FillViewport viewport;
	
	public GenericScreen () {
		viewport = new FillViewport(WIDTH, HEIGHT);
		stage = new Stage (viewport);
		batch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
