package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public interface ILevel {
	public void createLevel(World world); // Crear nivel
	public void destroyLevel(); // Destruir nivel
	public void act(); // Interacciones del nivel
}
