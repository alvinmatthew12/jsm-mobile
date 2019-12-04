package com.example.jsm_project.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsm_project.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
       private static final String API_GET="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Customer.php?method=get&type=all";
//    private static final String API_DLT="http://batam.shop/api_5psi/lastminute/jsm-api/api/v1/Customer.php?method=post";

    private Context context;
    ListView lvCustomer;
    List<Customer> customerList;

    List<Customer> customerArray;
    CustomerAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        lvCustomer = findViewById(R.id.lvCustomer);
        customerList = new ArrayList<Customer>();
        getCustomer();


        searchView = findViewById(R.id.srcCustomer);
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(true);

        customerArray = new ArrayList<Customer>();
        adapter = new CustomerAdapter(this, customerList);
        lvCustomer.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customerList.clear();
                for (Customer customer: customerArray){
                    if (customer.getCustomerName().contains(newText)) {
                        customerList.add(customer);
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        Button btn_add = findViewById(R.id.btn_add_customer);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCustomerActivity.class);
                startActivity(intent);

            }
        });

    }

    public void getCustomer() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, API_GET, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Customer customer = new Customer(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("address"),
                                jsonObject.getString("contact_no"),
                                jsonObject.getString("city"),
                                jsonObject.getString("country")
                        );
                        customerList.add(customer);
                        customerArray.add(customer);
                    }
                    CustomerAdapter customerAdapter = new CustomerAdapter(getApplicationContext(), customerList);
                    lvCustomer.setAdapter(customerAdapter);
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
        if(customerList.contains(newText)){
            adapter.getFilter().filter(newText);
        }
        return true;
    }
}
