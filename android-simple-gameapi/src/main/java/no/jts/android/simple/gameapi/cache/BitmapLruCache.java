package no.jts.android.simple.gameapi.cache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class BitmapLruCache extends LruCache<Integer, Bitmap> {
	private static final String TAG = "BitmapLruCache";
	private final static int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	private final static int cacheSize = maxMemory / 2;
	
	public BitmapLruCache() {
		super(cacheSize);
		Log.i(TAG, "Device memory size is " + maxMemory);
		Log.i(TAG, "Cache memory size is " + cacheSize);
	}

	@Override
	protected int sizeOf(Integer key, Bitmap bitmap) {
		// The cache size will be measured in kilobytes rather than number of items.
		return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
	}
}
