package no.jts.android.simple.gameapi.example;

import no.jts.android.simple.gameapi.screenmanagement.AbstractScreenManager;
import no.jts.android.simple.gameapi.screenmanagement.ScreenType;
import android.app.Activity;
import android.util.Log;

public class GameScreenManager extends AbstractScreenManager {

	private static final String TAG = "GameScreenManager";  
	
	public GameScreenManager(Activity activity) {
		super(activity);
		Log.i(TAG, "Displaying loading screen");
		screens.put(ScreenType.INTRO, new IntroScreen(activity, this));
		setScreenInFocus(ScreenType.INTRO);
	}

	@Override
	public void doInBackground(Activity activity) {
		Log.i(TAG, "Loading game assets and screens");
		screens.put(ScreenType.MENU, new MenuScreen(activity, this));
		screens.put(ScreenType.GAME, new GameScreen(activity, this));
	}

	@Override
	public void onPostExecute() {
		Log.i(TAG, "Loading game finished, set menu in focus");
		setScreenInFocus(ScreenType.MENU);
	}

}
