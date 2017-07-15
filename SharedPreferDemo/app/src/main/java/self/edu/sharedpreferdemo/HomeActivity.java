package self.edu.sharedpreferdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {


    private TextView userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUi();
    }

    private void initUi() {

        userEmail = (TextView) findViewById(R.id.savedEmail);
        userPassword = (TextView) findViewById(R.id.savedPassword);

        SharedPreferences sharedPreferences = this.getSharedPreferences("self.edu.sharedpreferdemo.SHARED_PREF_FILE", Context.MODE_PRIVATE);

        // retrieving data from shared preference
        String email = sharedPreferences.getString("KEY_USER_EMAIL", "No EmailId Present");
        String password = sharedPreferences.getString("KEY_USER_PASSWORD", "*****");

        userEmail.setText(email);
        userPassword.setText(password);
    }
}
