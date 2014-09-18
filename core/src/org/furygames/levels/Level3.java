package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Ball;
import org.furygames.actors.Wall;
import org.furygames.actors.Portal;
import org.furygames.actors.Rectangle;
import org.furygames.actors.Triangle;
import org.furygames.screens.GenericScreen;

public class Level3 extends GenericLevel {

    private Ball ball;
    private Wall wall;
    private Portal portal;
    private Rectangle rect;
    private Triangle tri;
    private Rectangle rect1;
    private Rectangle rect2;
    private Rectangle rect3;
    private Rectangle rect4;
    private int i;
    private boolean colliding;

    public Level3(World world) {
        super(world);

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 1,
                GenericScreen.HUNIT / 2);

        wall = new Wall(world, GenericScreen.WUNIT * 6, GenericScreen.HUNIT * 5, 1f, 1f);
        i = 10;

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 1, GenericScreen.HUNIT / 2);

        tri = new Triangle(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 3, (float) Math.PI);

        rect1 = new Rectangle(world, GenericScreen.WUNIT * 2.5f, GenericScreen.HUNIT * 9, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, /*1.5707f*/ (float) ((Math.PI * 2) / 360 * 90));
        rect2 = new Rectangle(world, GenericScreen.WUNIT * 1.2f, GenericScreen.HUNIT * 8.15f, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, 2.3f);

        rect3 = new Rectangle(world, GenericScreen.WUNIT * 7.5f, GenericScreen.HUNIT * 9, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, /*1.5707f*/ (float) ((Math.PI * 2) / 360 * 90));
        rect4 = new Rectangle(world, GenericScreen.WUNIT * 8.8f, GenericScreen.HUNIT * 8.15f, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, -2.3f);

        portal = new Portal(world, GenericScreen.WUNIT * 5f, GenericScreen.HUNIT * 8.5f, GenericScreen.HUNIT);
    }

    @Override
    public void destroyLevel() {
    }

    @Override
    public void act() {
        if (colliding) {
            ball.getBody().applyForce((portal.getBody().getPosition().x - ball.getBody().getPosition().x) * i,
                    (portal.getBody().getPosition().y - ball.getBody().getPosition().y) * i,
                    ball.getBody().getPosition().x, ball.getBody().getPosition().y, true);

            i++;
        } else
            i = 10;
    }

    @Override
    public void setColliding(boolean bool) {
        this.colliding = bool;
    }

    @Override
    public boolean isColliding() {
        return colliding;
    }
}
