package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.setup.Globals;

public class Point {
	public float x = 0;
	public float y = 0;
		
	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}

    public void setPositionXInPercent(int percent){
        x = Globals.displayWidth * (percent / 100f);
    }

    public void setPositionYInPercent(int percent){
        y = Globals.displayHeight * (percent / 100f);
    }

    public void setPositionInPercent(int percentX, int percentY){
        setPositionXInPercent(percentX);
        setPositionYInPercent(percentY);
    }
}
