package no.jts.android.gameapi.graphics;

import no.jts.android.gameapi.screenmanagement.Globals;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends Position{

	protected Bitmap bitmap;
	protected int spriteWidth;
	protected int spriteHeight;
	protected Frame frame;
	protected Rect bounds;
	protected boolean isVisible;

	public Sprite(Bitmap bitmap, int spriteWidth, int spriteHeight){
		super();
		this.bitmap = bitmap;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.bounds = new Rect((int)x, (int)y, (int)x+spriteWidth, (int)y+spriteHeight);
		this.frame = new Frame(spriteWidth, spriteHeight);
		this.isVisible = true;
	}

	public Sprite(Sprite sprite) {
		this(sprite.getBitmap(), sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}

	public Sprite(Sprite sprite, int speed, int min, int max ){
		this(sprite);
		this.speed = speed;
		this.min = min;
		this.max = max;
	}

	public Frame getFrame(){
		return frame;
	}

	public void setFocusOnFrame(int row, int column){
		frame.setFocusOnFrame(row, column);
	}

	public void setPositionCenterHorizontal(){
		x = (Globals.displayWidth/2) - (spriteWidth/2);
	}

	public void setPositionCenterVertical(){
		y = (Globals.displayHeight/2) - (spriteHeight/2);
	}

	public void setPositionCenter() {
		setPositionCenterHorizontal();
		setPositionCenterVertical();
	}

	public void setPositionRight(){
		x = Globals.displayWidth - spriteWidth; 
	}

	public void setPositionBottom(){
		y = Globals.displayHeight - spriteHeight; 
	}

	public void setPositionBottomRight(){
		setPositionRight();
		setPositionBottom();
	}

	public void draw(Canvas canvas) {
		if(canvas != null && bitmap != null && isVisible){
			RectF dest = new RectF(x, y, x + spriteWidth, y + spriteHeight);
			canvas.drawBitmap(bitmap, frame.getRect(), dest, null);
		}
	}

	public boolean isTouched(float touchX, float touchY){
		return isTouchedX(touchX) && isTouchedY(touchY);
	}

	public void setBitmap( Bitmap bitmap ){
		this.bitmap = bitmap;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public Bitmap getBitmap(){
		return bitmap;
	}

	public Rect getBounds(){
		bounds.left = (int)x;
		bounds.top = (int)y;
		bounds.right = (int)x+spriteWidth;
		bounds.bottom = (int)y+spriteHeight;
		return bounds; 
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public float getXCenter(){
		return x + (spriteWidth/2);
	}

	private boolean isTouchedY(float touchY) {
		boolean isTouchedY = false;
		if( touchY > y && touchY < y + spriteHeight ){
			isTouchedY = true;
		}
		return isTouchedY;
	}

	private boolean isTouchedX(float touchX) {
		boolean isTouchedX = false;
		if( touchX > x && touchX < x + spriteWidth ){
			isTouchedX = true;
		}
		return isTouchedX;
	}
}
