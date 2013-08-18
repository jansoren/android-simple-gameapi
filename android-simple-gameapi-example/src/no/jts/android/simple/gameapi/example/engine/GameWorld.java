package no.jts.android.simple.gameapi.example.engine;

import no.jts.android.simple.gameapi.graphics.Point;
import no.jts.android.simple.gameapi.physics.MeterPixelConverter;
import no.jts.android.simple.gameapi.physics.PhysicsWorld;

import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class GameWorld extends PhysicsWorld {

	public GameWorld(float worldSize, float gravityX, float gravityY,	Paint paint) {
		super(worldSize, gravityX, gravityY, paint);
	
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Vec2 touchPoint = MeterPixelConverter.getMeters(new Point(event.getX(), event.getY()));
		Log.w("TAG", "x:" + touchPoint.x + " y:" + touchPoint.y );
		
		Body body = world.getBodyList();
		while(body != null){
			Fixture fixture = body.getFixtureList();
			while(fixture != null){
				ShapeType type = fixture.getType();
				if(type == ShapeType.CIRCLE){
					Log.w("TAG", "body x:" + body.getPosition().x + " body y:" + body.getPosition().y);
				}
				
				if(fixture.testPoint(touchPoint)){
					body.setActive(false);
					world.destroyBody(body);
				}

				
				fixture = fixture.getNext();
			}
			body = body.getNext();
		}		
		return true;
	}

}
