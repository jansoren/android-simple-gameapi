package no.jts.android.gameapi;

import no.jts.android.gameapi.screenmanager.AbstractScreenManager;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

public abstract class AbstractActivity extends Activity {

	protected AbstractScreenManager gameScreenManager;

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