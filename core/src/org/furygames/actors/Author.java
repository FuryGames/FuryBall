package org.furygames.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class Author extends GenericAuthor implements Disposable {
	
	public enum EAuthors {
		WRITKAS,
		JRF,
		ENKI,
		GUITAURICIO
	}
	
	public Author (float posX, float posY, World world, float size, EAuthors eAuthors) {
		super();
		
		switch (eAuthors) {
			case WRITKAS:
				ttAvatar = new Texture (Gdx.files.internal("actors/authors/writkas.png"));
				break;
			case JRF:
				ttAvatar = new Texture (Gdx.files.internal("actors/authors/alguien.png"));
				break;
			case ENKI:
				ttAvatar = new Texture (Gdx.files.internal("actors/authors/alguien.png"));
				break;
			case GUITAURICIO:
				ttAvatar = new Texture (Gdx.files.internal("actors/authors/alguien.png"));
				break;
		}
		
		avatar = new Sprite (ttAvatar);
		
		avatar.setSize(size, size);
		avatar.setOrigin(avatar.getWidth() / 2, avatar.getHeight() / 2);
		
		// Crear figura
		//
		
		bd.position.set(posX, posY);
		bd.type = BodyType.DynamicBody;
		 
		shape.setRadius(size / 2);
		body = world.createBody(bd);
		body.createFixture(fixDef);
		
		body.setUserData(avatar);
	}
	
	@Override
	public void dispose() {
		ttAvatar.dispose();
	}
}
