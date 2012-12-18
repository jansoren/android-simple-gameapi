package no.jts.android.simple.gameapi;

import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

/**
 * AbstractActivity is the games activity. 
 * Extend this and implement an instance of your own AbstractScreenManager.
 * 
 * Example:
 * https://github.com/jansoren/AndroidGameAPI/tree/develop/android-gameapi-example/src/no/jts/android/gameapi/example
 */
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