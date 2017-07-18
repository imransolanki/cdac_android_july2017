package self.edu.frameanimationdemo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startFillingHeartAnimation();
        startEmptyHeartAnimation();
    }

    private void startEmptyHeartAnimation() {
        ImageView heart = (ImageView) findViewById(R.id.empty_heart);
        AnimationDrawable animation = (AnimationDrawable) heart.getBackground();
        animation.start();
    }

    private void startFillingHeartAnimation() {
        ImageView heart = (ImageView) findViewById(R.id.fill_heart);

        //((AnimationDrawable) heart.getBackground()).start();
        AnimationDrawable animation = (AnimationDrawable) heart.getBackground();
        animation.start();
    }
}
