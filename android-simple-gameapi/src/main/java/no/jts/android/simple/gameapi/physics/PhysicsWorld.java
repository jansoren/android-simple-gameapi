package no.jts.android.simple.gameapi.physics;

import java.util.List;

import no.jts.android.simple.gameapi.graphics.Sprite;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public abstract class PhysicsWorld {

	protected World world;
	private Paint paint;
	private int velocityIterations = 6;
	private int positionIterations = 2;
	private boolean isDebug = true;

	public PhysicsWorld(float worldSize, float gravityX, float gravityY, Paint paint){
		WorldGlobals.init(worldSize);
		Vec2 gravity = new Vec2(gravityX, gravityY);
		this.world = new World(gravity);
		this.paint = paint;
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
					DrawUtil.drawPolygon(canvas, paint, body, (PolygonShape)fixture.getShape(), fixture.m_userData, isDebug);
				}else if(type == ShapeType.CIRCLE){
					DrawUtil.drawCircle(canvas, paint, body, (CircleShape)fixture.getShape(), fixture.m_userData, isDebug);
				}
				fixture = fixture.getNext();
			}
			body = body.getNext();
		}
		if(isDebug){
			drawWorldCoordinates(canvas);
		}
	}

	private void drawWorldCoordinates(Canvas canvas) {
		//canvas.drawLine(pixels[i-1].x, pixels[i-1].y, pixels[i].x, pixels[i].y, paint);
		
	}

	public abstract boolean onTouchEvent(MotionEvent event);

	public void setVelocityIterations(int velocityIterations) {
		this.velocityIterations = velocityIterations;
	}

	public void setPositionIterations(int positionIterations) {
		this.positionIterations = positionIterations;
	}

	public void setDebug(boolean isDebug){
		this.isDebug = isDebug;
	}

	public void addGround(){
		Vec2 position = WorldGlobals.worldButtomLeft;
		position.y = position.y + 0.01f;
		float width = WorldGlobals.worldWidth;
		float height = 0.0f;
		addConstraint(width, height, position);
	}

	public void addWallLeft(){
		Vec2 position = WorldGlobals.worldTopLeft;
		float width = 0.0f;
		float height = WorldGlobals.worldHeight;
		addConstraint(width, height, position);
	}

	public void addWallRight(){
		Vec2 position = WorldGlobals.worldTopRight;
		position.x = position.x - 0.01f;
		float width = 0.0f;
		float height = WorldGlobals.worldHeight;
		addConstraint(width, height, position);
	}

	public void addRoof(){
		Vec2 position = WorldGlobals.worldTopLeft;
		float width = WorldGlobals.worldWidth;
		float height = 0.0f;
		addConstraint(width, height, position);
	}

	public void addRectangle(float width, float height, Vec2 position, boolean isDynamic){
		addRectangle(width, height, position, new FixtureDef(), isDynamic, null);
	}

	public void addRectangle(float width, float height, Vec2 position, FixtureDef fixtureDef, boolean isDynamic){
		addRectangle(width, height, position, fixtureDef, isDynamic, null);
	}

	public void addRectangle(Sprite sprite, FixtureDef fixtureDef, boolean isDynamic){
		float width = MeterPixelConverter.getMeters(sprite.getSpriteWidth());
		float height = MeterPixelConverter.getMeters(sprite.getSpriteHeight());
		Vec2 position = MeterPixelConverter.getMeters(sprite);
		addRectangle(width, height, position, fixtureDef, isDynamic, sprite);
	}

	public Body addCircle(Vec2 position, float radius, FixtureDef fixtureDef, boolean isDynamic){
		return addCircle(position, radius, fixtureDef, isDynamic, null);
	}

	public Body addCircle(Sprite sprite, FixtureDef fixtureDef, boolean isDynamic) {
		float radius = MeterPixelConverter.getMeters(sprite.getSpriteWidth());
		Vec2 position = MeterPixelConverter.getMeters(sprite);
		return addCircle(position, radius, fixtureDef, isDynamic, sprite);
	}

	public void addPolygon(float posX, float posY, List<Vec2> vertices, FixtureDef fixtureDef, boolean isDynamic){
		addPolygon(posX, posY, vertices, fixtureDef, isDynamic, null);
	}

	public Body addPolygon(float posX, float posY, List<Vec2> vertices, FixtureDef fixtureDef, boolean isDynamic, Object userData){
		PolygonShape shape = new PolygonShape();
		Vec2[] array = new Vec2[vertices.size()];
		for(int i=0; i<vertices.size(); i++){
			array[i] = vertices.get(i);
		}
		shape.set(array, array.length);

		fixtureDef.shape = shape;
		fixtureDef.userData = userData;

		BodyDef bodyDef = new BodyDef();
		if(isDynamic){
			bodyDef.type = BodyType.DYNAMIC;
		}
		bodyDef.position.set(posX, posY);
		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		return body;
	}

	private void addConstraint(float width, float height, Vec2 position) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		FixtureDef fixureDef = new FixtureDef();
		fixureDef.shape = shape;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position= new Vec2(position);

		world.createBody(bodyDef).createFixture(fixureDef);
	}

	private void addRectangle(float width, float height, Vec2 position, FixtureDef fixtureDef, boolean isDynamic, Object userData){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width,height);

		fixtureDef.shape = shape;
		fixtureDef.userData = userData;

		BodyDef bodyDef = new BodyDef();
		if(isDynamic){
			bodyDef.type = BodyType.DYNAMIC;
		}
		bodyDef.position.set(position);

		world.createBody(bodyDef).createFixture(fixtureDef);
	}

	private Body addCircle(Vec2 position, float radius, FixtureDef fixtureDef, boolean isDynamic, Object userData){
		CircleShape shape = new CircleShape();
		shape.m_radius = radius;

		fixtureDef.shape = shape;
		fixtureDef.userData = userData;

		BodyDef bodyDef = new BodyDef();
		if(isDynamic){
			bodyDef.type = BodyType.DYNAMIC;
		}
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);
		if(body != null) {
			body.createFixture(fixtureDef);
		}
		return body;
	}
}
