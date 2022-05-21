package com.example.appcomidi.Fragment.User;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appcomidi.Adapter.EmptyAdapter;
import com.example.appcomidi.Adapter.User.HistoryAdapter;
import com.example.appcomidi.Adapter.User.PaymentAdapter;
import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.R;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.OrderViewModel;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {

    ListView listViewHR,listViewCTHR;
    private View view,viewHCT;
    private HomePageActivity homePageActivity;
    private HistoryAdapter historyAdapter;
    private PaymentAdapter paymentAdapter;
    private List<Order> listorder;
    private String[] listempty= new String[]{"Empty History"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_history, container, false);
        Anhxa();
        return view;
    }

    public void Anhxa()
    {
        homePageActivity= (HomePageActivity) getActivity();
        listViewHR=view.findViewById(R.id.listviewHR);

        if ( CartViewModel.getListSaleOrderbyUserid( MainActivity.user.getId())!=null)
        {

            listorder=CartViewModel.getListSaleOrderbyUserid( MainActivity.user.getId());
            historyAdapter=new HistoryAdapter(listorder, homePageActivity, new HistoryAdapter.IFChitietClick() {
                @Override
                public void IFBTNChitietClick(int position) {
                    ArrayList<Giohang> giohangArrayList= OrderViewModel.getALlOrderDetailbyOrderId(position);
                    AlertDialog.Builder alertDialog = new
                            AlertDialog.Builder(homePageActivity);
                    viewHCT = getLayoutInflater().inflate(R.layout.activity_chitiet_hractivity, null);
                    listViewCTHR=viewHCT.findViewById(R.id.listviewHRCT);
                    paymentAdapter = new PaymentAdapter(giohangArrayList, homePageActivity);
                    listViewCTHR.setAdapter(paymentAdapter);
                    alertDialog.setView(viewHCT);
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();

                }

        });
            listViewHR.setAdapter(historyAdapter);
        }
        else {
            EmptyAdapter emptyAdapter=new EmptyAdapter(homePageActivity,listempty);
            listViewHR.setAdapter(emptyAdapter);
        }


    }
}