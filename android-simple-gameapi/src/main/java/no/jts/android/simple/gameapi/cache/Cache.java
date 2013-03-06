package no.jts.android.simple.gameapi.cache;

import java.lang.reflect.Field;

import no.jts.android.simple.gameapi.graphics.SpriteUtil;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class Cache {

	private static final String TAG = "Cache";

	private static LruCache<Integer, Bitmap> bitmaps = new BitmapLruCache();

	public static Bitmap put(int drawableId){
		Bitmap bitmap = SpriteUtil.createScaledBitmap(drawableId);
		bitmaps.put(drawableId, bitmap);
		Log.i(TAG, "Scaled and put bitmap (" + sizeOf(bitmap) + " KB) into cache ("+ bitmaps.size() +" KB)");
		return bitmap;
	}

	public static Bitmap get(int drawableId){
		Bitmap bitmap = bitmaps.get(drawableId);
		if(bitmap != null){
			Log.i(TAG, "Getting bitmap from cache");
			return bitmap;	
		} else {
			return put(drawableId);
		}
	}

	@Deprecated
	public static void loadDrawables(Class<?> clz){
		final Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			final int drawableId;
			try {
				drawableId = field.getInt(clz);
			} catch (Exception e) {
				continue;
			}
			Log.i(TAG, field.getName());
			put(drawableId);
		}	
	}

	private static float sizeOf(Bitmap data) {
		return data.getRowBytes() * data.getHeight() / 1024;
	}
}
