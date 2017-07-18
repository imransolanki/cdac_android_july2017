package self.edu.audiovideodemo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private ImageButton playAudioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startVideo();
    }

    private void startVideo() {
        String videoFilePath = "/storage/sdcard/dummy.mp4";

        VideoView videoView = (VideoView) findViewById(R.id.video);
        videoView.setVideoPath(videoFilePath);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.start();
    }

    private void initUi() {
        playAudioButton = (ImageButton) findViewById(R.id.ic_play_audio);
    }

    public void onClick_playBtn(View view) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.test_ringtone);
            playAudioButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playAudioButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
        } else {
            mediaPlayer.start();
            playAudioButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
        }
    }

}
