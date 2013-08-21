package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.setup.Globals;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Area extends Point{

    protected int width;
    protected int height;

    public Area(float x, float y){
        super(x, y);
    }

    public void setPositionCenterHorizontal(){
        x = (Globals.displayWidth/2) - (width /2);
    }

    public void setPositionCenterVertical(){
        y = (Globals.displayHeight/2) - (height /2);
    }

    public void setPositionCenter() {
        setPositionCenterHorizontal();
        setPositionCenterVertical();
    }

    public void setPositionRight(){
        x = Globals.displayWidth - width;
    }

    public void setPositionBottom(){
        y = Globals.displayHeight - height;
    }

    public void setPositionBottomRight(){
        setPositionRight();
        setPositionBottom();
    }

    public int getWidth() {
        return width;
    }

    public void setWidthInPercent(int percent){
        width = Math.round(Globals.displayWidth * (percent / 100f));
    }

    public int getHeight() {
        return height;
    }

    public void setHeightInPercent(int percent){
        height = Math.round(Globals.displayHeight * (percent / 100f));
    }

    public float getXCenter(){
        return x + (width /2f);
    }

    public float getYCenter(){
        return y + (height /2f);
    }

    public boolean isTouched(float touchX, float touchY){
        return isTouchedX(touchX) && isTouchedY(touchY);
    }

    public void draw(Canvas canvas, Paint paint){
    	paint.setStrokeWidth(3);
    	paint.setColor(Color.BLUE);
    	RectF rect = new RectF(x, y, x + width, y + height);
        canvas.drawRect(rect, paint);
    }

    private boolean isTouchedY(float touchY) {
        boolean isTouchedY = false;
        if( touchY > y && touchY < y + height){
            isTouchedY = true;
        }
        return isTouchedY;
    }

    private boolean isTouchedX(float touchX) {
        boolean isTouchedX = false;
        if( touchX > x && touchX < x + width){
            isTouchedX = true;
        }
        return isTouchedX;
    }
}
