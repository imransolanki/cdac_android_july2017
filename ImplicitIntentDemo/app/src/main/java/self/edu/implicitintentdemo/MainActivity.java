package self.edu.implicitintentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_launchBrowserBtn(View view) {
        // launch browser
        String url = "http://www.yahoo.co.in";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(url));
        startActivity(browserIntent);
    }

    public void onClick_launchCallBtn(View view) {
        String phoneNumber = "tel:9763067364";

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse(phoneNumber));


        if (phoneIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(phoneIntent);
        } else {
            Toast.makeText(this, "Can not call!!", Toast.LENGTH_LONG).show();
        }
    }

}
