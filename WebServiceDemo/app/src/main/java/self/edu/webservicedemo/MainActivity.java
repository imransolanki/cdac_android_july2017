package self.edu.webservicedemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

    public static final String TAG = "WebServiceDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick_webServiceBtn(View view) {
        String url = "http://www.webservicex.net/globalweather.asmx/GetCitiesByCountry?CountryName=US";
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Web Service");
        progressDialog.setMessage("Please wait...");

        // creating a string request
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response listener
                                progressDialog.dismiss();
                                Log.d(TAG, "onResponse: response=" + response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error listener
                                progressDialog.dismiss();
                                Log.d(TAG, "onErrorResponse: Error=" + error.toString());
                            }
                        });
        // this will call your web service
        requestQueue.add(stringRequest);

        progressDialog.show();
    }
}
