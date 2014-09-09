package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public abstract class GenericLevel implements ILevel {
	
	protected double time; // Tiempo del nivel

	@Override
	public void createLevel(World world) {
			
	}
	
	@Override
	public void destroyLevel(World world) {
		// TODO Auto-generated method stub
		
	}
}
