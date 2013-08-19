package no.jts.android.simple.gameapi.screenmanagement;

import no.jts.android.simple.gameapi.setup.Globals;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * AbstractScreen is the view itself.
 * Extend this class to start implementing your intro, menu and game screen.
 * 
 * Example:
 * https://github.com/jansoren/AndroidGameAPI/tree/develop/android-gameapi-example/src/no/jts/android/gameapi/example
 */
public abstract class AbstractScreen {

	protected AbstractScreenManager gameScreenManager;

	public AbstractScreen(AbstractScreenManager gameScreenManager){
		this.gameScreenManager = gameScreenManager;
	}

	public abstract void onFocus();

	public abstract void update(float deltaTime);

	public abstract void draw(Canvas canvas);

	public abstract boolean onTouchEvent(MotionEvent event);

	public abstract boolean onKeyDown(int keyCode, KeyEvent event);

	public abstract void onExit();
	
	protected void drawBackgroundColor(Canvas canvas, int color) {
		Paint paint = new Paint();
		int backupColor = paint.getColor();
		paint.setColor(color);
		canvas.drawRect(0, 0, Globals.displayWidth, Globals.displayHeight, paint);
		paint.setColor(backupColor);
	}
}
