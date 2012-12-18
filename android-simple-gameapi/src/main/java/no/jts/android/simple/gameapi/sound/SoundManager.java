package no.jts.android.simple.gameapi.sound;

import java.util.HashMap;
import java.util.Map;

import no.jts.android.simple.gameapi.setup.Globals;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.KeyEvent;

public class SoundManager {

	private static SoundManager instance = null;

	private SoundPool soundPool;
	private Map<String, Integer> soundPoolMap;
	private AudioManager  audioManager;
	protected Context context;
	private boolean isSoundEnabled = true;

	public static SoundManager getInstance(){
		if (instance == null) {
			synchronized (SoundManager.class){
				instance = new SoundManager();
			}
		}
		return instance;
	}

	private SoundManager() {
		this.context = Globals.context;
		this.soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		this.soundPoolMap = new HashMap<String, Integer>();
		this.audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
	}

	public void setSoundEnabled(boolean isSoundEnabled){
		this.isSoundEnabled = isSoundEnabled;
	}

	public void addSound(String index, int soundID){
		soundPoolMap.put(index, soundPool.load(context, soundID, 1));
	}

	public void playSound(String index){
		if (isSoundEnabled) {
			float streamVolume = getStreamVolume();
			soundPool.play(soundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
		}
	}

	public void playLoopedSound(String index){
		if (isSoundEnabled) {
			float streamVolume = getStreamVolume();
			soundPool.play(soundPoolMap.get(index), streamVolume, streamVolume, 1, -1, 1f);
		}
	}

	public void stop(String index){
		soundPool.stop(soundPoolMap.get(index));
	}

	private float getStreamVolume() {
		float streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		return streamVolume / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	}

	public void cleanup() {
		soundPool.release();
		soundPool = null;
		soundPoolMap.clear();
		audioManager.unloadSoundEffects();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(audioManager != null){ 
			switch (keyCode) {
			case KeyEvent.KEYCODE_VOLUME_UP:
				audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
				break;
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
				break;
			}
		}
		return true;
	}
}
