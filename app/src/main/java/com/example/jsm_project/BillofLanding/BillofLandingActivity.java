package com.example.jsm_project.BillofLanding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsm_project.Container.BillofLanding;
import com.example.jsm_project.HomeActivity;
import com.example.jsm_project.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BillofLandingActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private static final String API_GET="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/BillOfLanding.php?method=get&type=all";

    ListView lvBL;
    List<BillofLanding> blList;

    List<BillofLanding> blArray;
    BillofLandingAdapter bladapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billof_landing);

        lvBL = findViewById(R.id.lvBilloflanding);
        blList = new ArrayList<BillofLanding>();
        getBL();


        searchView = findViewById(R.id.srcBilloflanding);
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(true);

        blArray = new ArrayList<BillofLanding>();
        bladapter = new BillofLandingAdapter(this, blList);
        lvBL.setAdapter(bladapter);
        searchView.setOnQueryTextListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                blList.clear();
                for (BillofLanding bl: blArray){
                    if (bl.getBilloflandingNo().contains(newText)) {
                        blList.add(bl);
                    }
                }
                bladapter.notifyDataSetChanged();
                return true;
            }
        });

        Button btn_add = findViewById(R.id.btn_add_Billoflanding);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddBillofLandingActivity.class);
                startActivity(intent);

            }
        });

    }

    public void getBL() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, API_GET, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        BillofLanding billoflanding = new BillofLanding(
                                jsonObject.getInt("id"),
                                jsonObject.getString("no"),
                                jsonObject.getString("shipping_date"),
                                jsonObject.getString("date_of_receipt"),
                                jsonObject.getString("shipper_id"),
                                jsonObject.getString("customer_id"),
                                jsonObject.getString("status")
                        );
                        blList.add(billoflanding);
                        blArray.add(billoflanding);
                    }
                    BillofLandingAdapter blAdapter = new BillofLandingAdapter(getApplicationContext(), blList);
                    lvBL.setAdapter(blAdapter);
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(blList.contains(newText)){
            bladapter.getFilter().filter(newText);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        Intent intent = new Intent(BillofLandingActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
