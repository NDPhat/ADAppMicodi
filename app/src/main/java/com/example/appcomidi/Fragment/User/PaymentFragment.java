package com.example.appcomidi.Fragment.User;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.audiofx.DynamicsProcessing;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
//import vn.momo.momo_partner.AppMoMoLib;
//import vn.momo.momo_partner.MoMoParameterNameMap;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.appcomidi.Adapter.User.PaymentAdapter;
import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.Config;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.View.PayPalActivity;
import com.example.appcomidi.View.PaymentmethodAcitivity;
import com.example.appcomidi.ViewModel.AddressViewModel;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.OrderViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;


public class PaymentFragment extends Fragment {


    private View view, viewCT;
    private TextView tongtien, qtt, mahd,totalamount;
    private ListView paymentlistview;
    private PaymentAdapter paymentAdapter;
    private HomePageActivity homePageActivity;
    private Address address;
    private Order order;
    private String ten, dienthoai, sn, d, tp;
    private int ordernextid;
    private Button btnthanhtoan, btnchitiet;
    private EditText editten, editdienthoai, editsonha, editduong, editthanhpho;
    private Button btnsubmit;
    private Users users;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentpayment, container, false);
        Anhxa();
        setDataP();
        btnChitietClick();
        btnThanhtoanClick();
        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Anhxa() {
        homePageActivity = (HomePageActivity) getActivity();
        paymentlistview = view.findViewById(R.id.listitempayment);
        paymentAdapter = new PaymentAdapter(HomePageActivity.giohangArrayList, homePageActivity);
        paymentlistview.setAdapter(paymentAdapter);
        findNextOrderid();
        mahd = view.findViewById(R.id.editMaHDKHTT);
        tongtien = view.findViewById(R.id.edittongtienKHTT);
        qtt = view.findViewById(R.id.editQTTKHTT);
        btnthanhtoan = view.findViewById(R.id.btnthanhtoanTT);
        btnchitiet = view.findViewById(R.id.btnchitietTT);
        AnhxaCT();
        setDataCT();
    }


    public void setDataP() {
        tongtien.setText("" + CartFragment.sum);
        mahd.setText("" + ordernextid);
        qtt.setText("" + HomePageActivity.giohangArrayList.size());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void InitCart() {
        CartViewModel.initcart(homePageActivity, MainActivity.user.getId(), ordernextid);
        order = CartViewModel.getCartStillbuy(MainActivity.user.getId());
    }

    public void findNextOrderid() {
        ordernextid = CartViewModel.FindNextIdCart();
    }


    public void btnThanhtoanClick() {

        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (HomePageActivity.giohangArrayList.size() > 0) {
                    Getdata();
                    if (KtraData(ten, dienthoai, sn, d, tp)==true)
                    {
                        InitCart();
                        order.setTongtien(CartFragment.sum);
                        Intent intent=new Intent(homePageActivity, PaymentmethodAcitivity.class);
                        intent.putExtra("OrderAmount",order.getTongtien());
                        intent.putExtra("OrderId",order.getId());
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(homePageActivity, "Thông tin rỗng !!", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(homePageActivity, "Giỏ hàng rỗng !!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void btnChitietClick() {
        btnchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new
                        AlertDialog.Builder(homePageActivity);
                AnhxaCT();
                setDataCT();
                alertDialog.setView(viewCT);
                AlertDialog dialog = alertDialog.create();
                clickSubmitCT();
                dialog.show();
            }
        });

    }

    public void AnhxaCT() {
        viewCT = getLayoutInflater().inflate(R.layout.activity_chitiet, null);
        editten = viewCT.findViewById(R.id.tenCT);
        editdienthoai = viewCT.findViewById(R.id.dienthoaiCT);
        editsonha = viewCT.findViewById(R.id.sonhaCT);
        editduong = viewCT.findViewById(R.id.duongCT);
        editthanhpho = viewCT.findViewById(R.id.thanhphoCT);
        btnsubmit = viewCT.findViewById(R.id.btnsubmitCT);

    }


    public void setDataCT() {

        if (UserViewModel.getUserIdById(MainActivity.user.getId()) != null && AddressViewModel.getAddressbyuid(MainActivity.user.getId()) != null) {
            users = UserViewModel.getUserIdById(MainActivity.user.getId());
            address = AddressViewModel.getAddressbyuid(MainActivity.user.getId());
            editten.setText(users.getName());
            editdienthoai.setText(users.getPhone());
            editsonha.setText(address.getHomenumber());
            editduong.setText(address.getStreet());
            editthanhpho.setText(address.getCity());

        }
    }

    public boolean KtraData(String ten, String dienthoai, String sn, String d, String tp) {
        if (ten.equals("") == true || dienthoai.equals("") == true || sn.equals("") == true || d.equals("") == true || tp.equals("") == true) {
            return false;
        }
        return true;
    }

    public void Getdata() {
        ten = editten.getText().toString();
        dienthoai = editdienthoai.getText().toString();
        sn = editsonha.getText().toString();
        d = editduong.getText().toString();
        tp = editthanhpho.getText().toString();
    }

    public void clickSubmitCT() {
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getdata();
                if (KtraData(ten, dienthoai, sn, d, tp) == false) {
                    Toast.makeText(homePageActivity, "Vui lòng điền đủ thông tin !!!", Toast.LENGTH_LONG).show();
                } else {

                    Users users = new Users(MainActivity.user.getId(), ten, dienthoai, MainActivity.user.getIdrole());
                    address = AddressViewModel.getAddressbyuid(MainActivity.user.getId());
                    if (address != null) {
                        address.setCity(tp);
                        address.setStreet(d);
                        address.setHomenumber(sn);
                        UserViewModel.updateUserChitieteById(users);
                        AddressViewModel.updateAddbyId(address);
                        Toast.makeText(homePageActivity, "Update thành công!!", Toast.LENGTH_LONG).show();
                    } else {
                        Address address = new Address();
                        address.setCity(tp);
                        address.setStreet(d);
                        address.setHomenumber(sn);
                        UserViewModel.updateUserChitieteById(users);
                        AddressViewModel.insertAddbyId(address);
                        Toast.makeText(homePageActivity, "Update thành công!!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

}
