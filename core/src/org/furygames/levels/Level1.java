package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Author;
import org.furygames.actors.Rectangle;
import org.furygames.screens.GenericScreen;

public class Level1 extends GenericLevel {

    private Author author;
    private Rectangle rect;

    public Level1(World world) {
        super(world);

        author = new Author(GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 5,
                world, GenericScreen.WUNIT, Author.EAuthors.WRITKAS);
        rect = new Rectangle(world, GenericScreen.WUNIT * 3, GenericScreen.HUNIT * 2);
    }

    @Override
    public void destroyLevel() {
        //  TODO
    }

    @Override
    public void act() {
        //TODO
    }
}
