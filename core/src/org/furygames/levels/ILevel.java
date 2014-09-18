package org.furygames.levels;

public interface ILevel {
    public void destroyLevel(); // Destruir nivel

    public void act(); // Interacciones del nivel

    public void setColliding(boolean bool);

    public boolean isColliding();
}
