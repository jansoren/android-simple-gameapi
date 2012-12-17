package no.jts.android.gameapi.graphics;

import no.jts.android.gameapi.sound.SoundManager;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public abstract class AbstractButton extends Sprite {

	protected boolean isClicked = false;
	protected boolean isTouched = false;
	protected String soundIndex;
	
	public AbstractButton(Bitmap bitmap, int spriteWidth, int spriteHeight) {
		super(bitmap, spriteWidth, spriteHeight);
	}

	public AbstractButton(Sprite sprite) {
		super(sprite);
	}

	public boolean onTouchEvent(MotionEvent event) {
		return onTouchEvent(event, 0);
	}
	
	public boolean onTouchEvent(MotionEvent event, int fingerIndex) {
		float touchX = event.getX(fingerIndex);
		float touchY = event.getY(fingerIndex);
		if(isVisible){
			isTouched = isTouched(touchX, touchY);
			if(isTouched){
				processTouched(event, fingerIndex);
			}else{
				processNotTouched(event, fingerIndex);
			}
		}
		return true;
	}

	protected abstract void processTouched(MotionEvent event, int fingerIndex);

	protected abstract void processNotTouched(MotionEvent event, int fingerIndex);

	public boolean isClicked(){
		if(isClicked){
			isClicked = false;
			return true;
		}
		return isClicked;
	}
	
	public boolean isTouched(){
		return isTouched;
	}
	
	public void setSoundIndex(String soundIndex){
		this.soundIndex = soundIndex;
	}
	
	protected boolean isActionUp(MotionEvent event, int index) {
		if(isMultiTouch(event)){
			if(index == 0 && event.getAction() == MotionEvent.ACTION_POINTER_1_UP){
				return true;
			}else if(index == 1 && event.getAction() == MotionEvent.ACTION_POINTER_2_UP){
				return true;
			}
		}
		return event.getAction() == MotionEvent.ACTION_UP;
	}

	protected void playSound() {
		if( soundIndex != null ){
			SoundManager.getInstance().playSound(soundIndex);
		}
	}

	private boolean isMultiTouch(MotionEvent event) {
		return event.getPointerCount() > 1;
	}
}
