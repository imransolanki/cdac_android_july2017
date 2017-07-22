package self.edu.asynctaskdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String TAG = "AsyncTaskDemo";

    private TextView counterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        counterText = (TextView) findViewById(R.id.counter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //startLoopAndDisplayCount();

        // start async task
        CounterTask counterTask = new CounterTask(counterText);
        counterTask.execute();
    }


    // Params,Progress,Result
    class CounterTask extends AsyncTask<Void, Integer, Void> {

        private TextView textView;

        public CounterTask(TextView textView) {
            this.textView = textView;
        }

        @Override
        protected void onPreExecute() {
            // anything you want to execute before starting task
            Toast.makeText(MainActivity.this, "Starting Counting...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                publishProgress(i);
                try {
                    // Thread.sleep(5 * 1000);
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "doInBackground: ", e);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this, "Completed Successfully...", Toast.LENGTH_LONG).show();
        }
    }


    private void startLoopAndDisplayCount() {
        int count = 0;

        // infinite loop
        while (true) {
            // display count
            counterText.setText(String.valueOf(count));
            // sleep for 5 seconds
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "startLoopAndDisplayCount: ", e);
            }
            // increment counter
            count++;
        }
    }
}
