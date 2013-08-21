package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.physics.MeterPixelConverter;
import no.jts.android.simple.gameapi.setup.Globals;

import org.jbox2d.common.Vec2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends Position{

	protected Bitmap bitmap;
	protected Frame frame;
	protected Rect bounds;
	protected boolean isVisible;
    protected float degrees;

	public Sprite(Bitmap bitmap, int width, int height){
		super();
		this.bitmap = bitmap;
		this.width = width;
		this.height = height;
		this.bounds = new Rect((int)x, (int)y, (int)x+ width, (int)y+ height);
		this.frame = new Frame(width, height);
		this.isVisible = true;
        this.degrees = 0;
	}

	public Sprite(Sprite sprite) {
		this(sprite.getBitmap(), sprite.getWidth(), sprite.getHeight());
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

	public void draw(Canvas canvas) {
		if(canvas != null && bitmap != null && isVisible){
            canvas.save();
            if(degrees != 0){
                canvas.setMatrix(rotate());
            }
			RectF dest = new RectF(x, y, x + width, y + height);
			canvas.drawBitmap(bitmap, frame.getRect(), dest, null);
            canvas.restore();
        }
	}
	
	public void update(Canvas canvas, Vec2 position, float angle){
        Point pixels = MeterPixelConverter.getPixels(position);
    	x = pixels.x - (width / 2f);
        y = pixels.y - (height / 2f);
        degrees = -1 * (float)Math.toDegrees(angle);
    }

    public Rect getBounds(){
        bounds.left = (int)x;
        bounds.top = (int)y;
        bounds.right = (int)x+ width;
        bounds.bottom = (int)y+ height;
        return bounds;
    }

    public void setBitmap( Bitmap bitmap ){
		this.bitmap = bitmap;
	}

	public Bitmap getBitmap(){
		return bitmap;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

    private Matrix rotate() {
        Matrix matrix = new Matrix();
        matrix.postTranslate( -getXCenter(), -getYCenter());
        matrix.postRotate(degrees);
        matrix.postTranslate(getXCenter(), getYCenter());
        return matrix;
    }

}
