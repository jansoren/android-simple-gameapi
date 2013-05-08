package no.jts.android.simple.gameapi.physics;

import org.jbox2d.collision.shapes.PolygonShape;
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
	public static Vec2[] convertPolygon(PolygonShape shape, Vec2 position, float angle){
		Vec2[] meters = transform(shape, angle);
		Vec2[] pixels = convertVectors(shape, position, meters);
		return pixels;
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

	private static Vec2[] convertVectors(PolygonShape shape, Vec2 position, Vec2[] meters) {
		int size = shape.getVertexCount();
		Vec2[] pixels = new Vec2[size];
		for(int i=0; i < size; i++){
			pixels[i] = convertVector(meters[i], position);
		}
		return pixels;
	}

	private static Vec2[] transform(PolygonShape shape, float angle) {
		int size = shape.getVertexCount();
		Vec2[] meters = new Vec2[size];
		Transform transform = new Transform();
		transform.set(new Vec2(0, 0), angle);
		for (int i = 0; i < size; ++i){
			meters[i] = Transform.mul(transform, shape.getVertex(i));
		}
		return meters;
	}

	private static Vec2 convertVector(Vec2 meters, Vec2 position){
		float x = convertPositionX(position) + getPixels(meters.x);
		float y = convertPositionY(position) + (-1 * getPixels(meters.y));
		return new Vec2(x, y);
	}

	private static float getPixels(float meters){
		return meters * WorldGlobals.mtp_ratio;
	}

}
