package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public class Level5 extends GenericLevel {

	public Level5 (World world) {
        super(world);
	}

	@Override
	public void destroyLevel() {
		
	}

	@Override
	public void act() {
		System.out.println("level5 xd");
	}

    @Override
    public void setColliding(boolean bool) {

    }

    @Override
    public boolean isColliding() {
        return false;
    }
}
