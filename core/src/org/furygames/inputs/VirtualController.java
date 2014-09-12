package org.furygames.inputs;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by writkas on 11/09/14.
 */
public class VirtualController {
    // Gravity states
    private static boolean gUp = false;
    private static boolean gDown = false;
    private static boolean gLeft = false;
    private static boolean gRight = false;
    private static boolean gNeutral = false;
    private static boolean gUpRight = false;
    private static boolean gUpLeft = false;
    private static boolean gDownRight = false;
    private static boolean gDownLeft = false;

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
        gUp = bool;
        gDown = bool;
        gLeft = bool;
        gRight = bool;
        gNeutral = !bool;
        gUpRight = bool;
        gUpLeft = bool;
        gDownRight = bool;
        gDownLeft = bool;

        force = bool;
    }

    public static boolean isgUp() {
        return gUp;
    }

    public static void setgUp(boolean gUp) {
        VirtualController.gUp = gUp;
    }

    public static boolean isgDown() {
        return gDown;
    }

    public static void setgDown(boolean gDown) {
        VirtualController.gDown = gDown;
    }

    public static boolean isgLeft() {
        return gLeft;
    }

    public static void setgLeft(boolean gLeft) {
        VirtualController.gLeft = gLeft;
    }

    public static boolean isgRight() {
        return gRight;
    }

    public static void setgRight(boolean gRight) {
        VirtualController.gRight = gRight;
    }

    public static boolean isgNeutral() {
        return gNeutral;
    }

    public static void setgNeutral(boolean gNeutral) {
        VirtualController.gNeutral = gNeutral;
    }

    public static boolean isgUpRight() {
        return gUpRight;
    }

    public static void setgUpRight(boolean gUpRight) {
        VirtualController.gUpRight = gUpRight;
    }

    public static boolean isgUpLeft() {
        return gUpLeft;
    }

    public static void setgUpLeft(boolean gUpLeft) {
        VirtualController.gUpLeft = gUpLeft;
    }

    public static boolean isgDownRight() {
        return gDownRight;
    }

    public static void setgDownRight(boolean gDownRight) {
        VirtualController.gDownRight = gDownRight;
    }

    public static boolean isgDownLeft() {
        return gDownLeft;
    }

    public static void setgDownLeft(boolean gDownLeft) {
        VirtualController.gDownLeft = gDownLeft;
    }
}