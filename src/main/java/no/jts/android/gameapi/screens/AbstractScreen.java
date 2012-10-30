package no.jts.android.gameapi.screens;

import no.jts.android.gameapi.screenmanager.AbstractScreenManager;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class AbstractScreen {

	protected Activity activity;
	protected AbstractScreenManager gameScreenManager;

	public AbstractScreen(Activity activity, AbstractScreenManager gameScreenManager){
		this.activity = activity;
		this.gameScreenManager = gameScreenManager;
	}

	public abstract void onFocus();

	public abstract void update(float deltaTime);

	public abstract void draw(Canvas canvas);

	public abstract boolean onTouchEvent(MotionEvent event);

	public abstract boolean onKeyDown(int keyCode, KeyEvent event);

	public abstract void onExit();
}
