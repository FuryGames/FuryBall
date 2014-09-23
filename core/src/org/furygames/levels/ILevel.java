package org.furygames.levels;

public interface ILevel {
    public void destroyLevel(); // Destruir nivel

    public void act(); // Interacciones del nivel

    public void setCollidingPortal(boolean bool);

    public boolean isCollidingPortal();
}
