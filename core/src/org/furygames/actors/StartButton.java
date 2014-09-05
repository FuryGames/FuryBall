package org.furygames.actors;

import org.furygames.furyball.FuryBall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class StartButton extends GenericButton implements Disposable {
	private CircleShape circle;
	
	public StartButton (World world, float posX, float posY, float size) {
		super();
		
		circle = new CircleShape();
		
		fixture.shape = circle;
		fixture.friction = MathUtils.random(.1f, .9f);
		fixture.restitution = MathUtils.random(.1f, .9f);
		fixture.density = MathUtils.random(.1f, 5f);
		
		//Texture startButton = FuryBall.assets.manager.get("buttons/start_button.png", Texture.class);
		//ttButton = startButton;
		ttButton = new Texture (Gdx.files.internal("buttons/start_button.png"));
		spButton = new Sprite(ttButton);
		spButton.setSize(size, size);
		spButton.setOrigin(spButton.getWidth() / 2, spButton.getHeight() / 2);
		
		bd.position.set(posX, posY);
		bd.type = BodyType.DynamicBody;

		circle.setRadius(size / 2);
		
		body = world.createBody(bd);
		body.createFixture(fixture);
		body.setUserData(spButton);
	}
	
	@Override
	public void dispose() {
		ttButton.dispose();
	}
}
