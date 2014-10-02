package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;
import org.furygames.actors.Ball;
import org.furygames.actors.Portal;
import org.furygames.actors.Wall;
import org.furygames.screens.GenericScreen;

public class Level1 extends GenericLevel {

    private Ball ball;
    private Wall walls[];
    private Portal portal;
    private boolean collidingPortal;
    private boolean win;
    private int i;

    public Level1(World world) {
        super(world);

        walls = new Wall[5];

        portal = new Portal(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 8 + .3f,
                GenericScreen.HUNIT);

        ball = new Ball(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 1,
                GenericScreen.HUNIT / 2);

        // Centro
        walls[0] = new Wall(world, GenericScreen.WUNIT * 5, GenericScreen.HUNIT * 5,
                GenericScreen.WUNIT / 3, GenericScreen.HUNIT * 2);
        // Arriba izquierda
        walls[1] = new Wall(world, GenericScreen.WUNIT * 3, GenericScreen.HUNIT * 7 - .35f,
                GenericScreen.WUNIT * 1.655f, GenericScreen.HUNIT / 2);
        // Arriba derecha
        walls[2] = new Wall(world, GenericScreen.WUNIT * 7, GenericScreen.HUNIT * 7 - .35f,
                GenericScreen.WUNIT * 1.655f, GenericScreen.HUNIT / 2);
        // Abajo derecha
        walls[3] = new Wall(world, GenericScreen.WUNIT * 1 + .2f, GenericScreen.HUNIT * 3,
                GenericScreen.WUNIT, GenericScreen.HUNIT / 2);
        // Abajo izquierda
        walls[4] = new Wall(world, GenericScreen.WUNIT * 9 - .2f, GenericScreen.HUNIT * 3,
                GenericScreen.WUNIT, GenericScreen.HUNIT / 2);
    }

    @Override
    public void destroyLevel() {
        //  TODO
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
        }
    }

    @Override
    public void setCollidingPortal(boolean bool) {
        this.collidingPortal = bool;
    }

    @Override
    public boolean isCollidingPortal() {
        return collidingPortal;
    }

    @Override
    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
