package no.jts.android.simple.gameapi.graphics;

import java.util.List;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class SelectButton{

	protected List<ToggleButton> buttons;
	
	public SelectButton(List<ToggleButton> buttons) {
		this.buttons = buttons;
	}

	public boolean onTouchEvent(MotionEvent event) {
		for(ToggleButton button : buttons){
			button.onTouchEvent(event);
		}
		return true;
	}

	public void update(){
		for(ToggleButton button : buttons){
			button.update();
		}
	}
	
	public void draw(Canvas canvas) {
		for(ToggleButton button : buttons){
			button.draw(canvas);
		}
	}

	public void setPositionInPercent(int percentX, int percentY, int widthToNextButton){
		for(ToggleButton button : buttons){
			button.setPositionInPercent(percentX, percentY);
			percentX += widthToNextButton;
		}
	}
	
	public boolean isClicked(int index) {
		boolean isClicked = buttons.get(index).isClicked();
		if(isClicked){
			for(int i=0; i<buttons.size(); i++){
				ToggleButton button = buttons.get(i);
				if(i == index){
					button.setOn(true);
				}else{
					button.setOn(false);
				}
			}
		}
		return isClicked;
	}

}
