package com.example.appcomidi.Fragment.Shipper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appcomidi.Adapter.EmptyAdapter;
import com.example.appcomidi.Adapter.Shipper.OrderShipperAdapter;
import com.example.appcomidi.Adapter.Shipper.ShipperDetailFoodAdapter;
import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.View.ShipperActivity;
import com.example.appcomidi.ViewModel.AddressViewModel;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.OrderViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class ShipperOrderFragment extends Fragment {
    private static final int REQUEST_PHONE_CALL =100 ;
    private View view,viewShipperODCT;
    ListView listViewORSP,listViewShipperODCT;
    private ShipperActivity shipperActivity;
    private OrderShipperAdapter orderShipperAdapter;
    private ShipperDetailFoodAdapter shipperDetailFoodAdapter;
    private TextView ten,sdt,sonha,duong,mahd,tongtien;
    private List<Order> listAllorder;
    private String[] listempty= new String[]{"Empty History"};
    private ArrayList<Giohang> giohangArrayList;
    private Users users;
    private Order order;
    private Address address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_shipper_order, container, false);
        Anhxa();
        return view;
    }
    public void Anhxa()
    {
        shipperActivity= (ShipperActivity) getActivity();
        listViewORSP=view.findViewById(R.id.listviewORSP);
        if (CartViewModel.getAllListSaleOrder()!=null)
        {
            listAllorder= CartViewModel.getAllListSaleOrder();
            orderShipperAdapter=new OrderShipperAdapter(shipperActivity, listAllorder, new OrderShipperAdapter.IFChitietLClick() {
                @Override
                public void IFBtnChitietClick(int orderid) {
                    giohangArrayList = OrderViewModel.getALlOrderDetailbyOrderId(orderid);
                    order = CartViewModel.getCartbyId(orderid);
                    users = UserViewModel.getUserIdById(order.getUserid());
                    address = AddressViewModel.getAddressbyuid(order.getUserid());
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(shipperActivity);
                    viewShipperODCT = getLayoutInflater().inflate(R.layout.activity_shipper_order_detail, null);
                    AnhxaCTORHS();
                    setDataCTORHS();
                    shipperDetailFoodAdapter = new ShipperDetailFoodAdapter(giohangArrayList, shipperActivity);
                    listViewShipperODCT.setAdapter(shipperDetailFoodAdapter);
                    alertDialog.setView(viewShipperODCT);
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();
                }
            },
                    new OrderShipperAdapter.IFClickPhoneCall() {
                        @Override
                        public void ClickPhoenCall(Order order) {
                            if (ContextCompat.checkSelfPermission(shipperActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(shipperActivity, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                            }
                            Users user=UserViewModel.getUserIdById(order.getUserid());
                            String telephone = user.getPhone().toString();
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", telephone, null));
                            startActivity(intent);
                        }
        });
            listViewORSP.setAdapter(orderShipperAdapter);
        }
        else {
            EmptyAdapter emptyAdapter=new EmptyAdapter(shipperActivity,listempty);
            listViewORSP.setAdapter(emptyAdapter);
        }

    }
    public void setDataCTORHS()
    {
        ten.setText(users.getName());
        sdt.setText(users.getPhone());
        sonha.setText(address.getHomenumber());
        duong.setText(address.getStreet());
        mahd.setText(""+order.getId());
        tongtien.setText(""+order.getTongtien());
    }
    public void AnhxaCTORHS()
    {
        listViewShipperODCT=viewShipperODCT.findViewById(R.id.listviewHORSP);
        ten=viewShipperODCT.findViewById(R.id.usernameHORSP);
        sdt=viewShipperODCT.findViewById(R.id.userphoneHORSP);
        sonha=viewShipperODCT.findViewById(R.id.sonhaHORSP);
        duong=viewShipperODCT.findViewById(R.id.duongHORSP);
        mahd=viewShipperODCT.findViewById(R.id.mahoadonHORSP);
        tongtien=viewShipperODCT.findViewById(R.id.tongtienHORSP);


    }
}