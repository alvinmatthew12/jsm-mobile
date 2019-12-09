package com.example.jsm_project.Container;

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
import com.example.jsm_project.Shipper.EditShipperActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainerAdapter extends ArrayAdapter<Container> {
    private static final String API_DLT="http://www.jambisuksesmandiri.erwinkho.com/jsm-api/api/v1/Container.php?method=delete";

    private List<Container> containerList;
    private Context context;

    public ContainerAdapter(@NonNull Context context, List<Container> containerList) {
        super(context, R.layout.activity_container, containerList);

        this.containerList = containerList;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listViewContainer = layoutInflater.inflate(R.layout.container_card,null,true);

        TextView containerNo = listViewContainer.findViewById(R.id.txtContainerNo);
        TextView containerName = listViewContainer.findViewById(R.id.txtContainerName);
        TextView blID = listViewContainer.findViewById(R.id.txtBlId);

        final Container containerdata = containerList.get(position);

        containerNo.setText(containerdata.getContainerNo());
        containerName.setText(containerdata.getContainerName());
        blID.setText(containerdata.getBlID());

        Button btn_edit_shipper = listViewContainer.findViewById(R.id.btn_edit_container);
        btn_edit_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Intent intent = new Intent(context, EditContainerActivity.class);
                intent.putExtra("id", containerList.get(position).getId());
                intent.putExtra("no", containerList.get(position).getContainerNo());
                intent.putExtra("name", containerList.get(position).getContainerName());
                intent.putExtra("bl_id", containerList.get(position).getBlID());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

        });

        Button btn_dlt_container = listViewContainer.findViewById(R.id.btn_delete_container);
        btn_dlt_container.setOnClickListener(new View.OnClickListener() {
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
                                    Intent intent = new Intent(context, ContainerActivity.class);
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
                            String id_container = String.valueOf(containerList.get(position).getId());
                            params.put("id", id_container);

                            return params;

                        }
                    };
                    requestQueue.add(stringRequest);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return listViewContainer;
    }
}