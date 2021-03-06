package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.setup.Globals;
import android.graphics.Canvas;


public abstract class Position extends Point{
	protected int speed; // pixels per second
	protected int min;
	protected int max;
	private float pixelWidthRelation;
	private float pixelHeightRelation;

	public Position(){
		this(0, 0, 0);
	}

	public Position(int speed, int min, int max){
		super(0, 0);
		this.speed = speed;
		this.min = min;
		this.max = max;
		this.pixelWidthRelation = Globals.displayWidth/320f;
		this.pixelHeightRelation = Globals.displayHeight/480f;
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
	public int getSpeed(){
		return speed;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void moveLeft(float deltaTime) {
		this.x = this.x-(speed*deltaTime * pixelWidthRelation);
	}

	public void moveRight(float deltaTime){
		this.x = this.x+(speed*deltaTime * pixelWidthRelation);
	}

	public void moveUp(float deltaTime){
		this.y = this.y-(speed*deltaTime * pixelHeightRelation);
	}

	public void moveDown(float deltaTime){
		this.y = this.y+(speed*deltaTime * pixelHeightRelation);
	}

	public void changeSpeed(int change){
		int speed_ = speed + change;
		if( speed_ < min){
			this.speed = min; 
		}else if(speed_ > max){
			this.speed = max;
		}else{
			this.speed = speed_;
		}
	}

	public abstract void draw(Canvas canvas);
}
