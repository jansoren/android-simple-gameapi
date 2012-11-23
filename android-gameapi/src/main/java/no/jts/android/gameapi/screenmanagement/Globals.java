package no.jts.android.gameapi.screenmanagement;

import android.util.Log;
import android.view.Display;

public class Globals {

	public static final String TAG = "GameGlobals";

	public static int displayWidth;
	public static int displayHeight;

	public static void init(Display display){
		displayWidth = display.getWidth();
		displayHeight = display.getHeight();
		Log.i(TAG, "Display width: " + displayWidth + " height: " + displayHeight );
	}
}
