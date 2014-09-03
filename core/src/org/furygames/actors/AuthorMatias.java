package org.furygames.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class AuthorMatias extends GenericAuthor implements Disposable {
	
	private Sprite avatar;
	private Texture ttAvatar;
	
	private Body body;
	
	public AuthorMatias (int posX, int posY, World world) {
		super(posX, posY);
		
		ttAvatar = new Texture (Gdx.files.internal("actors/authors/writkas.png"));
		avatar = new Sprite (ttAvatar);
		
		avatar.setSize(3f, 3f);
		avatar.setOrigin(avatar.getWidth() / 2, avatar.getHeight() / 2);
		
		// Crear figura
		//
		
		BodyDef bd = new BodyDef();
		bd.position.set(MathUtils.random(3, 5), MathUtils.random(3, 5));
		bd.type = BodyType.DynamicBody;
		 
		CircleShape shape = new CircleShape();
		shape.setRadius(3f / 2);
		 
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = .75f;
		fixDef.restitution = .5f;
		fixDef.density = 5f;
		 
		body = world.createBody(bd);
		body.createFixture(fixDef);
		
		body.setUserData(avatar);
	}
	
	public Sprite getAvatar () {
		return avatar;
	}
	
	@Override
	public void dispose() {
		ttAvatar.dispose();
	}
}
