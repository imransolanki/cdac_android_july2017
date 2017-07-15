package self.edu.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by imran on 15/7/17.
 */

// Step 1
public class MusicService extends Service {


    public static final String TAG = "MusicService";

    private MediaPlayer mediaPlayer;

//    private String audioFilePath = "/storage/sdcard/test_ringtone.mp3";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // retrieving passed data
        String audioFilePath = intent.getStringExtra("KEY_AUDIO_FILE_PATH");
        try {
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.e(TAG, "onCreate: ", e);
        }
        mediaPlayer.start();

        // stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
