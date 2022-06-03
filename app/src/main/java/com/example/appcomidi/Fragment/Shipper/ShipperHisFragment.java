package com.example.appcomidi.Fragment.Shipper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appcomidi.Adapter.EmptyAdapter;
import com.example.appcomidi.Adapter.Shipper.ShipperOrderHistoryAdapter;
import com.example.appcomidi.Model.OrderShipper;
import com.example.appcomidi.R;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.View.ShipperActivity;
import com.example.appcomidi.ViewModel.OrderShipperViewModel;

import java.util.ArrayList;


public class ShipperHisFragment extends Fragment {

    private View view;
    private ListView listviewHSSP;
    private ShipperOrderHistoryAdapter shipperOrderHistoryAdapter;
    private ArrayList<OrderShipper> arrayList;
    private ShipperActivity shipperActivity;
    private String[] listempty= new String[]{"Empty History"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view=inflater.inflate(R.layout.fragment_shipper_his, container, false);
       Anhxa();
       return view;
    }
    public void Anhxa()
    {
        listviewHSSP=view.findViewById(R.id.listviewSPODHR);
        shipperActivity = (ShipperActivity) getActivity();
        arrayList= OrderShipperViewModel.getALlShipperOrderDetailbySId(MainActivity.user.getId());
        if (arrayList!=null)
        {
            shipperOrderHistoryAdapter=new ShipperOrderHistoryAdapter(arrayList,shipperActivity);
            listviewHSSP.setAdapter(shipperOrderHistoryAdapter);
        }
        else {
            EmptyAdapter emptyAdapter=new EmptyAdapter(shipperActivity,listempty);
            listviewHSSP.setAdapter(emptyAdapter);
        }

    }

}