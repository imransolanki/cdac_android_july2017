package self.edu.jsondemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayPostsActivity extends Activity {

    public static final String TAG = "JSONDemo";
    private ProgressDialog progressDialog;
    private TextView userIdText, idText, titleText, bodyText;
    private int userId, id;
    private String body, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_posts);
        initUi();
    }

    private void initUi() {
        userIdText = (TextView) findViewById(R.id.user_id);
        idText = (TextView) findViewById(R.id.id);
        titleText = (TextView) findViewById(R.id.user_title);
        bodyText = (TextView) findViewById(R.id.body);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void populateData() {
        idText.setText(String.valueOf(id));
        userIdText.setText(String.valueOf(userId));
        titleText.setText(title);
        bodyText.setText(body);
    }

    private void getData() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d(TAG, "onResponse: response=" + response);
                parseResult(response);
                populateData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e(TAG, "onErrorResponse: ", error.getCause());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.json_demo));
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.show();
    }

    private void parseResult(String response) {
        JSONObject post;
        try {
            post = new JSONObject(response);
            userId = post.getInt("userId");
            id = post.getInt("id");
            title = post.getString("title");
            body = post.getString("body");
        } catch (JSONException e) {
            Log.e(TAG, "parseResult: ", e);
        }
    }
}
