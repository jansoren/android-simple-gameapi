package no.jts.android.simple.gameapi.graphics;

import android.graphics.Bitmap;
import android.view.MotionEvent;

public class ToggleButton extends AbstractButton {

	private boolean isOn;

	public ToggleButton(Bitmap bitmap, int spriteWidth, int spriteHeight, boolean isOn) {
		super(bitmap, spriteWidth, spriteHeight);
		this.isOn = isOn;
	}
	
	public ToggleButton(Sprite sprite, boolean isOn) {
		super(sprite);
		this.isOn = isOn;
	}
	
	@Override
	protected void processTouched(MotionEvent event, int fingerIndex) {
		if(isActionUp(event, fingerIndex)){
			isClicked = true;
			isOn = !isOn;
			playSound();
		}else{
			isClicked = false;
		}
	}
	
	public void update(){
		if(isOn){
			setFocusOnFrame(0, 1);
		}else{
			setFocusOnFrame(0, 0);
		}		
	}

	@Override
	protected void processNotTouched(MotionEvent event, int fingerIndex) {
		
	}
	
	public boolean isOn(){
		return isOn;
	}
	
	public void setOn(boolean isOn){
		this.isOn = isOn;
	}
}
