package no.jts.android.simple.gameapi.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class ShapeUtil {
	
	public static void addGround(World world, float width, float height){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width,height);

		FixtureDef fixureDef = new FixtureDef();
		fixureDef.shape = shape;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position= new Vec2(0.0f,-9f);

		world.createBody(bodyDef).createFixture(fixureDef);
	}

	public static void addWall(World world, float posX, float posY, float width, float height){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width,height, new Vec2(0.0f, 0.0f), 3f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.3f;   
		fixtureDef.restitution = 0.7f;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(posX, posY);

		world.createBody(bodyDef).createFixture(fixtureDef);
	}

	public static void addPolygon(World world, float posX, float posY){
		PolygonShape shape = new PolygonShape();

		Vec2[] vec2 = new Vec2[5];
		vec2[0] = new Vec2(-1, 0);
		vec2[1] = new Vec2(-0.5f, -0.5f);
		vec2[2] = new Vec2(0, -1);
		vec2[3] = new Vec2(0.5f, -0.5f);
		vec2[4] = new Vec2(1,1);

		shape.set(vec2, 5);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.3f;   
		fixtureDef.restitution = 0.7f;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(posX, posY);

		world.createBody(bodyDef).createFixture(fixtureDef);
	}

	public static void addBall(World world, float posX, float posY, float radius) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(posX, posY);

		CircleShape shape = new CircleShape();
		shape.m_radius = radius;

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.7f;
		fixtureDef.restitution = 0.7f;

		world.createBody(bodyDef).createFixture(fixtureDef);
	}
}
