package self.edu.activitydemo2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick_registerBtn(View view) {
        //TODO code for accepting data and saving it in ...
        setResult(Activity.RESULT_OK);
        finish();
    }
}
