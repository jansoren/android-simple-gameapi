package no.jts.android.simple.gameapi.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class TextButton extends Button {

	private Text text;
	private int originColor;
	
	public TextButton(Sprite sprite, Paint paint, String text) {
		super(sprite);
		this.text = new Text(paint, text);
		this.originColor = paint.getColor();
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		text.draw(canvas);
	}

	@Override
	protected void processTouched(MotionEvent event, int fingerIndex) {
		super.processTouched(event, fingerIndex);
		textDown(event, fingerIndex);
	}

	@Override
	protected void processNotTouched(MotionEvent event, int fingerIndex) {
		super.processNotTouched(event, fingerIndex);
		textUp();
	}

	@Override
	public void setPositionInPercent(int percentX, int percentY) {
		super.setPositionInPercent(percentX, percentY);
		text.setPositionInPercent(percentX, percentY);
		centerText();
	}
	
	private void centerText()  {
		Rect rect = text.getBounds();
		text.x = text.x + (width / 2f) - ((rect.left + rect.right) / 2f);
		text.y = text.y + (height / 2f) - (rect.top/2f);
		Log.i("TextButton", "bottom:" + rect.bottom + " left:" + rect.left + " right:" + rect.right + " top:" + rect.top);
	}
	
	private void textDown(MotionEvent event, int fingerIndex) {
		text.setColor(Color.GRAY);
		if(isActionUp(event, fingerIndex)){
			textUp();
		}
	}
	
	private void textUp() {
		text.setColor(originColor);
	}
}
