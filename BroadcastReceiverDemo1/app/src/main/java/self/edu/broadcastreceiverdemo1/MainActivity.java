package self.edu.broadcastreceiverdemo1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {


    private TextView batteryLevelText;

    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            batteryLevelText.setText(String.valueOf(batteryLevel) + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        registerBatteryReceiver();
    }

    private void initUi() {
        batteryLevelText = (TextView) findViewById(R.id.battery_level);
    }

    private void registerBatteryReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        unregisterBatteryReceiver();
        super.onDestroy();
    }

    private void unregisterBatteryReceiver() {
        unregisterReceiver(batteryReceiver);
    }
}
