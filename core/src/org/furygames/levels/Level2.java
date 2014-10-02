package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public class Level2 extends GenericLevel {

    private boolean win;

    public Level2(World world) {
        super(world);
    }

    @Override
    public void destroyLevel() {
    }

    @Override
    public void act() {
        System.out.println("level2 xd");
    }

    @Override
    public void setCollidingPortal(boolean bool) {

    }

    @Override
    public boolean isCollidingPortal() {
        return false;
    }

    @Override
    public boolean isWin() {
        return win;
    }
}