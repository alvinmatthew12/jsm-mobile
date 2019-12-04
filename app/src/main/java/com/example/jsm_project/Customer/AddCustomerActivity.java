package com.example.jsm_project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsm_project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddCustomerActivity extends AppCompatActivity {
//    private static final String API_INSERT = "http://batam.shop/api_5psi/lastminute/jsm-api/api/v1/Customer.php?method=post";
      private static final String API_INSERT="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Customer.php?method=post";

    EditText edtCustomerName, edtCustomerAddress, edtCustomerContact, edtCustomerCity, edtCustomerCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        edtCustomerName = findViewById(R.id.edtCustomerName);
        edtCustomerAddress = findViewById(R.id.edtCustomerAddress);
        edtCustomerContact = findViewById(R.id.edtCustomerContact);
        edtCustomerCity = findViewById(R.id.edtCustomerCity);
        edtCustomerCountry = findViewById(R.id.edtCustomerCountry);
    }

    public void addCustomer(View view) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, API_INSERT, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d("asd", response);
                    Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
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
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Accept", "application/json");
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name",  edtCustomerName.getText().toString());
                    params.put("address", edtCustomerAddress.getText().toString());
                    params.put("contact_no", edtCustomerContact.getText().toString());
                    params.put("city", edtCustomerCity.getText().toString());
                    params.put("country", edtCustomerCountry.getText().toString());

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
