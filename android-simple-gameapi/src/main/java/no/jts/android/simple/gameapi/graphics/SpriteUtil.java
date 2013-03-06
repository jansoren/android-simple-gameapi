package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.setup.Globals;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpriteUtil {

	public static Sprite createSprite(int id){
		return createSprite(id, 1, 1);
	}

	public static Sprite createSprite(Bitmap scaledBitmap){
		return createSprite(scaledBitmap, 1, 1);
	}

	public static Sprite createSprite(int id, int noOfRowFrames, int noOfColumnFrames){
		Bitmap scaledBitmap = createScaledBitmap(id);
		return new Sprite(scaledBitmap, scaledBitmap.getWidth() / noOfColumnFrames, scaledBitmap.getHeight() / noOfRowFrames);
	}
	
	public static Sprite createSprite(Bitmap scaledBitmap, int noOfRowFrames, int noOfColumnFrames){
		return new Sprite(scaledBitmap, scaledBitmap.getWidth() / noOfColumnFrames, scaledBitmap.getHeight() / noOfRowFrames);
	}
	
	public static Button createButton(int id){
		return createButton(id, null);
	}
	
	public static Button createButton(Bitmap bitmap){
		return createButton(bitmap, null);
	}
	
	public static Button createButton(int id, String soundIndex){
		Sprite sprite = createSprite(id, 1, 2);
		return createButton(soundIndex, sprite);
	}

	public static Button createButton(Bitmap bitmap, String soundIndex){
		Sprite sprite = createSprite(bitmap, 1, 2);
		return createButton(soundIndex, sprite);
	}

	public static ToggleButton createToggleButton(int id, boolean isOn){
		return createToggleButton(id, null, isOn);
	}
	
	public static ToggleButton createToggleButton(Bitmap bitmap, boolean isOn){
		return createToggleButton(bitmap, null, isOn);
	}
	
	public static ToggleButton createToggleButton(int id, String soundIndex, boolean isOn){
		Sprite sprite = createSprite(id, 1, 2);
		ToggleButton toggleButton = new ToggleButton(sprite, isOn);
		if(soundIndex != null){
			toggleButton.setSoundIndex(soundIndex);
		}
		return toggleButton;
	}
	
	public static ToggleButton createToggleButton(Bitmap bitmap, String soundIndex, boolean isOn){
		Sprite sprite = createSprite(bitmap, 1, 2);
		ToggleButton toggleButton = new ToggleButton(sprite, isOn);
		if(soundIndex != null){
			toggleButton.setSoundIndex(soundIndex);
		}
		return toggleButton;
	}
	public static Bitmap createScaledBitmap(int id) {
		Bitmap bitmap = BitmapFactory.decodeResource( Globals.resources, id, Globals.options);
		int scaleToWidth = (int)(Globals.scaleFactorWidth * bitmap.getWidth());
		int scaleToHeight = (int)(Globals.scaleFactorHeight * bitmap.getHeight());
		return Bitmap.createScaledBitmap(bitmap, scaleToWidth, scaleToHeight, true);
	}
	
	private static Button createButton(String soundIndex, Sprite sprite) {
		Button button = new Button(sprite);
		if(soundIndex != null){
			button.setSoundIndex(soundIndex);
		}
		return button;
	}
}
