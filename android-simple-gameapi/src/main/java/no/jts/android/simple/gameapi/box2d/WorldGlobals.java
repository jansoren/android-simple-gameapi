package no.jts.android.simple.gameapi.box2d;

import no.jts.android.simple.gameapi.setup.Globals;

public class WorldGlobals {
	public static int displayCenterX;
	public static int displayCenterY;
	public static float mtp_ratio; // meter to pixel ratio
	public static float worldHeight = 10.0f;
	public static float worldWidth = 6.0f;

	public static void init(){
		mtp_ratio = getPtmRatio();
		displayCenterX = Globals.displayWidth / 2;
	    displayCenterY = Globals.displayHeight / 2;
	}
	
	private static float getPtmRatio() {
	    /*
		if(Globals.displayHeight > Globals.displayWidth){
	    	return (Globals.displayHeight / WORLD_SIZE);
	    } else {
	    	return (Globals.displayWidth / WORLD_SIZE);
	    }*/
		return (Globals.displayHeight / worldHeight) / 2;	    
	}
}
