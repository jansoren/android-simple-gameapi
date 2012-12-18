package no.jts.android.gameapi.setup;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;

public class Globals {

	public static final String TAG = "GameGlobals";

	public static Context context;
	public static int displayWidth;
	public static int displayHeight;

	public static void init(Activity activity){
		Display display = activity.getWindowManager().getDefaultDisplay();
		displayWidth = display.getWidth();
		displayHeight = display.getHeight();
		Log.i(TAG, "Display width: " + displayWidth + " height: " + displayHeight );
		
		context = activity.getApplicationContext();
	}
}
