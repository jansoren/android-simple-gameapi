package no.jts.android.simple.gameapi.example;

import no.jts.android.simple.gameapi.screenmanagement.AbstractScreen;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class IntroScreen extends AbstractScreen {

	private static final String TAG = "IntroScreen";
	
	public IntroScreen(Activity activity, AbstractScreenManager gameScreenManager) {
		super(activity, gameScreenManager);

	}

	@Override
	public void onFocus() {
		Log.i(TAG, "IntroScreen in focus");
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
