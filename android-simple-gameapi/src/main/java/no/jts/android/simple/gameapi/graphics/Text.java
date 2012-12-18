package no.jts.android.simple.gameapi.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

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
			canvas.drawText( text, x, y, paint);
		}
	}
}
