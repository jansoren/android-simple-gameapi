package no.jts.android.simple.gameapi.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Text extends Position {

	private String text;
	private Paint paint;

	public Text(Paint paint){
		this(paint, "", 0);
	}
	public Text(Paint paint, String text){
		this(paint, text, 0);
	}
	public Text(Paint paint, String text, int speed ){
		super();
		this.text = text;
		this.paint = paint;
		this.speed = speed;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void draw(Canvas canvas) {
		if(canvas!=null && text!=null && paint!=null){
			canvas.drawText(text, x, y, paint);
		}
	}
	public Rect getBounds()  {
		Rect bounds = new Rect();
		if(paint != null)  {
			paint.getTextBounds(text, 0, text.length(), bounds);
		}
		return bounds;
	}
	public void setColor(int color) {
		if(paint != null) {
			paint.setColor(color);
		}
	}
	@Override
	public void setPositionCenterHorizontal() {
		super.setPositionCenterHorizontal();
		Rect rect = getBounds();
		x = x  - ((rect.left + rect.right) / 2f);		
	}
	@Override
	public void setPositionCenterVertical() {
		super.setPositionCenterVertical();
		Rect rect = getBounds();
		y = y - (rect.top/2f);		
	}
	
}
