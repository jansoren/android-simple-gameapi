package no.jts.android.simple.gameapi.box2d;

import org.jbox2d.common.Vec2;

import android.util.Log;

public class MetersToPixelsUtil {
	
	private static final String TAG = "MetersToPixelsUtil";
	
	public static Vec2[] metersToPixels(Vec2[] meters, int size){
		Vec2[] pixels = new Vec2[size];
		for(int i=0; i < size; i++){
			pixels[i] = metersToPixels(meters[i]);
		}
		return pixels;
	}
	
	public static Vec2 metersToPixels(Vec2 meters){
		float x = (meters.x * WorldGlobals.mtp_ratio) + WorldGlobals.displayCenterX;
		float y = (-1 * meters.y * WorldGlobals.mtp_ratio) + WorldGlobals.displayCenterY; 
		return new Vec2(x, y);
	}
		
	public static void log(Vec2[] vec2){
		Log.i(TAG, "X - left: " + vec2[0].x + " right: " + vec2[1].x );
		Log.i(TAG, "Y - bottom: " + vec2[0].y + " top: " + vec2[1].y );
	}
}
