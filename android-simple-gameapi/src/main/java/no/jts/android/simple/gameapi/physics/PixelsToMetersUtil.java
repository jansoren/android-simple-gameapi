package no.jts.android.simple.gameapi.physics;

import org.jbox2d.common.Vec2;

public class PixelsToMetersUtil {

    /**
     * Converts pixels to meters for position X
     * @param x
     * @return position x in pixels
     */
    public static float convertPositionX(float x) {
        return getMeters(x);
    }

    /**
     * Converts pixels to meters for position Y
     * @param y
     * @return position y in pixels
     */
    public static float convertPositionY(float y) {
        return ( -1 * getMeters(y));
    }

    /**
     * Converts pixels to meters for radius
     * @param radius
     * @return radius in meters
     */
    public static float convertRadius(float radius) {
        return getMeters(radius);
    }

    public static float getMeters(float pixels){
        return pixels / WorldGlobals.mtp_ratio;
    }
}
