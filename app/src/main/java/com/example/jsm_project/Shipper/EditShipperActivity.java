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
import com.example.jsm_project.Customer.Customer;
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.R;

import java.util.HashMap;
import java.util.Map;

public class EditShipperActivity extends AppCompatActivity {
    private static String API_UPDATE;
    public int idshipper;
    Shipper shipper;
    EditText edtShipperName, edtShipperAddress, edtShipperContact, edtShipperCity, edtShipperCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shipper);
        edtShipperName = findViewById(R.id.edtShipperName);
        edtShipperAddress = findViewById(R.id.edtShipperAddress);
        edtShipperContact = findViewById(R.id.edtShipperContact);
        edtShipperCity = findViewById(R.id.edtShipperCity);
        edtShipperCountry = findViewById(R.id.edtShipperCountry);

        Bundle extras = getIntent().getExtras();
        shipper = new Shipper (
                extras.getInt("id"),
                extras.getString("name"),
                extras.getString("address"),
                extras.getString("contact_no"),
                extras.getString("city"),
                extras.getString("country")
        );

        idshipper = extras.getInt("id");
        edtShipperName.setText(shipper.getShipperName());
        edtShipperAddress.setText(shipper.getShipperAddress());
        edtShipperContact.setText(shipper.getShipperContact());
        edtShipperCity.setText(shipper.getShipperCity());
        edtShipperCountry.setText(shipper.getShipperCountry());
    }

    public void editShipper(View view) {
        try {
            API_UPDATE = "http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Shipper.php?method=put&id="+idshipper;
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, API_UPDATE , new Response.Listener<String>() {

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
