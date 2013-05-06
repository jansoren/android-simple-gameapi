package no.jts.android.simple.gameapi.box2d;

import org.junit.Before;
import org.junit.Test;

public class MetersToPixelsUtilTest {

	@Before
	public void setup(){
		int displayWidth = 480;
		int displayHeight = 800;
		
		WorldGlobals.centerX = (displayWidth / 2);
		WorldGlobals.centerY = (displayHeight / 2);
		WorldGlobals.worldHeight = 10;
		WorldGlobals.worldWidth = 6;
		WorldGlobals.mtp_ratio = (displayHeight / WorldGlobals.worldHeight);
	}
	
	@Test
	public void testShould(){
		//MetersToPixelsUtil.metersToPixels(meters, mtp_ratio, displayCenterX, displayCenterY);
	}
}
