package com.example.jsm_project.Shipper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.jsm_project.Customer.CustomerActivity;
import com.example.jsm_project.Customer.EditCustomerActivity;
import com.example.jsm_project.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipperAdapter extends ArrayAdapter<Shipper> {
    private static final String API_DLT="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Shipper.php?method=delete";

    private List<Shipper> shipperList;
    private Context context;

    public ShipperAdapter(@NonNull Context context, List<Shipper> shipperList) {
        super(context, R.layout.activity_shipper, shipperList);

        this.shipperList = shipperList;
        this.context = context;
    }

    @Override
    public View getView(final int position,@Nullable View convertView, @Nullable ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listViewShipper = layoutInflater.inflate(R.layout.shipper_card, null, true);

        TextView shipperName = listViewShipper.findViewById(R.id.txtShipperName);
        TextView shipperAddress = listViewShipper.findViewById(R.id.txtShipperAddress);
        TextView shipperContact = listViewShipper.findViewById(R.id.txtShipperContact);
        TextView shipperCity = listViewShipper.findViewById(R.id.txtShipperCity);
        TextView shipperCountry = listViewShipper.findViewById(R.id.txtShipperCountry);

        Shipper shipperdata = shipperList.get(position);

        shipperName.setText(shipperdata.getShipperName());
        shipperAddress.setText(shipperdata.getShipperAddress());
        shipperContact.setText(shipperdata.getShipperContact());
        shipperCity.setText(shipperdata.getShipperCity());
        shipperCountry.setText(shipperdata.getShipperCountry());


        Button btn_edit_shipper = listViewShipper.findViewById(R.id.btn_edit_shipper);
        btn_edit_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Intent intent = new Intent(context, EditShipperActivity.class);
                intent.putExtra("id", shipperList.get(position).getId());
                intent.putExtra("name", shipperList.get(position).getShipperName());
                intent.putExtra("address", shipperList.get(position).getShipperAddress());
                intent.putExtra("contact_no", shipperList.get(position).getShipperContact());
                intent.putExtra("city", shipperList.get(position).getShipperCity());
                intent.putExtra("country", shipperList.get(position).getShipperCountry());
                context.startActivity(intent);
            }

        });

        Button btn_dlt_shipper = listViewShipper.findViewById(R.id.btn_delete_shipper);
        btn_dlt_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final Context context = getContext();
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST,
                            API_DLT,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent intent = new Intent(context, ShipperActivity.class);
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
                            String id_shipper = String.valueOf(shipperList.get(position).getId());
                            params.put("id", id_shipper);

                            return params;

                        }
                    };
                    requestQueue.add(stringRequest);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return listViewShipper;
    }
}
