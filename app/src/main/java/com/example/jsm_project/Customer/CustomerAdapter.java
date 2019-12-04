package com.example.jsm_project.Customer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsm_project.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CustomerAdapter extends ArrayAdapter<Customer> {
    private static final String API_DLT="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Customer.php?method=delete";

    private List<Customer> customerList;
    private Context context;

    public CustomerAdapter(@NonNull Context context, List<Customer> customerList) {
        super(context, R.layout.activity_customer, customerList);

        this.customerList = customerList;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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


        Button btn_edit_customer = listViewCustomer.findViewById(R.id.btn_edit_customer);
        btn_edit_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Intent intent = new Intent(context, EditCustomerActivity.class);
                intent.putExtra("id", customerList.get(position).getId());
                intent.putExtra("name", customerList.get(position).getCustomerName());
                intent.putExtra("address", customerList.get(position).getCustomerAddress());
                intent.putExtra("contact_no", customerList.get(position).getCustomerContact());
                intent.putExtra("city", customerList.get(position).getCustomerCity());
                intent.putExtra("country", customerList.get(position).getCustomerCountry());
                context.startActivity(intent);

            }
        });

        Button btn_dlt_customer = listViewCustomer.findViewById(R.id.btn_delete_customer);
        btn_dlt_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST,
                            API_DLT,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent intent = new Intent(context, CustomerActivity.class);
                                    context.startActivity(intent);

                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String id_customer = String.valueOf(customerList.get(position).getId());
                            params.put("id", id_customer);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return listViewCustomer;
    }

}
