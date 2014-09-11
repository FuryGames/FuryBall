package org.furygames.inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by writkas on 11/09/14.
 */
public class GravityInput extends InputAdapter {

    public GravityInput() {
        super();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                VirtualController.setGUp(true);
                break;
            case Input.Keys.X:
                VirtualController.setGDown(true);
                break;
            case Input.Keys.A:
                VirtualController.setGLeft(true);
                break;
            case Input.Keys.D:
                VirtualController.setGRight(true);
                break;
            case Input.Keys.S:
                VirtualController.setgNeutral(true);
                break;
        }

        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                VirtualController.setGUp(false);
                break;
            case Input.Keys.X:
                VirtualController.setGDown(false);
                break;
            case Input.Keys.A:
                VirtualController.setGLeft(false);
                break;
            case Input.Keys.D:
                VirtualController.setGRight(false);
                break;
            case Input.Keys.S:
                VirtualController.setgNeutral(false);
                break;
        }

        return super.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return super.keyTyped(character);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {
        return super.scrolled(amount);
    }
}
