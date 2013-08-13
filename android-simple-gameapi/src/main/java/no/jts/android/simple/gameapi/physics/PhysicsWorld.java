package no.jts.android.simple.gameapi.physics;

import java.util.List;

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

import android.R;
import android.graphics.Canvas;
import android.graphics.Paint;

import no.jts.android.simple.gameapi.cache.Cache;
import no.jts.android.simple.gameapi.graphics.Sprite;
import no.jts.android.simple.gameapi.graphics.SpriteUtil;

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

	public void addGround(){
		float width = WorldGlobals.worldWidth / 2.0f;
		float height = 0.0f;
		float posX = 0.0f;
		float posY = -(WorldGlobals.worldHeight / 2.0f);
		addConstraint(width, height, posX, posY);
	}

	public void addWallLeft(){
		float width = 0.0f;
		float height = WorldGlobals.worldHeight / 2.0f;
		float posX = -(WorldGlobals.worldWidth / 2.0f) - 0.01f;
		float posY = 0.0f;
		addConstraint(width, height, posX, posY);
	}

	public void addWallRight(){
		float width = 0.0f;
		float height = WorldGlobals.worldHeight / 2.0f;
		float posX = WorldGlobals.worldWidth / 2.0f;
		float posY = 0.0f;
		addConstraint(width, height, posX, posY);
	}

	public void addRoof(){
		float width = WorldGlobals.worldWidth / 2.0f;
		float height = 0.0f;
		float posX = 0.0f;
		float posY = (WorldGlobals.worldHeight / 2.0f) + 0.01f;
		addConstraint(width, height, posX, posY);
	}

	public void addRectangle(float width, float height, float posX, float posY, boolean isDynamic){
		addRectangle(width, height, posX, posY, new FixtureDef(), isDynamic);
	}
	
	public void addRectangle(float width, float height, float posX, float posY, FixtureDef fixtureDef, boolean isDynamic){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width,height);
		
		fixtureDef.shape = shape;
		
		BodyDef bodyDef = new BodyDef();
		if(isDynamic){
			bodyDef.type = BodyType.DYNAMIC;
		}
		bodyDef.position.set(posX, posY);

		world.createBody(bodyDef).createFixture(fixtureDef);
	}
	
	public void addCircle(float posX, float posY, float radius, boolean isDynamic){
		addCircle(posX, posY, radius, new FixtureDef(), isDynamic);
	}
	
	public void addCircle(float posX, float posY, float radius, FixtureDef fixtureDef, boolean isDynamic){
		CircleShape shape = new CircleShape();
		shape.m_radius = radius;
		
		fixtureDef.shape = shape;
		
		BodyDef bodyDef = new BodyDef();
		if(isDynamic){
			bodyDef.type = BodyType.DYNAMIC;
		}
		bodyDef.position.set(posX, posY);
		world.createBody(bodyDef).createFixture(fixtureDef);
	}
	
	public void addPolygon(float posX, float posY, List<Vec2> vertices, FixtureDef fixtureDef, boolean isDynamic){
		PolygonShape shape = new PolygonShape();
		Vec2[] array = new Vec2[vertices.size()];
		for(int i=0; i<vertices.size(); i++){
			array[i] = vertices.get(i);
		}
		shape.set(array, array.length);
		
		fixtureDef.shape = shape;
		
		BodyDef bodyDef = new BodyDef();
		if(isDynamic){
			bodyDef.type = BodyType.DYNAMIC;
		}
		bodyDef.position.set(posX, posY);
		world.createBody(bodyDef).createFixture(fixtureDef);
	}

    public void addSprite(Sprite sprite, FixtureDef fixtureDef, boolean isDynamic){
        float width = PixelsToMetersUtil.getMeters(sprite.getSpriteWidth()) / 2f;
        float height = PixelsToMetersUtil.getMeters(sprite.getSpriteHeight()) / 2f ;
        float posX = PixelsToMetersUtil.getMeters(sprite.getX());
        float posY = ( -1 * PixelsToMetersUtil.getMeters(sprite.getY()) );
        addRectangle(width, height, posX, posY, fixtureDef, isDynamic);
    }

	private void addConstraint(float width, float height, float posX, float posY) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		FixtureDef fixureDef = new FixtureDef();
		fixureDef.shape = shape;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position= new Vec2(posX, posY);

		world.createBody(bodyDef).createFixture(fixureDef);
	}
}
