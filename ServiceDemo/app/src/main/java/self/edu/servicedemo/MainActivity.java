package self.edu.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private Intent musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startInfiniteLoop();
    }

    public void onClick_startServiceBtn(View view) {
        //Start Service
        String audioFilePath = "/storage/sdcard/test_ringtone.mp3";

        musicService = new Intent(this, MusicService.class);
        // sending data to service
        musicService.putExtra("KEY_AUDIO_FILE_PATH", audioFilePath);
        startService(musicService);
    }

    public void onClick_stopServiceBtn(View view) {
        //Stop Service
        if (musicService != null) {
            stopService(musicService);
        }
    }

    private void startInfiniteLoop() {
        while (true) {
            //Do Nothing
        }
    }
}
