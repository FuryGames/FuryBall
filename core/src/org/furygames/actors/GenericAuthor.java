package org.furygames.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class GenericAuthor {

	protected Sprite avatar;
	protected Texture ttAvatar;
	
	protected Body body;
	protected BodyDef bd;
	protected CircleShape shape;
	protected FixtureDef fixDef;
	
	public GenericAuthor () {
		bd = new BodyDef();
		shape = new CircleShape();
		
		fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = MathUtils.random(.1f, .9f);
		fixDef.restitution = MathUtils.random(.1f, .9f);
		fixDef.density = MathUtils.random(.1f, 5f);
	}
}
