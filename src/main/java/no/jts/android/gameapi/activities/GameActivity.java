package no.jts.android.gameapi.activities;

import no.jts.android.gameapi.views.GameScreenManager;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

public abstract class GameActivity extends Activity {

	protected GameScreenManager gameScreenManager;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(gameScreenManager != null){
			return gameScreenManager.onKeyDown(keyCode, event);
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}