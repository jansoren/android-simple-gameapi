package no.jts.android.gameapi.screenmanagement;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.Display;

public class BackgroundTask extends AsyncTask<Object, Void, Void>{

	private AbstractScreenManager gameScreenManager;

	@Override
	protected Void doInBackground(Object... params) {
		Activity activity = (Activity) params[0];
		Display display = activity.getWindowManager().getDefaultDisplay();
		Globals.init(display);
		gameScreenManager = (AbstractScreenManager) params[1];
		gameScreenManager.doInBackground(activity);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		gameScreenManager.onPostExecute();
	}

}
