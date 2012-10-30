package no.jts.android.gameapi.threads;

import no.jts.android.gameapi.types.ScreenType;
import no.jts.android.gameapi.views.GameScreenManager;
import android.app.Activity;
import android.os.AsyncTask;

public class BackgroundTask extends AsyncTask<Object, Void, Void>{

	private GameScreenManager gameScreenManager;

	@Override
	protected Void doInBackground(Object... params) {
		Activity activity = (Activity) params[0];
		gameScreenManager = (GameScreenManager) params[1];
		gameScreenManager.doInBackground(activity);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		gameScreenManager.setScreenInFocus(ScreenType.MENU);
	}

}
