package org.furygames.actors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public final class AuthorsCreator {

	public static Body Matias (World world) {
		BodyDef bd = new BodyDef();
		bd.position.set(MathUtils.random(2, 4), MathUtils.random(1, 2));
		bd.type = BodyType.DynamicBody;
		 
		CircleShape shape = new CircleShape();
		shape.setRadius(.5f);
		 
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = .75f;
		fixDef.restitution = .5f;
		fixDef.density = 5f;
		 
		Body oBody = world.createBody(bd);
		oBody.createFixture(fixDef);
		
		return oBody;
	}
	
	public static void Test (World world) {
		BodyDef bd = new BodyDef();
		bd.position.set(MathUtils.random(2, 4), MathUtils.random(1, 2));
		bd.type = BodyType.DynamicBody;
		 
		
		Vector2[] vertices = new Vector2[5];

	    vertices[0] = new Vector2(.82f, .0f);
	    vertices[1] = new Vector2(.146f, .40f);
	    vertices[2] = new Vector2(.385f, .268f);
	    vertices[3] = new Vector2(.322f, .341f);
	    vertices[4] = new Vector2(.225f, .322f);
	    
	    PolygonShape shape = new PolygonShape();
	    shape.set(vertices);
		 
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		fixDef.friction = .75f;
		fixDef.restitution = .5f;
		fixDef.density = 5f;
		 
		Body oBody = world.createBody(bd);
		oBody.createFixture(fixDef);
	}
}
