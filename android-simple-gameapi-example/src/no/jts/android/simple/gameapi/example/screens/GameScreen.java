package no.jts.android.simple.gameapi.example.screens;

import no.jts.android.simple.gameapi.cache.Cache;
import no.jts.android.simple.gameapi.example.R;
import no.jts.android.simple.gameapi.example.setup.Assets;
import no.jts.android.simple.gameapi.graphics.Sprite;
import no.jts.android.simple.gameapi.graphics.SpriteUtil;
import no.jts.android.simple.gameapi.graphics.Text;
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
		
	public GameScreen(AbstractScreenManager gameScreenManager) {
		super(gameScreenManager);
		background = SpriteUtil.createSprite(Cache.get(R.drawable.background));
		gameEngine = new Text(Assets.createPaint(), "Implement your game here!");
		gameEngine.setPositionInPercent(2, 50);
	}

	@Override
	public void onFocus() {
		Log.i(TAG, "GameScreen in focus");
	}

	@Override
	public void update(float deltaTime) {
	
	}

	@Override
	public void draw(Canvas canvas) {
		background.draw(canvas);
		gameEngine.draw(canvas);
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

}
