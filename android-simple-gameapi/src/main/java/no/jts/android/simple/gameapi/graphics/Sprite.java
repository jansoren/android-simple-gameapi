package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.physics.MetersToPixelsUtil;
import no.jts.android.simple.gameapi.setup.Globals;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

import org.jbox2d.common.Vec2;

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

    public void draw(Canvas canvas, Vec2 position, float angle){
        if(canvas != null && bitmap != null && isVisible){
            Matrix matrix = transform(position, angle);
            canvas.setMatrix(matrix);
            draw(canvas);
            canvas.restore();
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

    private Matrix transform(Vec2 position, float angle) {
        float x = MetersToPixelsUtil.convertPositionX(position);
        float y = MetersToPixelsUtil.convertPositionY(position);
        float degrees = -1 * (float)Math.toDegrees(angle);

        Matrix matrix = new Matrix();
        matrix.postTranslate(-spriteWidth / 2f, -spriteHeight / 2f); // Centers image
        matrix.postRotate(degrees);
        matrix.postTranslate(x, y);
        return matrix;
    }
}
