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
import com.example.jsm_project.Container.BillofLanding;
import com.example.jsm_project.Customer.Customer;
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.Customer.EditCustomerActivity;
import com.example.jsm_project.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditBillofLandingActivity extends AppCompatActivity {
    private static String API_UPDATE;
    private static final String API_GET_SHIPPER ="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Shipper.php?method=get&type=all";
    private static final String API_GET_CUSTOMER ="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Customer.php?method=get&type=all";

    public int idbilloflanding;
    BillofLanding billofLanding;
    EditText edtBlNo, edtShippingDate, edtDateOfReceipt;
    Spinner shipperid_spinner, customerid_spinner, status_spinner;
    private String shipper_id, customer_id, statusket,status;
    ArrayList<String> Shipper_Spinner;
    ArrayList<String> Customer_Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_billof_landing);

        edtBlNo = findViewById(R.id.edtBlNo);
        edtShippingDate = findViewById(R.id.edtShippingDate);
        edtDateOfReceipt= findViewById(R.id.edtDateOfReceipt);
        shipperid_spinner = findViewById(R.id.shipperid_spinner);
        customerid_spinner = findViewById(R.id.customerid_spinner);
        status_spinner = findViewById(R.id.status_spinner);
        Shipper_Spinner = new ArrayList<>();
        Customer_Spinner = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        billofLanding = new BillofLanding(
                extras.getInt("id"),
                extras.getString("no"),
                extras.getString("shipping_date"),
                extras.getString("date_of_receipt"),
                extras.getString("shipper_id"),
                extras.getString("customer_id"),
                extras.getString("status")
                );
        idbilloflanding = extras.getInt("id");
        shipper_id = extras.getString("shipper_id");
        customer_id = extras.getString("customer_id");
        status = extras.getString("status");

        String[] Status =getResources().getStringArray(R.array.array_status);
        ArrayAdapter<String> Statusadapter=new ArrayAdapter<String>(this,R.layout.spinnerbold_card,R.id.text, Status);
        status_spinner.setAdapter(Statusadapter);
        statusket = status_spinner.getItemAtPosition(status_spinner.getSelectedItemPosition()).toString();
        int selectionPosition= Statusadapter.getPosition(status);
        status_spinner.setSelection(selectionPosition);


        edtBlNo.setText(billofLanding.getBilloflandingNo());
        edtShippingDate.setText(billofLanding.getShippingDate());
        edtDateOfReceipt.setText(billofLanding.getDateofReceipt());


    }


    public void editBillOfLanding(View view) {
        try {
            API_UPDATE="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/BillOfLanding.php?method=put&id="+idbilloflanding;
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, API_UPDATE, new Response.Listener<String>() {

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
                    String id_bl = String.valueOf(billofLanding.getId());
                    params.put("id", id_bl);
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

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        Intent intent = new Intent(EditBillofLandingActivity.this, BillofLandingActivity.class);
        startActivity(intent);
        finish();
    }
}