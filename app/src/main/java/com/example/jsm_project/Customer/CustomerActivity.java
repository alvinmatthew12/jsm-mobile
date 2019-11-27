package com.example.jsm_project.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
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

public class CustomerActivity extends AppCompatActivity {
private static final String API_GET="http://batam.shop/api_5psi/lastminute/jsm-api/api/v1/Customer.php?method=get";

    private Context context;
    ListView lvCustomer;
    List<Customer> customerList;

    List<Customer> customerArray;
    CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        lvCustomer = findViewById(R.id.lvCustomer);
        customerList = new ArrayList<Customer>();
        getCustomer();
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

}
