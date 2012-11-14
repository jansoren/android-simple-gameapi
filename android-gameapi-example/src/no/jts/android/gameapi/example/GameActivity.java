package no.jts.android.gameapi.example;

import no.jts.android.gameapi.AbstractActivity;
import android.os.Bundle;

public class GameActivity extends AbstractActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameScreenManager = new GameScreenManager(this);
    }
}