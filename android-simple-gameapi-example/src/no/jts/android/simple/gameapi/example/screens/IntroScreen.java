package no.jts.android.simple.gameapi.example.screens;

import no.jts.android.simple.gameapi.cache.Cache;
import no.jts.android.simple.gameapi.example.R;
import no.jts.android.simple.gameapi.example.setup.Assets;
import no.jts.android.simple.gameapi.graphics.Sprite;
import no.jts.android.simple.gameapi.graphics.SpriteUtil;
import no.jts.android.simple.gameapi.graphics.Text;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreen;
import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class IntroScreen extends AbstractScreen {

	private static final String TAG = "IntroScreen";
	
	private Sprite background;
	private Text loading;
	
	public IntroScreen(AbstractScreenManager gameScreenManager) {
		super(gameScreenManager);
		background = SpriteUtil.createSprite(Cache.get(R.drawable.background));
		loading = new Text(Assets.createPaint(), "Loading...", 0);
		loading.setPositionInPercent(39, 50);
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
		background.draw(canvas);
		loading.draw(canvas);
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
