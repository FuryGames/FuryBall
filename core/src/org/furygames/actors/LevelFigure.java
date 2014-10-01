package org.furygames.actors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import net.dermetfan.utils.libgdx.graphics.Box2DSprite;
import org.furygames.screens.GenericScreen;

/**
 * Created by writkas on 25/09/14.
 */
public class LevelFigure extends GenericFigure implements InputProcessor {

    private PolygonShape rect;
    private Body hitBody = null;
    private Camera camera = null;

    public LevelFigure (World world, int numLevel, float posX, float posY, float size) {
        super(world);

        switch (numLevel) {
            case 1:
                super.setImg("buttons/levels/level-1.png");
                break;
            case 2:
                super.setImg("buttons/levels/level-2.png");
                break;
            case 3:
                super.setImg("buttons/levels/level-3.png");
                break;
            case 4:
                super.setImg("buttons/levels/level-4.png");
                break;
            case 5:
                super.setImg("buttons/levels/level-5.png");
                break;
            case 6:
                super.setImg("buttons/levels/level-6.png");
                break;
            case 7:
                super.setImg("buttons/levels/level-7.png");
                break;
            case 8:
                super.setImg("buttons/levels/level-8.png");
                break;
        }

        super.setX(posX);
        super.setY(posY);
        super.setWidth(size);
        super.setHeight(size);

        sprite.setSize(GenericScreen.WUNIT, GenericScreen.HUNIT);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        bodyDef.position.set(posX, posY);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        rect = new PolygonShape();
        rect.setAsBox(size, size);
        fixtureDef.shape = rect;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(new Box2DSprite(sprite));

        rect.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    Vector3 testPoint = new Vector3();
    QueryCallback callback = new QueryCallback() {
        @Override
        public boolean reportFixture (Fixture fixture) {
        // if the hit point is inside the fixture of the body
        // we report it
            if (fixture.testPoint(testPoint.x, testPoint.y)) {
                hitBody = fixture.getBody();
                return false;
            } else
                return true;
        }
    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // translate the mouse coordinates to world coordinates
        if (camera != null)
            camera.unproject(testPoint.set(screenX, screenY, 0));
        // ask the world which bodies are within the given
        // bounding box around the mouse pointer
        hitBody = null;
        world.QueryAABB(callback, testPoint.x - 0.0001f, testPoint.y - 0.0001f,
                testPoint.x + 0.0001f, testPoint.y + 0.0001f);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
