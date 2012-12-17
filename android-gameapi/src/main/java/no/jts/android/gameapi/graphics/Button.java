package no.jts.android.gameapi.graphics;

import android.graphics.Bitmap;
import android.view.MotionEvent;

public class Button extends AbstractButton {

	public Button(Bitmap bitmap, int spriteWidth, int spriteHeight) {
		super(bitmap, spriteWidth, spriteHeight);
	}
	
	public Button(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	protected void processTouched(MotionEvent event, int fingerIndex) {
		setFocusOnFrame(0, 1);
		if(isActionUp(event, fingerIndex)){
			isClicked = true;
			isTouched = false;
			setFocusOnFrame(0, 0);
			playSound();
		}else{
			isClicked = false;
		}
	}

	@Override
	protected void processNotTouched(MotionEvent event, int fingerIndex) {
		setFocusOnFrame(0, 0);	
	}
	
}
