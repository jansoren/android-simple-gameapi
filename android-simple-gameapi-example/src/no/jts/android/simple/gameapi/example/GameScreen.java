package no.jts.android.simple.gameapi.example;

import no.jts.android.simple.gameapi.screenmanagement.AbstractScreen;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameScreen extends AbstractScreen {

	private static final String TAG = "GameScreen";
	
	public GameScreen(AbstractScreenManager gameScreenManager) {
		super(gameScreenManager);
	
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
	
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public void onExit() {
	
	}

}
