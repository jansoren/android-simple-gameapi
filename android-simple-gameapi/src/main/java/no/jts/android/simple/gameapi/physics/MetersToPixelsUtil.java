package no.jts.android.simple.gameapi.physics;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class MetersToPixelsUtil {

	/**
	 * Converts meters to pixels for polygon vectors
	 * @param shape
	 * @param position
	 * @param angle
	 * @return
	 */
	public static Vec2[] convertPolygon(Vec2[] vertices, int vertexCount, Vec2 position, float angle){
		Vec2[] meters = transform(vertices, vertexCount, angle);
		Vec2[] pixels = convertVectors(meters, vertexCount);
		Vec2[] positionedPixels = updatePosition(pixels, vertexCount, position);
		return positionedPixels;
	}

	/**
	 * Converts meters to pixels for radius
	 * @param shape
	 * @return
	 */
	public static float convertRadius(float radius) {
		return getPixels(radius);
	}

	/**
	 * Converts meters to pixels for position X
	 * @param position
	 * @return
	 */
	public static float convertPositionX(Vec2 position) {
		return getPixels(position.x) + WorldGlobals.displayCenterX ;
	}

	/**
	 * Converts meters to pixels for position Y
	 * @param position 
	 * @return
	 */
	public static float convertPositionY(Vec2 position) {
		return ( -1 * getPixels(position.y) ) + WorldGlobals.displayCenterY;
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

	private static Vec2[] convertVectors(Vec2[] meters, int vertexCount) {
		Vec2[] pixels = new Vec2[vertexCount];
		for(int i=0; i < vertexCount; i++){
			pixels[i] = convertVector(meters[i]);
		}
		return pixels;
	}
	
	private static Vec2 convertVector(Vec2 meters){
		float x = getPixels(meters.x);
		float y = (-1 * getPixels(meters.y));
		return new Vec2(x, y);
	}
	
	private static Vec2[] updatePosition(Vec2[] pixels, int vertexCount, Vec2 position) {
		for(int i=0; i < vertexCount; i++){
			pixels[i].x = pixels[i].x + convertPositionX(position);
			pixels[i].y = pixels[i].y + convertPositionY(position);
		}
		return pixels;
	}
	
	private static float getPixels(float meters){
		return meters * WorldGlobals.mtp_ratio;
	}

}
