package org.furygames.actors;

import org.furygames.furyball.FuryBall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
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
				ttAvatar = FuryBall.assets.manager.get("actors/authors/writkas.png", Texture.class);
				break;
			case JRF:
				ttAvatar = FuryBall.assets.manager.get("actors/authors/enki.png", Texture.class);
				break;
			case ENKI:
				ttAvatar = FuryBall.assets.manager.get("actors/authors/jrf.png", Texture.class);
				break;
			case GUITAURICIO:
				ttAvatar = FuryBall.assets.manager.get("actors/authors/alguien.png", Texture.class);
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
