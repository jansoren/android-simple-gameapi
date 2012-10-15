package no.jts.android.gameapi.threads;

import no.jts.android.gameapi.views.GameSurfaceView;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameSurfaceThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private GameSurfaceView surfaceView;
	private boolean isRunning = false;
	private long previousTime;

	// desired fps
	private final static int MAX_FPS = 60;
	// maximum number of frames to be skipped
	private final static int MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;   


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

		long beginTime;     // the time when the cycle begun
		long timeDiff;      // the time it took for the cycle to execute
		int sleepTime;      // ms to sleep (<0 if we're behind)
		int framesSkipped;  // number of frames being skipped 
		previousTime = System.currentTimeMillis();

		sleepTime = 0;

		while (isRunning) {
			try {
				canvas = surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;  // resetting the frames skipped

					// update game state
					surfaceView.update(getDeltaTime());
					// render state to the screen
					// draws the canvas on the panel
					surfaceView.draw(canvas);
					// calculate how long did the cycle take
					timeDiff = System.currentTimeMillis() - beginTime;
					// calculate sleep time
					sleepTime = (int)(FRAME_PERIOD - timeDiff);

					if (sleepTime > 0) {
						// if sleepTime > 0 we're OK
						try {
							// send the thread to sleep for a short period
							// very useful for battery saving
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {}
					}

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

	private float getDeltaTime() {
		long currentTime = System.currentTimeMillis();
		float deltaTime = (currentTime-previousTime) / 1000f;
		previousTime = currentTime;
		return deltaTime;
	}
}
