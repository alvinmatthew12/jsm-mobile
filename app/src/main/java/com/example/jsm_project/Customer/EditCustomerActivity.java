package com.example.jsm_project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.jsm_project.R;

public class EditCustomerActivity extends AppCompatActivity {

//    private static final String API_UPDATE = "http://batam.shop/api_5psi/lastminute/jsm-api/api/v1/Customer.php?method=put&id=5";
    private static final String API_UPDATE="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Customer.php?method=put";

    Customer customer;
    EditText edtCustomerName, edtCustomerAddress, edtCustomerContact, edtCustomerCity, edtCustomerCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        edtCustomerName = findViewById(R.id.edtCustomerName);
        edtCustomerAddress = findViewById(R.id.edtCustomerAddress);
        edtCustomerContact = findViewById(R.id.edtCustomerContact);
        edtCustomerCity = findViewById(R.id.edtCustomerCity);
        edtCustomerCountry = findViewById(R.id.edtCustomerCountry);

        Bundle extras = getIntent().getExtras();
        customer = new Customer(
                extras.getInt("id"),
                extras.getString("name"),
                extras.getString("address"),
                extras.getString("contact_no"),
                extras.getString("city"),
                extras.getString("country")
        );

        edtCustomerName.setText(customer.getCustomerName());
        edtCustomerAddress.setText(customer.getCustomerAddress());
        edtCustomerContact.setText(customer.getCustomerContact());
        edtCustomerCity.setText(customer.getCustomerCity());
        edtCustomerCountry.setText(customer.getCustomerCountry());
    }

    public void editCustomer(View view) {

    }
}
