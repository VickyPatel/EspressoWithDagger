package ca.vickypatel.dagger_2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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

public class ScrollingActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ((MyApplication) getApplication()).getComponent().inject(this);

        SharedPreferences.Editor editor = defaultSharedPreferences.edit();
        editor.putString("Name", "vicky");
        editor.apply();


        SharedPreferences.Editor editor2 = secretSharedPreferences.edit();
        editor2.putString("Last Name", "patel");
        editor2.apply();


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScrollingActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });



        final Button makeNetworkCall = (Button) findViewById(R.id.makeNetworkCall);
        makeNetworkCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeCall() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                "http://jsonplaceholder.typicode.com/posts/1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            Toast.makeText(getApplicationContext(),response.getString("title"),Toast.LENGTH_LONG).show();

                            SharedPreferences.Editor editor = secretSharedPreferences.edit();
                            editor.putString("response", response.getString("title"));
                            editor.apply();

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
