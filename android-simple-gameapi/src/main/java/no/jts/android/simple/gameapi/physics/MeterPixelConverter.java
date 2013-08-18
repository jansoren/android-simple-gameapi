package no.jts.android.simple.gameapi.physics;

import no.jts.android.simple.gameapi.graphics.Point;
import no.jts.android.simple.gameapi.setup.Globals;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class MeterPixelConverter {

	public static float getPixelsRadius(float meters) {
		return meters * WorldGlobals.mtp_ratio;
	}
	
	public static float getMeters(float pixels){
        return (pixels / 2f) / WorldGlobals.mtp_ratio;
	}
	
	public static Point getPixels(Vec2 meters){
		float x = (meters.x * WorldGlobals.mtp_ratio) + (Globals.displayWidth / 2f);
		float y = (-meters.y * WorldGlobals.mtp_ratio) + (Globals.displayHeight / 2f);
		return new Point(x, y);
	}

	public static Vec2 getMeters(Point pixels) {
		float x = (pixels.x / WorldGlobals.mtp_ratio) - (WorldGlobals.worldWidth / 2f);
		float y = -(pixels.y / WorldGlobals.mtp_ratio) + (WorldGlobals.worldHeight / 2f);
		return new Vec2(x, y);
	}
	
	/**
	 * Converts meters to pixels for polygon vectors
	 * @param vertices
     * @param vertexCount
     * @param position
	 * @param angle
	 * @return positions in pixels
	 */
	public static Point[] convertPolygon(Vec2[] vertices, int vertexCount, Vec2 position, float angle){
		Vec2[] meters = transform(vertices, vertexCount, angle);
		Point[] pixels = convertVectors(meters, vertexCount);
		Point[] positionedPixels = updatePosition(pixels, vertexCount, position);
		return positionedPixels;
	}
	
	private static Vec2[] transform(Vec2[] vertices, int vertexCount, float angle) {
		Vec2[] meters = new Vec2[vertexCount];
		Transform transform = new Transform();
		transform.set(new Vec2(0, 0), angle);
		for (int i = 0; i < vertexCount; ++i){
			meters[i] = Transform.mul(transform, vertices[i]);
		}
		return meters;
	}

		
	private static Point[] convertVectors(Vec2[] meters, int vertexCount) {
		Point[] pixels = new Point[vertexCount];
		for(int i=0; i < vertexCount; i++){
			pixels[i] = convertVector(meters[i]);
		}
		return pixels;
	}
	
	private static Point convertVector(Vec2 meters){
		float x = (meters.x * WorldGlobals.mtp_ratio);
		float y = (-meters.y * WorldGlobals.mtp_ratio);
		return new Point(x, y);
	}
	
	/*
	private static Vec2 convertVector(Vec2 meters){
		float x = getPixels(meters.x);
		float y = (-1 * getPixels(meters.y));
		return new Vec2(x, y);
	}*/
	
	private static Point[] updatePosition(Point[] pixels, int vertexCount, Vec2 position) {
		for(int i=0; i < vertexCount; i++){
			Point point = getPixels(position);
			pixels[i].x = pixels[i].x + point.x;
			pixels[i].y = pixels[i].y + point.y;
		}
		return pixels;
	}
}
