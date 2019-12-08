package com.example.jsm_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jsm_project.BillofLanding.BillofLandingActivity;
import com.example.jsm_project.Container.BillofLanding;
import com.example.jsm_project.Container.ContainerActivity;
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.Customer.EditCustomerActivity;
import com.example.jsm_project.Login.Login;
import com.example.jsm_project.Shipper.ShipperActivity;

public class HomeActivity extends AppCompatActivity {
    Button btn_logout;
    Button btn_customer;
    Button btn_shipper;
    Button btn_container;
    Button btn_bol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_customer = findViewById(R.id.btn_customer);
        btn_shipper = findViewById(R.id.btn_shipper);
        btn_container = findViewById(R.id.btn_container);
        btn_bol = findViewById(R.id.btn_bol);



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        Intent intent = new Intent(HomeActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
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

        btn_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (HomeActivity.this, ContainerActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_bol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (HomeActivity.this, BillofLandingActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Intent intent = new Intent(HomeActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
