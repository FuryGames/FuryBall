package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Ball;
import org.furygames.actors.Wall;
import org.furygames.actors.Triangle;
import org.furygames.screens.GenericScreen;

public class Level3 extends GenericLevel {

    private Ball ball;
    private Wall wall;
    private Triangle tri;

    public Level3(World world) {
        super(world);

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 1,
                GenericScreen.HUNIT / 2);

        wall = new Wall(world, GenericScreen.WUNIT * 6, GenericScreen.HUNIT * 5, 1f, 1f);

        tri = new Triangle(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 3, (float) Math.PI);
    }

    @Override
    public void destroyLevel() {
    }

    @Override
    public void act() {
    }
}
