package no.jts.android.gameapi.screenmanagement;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public abstract class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private GameSurfaceThread gameSurfaceThread;

	public GameSurfaceView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	public abstract void update(float deltaTime);

	public abstract void draw(Canvas canvas);

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameSurfaceThread = new GameSurfaceThread(getHolder(), this);
		gameSurfaceThread.setRunning(true);
		gameSurfaceThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// we have to tell thread to shut down & wait for it to finish, or else
		// it might touch the Surface after we return and explode
		boolean retry = true;
		gameSurfaceThread.setRunning(false);
		while (retry) {
			try {
				gameSurfaceThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}
	}

}
