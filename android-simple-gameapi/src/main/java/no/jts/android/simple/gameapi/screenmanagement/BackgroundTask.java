package no.jts.android.simple.gameapi.screenmanagement;

import android.app.Activity;
import android.os.AsyncTask;

public class BackgroundTask extends AsyncTask<Object, Void, Void>{

	private AbstractScreenManager gameScreenManager;

	@Override
	protected Void doInBackground(Object... params) {
		Activity activity = (Activity) params[0];
		gameScreenManager = (AbstractScreenManager) params[1];
		gameScreenManager.doInBackground(activity);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		gameScreenManager.onPostExecute();
	}

}
