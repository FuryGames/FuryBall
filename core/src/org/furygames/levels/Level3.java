package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public class Level3 extends GenericLevel {

	public Level3 (World world) {
        super(world);
    }

	@Override
	public void destroyLevel() {
		
	}

	@Override
	public void act() {
		System.out.println("level3 xd");
	}
}
