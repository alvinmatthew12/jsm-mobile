package com.example.jsm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jsm_project.Login.Login;

public class HomeActivity extends AppCompatActivity {
Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = "aaa";
                Log.d(tag,"Now log out and start the activity login");
                Intent intent = new Intent(HomeActivity.this,
                        Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

}
