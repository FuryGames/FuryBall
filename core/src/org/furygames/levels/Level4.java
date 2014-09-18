package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public class Level4 extends GenericLevel {

	public Level4 (World world) {
        super(world);
        // Crear objetos
	}
	
	@Override
	public void destroyLevel() {
		
	}

	@Override
	public void act() {
		System.out.println("level4 xd");
	}

    @Override
    public void setColliding(boolean bool) {

    }

    @Override
    public boolean isColliding() {
        return false;
    }
}
