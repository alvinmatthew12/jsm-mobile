package com.example.jsm_project.Shipper;

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
import com.example.jsm_project.R;

import java.util.HashMap;
import java.util.Map;

public class AddShipperActivity extends AppCompatActivity {
//    private static final String API_INSERT = "http://batam.shop/rightshop/insertproduct.php";
    private static final String API_INSERT = "http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Shipper.php?method=post";
    EditText edtShipperName, edtShipperAddress, edtShipperContact, edtShipperCity, edtShipperCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipper);
        edtShipperName = findViewById(R.id.edtShipperName);
        edtShipperAddress = findViewById(R.id.edtShipperAddress);
        edtShipperContact = findViewById(R.id.edtShipperContact);
        edtShipperCity = findViewById(R.id.edtShipperCity);
        edtShipperCountry = findViewById(R.id.edtShipperCountry);
    }

    public void addShipper(View view) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, API_INSERT, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d("asd", response);
                    Intent intent = new Intent(getApplicationContext(), ShipperActivity.class);
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
                    params.put("name",  edtShipperName.getText().toString());
                    params.put("address", edtShipperAddress.getText().toString());
                    params.put("contact_no", edtShipperContact.getText().toString());
                    params.put("city", edtShipperCity.getText().toString());
                    params.put("country", edtShipperCountry.getText().toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
