package no.jts.android.gameapi.example;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import no.jts.android.gameapi.screenmanagement.AbstractScreen;
import no.jts.android.gameapi.screenmanagement.AbstractScreenManager;

public class GameScreen extends AbstractScreen {

	private static final String TAG = "GameScreen";
	
	public GameScreen(Activity activity, AbstractScreenManager gameScreenManager) {
		super(activity, gameScreenManager);
	
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
