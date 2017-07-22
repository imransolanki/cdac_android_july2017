package self.edu.jsondemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentsListActivity extends Activity {

    public static final String TAG = "JSONComments";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String url = "https://jsonplaceholder.typicode.com/comments";
        StringRequest commentsRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d(TAG, "onResponse: " + response);

                String[] emails = parseCommentsArray(response);
                populateListView(emails);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e(TAG, "onErrorResponse: ", error.getCause());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(commentsRequest);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.json_demo));
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.show();

    }

    private void populateListView(String[] emails) {
        ListView listView = (ListView) findViewById(R.id.comments_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emails);
        listView.setAdapter(arrayAdapter);
    }

    private String[] parseCommentsArray(String response) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(response);
        } catch (JSONException e) {
            Log.e(TAG, "parseCommentsArray: ", e);
        }

        String[] emails = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                // create again json object
                JSONObject commentData = (JSONObject) jsonArray.get(i);
                // retrieve email
                String email = commentData.getString("email");
                // storing in your array which will  be used by ArrayAdapter
                emails[i] = email;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return emails;
    }
}
