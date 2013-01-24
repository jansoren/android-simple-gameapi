package no.jts.android.simple.gameapi.graphics;

import no.jts.android.simple.gameapi.setup.Globals;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpriteUtil {

	public static Sprite createBackground(int id) {
		Bitmap bitmap = BitmapFactory.decodeResource(Globals.resources, id, Globals.options );
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, Globals.displayWidth, Globals.displayHeight, true);
		return new Sprite(scaledBitmap, scaledBitmap.getWidth(), scaledBitmap.getHeight());
	}

	public static Sprite createSprite(int id, int noOfRowFrames, int noOfColumnFrames){
		Bitmap scaledBitmap = getScaledBitmap(id);
		return new Sprite(scaledBitmap, scaledBitmap.getWidth() / noOfColumnFrames, scaledBitmap.getHeight() / noOfRowFrames);
	}
	
	public static Button createButton(int id){
		return createButton(id, null);
	}
	
	public static Button createButton(int id, String soundIndex){
		Sprite sprite = createSprite(id, 1, 2);
		Button button = new Button(sprite);
		if(soundIndex != null){
			button.setSoundIndex(soundIndex);
		}
		return button;
	}

	public static ToggleButton createToggleButton(int id, boolean isOn){
		return createToggleButton(id, null, isOn);
	}
	
	public static ToggleButton createToggleButton(int id, String soundIndex, boolean isOn){
		Sprite sprite = createSprite(id, 1, 2);
		ToggleButton toggleButton = new ToggleButton(sprite, isOn);
		if(soundIndex != null){
			toggleButton.setSoundIndex(soundIndex);
		}
		return toggleButton;
	}

	private static Bitmap getScaledBitmap(int id) {
		Bitmap bitmap = BitmapFactory.decodeResource( Globals.resources, id, Globals.options);
		int scaleToWidth = (int)(Globals.scaleFactorWidth * bitmap.getWidth());
		int scaleToHeight = (int)(Globals.scaleFactorHeight * bitmap.getHeight());
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaleToWidth, scaleToHeight, true);
		return scaledBitmap;
	}
}
