package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public class Level1 extends GenericLevel {

	public Level1 () {
		// Crear objetos
	}
	
	@Override
	public void createLevel(World world) {
		super.createLevel(world);
	}
	
	@Override
	public void destroyLevel() {
		
	}

	@Override
	public void run() {
		System.out.println("level1 xd");
	}
}
