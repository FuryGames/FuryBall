package org.furygames.actors;

import org.furygames.screens.GenericScreen;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public final class Box2DCreator {
	
	public static void createLimits (World world) {
		
		final float SEPARATOR = .2f;
		//final float WSEPARATOR = GenericScreen.WUNIT / 6;
		//final float HSEPARATOR = GenericScreen.HUNIT / 6;
		
		// Bottom
		//
		
		BodyDef bottom = new BodyDef();
		bottom.type = BodyType.StaticBody;
		 
		EdgeShape shape = new EdgeShape();
		shape.set(SEPARATOR, SEPARATOR, GenericScreen.WIDTH - SEPARATOR, SEPARATOR);
		 
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		 
		Body oBody = world.createBody(bottom);
		oBody.createFixture(fixDef);
		 
		shape.dispose();
		
		// Up
		//
		
		BodyDef up = new BodyDef();
		up.type = BodyType.StaticBody;
		 
		EdgeShape shape2 = new EdgeShape();
		shape2.set(SEPARATOR, GenericScreen.HEIGHT - SEPARATOR , GenericScreen.WIDTH - SEPARATOR, GenericScreen.HEIGHT - SEPARATOR);
		 
		FixtureDef fixDef2 = new FixtureDef();
		fixDef2.shape = shape2;
		 
		Body body = world.createBody(up);
		body.createFixture(fixDef2);
		 
		shape2.dispose();
		
		// Left
		//
		
		BodyDef left = new BodyDef();
		left.type = BodyType.StaticBody;
		 
		EdgeShape leftShape = new EdgeShape();
		shape2.set(SEPARATOR, SEPARATOR, SEPARATOR, GenericScreen.HEIGHT - SEPARATOR);
		 
		FixtureDef leftFix = new FixtureDef();
		leftFix.shape = leftShape;
		 
		Body leftBody = world.createBody(left);
		leftBody.createFixture(leftFix);
		 
		leftShape.dispose();
		
		// Right
		//
		
		BodyDef right = new BodyDef();
		right.type = BodyType.StaticBody;
		 
		EdgeShape rightShape = new EdgeShape();
		shape2.set(GenericScreen.WIDTH - SEPARATOR, SEPARATOR, GenericScreen.WIDTH - SEPARATOR, GenericScreen.HEIGHT - SEPARATOR);
		 
		FixtureDef rightFix = new FixtureDef();
		rightFix.shape = rightShape;
		 
		Body rightBody = world.createBody(right);
		rightBody.createFixture(rightFix);
		 
		rightShape.dispose();
	}
}
