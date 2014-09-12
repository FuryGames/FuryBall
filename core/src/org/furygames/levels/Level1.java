package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Ball;
import org.furygames.actors.Wall;
import org.furygames.screens.GenericScreen;

public class Level1 extends GenericLevel {

    private Ball ball;
    private Wall rect;

    public Level1(World world) {
        super(world);

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 5,
                GenericScreen.HUNIT / 2);
        rect = new Wall(world, GenericScreen.WUNIT * 3, GenericScreen.HUNIT * 3,
                1f, 1f);
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
