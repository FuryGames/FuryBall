package org.furygames.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class GenericButton {
	protected Sprite spButton;
	protected Texture ttButton;
	
	protected Body body;
	protected BodyDef bd;
	protected FixtureDef fixture;
	
	public GenericButton () {
		bd = new BodyDef();
		
		fixture = new FixtureDef();
	}
	
	public Body getBody () {
		return body;
	}
	
	public Sprite getSprite () {
		return spButton;
	}
}
