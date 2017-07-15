package self.edu.activitydemo1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {

    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // retrieve the passed data
        String userName = getIntent().getStringExtra("KEY_USER_NAME");
        Toast.makeText(this, "Welcome " + userName, Toast.LENGTH_LONG).show();

        user = (TextView) findViewById(R.id.user);
        user.setText(userName);
    }
}
