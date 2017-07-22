package self.edu.jsondemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_showPostBtn(View view) {
        Intent showPostsActivity = new Intent(this, DisplayPostsActivity.class);
        startActivity(showPostsActivity);
    }

    public void onClick_showCommentsBtn(View view) {
        Intent showCommentsActivity = new Intent(this, CommentsListActivity.class);
        startActivity(showCommentsActivity);

    }

}
