package org.furygames.actors;

import org.furygames.furyball.FuryBall;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;

public class ExitButton extends GenericButton implements Disposable {
	private PolygonShape triangle;
	
	public ExitButton (World world, float posX, float posY, float halfWidth, float halfHeight) {
		super();
		
		triangle = new PolygonShape();
		
		fixture.shape = triangle;
		fixture.friction = MathUtils.random(.1f, .9f);
		fixture.restitution = MathUtils.random(.1f, .9f);
		fixture.density = MathUtils.random(.1f, .5f);
		
		Texture exitButton = FuryBall.assets.manager.get("buttons/exit_button.png", Texture.class);
		ttButton = exitButton;
		spButton = new Sprite(ttButton);
		spButton.setSize(halfWidth, halfHeight);
		spButton.setOrigin(spButton.getWidth() / 2, spButton.getHeight() / 2);
		
		bd.position.set(posX, posY);
		bd.type = BodyType.DynamicBody;

		halfWidth /= 2;
		halfHeight /= 2;
		
		triangle.set(new Vector2[] { new Vector2(-halfWidth, -halfHeight),
				new Vector2(0, halfHeight),
				new Vector2(halfWidth, -halfHeight) });
		
		body = world.createBody(bd);
		body.createFixture(fixture);
		body.setUserData(spButton);
		
		triangle.dispose();
	}

	@Override
	public void dispose() {
		ttButton.dispose();
	}
}
