package self.edu.locationdemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final long TIME = 5 * 1000;
    private static final float DISTANCE = 10;

    public static final String TAG = "locationdemo";
    private TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        locationText = (TextView) findViewById(R.id.result_text);
    }

    public void onClick_retrieveLocationBtn(View view) {

        // check OS version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // check for permission
            if (
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            &&
                            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Asking for permission
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                Toast.makeText(this, "Please grant Permissions", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME, DISTANCE,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        // retrieve locationText
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        Log.d(TAG, "onLocationChanged: latitude=" + latitude + ",longitude=" + longitude);
                        String userCurrentLocation = "(" + latitude + "," + longitude + ")";
                        locationText.setText(userCurrentLocation);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {
                    }

                    @Override
                    public void onProviderEnabled(String s) {
                    }

                    @Override
                    public void onProviderDisabled(String s) {
                    }
                });
    }

}
