package com.example.jsm_project.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jsm_project.R;

import java.util.List;

class CustomerAdapter extends ArrayAdapter<Customer> {
    private List<Customer> customerList;
    private Context context;

    public CustomerAdapter(@NonNull Context context, List<Customer> customerList) {
        super(context, R.layout.activity_customer, customerList);

        this.customerList = customerList;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listViewCustomer = layoutInflater.inflate(R.layout.customer_card,null,true);

        TextView customerName = listViewCustomer.findViewById(R.id.txtCustomerName);
        TextView customerAddress = listViewCustomer.findViewById(R.id.txtCustomerAddress);
        TextView customerContact = listViewCustomer.findViewById(R.id.txtCustomerContact);
        TextView customerCity = listViewCustomer.findViewById(R.id.txtCustomerCity);
        TextView customerCountry = listViewCustomer.findViewById(R.id.txtCustomerCountry);

        Customer customerdata = customerList.get(position);

        customerName.setText(customerdata.getCustomerName());
        customerContact.setText(customerdata.getCustomerContact());
        customerAddress.setText(customerdata.getCustomerAddress());
        customerCity.setText(customerdata.getCustomerCity());
        customerCountry.setText(customerdata.getCustomerCountry());

        return listViewCustomer;
    }

}
