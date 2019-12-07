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


//

        return listViewBillofLanding;
    }


}