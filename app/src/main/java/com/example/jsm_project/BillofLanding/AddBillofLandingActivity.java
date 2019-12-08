package com.example.jsm_project.BillofLanding;

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
import com.example.jsm_project.Container.AddContainerActivity;
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddBillofLandingActivity extends AppCompatActivity {

    private static final String API_INSERT = "http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/BillOfLanding.php?method=post";
    private static final String API_GET_SHIPPER ="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Shipper.php?method=get&type=all";
    private static final String API_GET_CUSTOMER ="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Customer.php?method=get&type=all";

    EditText edtBlNo, edtShippingDate, edtDateOfReceipt;
    Spinner shipperid_spinner, customerid_spinner, status_spinner;
    private String shipper_id, customer_id, statusket;
    ArrayList<String> Shipper_Spinner;
    ArrayList<String> Customer_Spinner;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_billof_landing);

            edtBlNo= findViewById(R.id.edtBlNo);
            edtShippingDate = findViewById(R.id.edtShippingDate);
            edtDateOfReceipt = findViewById(R.id.edtDateOfReceipt);
            shipperid_spinner = findViewById(R.id.shipperid_spinner);
            customerid_spinner = findViewById(R.id.customerid_spinner);
            status_spinner = findViewById(R.id.status_spinner);
            Shipper_Spinner = new ArrayList<>();
            Customer_Spinner = new ArrayList<>();


            String[] Status =getResources().getStringArray(R.array.array_status);
            ArrayAdapter<String> Statusadapter = new ArrayAdapter<String>(this,R.layout.spinnerbold_card,R.id.text, Status);
            status_spinner.setAdapter(Statusadapter);
            statusket = status_spinner.getItemAtPosition(status_spinner.getSelectedItemPosition()).toString();

            loadShipperSpinner();
            shipperid_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    shipper_id = shipperid_spinner.getItemAtPosition(shipperid_spinner.getSelectedItemPosition()).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            loadCustomerSpinner();
            customerid_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    customer_id = customerid_spinner.getItemAtPosition(customerid_spinner.getSelectedItemPosition()).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        public void addBillOfLanding(View view) {
            try {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, API_INSERT, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("asd", response);
                        Intent intent = new Intent(getApplicationContext(), BillofLandingActivity.class);
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
                        params.put("no",  edtBlNo.getText().toString());
                        params.put("shipping_date", edtShippingDate.getText().toString());
                        params.put("date_of_receipt", edtDateOfReceipt.getText().toString());
                        params.put("shipper_id", shipper_id);
                        params.put("customer_id", customer_id);
                        params.put("status", status_spinner.getItemAtPosition(status_spinner.getSelectedItemPosition()).toString());

                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void loadShipperSpinner() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, API_GET_SHIPPER, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String shipper_name= jsonObject.getString("name");
                        Shipper_Spinner.add(shipper_name);
                    }
                    shipperid_spinner.setAdapter(new ArrayAdapter<String>(AddBillofLandingActivity.this, R.layout.spinnerbold_card,R.id.text, Shipper_Spinner));

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

    public void loadCustomerSpinner() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, API_GET_CUSTOMER, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String customer_id= jsonObject.getString("name");
                        Customer_Spinner.add(customer_id);
                    }
                    customerid_spinner.setAdapter(new ArrayAdapter<String>(AddBillofLandingActivity.this, R.layout.spinnerbold_card,R.id.text, Customer_Spinner));

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
    }