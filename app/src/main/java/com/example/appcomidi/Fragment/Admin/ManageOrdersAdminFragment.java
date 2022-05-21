package com.example.appcomidi.Fragment.Admin;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appcomidi.Adapter.Admin.LineOrderAdapter;
import com.example.appcomidi.Adapter.Admin.LineReviewAdapter;
import com.example.appcomidi.Adapter.EmptyAdapter;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.Model.Rating;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.OrderViewModel;

import java.util.List;


public class ManageOrdersAdminFragment extends Fragment {


    private View view;
    private List<Order> orderList;
    private LineOrderAdapter lineOrderAdapter;
    private androidx.appcompat.widget.SearchView searchView;
    private ListView listViewAMO;
    private AdminActivity adminActivity;
    private String[] listempty= new String[]{"Empty History"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_manage_orders_admin, container, false);
        Anhxa();
        SearchViewClick();
        return view;
    }
    public void Anhxa()
    {
        listViewAMO=view.findViewById(R.id.listviewAMO);
        searchView=view.findViewById(R.id.searchviewAMO);
        adminActivity= (AdminActivity) getActivity();
        if (CartViewModel.getAllSaleOrder()!=null)
        {
            orderList=CartViewModel.getAllSaleOrder();
            lineOrderAdapter=new LineOrderAdapter(orderList,adminActivity);
            listViewAMO.setAdapter(lineOrderAdapter);
        }
        else
        {
            EmptyAdapter emptyAdapter=new EmptyAdapter(adminActivity,listempty);
            listViewAMO.setAdapter(emptyAdapter);
        }
    }
    public boolean SearchViewClick()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lineOrderAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lineOrderAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}