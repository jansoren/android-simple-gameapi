package no.jts.android.simple.gameapi.box2d;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import android.util.Log;

public class MetersToPixelsUtil {

	private static final String TAG = "MetersToPixelsUtil";

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
			pixels[i] = metersToPixels(meters[i], body);
		}
		return pixels;
	}

	public static Vec2 metersToPixels(Vec2 meters, Body body){
		Vec2 position = body.getPosition();
		float x = ((meters.x + position.x) * WorldGlobals.mtp_ratio) + WorldGlobals.displayCenterX;
		float y = (-1 * (meters.y + position.y) * WorldGlobals.mtp_ratio) + WorldGlobals.displayCenterY; 
		return new Vec2(x, y);
	}

	public static void log(Vec2[] vec2){
		Log.i(TAG, "X - left: " + vec2[0].x + " right: " + vec2[1].x );
		Log.i(TAG, "Y - bottom: " + vec2[0].y + " top: " + vec2[1].y );
	}
}
