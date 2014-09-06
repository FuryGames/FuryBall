package org.furygames.actors;

import org.furygames.furyball.FuryBall;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;

public class CreditsButton extends GenericButton implements Disposable {
	private PolygonShape rect;
	
	public CreditsButton (World world, float posX, float posY, float size) {
		super();
		
		rect = new PolygonShape();
		
		fixture.shape = rect;
		fixture.friction = MathUtils.random(.1f, .9f);
		fixture.restitution = MathUtils.random(.1f, .9f);
		fixture.density = MathUtils.random(.1f, .5f);
		
		Texture creditsButton = FuryBall.assets.manager.get("buttons/credits_button.png", Texture.class);
		ttButton = creditsButton;
		spButton = new Sprite(ttButton);
		spButton.setSize(size, size);
		spButton.setOrigin(spButton.getWidth() / 2, spButton.getHeight() / 2);
		
		bd.position.set(posX, posY);
		bd.type = BodyType.DynamicBody;

		rect.setAsBox(size / 2, size / 2);

		body = world.createBody(bd);
		body.createFixture(fixture);
		body.setUserData(spButton);
		
		rect.dispose();
	}

	@Override
	public void dispose() {
		ttButton.dispose();
	}
}
