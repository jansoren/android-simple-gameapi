package no.jts.android.simple.gameapi.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import android.graphics.Canvas;
import android.graphics.Paint;


public class PhysicsWorld {

	private World world;
	private Paint paint;
	private int velocityIterations = 6;
	private int positionIterations = 2;

	public PhysicsWorld(float worldSize, float gravityX, float gravityY, Paint paint){
		WorldGlobals.init(worldSize);
		Vec2 gravity = new Vec2(gravityX, gravityY);
		this.world = new World(gravity);
		this.paint = paint;
		
		ShapeUtil.addGround(world, 5, 0.1f);
		ShapeUtil.addWall(world, -1.5f, 1.5f, 1, 1);
		ShapeUtil.addWall(world, -1.5f, 5.5f, 1, 1);
		ShapeUtil.addPolygon(world, 0, 4);
		ShapeUtil.addBall(world, 1, 0, 1);
		ShapeUtil.addBall(world, -1, 5, 0.5f);
	}

	public void update(float deltaTime){
		world.step(deltaTime, velocityIterations, positionIterations);
	}

	public void draw(Canvas canvas){
		Body body = world.getBodyList();
		while(body != null){
			Fixture fixture = body.getFixtureList();
			while(fixture != null){
				ShapeType type = fixture.getType();
				if(type == ShapeType.POLYGON){
					DrawUtil.drawPolygon(canvas, paint, body, (PolygonShape)fixture.getShape());
				}else if(type == ShapeType.CIRCLE){
					DrawUtil.drawCircle(canvas, paint, body, (CircleShape)fixture.getShape());
				}
				fixture = fixture.getNext();
			}
			body = body.getNext();
		}		
	}

	public void setVelocityIterations(int velocityIterations) {
		this.velocityIterations = velocityIterations;
	}

	public void setPositionIterations(int positionIterations) {
		this.positionIterations = positionIterations;
	}

	
}
