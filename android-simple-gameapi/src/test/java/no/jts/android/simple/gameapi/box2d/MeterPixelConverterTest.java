package no.jts.android.simple.gameapi.box2d;

import static org.junit.Assert.assertEquals;
import no.jts.android.simple.gameapi.graphics.Point;
import no.jts.android.simple.gameapi.physics.MeterPixelConverter;
import no.jts.android.simple.gameapi.physics.WorldGlobals;
import no.jts.android.simple.gameapi.setup.Globals;

import org.jbox2d.common.Vec2;
import org.junit.Before;
import org.junit.Test;

public class MeterPixelConverterTest {

	@Before
	public void setup(){
		Globals.displayWidth  = 480;
		Globals.displayHeight = 800;
		WorldGlobals.init(10f);
	}
	
	@Test
	public void testShouldConvertMetersOriginToPixels(){
		Vec2 meters = new Vec2(0,0);
		Point pixels = MeterPixelConverter.getPixels(meters);
		assertEquals(240, pixels.x, 0.1);
		assertEquals(400, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertPixelsOriginToMeters(){
		Point pixels = new Point(0,0);
		Vec2 meters = MeterPixelConverter.getMeters(pixels);
		assertEquals(-3, meters.x, 0.1);
		assertEquals(5, meters.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersLeftToPixels(){
		Vec2 meters = new Vec2(-3,0);
		Point pixels = MeterPixelConverter.getPixels(meters);
		assertEquals(0, pixels.x, 0.1);
		assertEquals(400, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertPixelsLeftToMeters(){
		Point pixels = new Point(0,400);
		Vec2 meters = MeterPixelConverter.getMeters(pixels);
		assertEquals(-3, meters.x, 0.1);
		assertEquals(0, meters.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersBottomToPixels(){
		Vec2 meters = new Vec2(0,-5);
		Point pixels = MeterPixelConverter.getPixels(meters);
		assertEquals(240, pixels.x, 0.1);
		assertEquals(800, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertPixelsBottomToMeters(){
		Point pixels = new Point(240, 800);
		Vec2 meters = MeterPixelConverter.getMeters(pixels);
		assertEquals(0, meters.x, 0.1);
		assertEquals(-5, meters.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersRightToPixels(){
		Vec2 meters = new Vec2(3,0);
		Point pixels = MeterPixelConverter.getPixels(meters);
		assertEquals(480, pixels.x, 0.1);
		assertEquals(400, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertPixelsRightToMeters(){
		Point pixels = new Point(480, 400);
		Vec2 meters = MeterPixelConverter.getMeters(pixels);
		assertEquals(3, meters.x, 0.1);
		assertEquals(0, meters.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersTopToPixels(){
		Vec2 meters = new Vec2(0, 5);
		Point pixels = MeterPixelConverter.getPixels(meters);
		assertEquals(240, pixels.x, 0.1);
		assertEquals(0, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertPixelsTopToMeters(){
		Point pixels = new Point(240, 0);
		Vec2 meters = MeterPixelConverter.getMeters(pixels);
		assertEquals(0, meters.x, 0.1);
		assertEquals(5, meters.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersButtomLeftToPixels(){
		Vec2 buttomLeft = WorldGlobals.worldButtomLeft;
		Point pixels = MeterPixelConverter.getPixels(buttomLeft);
		assertEquals(0, pixels.x, 0.1);
		assertEquals(800, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersTopLeftToPixels(){
		Vec2 topLeft = WorldGlobals.worldTopLeft;
		Point pixels = MeterPixelConverter.getPixels(topLeft);
		assertEquals(0, pixels.x, 0.1);
		assertEquals(0, pixels.y, 0.1);
	}
	
	@Test
	public void testShouldConvertMetersTopRightToPixels(){
		Vec2 topLeft = WorldGlobals.worldTopRight;
		Point pixels = MeterPixelConverter.getPixels(topLeft);
		assertEquals(480, pixels.x, 0.1);
		assertEquals(0, pixels.y, 0.1);
	}
	
	
}
