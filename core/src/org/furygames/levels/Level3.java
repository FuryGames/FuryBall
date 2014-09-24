package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.*;
import org.furygames.screens.GenericScreen;

public class Level3 extends GenericLevel {

    private Ball ball;
    private Portal portal;
    private BlackHole blackHole;
    private Rectangle rect;
    private Triangle tri;
    private Rectangle rect1;
    private Rectangle rect2;
    private Rectangle rect3;
    private Rectangle rect4;
    private Rectangle rect5;
    private Rectangle rect6;
    private int i;
    private boolean collidingPortal;
    private boolean win;
    private boolean collidingBlackHole;

    public Level3(World world) {
        super(world);

        i = 10;

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 1, GenericScreen.HUNIT / 2);

        tri = new Triangle(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 3, (float) Math.PI);

        rect1 = new Rectangle(world, GenericScreen.WUNIT * 2.5f, GenericScreen.HUNIT * 9, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, (float) ((Math.PI * 2) / 360 * 90));
        rect2 = new Rectangle(world, GenericScreen.WUNIT * 1.2f, GenericScreen.HUNIT * 8.15f, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, 2.3f);

        rect3 = new Rectangle(world, GenericScreen.WUNIT * 7.5f, GenericScreen.HUNIT * 9, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, (float) ((Math.PI * 2) / 360 * 90));
        rect4 = new Rectangle(world, GenericScreen.WUNIT * 8.8f, GenericScreen.HUNIT * 8.15f, GenericScreen.WUNIT * 0.3f, GenericScreen.HUNIT * 1.5f, -2.3f);

        rect5 = new Rectangle(world, GenericScreen.WUNIT * 4.02f, GenericScreen.HUNIT * 4.3f, GenericScreen.WUNIT * 0.2f, GenericScreen.HUNIT * 1.5f, -2.3f);
        rect6 = new Rectangle(world, GenericScreen.WUNIT * 5.98f, GenericScreen.HUNIT * 4.3f, GenericScreen.WUNIT * 0.2f, GenericScreen.HUNIT * 1.5f, 2.3f);

        portal = new Portal(world, GenericScreen.WUNIT * 5f, GenericScreen.HUNIT * 8.5f, GenericScreen.HUNIT);

        blackHole = new BlackHole(world, GenericScreen.WUNIT * 5f, GenericScreen.HUNIT * 4.9f, GenericScreen.HUNIT * 1.2f);
    }

    @Override
    public void destroyLevel() {
    }

    @Override
    public void act() {
        if (collidingPortal) {
            ball.getBody().setLinearVelocity(0, 0);

            ball.getBody().applyForce((portal.getBody().getPosition().x - ball.getBody().getPosition().x) * i,
                    (portal.getBody().getPosition().y - ball.getBody().getPosition().y) * i,
                    ball.getBody().getPosition().x, ball.getBody().getPosition().y, true);

            if (portal.getBody().getPosition().x - ball.getBody().getPosition().x == 0.0f)
                setWin(true);

            i++;
        } else if (collidingBlackHole) {
            ball.getBody().setLinearVelocity(0, 0);

            ball.getBody().applyForce((blackHole.getBody().getPosition().x - ball.getBody().getPosition().x) * i,
                    (blackHole.getBody().getPosition().y - ball.getBody().getPosition().y) * i,
                    ball.getBody().getPosition().x, ball.getBody().getPosition().y, true);

            if (blackHole.getBody().getPosition().x - ball.getBody().getPosition().x == 0.0f)
                win = false;

            i++;
        } else
            i = 10;
    }

    @Override
    public void setCollidingPortal(boolean bool) {
        this.collidingPortal = bool;
    }

    @Override
    public boolean isCollidingPortal() {
        return collidingPortal;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isCollidingBlackHole() {
        return collidingBlackHole;
    }

    public void setCollidingBlackHole(boolean collidingBlackHole) {
        this.collidingBlackHole = collidingBlackHole;
    }
}