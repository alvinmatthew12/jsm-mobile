package com.example.jsm_project.Container;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.R;

import java.util.HashMap;
import java.util.Map;

public class AddContainerActivity extends AppCompatActivity {
    private static final String API_INSERT = "http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Container.php?method=post";

    EditText edtContainerNo, edtContainerName, edtIdBl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_container);
        edtContainerNo = findViewById(R.id.edtContainerNo);
        edtContainerName = findViewById(R.id.edtContainerName);
        edtIdBl = findViewById(R.id.edtBlId);
    }

    public void addContainer(View view) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, API_INSERT, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d("asd", response);
                    Intent intent = new Intent(getApplicationContext(), ContainerActivity.class);
                    startActivity(intent);
                    finish();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }
            ) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("no",  edtContainerNo.getText().toString());
                    params.put("name", edtContainerName.getText().toString());
                    params.put("bl_id", edtIdBl.getText().toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
