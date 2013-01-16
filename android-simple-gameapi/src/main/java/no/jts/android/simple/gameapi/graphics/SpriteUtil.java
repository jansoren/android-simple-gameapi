package no.jts.android.simple.gameapi.graphics;

import android.graphics.Bitmap;

public class SpriteUtil {

	public static Sprite createBackground(Bitmap scaledBitmap) {
		return createSprite(scaledBitmap, 1, 1);
	}

	public static Button createButton(Bitmap scaledBitmap){
		Sprite sprite = createSprite(scaledBitmap, 1, 2);
		return new Button(sprite);
	}

	public static ToggleButton createToggleButton(Bitmap scaledBitmap, boolean isOn){
		Sprite sprite = createSprite(scaledBitmap, 1, 2);
		return new ToggleButton(sprite, isOn);
	}

	public static Sprite createSprite(Bitmap scaledBitmap, int noOfRowFrames, int noOfColumnFrames){
		return new Sprite(scaledBitmap, scaledBitmap.getWidth() / noOfColumnFrames, scaledBitmap.getHeight() / noOfRowFrames);
	}
}
