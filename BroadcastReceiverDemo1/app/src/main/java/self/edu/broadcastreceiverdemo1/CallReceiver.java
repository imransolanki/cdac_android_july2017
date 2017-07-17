package self.edu.broadcastreceiverdemo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by imran on 17/7/17.
 */

public class CallReceiver extends BroadcastReceiver {

    public static final String TAG = "CallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Log.d(TAG, "onReceive()-number=" + number);
        Toast.makeText(context, number, Toast.LENGTH_LONG).show();
    }
}
