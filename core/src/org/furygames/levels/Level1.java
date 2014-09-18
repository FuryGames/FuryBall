package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Ball;
import org.furygames.actors.Rectangle;
import org.furygames.screens.GenericScreen;

public class Level1 extends GenericLevel {

    private Ball ball;
    private Rectangle rect;

    public Level1(World world) {
        super(world);

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 5,
                GenericScreen.HUNIT / 2);
        rect = new Rectangle(world, GenericScreen.WUNIT * 3, GenericScreen.HUNIT * 2, GenericScreen.WUNIT, GenericScreen.HUNIT, 0);
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
