package com.example.jsm_project.Container;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsm_project.BillofLanding.BillofLandingActivity;
import com.example.jsm_project.Customer.Customer;
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.Customer.EditCustomerActivity;
import com.example.jsm_project.HomeActivity;
import com.example.jsm_project.R;
import com.example.jsm_project.Shipper.Shipper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditContainerActivity extends AppCompatActivity {
    private static String API_UPDATE;
    private static final String API_GET_BL = "http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/BillOfLanding.php?method=get&type=all";
    private String bl_id;
    public int idcontainer;
    Container container;
    EditText edtContainerNo, edtContainerName, edtBlId;
    ArrayList<String> blSpinner;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_container);
        edtContainerNo = findViewById(R.id.edtContainerNo);
        edtContainerName = findViewById(R.id.edtContainerName);
//        edtBlId = findViewById(R.id.edtBlId);
        Bundle extras = getIntent().getExtras();
        container = new Container(
                extras.getInt("id"),
                extras.getString("no"),
                extras.getString("name"),
                extras.getString("bl_id")
        );

        bl_id = extras.getString("bl_id");
        idcontainer = extras.getInt("id");
        edtContainerNo.setText(container.getContainerNo());
        edtContainerName.setText(container.getContainerName());
//        edtBlId.setText(container.getBlID());





        blSpinner = new ArrayList<>();
        spinner = (Spinner)findViewById(R.id.spinner_blno);
        loadSpinnerBL();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bl_id = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                    params.put("bl_id", bl_id);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadSpinnerBL() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, API_GET_BL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
//                        String bl_id = jsonObject.getString("no");
                        String bl_number = jsonObject.getString("no");
                        blSpinner.add(bl_number);
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(EditContainerActivity.this, R.layout.spinnerbold_card,R.id.text, blSpinner));
                    ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
                    int position = adapter.getPosition(bl_id);
                    spinner.setSelection(position);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        Intent intent = new Intent(EditContainerActivity.this, ContainerActivity.class);
        startActivity(intent);
        finish();
    }

}
