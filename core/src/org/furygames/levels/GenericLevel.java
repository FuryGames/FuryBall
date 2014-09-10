package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public abstract class GenericLevel implements ILevel {
	
	protected double time; // Tiempo del nivel
	protected World world;

	@Override
	public void createLevel(World world) {
		this.world = world;
	}
	
	@Override
	public void destroyLevel() {
		// Destruir nivel
	}
	
	@Override
	public void act() {
		// Interacciones genéricas del nivel
	}
}
