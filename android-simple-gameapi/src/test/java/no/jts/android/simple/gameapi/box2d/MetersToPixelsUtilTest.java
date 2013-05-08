package no.jts.android.simple.gameapi.box2d;

import static org.junit.Assert.assertEquals;

import no.jts.android.simple.gameapi.physics.MetersToPixelsUtil;
import no.jts.android.simple.gameapi.physics.WorldGlobals;

import org.jbox2d.common.Vec2;
import org.junit.Before;
import org.junit.Test;

public class MetersToPixelsUtilTest {

	@Before
	public void setup(){
		int displayWidth = 480;
		int displayHeight = 800;
		
		WorldGlobals.displayCenterX = (displayWidth / 2);
		WorldGlobals.displayCenterY = (displayHeight / 2);
		WorldGlobals.worldHeight = 10;
		WorldGlobals.worldWidth = 6;
		WorldGlobals.mtp_ratio = (displayHeight / WorldGlobals.worldHeight) / 2;
	}
	/*
	@Test
	public void testShouldConvertMetersOriginToPixels(){
		Vec2 meters = new Vec2(0,0);
		Vec2 pixels = MetersToPixelsUtil.metersToPixels(meters, null);
		assertEquals(240, pixels.x, 0.1);
		assertEquals(400, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersLeftToPixels(){
		Vec2 meters = new Vec2(-WorldGlobals.worldWidth,0);
		Vec2 pixels = MetersToPixelsUtil.metersToPixels(meters, null);
		assertEquals(0, pixels.x, 0.1);
		assertEquals(400, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersBottomToPixels(){
		Vec2 meters = new Vec2(0,-WorldGlobals.worldHeight);
		Vec2 pixels = MetersToPixelsUtil.metersToPixels(meters, null);
		assertEquals(240, pixels.x, 0.1);
		assertEquals(800, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersRightToPixels(){
		Vec2 meters = new Vec2(WorldGlobals.worldWidth,0);
		Vec2 pixels = MetersToPixelsUtil.metersToPixels(meters, null);
		assertEquals(480, pixels.x, 0.1);
		assertEquals(400, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersTopToPixels(){
		Vec2 meters = new Vec2(0, WorldGlobals.worldHeight);
		Vec2 pixels = MetersToPixelsUtil.metersToPixels(meters, null);
		assertEquals(240, pixels.x, 0.1);
		assertEquals(0, pixels.y, 0.1);
	}
	*/
}
