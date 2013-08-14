package no.jts.android.simple.gameapi.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import no.jts.android.simple.gameapi.graphics.Sprite;

public class DrawUtil {

    public static void drawCircle(Canvas canvas, Paint paint, Body body, CircleShape shape){
		paint.setColor(Color.GREEN);
		Vec2 position = body.getPosition();
		
		float pixelsX = MetersToPixelsUtil.convertPositionX(position);
		float pixelsY = MetersToPixelsUtil.convertPositionY(position);
		float radius = MetersToPixelsUtil.convertRadius(shape.getRadius());
		
		canvas.drawCircle(pixelsX, pixelsY, radius, paint);

		float angle = body.getAngle();
		float x = (float)(radius * Math.cos(angle));
		float y = -(float)(radius * Math.sin(angle));
		Vec2 vec2 = new Vec2(x + pixelsX, y + pixelsY);

		paint.setColor(Color.RED);
		canvas.drawLine(pixelsX, pixelsY, vec2.x, vec2.y, paint);
	}

	public static void drawPolygon(Canvas canvas, Paint paint, Body body, PolygonShape shape, Object userData){
        if(userData != null) {
            if(userData instanceof Sprite){
                Sprite sprite = (Sprite)userData;
                Bitmap bitmap = sprite.getBitmap();

                Matrix matrix = transform(bitmap, body.getPosition(), body.getAngle());
                canvas.drawBitmap(bitmap, matrix, null);
            }
        }

        paint.setColor(Color.GREEN);

        Vec2[] pixels = MetersToPixelsUtil.convertPolygon(shape.getVertices(), shape.getVertexCount(), body.getPosition(), body.getAngle());
        int length = pixels.length;
		if(length == 1){
			canvas.drawPoint(pixels[0].x, pixels[0].y, paint);
		} else if (length > 1){
			for(int i=1; i<length; i++){
				canvas.drawLine(pixels[i-1].x, pixels[i-1].y, pixels[i].x, pixels[i].y, paint);
			}
			int last = length - 1;
			canvas.drawLine(pixels[last].x, pixels[last].y, pixels[0].x, pixels[0].y, paint);
		}
	}

    private static Matrix transform(Bitmap bitmap, Vec2 position, float angle) {
        float x = MetersToPixelsUtil.convertPositionX(position);
        float y = MetersToPixelsUtil.convertPositionY(position);
        float degrees = -1 * (float)Math.toDegrees(angle);

        Matrix matrix = new Matrix();
        matrix.postTranslate(-bitmap.getWidth() / 2f, -bitmap.getHeight() / 2f); // Centers image
        matrix.postRotate(degrees);
        matrix.postTranslate(x, y);
        return matrix;
    }

}
