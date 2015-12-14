package ca.vickypatel.dagger_2.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ca.vickypatel.dagger_2.R;
import ca.vickypatel.dagger_2.adapter.DatabaseAdapter;
import ca.vickypatel.dagger_2.extras.MyApplication;
import ca.vickypatel.dagger_2.network.VolleySingleton;

public class DisplayActivity extends AppCompatActivity {


    @Inject
    @Named("default")
    public SharedPreferences defaultSharedPreferences;

    @Inject
    @Named("secret")
    public SharedPreferences secretSharedPreferences;

    @Inject
    public VolleySingleton volleySingleton;

    @Inject
    public RequestQueue requestQueue;

    @Inject
    public DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize all inject variables
        ((MyApplication)getApplication()).getComponent().inject(this);


        String string = defaultSharedPreferences.getString("Name", "");
        TextView textView = (TextView) findViewById(R.id.text);
        String string1 = secretSharedPreferences.getString("Last Name", "");
        textView.setText("Hello " + string + " from default sharedPref" + "\nHello " + string1 + " from secret sharedPref");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall();
            }
        });
    }


    private void makeCall() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                "http://jsonplaceholder.typicode.com/posts/1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {

                            long totalRows = adapter.insertIntoTest(response.getString("body"), response.getString("title"));
                            System.out.println("Total rows inserted " + totalRows);
                            Toast.makeText(getApplicationContext(), "Total rows inserted " +totalRows, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                return headers;
            }

        };

        requestQueue.add(jsonObjReq);

    }

}
