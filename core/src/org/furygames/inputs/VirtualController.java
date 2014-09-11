package org.furygames.inputs;

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

    public static boolean isGUp() {
        return gUp;
    }

    public static void setGUp(boolean gUp) {
        VirtualController.gUp = gUp;
    }

    public static boolean isGDown() {
        return gDown;
    }

    public static void setGDown(boolean gDown) {
        VirtualController.gDown = gDown;
    }

    public static boolean isGLeft() {
        return gLeft;
    }

    public static void setGLeft(boolean gLeft) {
        VirtualController.gLeft = gLeft;
    }

    public static boolean isGRight() {
        return gRight;
    }

    public static void setGRight(boolean gRight) {
        VirtualController.gRight = gRight;
    }

    public static boolean isgNeutral() {
        return gNeutral;
    }

    public static void setgNeutral(boolean gNeutral) {
        VirtualController.gNeutral = gNeutral;
    }
}
