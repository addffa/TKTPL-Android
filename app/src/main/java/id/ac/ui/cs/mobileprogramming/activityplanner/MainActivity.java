package id.ac.ui.cs.mobileprogramming.activityplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int LOCATION_PERMISSION_CODE = 0;

    public static final String POST_TAG = "PostTag";

    public static final String URL = "https://e9e8bca7862988920fe6e83f2b8916cf.m.pipedream.net/";

    BroadcastReceiver wifiScanReceiver;

    LinearLayoutManager layoutManager;

    ListWifiAdapter mAdapter;

    List<ScanResult> results;

    RecyclerView recyclerView;

    WifiManager wifiManager;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list_wifi);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListWifiAdapter();
        recyclerView.setAdapter(mAdapter);

        Context context = getApplicationContext();
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    scanSuccess();
                } else {
                    scanFailure();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver, intentFilter);

        if (ContextCompat.checkSelfPermission(
                getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE);
        }

        queue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(POST_TAG);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Click button to scan wifi",
                        Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }
        }
    }

    private void scanSuccess() {
        results = wifiManager.getScanResults();
        mAdapter.updateWifi(results);
        Toast.makeText(this, "Scanning Success!", Toast.LENGTH_SHORT).show();
    }

    private void scanFailure() {
        Toast.makeText(this, "Scanning Fail!", Toast.LENGTH_SHORT).show();
    }

    public void scanWifi(View view) {
        boolean success = wifiManager.startScan();
        if (!success) {
            scanFailure();
        }
        Log.d("masuk", "button clicked");
    }

    private JsonObjectRequest makePostRequest(List<ScanResult> scanResults) {
        JSONObject postData = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (ScanResult result: scanResults) {
            jsonArray.put(result.SSID);
        }
        try {
            postData.put("listWifi", jsonArray);
            Log.d("masuk", postData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JsonObjectRequest(Request.Method.POST, URL, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void postData(View view) {
        if (results != null && results.size() > 0) {
            JsonObjectRequest postRequest = makePostRequest(results);
            postRequest.setTag(POST_TAG);
            queue.add(postRequest);
        } else {
            Toast.makeText(this, "No Wifi Found", Toast.LENGTH_SHORT).show();
        }
    }
}