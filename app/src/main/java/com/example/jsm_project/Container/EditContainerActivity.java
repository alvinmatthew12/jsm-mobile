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
import com.example.jsm_project.Customer.Customer;
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.Customer.EditCustomerActivity;
import com.example.jsm_project.R;
import com.example.jsm_project.Shipper.Shipper;

import java.util.HashMap;
import java.util.Map;

public class EditContainerActivity extends AppCompatActivity {
    private static String API_UPDATE;
    public int idcontainer;
    Container container;
    EditText edtContainerNo, edtContainerName, edtBlId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_container);
        edtContainerNo = findViewById(R.id.edtContainerNo);
        edtContainerName = findViewById(R.id.edtContainerName);
        edtBlId = findViewById(R.id.edtBlId);
        Bundle extras = getIntent().getExtras();
        container = new Container(
                extras.getInt("id"),
                extras.getString("no"),
                extras.getString("name"),
                extras.getString("bl_id")
        );

        idcontainer = extras.getInt("id");
        edtContainerNo.setText(container.getContainerNo());
        edtContainerName.setText(container.getContainerName());
        edtBlId.setText(container.getBlID());
    }

    public void editContainer(View view) {
        try {
            API_UPDATE="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Container.php?method=put&id="+idcontainer;
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, API_UPDATE, new Response.Listener<String>() {

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
                    String id_container = String.valueOf(container.getId());
                    params.put("id", id_container);
                    params.put("no",  edtContainerNo.getText().toString());
                    params.put("name", edtContainerName.getText().toString());
                    params.put("bl_id", edtBlId.getText().toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
