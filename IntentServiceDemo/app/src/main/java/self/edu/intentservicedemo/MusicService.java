package self.edu.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by imran on 15/7/17.
 */

public class MusicService extends IntentService {

    public static final String TAG = "MusicIService";

    private MediaPlayer mediaPlayer;

    // you have to write this..
    public MusicService() {
        super("MusicService");
    }

    public MusicService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        mediaPlayer = new MediaPlayer();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // write service logic
        Log.d(TAG, "onHandleIntent()");
        String audioFilePath = "/storage/sdcard/test_ringtone.mp3";
        try {
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.e(TAG, "onHandleIntent: ", e);
        }
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        super.onDestroy();
    }
}
