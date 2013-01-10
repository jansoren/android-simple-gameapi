package no.jts.android.simple.gameapi.example;

import no.jts.android.simple.gameapi.AbstractActivity;
import android.os.Bundle;
import android.util.Log;

public class GameActivity extends AbstractActivity {

	private static final String TAG = "GameActivity";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "GameActivity created");
        gameScreenManager = new GameScreenManager(this);
    }
}