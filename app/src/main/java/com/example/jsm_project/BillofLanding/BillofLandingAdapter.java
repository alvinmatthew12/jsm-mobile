package com.example.jsm_project.BillofLanding;

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
import com.example.jsm_project.Container.BillofLanding;
import com.example.jsm_project.Container.Container;
import com.example.jsm_project.Container.ContainerActivity;
import com.example.jsm_project.Container.EditContainerActivity;
import com.example.jsm_project.Customer.EditCustomerActivity;
import com.example.jsm_project.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillofLandingAdapter extends ArrayAdapter<BillofLanding> {
    private static final String API_DLT="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/BillOfLanding.php?method=delete";

    private List<BillofLanding> billofLandingList;
    private Context context;

    public BillofLandingAdapter(@NonNull Context context, List<BillofLanding> billofLandingList) {
        super(context, R.layout.activity_billof_landing, billofLandingList);

        this.billofLandingList = billofLandingList;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listViewBillofLanding = layoutInflater.inflate(R.layout.billoflanding_card,null,true);

        TextView NoBl = listViewBillofLanding.findViewById(R.id.txtBlNo);
        TextView ShippingDate = listViewBillofLanding.findViewById(R.id.txtShippingDate);
        TextView DateofReceipt = listViewBillofLanding.findViewById(R.id.txtDateofReceipt);
        TextView ShipperID = listViewBillofLanding.findViewById(R.id.txtShipperID);
        TextView CustomerID = listViewBillofLanding.findViewById(R.id.txtCustomerID);
        TextView Status = listViewBillofLanding.findViewById(R.id.txtStatus);

        final BillofLanding billoflandingdata = billofLandingList.get(position);

        NoBl.setText(billoflandingdata.getBilloflandingNo());
        ShippingDate.setText(billoflandingdata.getShippingDate());
        DateofReceipt.setText(billoflandingdata.getDateofReceipt());
        ShipperID.setText(billoflandingdata.getShippingID());
        CustomerID.setText(billoflandingdata.getCustomerID());
        Status.setText(billoflandingdata.getStatus());

        Button btn_edit_bol = listViewBillofLanding.findViewById(R.id.btn_edit_bl);
        btn_edit_bol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Intent intent = new Intent(context, EditBillofLandingActivity.class);
                intent.putExtra("id", billofLandingList.get(position).getId());
                intent.putExtra("no", billofLandingList.get(position).getBilloflandingNo());
                intent.putExtra("shipping_date", billofLandingList.get(position).getShippingDate());
                intent.putExtra("date_of_receipt", billofLandingList.get(position).getDateofReceipt());
                intent.putExtra("shipper_id", billofLandingList.get(position).getShippingID());
                intent.putExtra("customer_id", billofLandingList.get(position).getCustomerID());
                intent.putExtra("status", billofLandingList.get(position).getStatus());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Button btn_dlt_bl = listViewBillofLanding.findViewById(R.id.btn_delete_bl);
        btn_dlt_bl.setOnClickListener(new View.OnClickListener() {
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
                                    Intent intent = new Intent(context, BillofLandingActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                            String id = String.valueOf(billofLandingList.get(position).getId());
                            params.put("id", id);

                            return params;

                        }
                    };
                    requestQueue.add(stringRequest);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return listViewBillofLanding;
    }


}