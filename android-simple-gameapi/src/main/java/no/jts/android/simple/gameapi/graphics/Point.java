package no.jts.android.simple.gameapi.graphics;

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
}
