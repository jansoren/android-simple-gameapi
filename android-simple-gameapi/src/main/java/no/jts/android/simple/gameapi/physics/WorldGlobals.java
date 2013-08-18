package no.jts.android.simple.gameapi.physics;

import no.jts.android.simple.gameapi.setup.Globals;

import org.jbox2d.common.Vec2;

public class WorldGlobals {
	public static float mtp_ratio; // meter to pixel ratio
	public static float worldHeight;
	public static float worldWidth;
	public static Vec2 worldButtomLeft;
	public static Vec2 worldTopLeft;
	public static Vec2 worldTopRight;
	
	public static void init(float worldSize){
		mtp_ratio = getPtmRatio(worldSize);
		worldHeight = getWorldHeight(mtp_ratio);
		worldWidth = getWorldWidth(mtp_ratio);
		worldButtomLeft = new Vec2(-worldWidth / 2.0f, -worldHeight / 2.0f);
		worldTopLeft = new Vec2(-worldWidth / 2.0f, worldHeight / 2.0f);
		worldTopRight = new Vec2(WorldGlobals.worldWidth / 2.0f, worldHeight / 2.0f);
	}

	private static float getPtmRatio(float worldSize) {
		if(Globals.displayHeight > Globals.displayWidth){
			return (Globals.displayHeight / worldSize);
		} else {
			return (Globals.displayWidth / worldSize);
		}	    
	}

	private static float getWorldHeight(float mtp_ratio) {
		return Globals.displayHeight / mtp_ratio;
	}

	private static float getWorldWidth(float mtp_ratio) {
		return Globals.displayWidth / mtp_ratio;
	}

}
