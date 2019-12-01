package com.example.jsm_project.Shipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsm_project.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShipperActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String API_GET="http://batam.shop/api_5psi/lastminute/jsm-api/api/v1/Shipper.php?method=get";

    private Context context;
    ListView lvShipper;
    List<Shipper> shipperList;

    List<Shipper> shipperArray;
    ShipperAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper);

        lvShipper = findViewById(R.id.lvShipper);
        shipperList = new ArrayList<Shipper>();
        getShipper();

        searchView = findViewById(R.id.srcShipper);
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(true);

        shipperArray = new ArrayList<Shipper>();
        adapter = new ShipperAdapter(this, shipperList);
        lvShipper.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                shipperList.clear();
                for (Shipper shipper: shipperArray){
                    if (shipper.getShipperName().contains(newText)) {
                        shipperList.add(shipper);
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void getShipper() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, API_GET, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Shipper shipper = new Shipper(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("address"),
                                jsonObject.getString("contact_no"),
                                jsonObject.getString("city"),
                                jsonObject.getString("country")
                        );
                        shipperList.add(shipper);
                        shipperArray.add(shipper);
                    }
                    ShipperAdapter customerAdapter = new ShipperAdapter(getApplicationContext(), shipperList);
                    lvShipper.setAdapter(customerAdapter);
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
        if(shipperList.contains(newText)){
            adapter.getFilter().filter(newText);
        }
        return true;
    }
}
