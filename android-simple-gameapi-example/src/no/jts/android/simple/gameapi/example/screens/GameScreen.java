package no.jts.android.simple.gameapi.example.screens;

import java.util.ArrayList;
import java.util.List;

import no.jts.android.simple.gameapi.cache.Cache;
import no.jts.android.simple.gameapi.example.R;
import no.jts.android.simple.gameapi.example.engine.GameWorld;
import no.jts.android.simple.gameapi.example.setup.Assets;
import no.jts.android.simple.gameapi.graphics.Button;
import no.jts.android.simple.gameapi.graphics.Sprite;
import no.jts.android.simple.gameapi.graphics.SpriteUtil;
import no.jts.android.simple.gameapi.graphics.Text;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreen;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import no.jts.android.simple.gameapi.screenmanagement.ScreenType;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameScreen extends AbstractScreen {

	private static final String TAG = "GameScreen";
	
	private Sprite background;
	private Text gameEngine;
	private GameWorld world;
    
    public GameScreen(AbstractScreenManager gameScreenManager) {
		super(gameScreenManager);
		background = SpriteUtil.createSprite(Cache.get(R.drawable.background));
        gameEngine = new Text(Assets.createPaint(), "Implement your game here!");
		gameEngine.setPositionInPercent(2, 50);
    }

	@Override
	public void onFocus() {
		Log.i(TAG, "GameScreen in focus");
		world = new GameWorld(10, 0, -10, Assets.paint);
		world.addGround();
		world.addWallLeft();
		world.addWallRight();
		world.addRoof();
		world.addRectangle(1, 1, new Vec2(-1.5f, 1.5f), createFixtureDef(), true);
		world.addPolygon(0, 4, createPolygon(), createFixtureDef(), true);
	    
		Sprite circle1 = SpriteUtil.createSprite(Cache.get(R.drawable.ic_launcher));
        circle1.setPosition(50, 150);
        world.addCircle(circle1, createFixtureDef(), true);
	    
        Sprite circle2 = SpriteUtil.createSprite(Cache.get(R.drawable.ic_launcher));
        circle2.setPosition(240, 400);
        world.addCircle(circle2, createFixtureDef(), true);
	    
        Sprite sprite1 = SpriteUtil.createSprite(Cache.get(R.drawable.button_new_game));
        sprite1.setPosition(200, 50);
        world.addRectangle(sprite1, createFixtureDef(), true);
	    
        Button button1 = SpriteUtil.createButton(Cache.get(R.drawable.button_new_game));
        button1.setPosition(200, 150);
        world.addRectangle(button1, createFixtureDef(), true);
	}

	@Override
	public void update(float deltaTime) {
		world.update(deltaTime);
	}

	@Override
	public void draw(Canvas canvas) {
		background.draw(canvas);
		gameEngine.draw(canvas);
		world.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		world.onTouchEvent(event);
        return true;
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
