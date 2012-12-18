package no.jts.android.simple.gameapi.graphics;

import android.graphics.Rect;

public class Frame {

	private Rect rect;
	private int row;
	private int column;
	private int width;
	private int height;

	public Frame(int width, int height){
		this.rect = new Rect(0,0,0,0);
		this.rect.right = width;
		this.rect.bottom = height;
		this.row = 0;
		this.column = 0;
		this.width = width;
		this.height = height;
	}

	public Rect getRect(){
		return rect;
	}

	public int getRow(){
		return row;
	}

	public int getColumn(){
		return column;
	}

	public void setFocusOnFrame(int row, int column) {
		this.row = row;
		this.column = column;

		rect.left = column * width;
		rect.top = row * height;
		rect.right = rect.left + width;
		rect.bottom = rect.top + height;
	}
}
