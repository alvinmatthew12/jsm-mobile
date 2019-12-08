package com.example.jsm_project.Container;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.example.jsm_project.BillofLanding.BillofLandingActivity;
import com.example.jsm_project.HomeActivity;
import com.example.jsm_project.R;
import com.example.jsm_project.Shipper.AddShipperActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContainerActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String API_GET="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Container.php?method=get&type=all";
    private Context context;
    ListView lvContainer;
    List<Container> containerList;
    List<Container> containerArray;
    ContainerAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        lvContainer = findViewById(R.id.lvContainer);
        containerList = new ArrayList<Container>();
        getContainer();


        searchView = findViewById(R.id.srcContainer);
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(true);
        containerArray = new ArrayList<Container>();
        adapter = new ContainerAdapter(this, containerList);
        lvContainer.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                containerList.clear();
                for (Container container: containerArray){
                    if (container.getContainerNo().contains(newText)) {
                        containerList.add(container);
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        Button btn_add = findViewById(R.id.btn_add_container);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddContainerActivity.class);
                startActivity(intent);

            }
        });

    }

    public void getContainer() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, API_GET, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Container container = new Container(
                                jsonObject.getInt("id"),
                                jsonObject.getString("no"),
                                jsonObject.getString("name"),
                                jsonObject.getString("bl_id")
                        );
                        containerList.add(container);
                        containerArray.add(container);
                    }
                    ContainerAdapter customerAdapter = new ContainerAdapter(getApplicationContext(), containerList);
                    lvContainer.setAdapter(customerAdapter);

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
        if(containerList.contains(newText)){
            adapter.getFilter().filter(newText);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        Intent intent = new Intent(ContainerActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
