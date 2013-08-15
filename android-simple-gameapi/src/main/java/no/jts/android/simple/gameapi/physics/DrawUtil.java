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

    public static void drawCircle(Canvas canvas, Paint paint, Body body, CircleShape shape, Object userData){
        if(userData != null) {
            if(userData instanceof Sprite){
                Sprite sprite = (Sprite)userData;
                sprite.draw(canvas, body.getPosition(), body.getAngle());
            }
        }

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

		Vec2 position = body.getPosition();
		
		float pixelsX = MetersToPixelsUtil.convertPositionX(position);
		float pixelsY = MetersToPixelsUtil.convertPositionY(position);
		float radius = MetersToPixelsUtil.convertRadius(shape.getRadius());
		
		//canvas.drawCircle(pixelsX, pixelsY, radius, paint);

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
                sprite.draw(canvas, body.getPosition(), body.getAngle());
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
}
