package no.jts.android.simple.gameapi.example.screens;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

import no.jts.android.simple.gameapi.cache.Cache;
import no.jts.android.simple.gameapi.example.R;
import no.jts.android.simple.gameapi.example.setup.Assets;
import no.jts.android.simple.gameapi.graphics.Sprite;
import no.jts.android.simple.gameapi.graphics.SpriteUtil;
import no.jts.android.simple.gameapi.graphics.Text;
import no.jts.android.simple.gameapi.physics.PhysicsWorld;
import no.jts.android.simple.gameapi.physics.PixelsToMetersUtil;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreen;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import no.jts.android.simple.gameapi.screenmanagement.ScreenType;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameScreen extends AbstractScreen {

	private static final String TAG = "GameScreen";
	
	private Sprite background;
	private Text gameEngine;
	private PhysicsWorld physicsWorld;
    	
	public GameScreen(AbstractScreenManager gameScreenManager) {
		super(gameScreenManager);
		background = SpriteUtil.createSprite(Cache.get(R.drawable.background));
		gameEngine = new Text(Assets.createPaint(), "Implement your game here!");
		gameEngine.setPositionInPercent(2, 50);
	}

	@Override
	public void onFocus() {
		Log.i(TAG, "GameScreen in focus");
		physicsWorld = new PhysicsWorld(10, 0, -10, Assets.paint);
		physicsWorld.addGround();
		physicsWorld.addWallLeft();
		physicsWorld.addWallRight();
		physicsWorld.addRoof();
		physicsWorld.addRectangle(1, 1, -1.5f, 1.5f, createFixtureDef(), true);
		physicsWorld.addRectangle(0.5f, 1.5f, -1.5f, 5.5f, true);
		physicsWorld.addPolygon(0, 4, createPolygon(), createFixtureDef(), true);
		physicsWorld.addCircle(1, 0, 1, createFixtureDef(), true);
		physicsWorld.addCircle(-1, -1, 0.5f, createFixtureDef(), true);
		
		Sprite sprite = SpriteUtil.createSprite(SpriteUtil.createScaledBitmap(R.drawable.button_new_game), 1, 2);
		physicsWorld.addSprite(sprite, createFixtureDef(), true);
	}

	@Override
	public void update(float deltaTime) {
		physicsWorld.update(deltaTime);
	}

	@Override
	public void draw(Canvas canvas) {
		background.draw(canvas);
		gameEngine.draw(canvas);
		physicsWorld.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			gameScreenManager.setScreenInFocus(ScreenType.MENU);
			return true;
		}
		return false;
	}

	@Override
	public void onExit() {
	
	}

	private FixtureDef createFixtureDef() {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.3f;   
		fixtureDef.restitution = 0.7f;
		return fixtureDef;
	}
	
	private List<Vec2> createPolygon() {
		List<Vec2> vertices = new ArrayList<Vec2>();
		vertices.add(new Vec2(-1, 0));
		vertices.add(new Vec2(-0.5f, -0.5f));
		vertices.add(new Vec2(0, -1));
		vertices.add(new Vec2(0.5f, -0.5f));
		vertices.add(new Vec2(1,1));
		return vertices;
	}
}
