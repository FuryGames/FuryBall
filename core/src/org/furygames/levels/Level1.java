package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Ball;
import org.furygames.actors.Wall;
import org.furygames.screens.GenericScreen;

public class Level1 extends GenericLevel {

    private Ball ball;
    private Wall walls [];

    public Level1(World world) {
        super(world);

        walls = new Wall [3];

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 1,
                GenericScreen.HUNIT / 2);

        walls[0] = new Wall(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 6,
                GenericScreen.WUNIT / 3,  GenericScreen.HUNIT * 2);
        walls[1] = new Wall(world, GenericScreen.WUNIT * 3, GenericScreen.HUNIT * 8,
                GenericScreen.WUNIT * 1.655f,  GenericScreen.HUNIT / 2);
        walls[2] = new Wall(world, GenericScreen.WUNIT * 7, GenericScreen.HUNIT * 8,
                GenericScreen.WUNIT,  GenericScreen.HUNIT / 2);
    }

    @Override
    public void destroyLevel() {
        //  TODO
    }

    @Override
    public void act() {
        //TODO
    }

    @Override
    public void setColliding(boolean bool) {

    }

    @Override
    public boolean isColliding() {
        return false;
    }
}
