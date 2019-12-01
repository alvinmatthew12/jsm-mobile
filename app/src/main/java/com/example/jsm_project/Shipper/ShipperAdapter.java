package com.example.jsm_project.Shipper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jsm_project.R;

import java.util.List;

public class ShipperAdapter extends ArrayAdapter<Shipper> {
    private List<Shipper> shipperList;
    private Context context;

    public ShipperAdapter(@NonNull Context context, List<Shipper> shipperList) {
        super(context, R.layout.activity_shipper, shipperList);

        this.shipperList = shipperList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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

        return listViewShipper;
    }
}
