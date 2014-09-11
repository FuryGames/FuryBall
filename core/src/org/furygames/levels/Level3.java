package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Ball;
import org.furygames.actors.Rectangle;
import org.furygames.actors.Triangle;
import org.furygames.screens.GenericScreen;

public class Level3 extends GenericLevel {

    private Ball ball;
    private Rectangle rect;
    private Triangle tri;

    public Level3(World world) {
        super(world);

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 5,
                GenericScreen.HUNIT / 2);
        rect = new Rectangle(world, GenericScreen.WUNIT * 3, GenericScreen.HUNIT * 2);

        tri = new Triangle(world, GenericScreen.WUNIT * 7, GenericScreen.HUNIT * 5);
    }

    @Override
    public void destroyLevel() {
    }

    @Override
    public void act() {
    }
}
