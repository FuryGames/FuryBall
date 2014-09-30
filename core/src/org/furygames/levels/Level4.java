package org.furygames.levels;

import com.badlogic.gdx.physics.box2d.World;

public class Level4 extends GenericLevel {

    private boolean win;

    public Level4(World world) {
        super(world);
        // Crear objetos
    }

    @Override
    public void destroyLevel() {

    }

    @Override
    public void act() {
        System.out.println("level4 xd");
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
