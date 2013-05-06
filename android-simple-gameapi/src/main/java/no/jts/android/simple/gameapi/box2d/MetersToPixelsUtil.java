package no.jts.android.simple.gameapi.box2d;

import org.jbox2d.common.Vec2;

import android.util.Log;

public class MetersToPixelsUtil {
	
	private static final String TAG = "MetersToPixelsUtil";
	
	public static Vec2[] metersToPixels(Vec2[] meters, float mtp_ratio, int displayCenterX, int displayCenterY){
		Vec2[] pixels = new Vec2[meters.length];
		for(int i=0; i < meters.length; i++){
			pixels[i] = metersToPixels(meters[i], mtp_ratio, displayCenterX, displayCenterY);
		}
		return pixels;
	}
	
	public static Vec2 metersToPixels(Vec2 meters, float mtp_ratio, int displayCenterX, int displayCenterY){
		float x = (meters.x * mtp_ratio) + displayCenterX + 1;
		float revertedY = ((-1 * meters.y) * mtp_ratio) + displayCenterY; // revert y to convert from box2d coordinatesystem to display coordinatesystem
		Vec2 pixels = new Vec2(x, revertedY);
		//Log.i(TAG, "x - meters: " + meters.x + " pixels: " + pixels.x);
		//Log.i(TAG, "y - meters: " + meters.y + " pixels: " + pixels.y);
		return pixels;
	}
		
	public static void log(Vec2[] vec2){
		Log.i(TAG, "X - left: " + vec2[0].x + " right: " + vec2[1].x );
		Log.i(TAG, "Y - bottom: " + vec2[0].y + " top: " + vec2[1].y );
	}
}
