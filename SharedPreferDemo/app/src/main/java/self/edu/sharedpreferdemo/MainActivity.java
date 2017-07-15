package self.edu.sharedpreferdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String TAG = "SharedPrefDemo";

    private EditText emailId, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi() {
        emailId = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

    }


    public void onClick_loginBtn(View view) {
        String userEmail = emailId.getText().toString();
        String userPassword = password.getText().toString();
        Log.d(TAG, "emailId=" + userEmail);


        SharedPreferences sharedPreferences = this.getSharedPreferences("self.edu.sharedpreferdemo.SHARED_PREF_FILE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // store username
        editor.putString("KEY_USER_EMAIL", userEmail);
        editor.commit();

        // store password
        editor.putString("KEY_USER_PASSWORD", userPassword);
        editor.commit();

        startHomeActivity();

        // destroy current activity
        finish();
    }

    public void onClick_showLastEmailBtn(View view) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("self.edu.sharedpreferdemo.SHARED_PREF_FILE", Context.MODE_PRIVATE);
        String lastEmail = sharedPreferences.getString("KEY_USER_EMAIL", "No EmailId Present");
        Toast.makeText(this, "Last Email was " + lastEmail, Toast.LENGTH_LONG).show();
    }

    private void startHomeActivity() {
        Intent homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);
    }
}
