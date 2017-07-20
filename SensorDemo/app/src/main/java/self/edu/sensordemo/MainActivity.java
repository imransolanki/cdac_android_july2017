package self.edu.sensordemo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {

    private ListView sensorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        sensorListView = (ListView) findViewById(R.id.sensor_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listAllSensors();
    }

    private void listAllSensors() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        String sensors[] = new String[sensorList.size()];
        int i = 0;
        for (Sensor sensor : sensorList) {
            String name = sensor.getName();
            sensors[i] = name;
            i++;
        }

        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensors);
        sensorListView.setAdapter(arrayAdapter);

    }
}
