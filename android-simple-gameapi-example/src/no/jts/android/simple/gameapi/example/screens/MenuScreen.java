package no.jts.android.simple.gameapi.example.screens;

import no.jts.android.simple.gameapi.cache.Cache;
import no.jts.android.simple.gameapi.example.R;
import no.jts.android.simple.gameapi.graphics.Button;
import no.jts.android.simple.gameapi.graphics.Sprite;
import no.jts.android.simple.gameapi.graphics.SpriteUtil;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreen;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import no.jts.android.simple.gameapi.screenmanagement.ScreenType;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MenuScreen extends AbstractScreen {

	private static final String TAG = "MenuScreen";
	
	private Sprite background;
	private Button buttonNewGame;
	
	public MenuScreen(AbstractScreenManager gameScreenManager) {
		super(gameScreenManager);
		background = SpriteUtil.createSprite(Cache.get(R.drawable.background));
		buttonNewGame = SpriteUtil.createButton(Cache.get(R.drawable.button_new_game));
		buttonNewGame.setPositionInPercent(50, 80);
	}

	@Override
	public void onFocus() {
		Log.i(TAG, "MenuScreen in focus");
	}

	@Override
	public void update(float deltaTime) {
		if(buttonNewGame.isClicked()){
			gameScreenManager.setScreenInFocus(ScreenType.GAME);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		background.draw(canvas);
		buttonNewGame.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		buttonNewGame.onTouchEvent(event);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public void onExit() {
	
	}

}
