package org.furygames.inputs;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by scanevaro on 11/09/2014.
 */
public class GestureInput implements GestureDetector.GestureListener {
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        VirtualController.setgNeutral(true);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        VirtualController.setAll(false);
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        float movX = x + deltaX;
        float movY = y - deltaY;

        float direccionX = movX - x;
        float direccionY = movY - y;

        VirtualController.setForce(true);
        VirtualController.setgForce(direccionX, direccionY);

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }
}