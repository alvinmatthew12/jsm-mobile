package com.example.jsm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.Login.Login;
import com.example.jsm_project.Shipper.ShipperActivity;

public class HomeActivity extends AppCompatActivity {
    Button btn_logout;
    Button btn_customer;
    Button btn_shipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_customer = findViewById(R.id.btn_customer);
        btn_shipper = findViewById(R.id.btn_shipper);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = "aaa";
                Log.d(tag,"Now log out and start the activity login");
                Intent intent = new Intent(HomeActivity.this,
                        Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (HomeActivity.this, CustomerActivity.class);
                startActivity(i);
                finish();

            }
        });

        btn_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (HomeActivity.this, ShipperActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
