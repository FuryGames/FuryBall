package org.furygames.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GenericAuthor {
	protected Vector2 position;
	protected float angle;
	
	public GenericAuthor (int posX, int posY) {
		position = new Vector2(posX, posY);
	}
	
	protected void update (Body body) {
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		angle = (float) Math.toDegrees(body.getAngle());
	}
}
