package self.edu.wifimanagerdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {

    public static final String TAG = "wifidemo";

    private WifiManager wifiManager;
    private ProgressDialog progressDialog;
    private ListView wifiAPList;

    private BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            unregisterReceiver(wifiScanReceiver);

            progressDialog.dismiss();

            List<ScanResult> scanResults = wifiManager.getScanResults();
            String[] accessPoints;
            if (scanResults.size() > 0) {
                accessPoints = new String[scanResults.size()];
                int i = 0;
                for (ScanResult wifi : scanResults) {
                    Log.d(TAG, "onReceive: scanResult=" + wifi.SSID);
                    accessPoints[i] = wifi.SSID;
                    i++;
                }
            } else {
                return;
            }

            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, accessPoints);
            wifiAPList.setAdapter(arrayAdapter);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi() {
        wifiAPList = (ListView) findViewById(R.id.wifi_ap_list);
    }


    @Override
    protected void onStart() {
        super.onStart();
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        wifiManager.startScan();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.progress_dlg_title));
        progressDialog.setMessage(getString(R.string.progress_dlg_msg));
        progressDialog.show();
    }
}
