package no.jts.android.gameapi.views;

import java.util.HashMap;
import java.util.Map;

import no.jts.android.gameapi.threads.BackgroundTask;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;


public abstract class GameScreenManager extends GameSurfaceView {

	protected String screenInFocus;
	protected Map<String, AbstractScreen> screens = new HashMap<String, AbstractScreen>();

	public GameScreenManager(Activity activity) {
		super(activity);
		BackgroundTask backgroundTask = new BackgroundTask();
		backgroundTask.execute(activity, this);
	}

	public abstract void doInBackground(Activity activity);

	public abstract void onPostExecute();

	public AbstractScreen getScreenInFocus() {
		return screens.get(screenInFocus);
	}

	public void setScreenInFocus(String screenInFocus) {
		AbstractScreen previousScreenInFocus = getScreenInFocus();
		if(previousScreenInFocus != null){
			previousScreenInFocus.onExit();
		}
		this.screenInFocus = screenInFocus;
		onFocus();
	}

	private void onFocus(){
		if(getScreenInFocus() != null){
			getScreenInFocus().onFocus();
		}
	}

	public void putScreen(String screenType, AbstractScreen screen) {
		screens.put(screenType, screen);		
	}

	@Override
	public void update(float deltaTime) {
		if(getScreenInFocus() != null){
			getScreenInFocus().update(deltaTime);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		if(getScreenInFocus() != null){
			getScreenInFocus().draw(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(getScreenInFocus() != null){
			getScreenInFocus().onTouchEvent(event);
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(getScreenInFocus() != null){
			return getScreenInFocus().onKeyDown(keyCode, event);
		}
		return false;		
	}
}
