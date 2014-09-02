package org.furygames.actors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public final class AuthorsCreator {

	public static void Matias (World world) {
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
	}
}
