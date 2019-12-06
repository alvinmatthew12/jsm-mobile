package com.example.jsm_project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.example.jsm_project.HomeActivity;
import com.example.jsm_project.R;

import java.util.HashMap;
import java.util.Map;

public class EditCustomerActivity extends AppCompatActivity {

    private static String API_UPDATE;
    public int idcustomer;
    Customer customer;
    EditText edtCustomerName, edtCustomerAddress, edtCustomerContact, edtCustomerCity, edtCustomerCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        edtCustomerName = findViewById(R.id.edtCustomerName);
        edtCustomerAddress = findViewById(R.id.edtCustomerAddress);
        edtCustomerContact = findViewById(R.id.edtCustomerContact);
        edtCustomerCity = findViewById(R.id.edtCustomerCity);
        edtCustomerCountry = findViewById(R.id.edtCustomerCountry);

        Bundle extras = getIntent().getExtras();
        customer = new Customer(
                extras.getInt("id"),
                extras.getString("name"),
                extras.getString("address"),
                extras.getString("contact_no"),
                extras.getString("city"),
                extras.getString("country")
        );
        idcustomer = extras.getInt("id");
        edtCustomerName.setText(customer.getCustomerName());
        edtCustomerAddress.setText(customer.getCustomerAddress());
        edtCustomerContact.setText(customer.getCustomerContact());
        edtCustomerCity.setText(customer.getCustomerCity());
        edtCustomerCountry.setText(customer.getCustomerCountry());
    }

    public void editCustomer(View view) {
        try {
            API_UPDATE="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Customer.php?method=put&id="+idcustomer;
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, API_UPDATE, new Response.Listener<String>() {

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
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    String id_customer = String.valueOf(customer.getId());
                    params.put("id", id_customer);
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

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
       Intent intent = new Intent(EditCustomerActivity.this, CustomerActivity.class);
       startActivity(intent);
       finish();
    }
}
