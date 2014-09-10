package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Author;
import org.furygames.screens.GenericScreen;

public class Level1 extends GenericLevel {

    private Author author;

	public Level1 () {
		author = new Author(GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 5,
                world, GenericScreen.WUNIT, Author.EAuthors.WRITKAS);
	}
	
	@Override
	public void createLevel(World world) {
		super.createLevel(world);
	}
	
	@Override
	public void destroyLevel() {
		
	}

	@Override
	public void act() {
		System.out.println("level1 xd");
	}
}
