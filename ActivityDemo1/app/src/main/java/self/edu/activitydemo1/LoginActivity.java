package self.edu.activitydemo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    public static final String TAG = "cdac-1";

    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //!!!
        setContentView(R.layout.activity_login);

        initUi();
        Log.d(TAG, "onCreate()");
    }

    private void initUi() {
        username = (EditText) findViewById(R.id.username);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }


    public void onClick_loginBtn(View view) {
        // code to be executed on click of Login button

        String name = username.getText().toString();
        Log.d(TAG, "User clicked on Login button. name=" + name);

        Toast.makeText(this, "Login clicked with name=" + name, Toast.LENGTH_SHORT).show();

        startHomeScreen(name);
    }

    private void startHomeScreen(String name) {
        // create intent object
        Intent homeActivity = new Intent(this, HomeActivity.class);
        // set data to be passed on another activity
        homeActivity.putExtra("KEY_USER_NAME", name);
        // start activity
        startActivity(homeActivity);
    }

}
