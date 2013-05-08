package no.jts.android.simple.gameapi.physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class MetersToPixelsUtil {

	public static Vec2[] metersToPixels(PolygonShape shape, Body body){
		Transform transform = new Transform();
		transform.set(new Vec2(0, 0), body.getAngle());
		int size = shape.getVertexCount();
		Vec2[] meters = new Vec2[size];
		for (int i = 0; i < size; ++i){
			meters[i] = Transform.mul(transform, shape.getVertex(i));
		}

		Vec2[] pixels = new Vec2[size];
		for(int i=0; i < size; i++){
			pixels[i] = metersToPixels(meters[i], body.getPosition());
		}
		return pixels;
	}

	public static Vec2 metersToPixels(Vec2 meters, Vec2 position){
		float x = getPositionX(position) + getPixels(meters.x);
		float y = getPositionY(position) + (-1 * getPixels(meters.y));
		return new Vec2(x, y);
	}

	/**
	 * Converts meters to pixels for radius
	 * @param shape
	 * @return
	 */
	public static float getRadius(float radius) {
		return getPixels(radius);
	}

	/**
	 * Converts meters to pixels for position X
	 * @param position
	 * @return
	 */
	public static float getPositionX(Vec2 position) {
		return getPixels(position.x) + WorldGlobals.displayCenterX ;
	}
	
	/**
	 * Converts meters to pixels for position Y
	 * @param position 
	 * @return
	 */
	public static float getPositionY(Vec2 position) {
		return ( -1 * getPixels(position.y) ) + WorldGlobals.displayCenterY;
	}

	private static float getPixels(float meters){
		return meters * WorldGlobals.mtp_ratio;
	}
	
}
