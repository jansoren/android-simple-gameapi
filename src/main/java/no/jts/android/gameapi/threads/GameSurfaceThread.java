package no.jts.android.gameapi.threads;

import no.jts.android.gameapi.views.GameSurfaceView;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameSurfaceThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private GameSurfaceView surfaceView;
	private boolean isRunning = false;
	private long previousTime;

	private final static int MAX_FPS = 60;					// desired fps
	private final static int MAX_FRAME_SKIPS = 5;			// maximum number of frames to be skipped
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;	// the frame period   

	public GameSurfaceThread(SurfaceHolder surfaceHolder, GameSurfaceView surfaceView) {
		this.surfaceHolder = surfaceHolder;
		this.surfaceView = surfaceView;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		Canvas canvas = null;

		previousTime = System.currentTimeMillis();
		int sleepTime = 0;	// ms to sleep (<0 if we're behind)

		while (isRunning) {
			try {
				canvas = surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					long beginTime = System.currentTimeMillis();

					surfaceView.update(getDeltaTime());
					surfaceView.draw(canvas);

					sleepTime = calculateSleepTime(beginTime);
					if (sleepTime > 0) {
						sleep(sleepTime);
					}

					int framesSkipped = 0; // number of frames being skipped

					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
						// we need to catch up
						// update without rendering
						surfaceView.update(getDeltaTime());
						// add frame period to check if in next frame
						sleepTime += FRAME_PERIOD;
						framesSkipped++;
					}
				}
			} finally {
				// do this in a finally so that if an exception is thrown
				// during the above, we don't leave the Surface in an
				// inconsistent state
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	private void sleep(int sleepTime) {
		// if sleepTime > 0 we're OK
		try {
			// send the thread to sleep for a short period
			// very useful for battery saving
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
	}

	private int calculateSleepTime(long beginTime) {
		long timeDiff = System.currentTimeMillis() - beginTime; // the time it took for the cycle to execute
		return (int)(FRAME_PERIOD - timeDiff);
	}

	private float getDeltaTime() {
		long currentTime = System.currentTimeMillis();
		float deltaTime = (currentTime-previousTime) / 1000f;
		previousTime = currentTime;
		return deltaTime;
	}
}
