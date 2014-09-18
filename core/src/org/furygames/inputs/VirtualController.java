package org.furygames.inputs;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by writkas on 11/09/14.
 */
public class VirtualController {
    // Gravity states
    private static boolean gNeutral = false;

    public static boolean isForce() {
        return force;
    }

    public static void setForce(boolean force) {
        VirtualController.force = force;
    }

    private static boolean force = false;

    public static Vector2 getgForce() {
        return gForce;
    }

    public static void setgForce(float x, float y) {
        VirtualController.gForce.set(x, y);
    }

    private static Vector2 gForce = new Vector2();

    public static void setAll(boolean bool) {
        gNeutral = !bool;
        force = bool;
    }

    public static boolean isgNeutral() {
        return gNeutral;
    }

    public static void setgNeutral(boolean gNeutral) {
        VirtualController.gNeutral = gNeutral;
    }
}