package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.setup.Globals;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

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
		return createButton(sprite, soundIndex);
	}

	public static Button createButton(Bitmap bitmap, String soundIndex){
		Sprite sprite = createSprite(bitmap, 1, 2);
		return createButton(sprite, soundIndex);
	}

	public static TextButton createTextButton(Bitmap bitmap, Paint paint, String text){
		Sprite sprite = createSprite(bitmap, 1, 2);
		return createTextButton(sprite, null, paint, text);
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
	
	private static Button createButton(Sprite sprite, String soundIndex) {
		Button button = new Button(sprite);
		if(soundIndex != null){
			button.setSoundIndex(soundIndex);
		}
		return button;
	}
	
	private static TextButton createTextButton(Sprite sprite, String soundIndex, Paint paint, String text) {
		TextButton textButton = new TextButton(sprite, paint, text);
		if(soundIndex != null){
			textButton.setSoundIndex(soundIndex);
		}
		return textButton;
	}
}
