package no.jts.android.simple.gameapi.screenmanagement;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;

import java.util.HashMap;
import java.util.Map;

import no.jts.android.simple.gameapi.Setup;
import no.jts.android.simple.gameapi.setup.Globals;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * AbstractScreenManager is the engine behind the game. 
 * Extend this class to:
 *   - get your game thread running (GameSurfaceThread)
 *   - get a background task for initializing all your assets (BackgroundTask)
 *   - manage all your screens (AbstractScreen)
 *   
 * Example: 
 * https://github.com/jansoren/AndroidGameAPI/tree/develop/android-gameapi-example/src/no/jts/android/gameapi/example
 */
public abstract class AbstractScreenManager extends GameSurfaceView {

	protected String screenInFocus;
	protected Map<String, AbstractScreen> screens = new HashMap<String, AbstractScreen>();
	protected RelativeLayout relativeLayout;
	
	public AbstractScreenManager(Activity activity, Setup setup) {
		super(activity);
		Globals.init(activity, setup);
		BackgroundTask backgroundTask = new BackgroundTask();
		backgroundTask.execute(activity, this);
		relativeLayout = createRelativeLayout(activity);
		activity.setContentView( relativeLayout, new FrameLayout.LayoutParams(FILL_PARENT, FILL_PARENT) );
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

	private RelativeLayout createRelativeLayout(Activity activity) {
		RelativeLayout relativeLayout = new RelativeLayout(activity);
		relativeLayout.addView(this, 0, new RelativeLayout.LayoutParams(FILL_PARENT, FILL_PARENT));
		return relativeLayout;
	}
}
