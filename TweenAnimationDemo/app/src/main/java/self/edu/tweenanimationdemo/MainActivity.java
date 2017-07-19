package self.edu.tweenanimationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView iconHeart, rectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startHeartAnimation();
        startRectangleAnimation();
    }

    private void startRectangleAnimation() {
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rectangle.setAnimation(rotateAnimation);

    }

    private void startHeartAnimation() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        iconHeart.setAnimation(fadeInAnimation);
    }

    private void initUi() {
        iconHeart = (ImageView) findViewById(R.id.icon_heart);
        rectangle = (ImageView) findViewById(R.id.rectangle);
    }
}
