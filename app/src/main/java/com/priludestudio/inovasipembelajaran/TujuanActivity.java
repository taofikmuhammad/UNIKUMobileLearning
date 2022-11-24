package com.priludestudio.inovasipembelajaran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TujuanActivity extends AppCompatActivity {
    private RequestQueue queue;
    private TextView tvIsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujuan);
        setTitle("Tujuan Pembelajaran");

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvIsi = (TextView) findViewById(R.id.tvIsi);
        ambilData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ambilData()
    {
        queue = Volley.newRequestQueue(TujuanActivity.this);
        String url = "https://apps.priludestudio.com/pelatihan/tujuan.php";

        StringRequest jsonObjReq = new
                StringRequest(Request.Method.GET,
                        url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONObject priludeObj=jsonObject.getJSONObject("materi");
                            String materi = priludeObj.getString("isi");
                            tvIsi.setText(materi);

                            Log.d("test",materi);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String,
                                String>();
                        return params;
                    }
                    @Override
                    public Map<String, String> getHeaders() throws
                            AuthFailureError {
                        Map<String, String> params = new HashMap<String,
                                String>();
                        params.put("Content-Type", "application/x-wwwform-urlencoded");
                        return params;
                    }
                };
        final int DEFAULT_MAX_RETRIES = 1;
        final float DEFAULT_BACKOFF_MULT = 1f;
        jsonObjReq.setRetryPolicy(
                new DefaultRetryPolicy(
                        (int) TimeUnit.SECONDS.toMillis(20),
                        DEFAULT_MAX_RETRIES,
                        DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjReq);
    }
}