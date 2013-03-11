package no.jts.android.simple.gameapi.cache;

import no.jts.android.simple.gameapi.graphics.SpriteUtil;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class Cache {

	private static final String TAG = "Cache";

	private static LruCache<Integer, Bitmap> bitmaps = new BitmapLruCache();

	public static Bitmap get(int drawableId){
		Bitmap bitmap = bitmaps.get(drawableId);
		if(bitmap != null){
			Log.i(TAG, "Getting bitmap (" + sizeOf(bitmap) + " KB) from cache");
			return bitmap;	
		} else {
			bitmap = SpriteUtil.createScaledBitmap(drawableId);
			bitmaps.put(drawableId, bitmap);
			Log.i(TAG, "Scaled and put bitmap (" + sizeOf(bitmap) + " KB) into cache ("+ bitmaps.size() +" KB)");
			return bitmap;
		}
	}

	private static float sizeOf(Bitmap data) {
		return data.getRowBytes() * data.getHeight() / 1024;
	}
}
