package no.jts.android.simple.gameapi.physics;

import no.jts.android.simple.gameapi.setup.Globals;

public class WorldGlobals {
	public static int displayCenterX;
	public static int displayCenterY;
	public static float mtp_ratio; // meter to pixel ratio
	public static float worldHeight = 10.0f;
	public static float worldWidth = 6.0f;

	public static void init(float worldSize){
		mtp_ratio = getPtmRatio(worldSize);
		displayCenterX = Globals.displayWidth / 2;
		displayCenterY = Globals.displayHeight / 2;
		worldHeight = getWorldHeight(mtp_ratio);
		worldWidth = getWorldWidth(mtp_ratio);
	}

	private static float getPtmRatio(float worldSize) {
		if(Globals.displayHeight > Globals.displayWidth){
			return (Globals.displayHeight / worldSize) / 2;
		} else {
			return (Globals.displayWidth / worldSize) / 2;
		}	    
	}

	private static float getWorldHeight(float mtp_ratio) {
		return Globals.displayHeight / mtp_ratio;
	}

	private static float getWorldWidth(float mtp_ratio) {
		return Globals.displayWidth / mtp_ratio;
	}

}
