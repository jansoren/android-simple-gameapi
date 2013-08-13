package no.jts.android.simple.gameapi.physics;

import org.jbox2d.common.Vec2;

public class PixelsToMetersUtil {

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
