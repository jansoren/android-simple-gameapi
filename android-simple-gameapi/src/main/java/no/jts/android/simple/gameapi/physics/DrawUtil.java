package no.jts.android.simple.gameapi.physics;

import no.jts.android.simple.gameapi.graphics.Point;
import no.jts.android.simple.gameapi.graphics.Sprite;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawUtil {

	public static void drawCircle(Canvas canvas, Paint paint, Body body, CircleShape shape, Object userData, boolean isDebug){
		if(userData != null) {
			if(userData instanceof Sprite){
				Sprite sprite = (Sprite)userData;
				sprite.update(canvas, body.getPosition(), body.getAngle());
				sprite.draw(canvas);
			}
		}
		if(isDebug){
			paint.setColor(Color.GREEN);
			paint.setStyle(Paint.Style.STROKE);
	
			Vec2 position = body.getPosition();
	
			Point pixels = MeterPixelConverter.getPixels(position);
			float radius = MeterPixelConverter.getPixelsRadius(shape.getRadius());
	
			//canvas.drawCircle(pixels.x, pixels.y, radius, paint);
	
			float angle = body.getAngle();
			float x = (float)(radius * Math.cos(angle));
			float y = -(float)(radius * Math.sin(angle));
			Point pixels2 = new Point(x + pixels.x, y + pixels.y);
	
			paint.setColor(Color.RED);
			canvas.drawLine(pixels.x, pixels.y, pixels2.x, pixels2.y, paint);
		}
	}

	public static void drawPolygon(Canvas canvas, Paint paint, Body body, PolygonShape shape, Object userData, boolean isDebug){
		if(userData != null) {
			if(userData instanceof Sprite){
				Sprite sprite = (Sprite)userData;
				sprite.update(canvas, body.getPosition(), body.getAngle());
				sprite.draw(canvas);
			}
		}
		if(isDebug){
			paint.setColor(Color.GREEN);

			Point[] pixels = MeterPixelConverter.convertPolygon(shape.getVertices(), shape.getVertexCount(), body.getPosition(), body.getAngle());
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
}
