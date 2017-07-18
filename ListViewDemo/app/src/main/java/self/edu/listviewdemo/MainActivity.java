package self.edu.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    // Data
    private static final String[] NAMES = {"Imran", "Satnam", "UK", "Priyanka", "Ishita"};

    private int[] TO = new int[]{R.id.image_view, R.id.text};
    private String[] FROM = new String[]{"ic_image", "text"};

//    private int[] TO = new int[]{R.id.text};
//    private String[] FROM = new String[]{"text"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //populateListView();
        populateListWithCustomView();
    }

    private void populateListWithCustomView() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        // android.R.drawable.ic_lock_idle_alarm "Idle Alarm"

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        // 1st row
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(FROM[0], String.valueOf(android.R.drawable.ic_menu_delete));
        hashMap.put(FROM[1], "Delete");
        list.add(hashMap);

        // 2nd row
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put(FROM[0], String.valueOf(android.R.drawable.ic_btn_speak_now));
        hashMap1.put(FROM[1], "Speak Now");
        list.add(hashMap1);

        SimpleAdapter simpleAdapter = new
                SimpleAdapter(this, list, R.layout.custom_list_item, FROM, TO);
        listView.setAdapter(simpleAdapter);
    }

    private void populateListView() {
        // list view
        ListView listView = (ListView) findViewById(R.id.list_view);

        // adapter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, NAMES);

        listView.setAdapter(arrayAdapter);
    }
}
