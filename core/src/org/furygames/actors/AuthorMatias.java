package org.furygames.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

public class AuthorMatias extends GenericAuthor implements Disposable {
	
	private Sprite avatar;
	private Texture ttAvatar;
	
	public AuthorMatias (int posX, int posY) {
		super(posX, posY);
		
		ttAvatar = new Texture (Gdx.files.external("actors/authors/writkas.png"));
		avatar = new Sprite (ttAvatar);
	}
	
	@Override
	public void dispose() {
		ttAvatar.dispose();
	}
}
