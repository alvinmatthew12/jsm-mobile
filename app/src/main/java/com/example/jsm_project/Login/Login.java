package com.example.jsm_project.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jsm_project.HomeActivity;
import com.example.jsm_project.MainActivity;
import com.example.jsm_project.R;

public class Login extends AppCompatActivity {

    Button btn_login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.edt_username);
        password = (EditText) findViewById(R.id.edt_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (user.equals("admin") && pass.equals("12345")) {
            startActivity(new Intent(Login.this, HomeActivity.class));
            finish();
            Toast.makeText(this, "Welcome Admin", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Username or Password Invalid", Toast.LENGTH_LONG).show();
        }
    }
}
