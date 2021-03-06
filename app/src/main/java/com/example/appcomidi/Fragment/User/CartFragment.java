package com.example.appcomidi.Fragment.User;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appcomidi.Adapter.User.GiohangAdapter;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.R;


public class CartFragment extends Fragment {

    private View view;
    private ListView listViewgiohang;
    private TextView textViewthongbbao;
    private static TextView textViewtongtien;
    private Button buttonthanhtoan,buttontieptuc;
    private Toolbar toolbar;
    public static double sum;
    private GiohangAdapter giohangAdapter;
    private HomePageActivity homePageActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_cart, container, false);
        Anhxa();
        ChecKData();
        Sumprice();
        Tieptucmua();
        Thanhtoan();
        return view;
    }

    private void ChecKData() {
        if (HomePageActivity.giohangArrayList.size()<=0)
        {
            giohangAdapter.notifyDataSetChanged();
            textViewthongbbao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.INVISIBLE);

        }
        else {
            giohangAdapter.notifyDataSetChanged();
            textViewthongbbao.setVisibility(View.INVISIBLE);
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    public static void Sumprice()
    {
         sum=0;
        for (int i=0;i<HomePageActivity.giohangArrayList.size();i++)
        {
            sum+=HomePageActivity.giohangArrayList.get(i).getGiasp();
        }
        textViewtongtien.setText(""+sum);
    }

    public  void Anhxa()
    {
        homePageActivity= (HomePageActivity) getActivity();
        listViewgiohang=view.findViewById(R.id.listitemcart);
        textViewthongbbao=view.findViewById(R.id.textstatuscart);
        textViewtongtien=view.findViewById(R.id.texttongtien);
        buttonthanhtoan=view.findViewById(R.id.btnThanhtoan);
        buttontieptuc=view.findViewById(R.id.btnTieptucmua);
        listViewgiohang=view.findViewById(R.id.listitemcart);
        giohangAdapter =new GiohangAdapter(homePageActivity, HomePageActivity.giohangArrayList, new GiohangAdapter.IFClickPlusItem() {
            @Override
            public void OnClickPlusFood() {
            }
        }, new GiohangAdapter.IFClickMinusItem() {
            @Override
            public void OnClickMinusFood() {
            }
        }, new GiohangAdapter.IFClickRemoveItem() {
            @Override
            public void OnClickRemoveFood(int position,View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("X??c nh???n x??a s???n ph???m!!");
                builder.setMessage("B???n co ch???c mu???n x??a s???n ph???m n??y kh???i gi??? h??ng?");
                builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HomePageActivity.giohangArrayList.remove(position);
                        giohangAdapter.notifyDataSetChanged();
                        CartFragment.Sumprice();
                        ChecKData();
                    }
                });
                builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        CartFragment.Sumprice();
                        ChecKData();
                    }
                });
                builder.show();
            }
    });
        listViewgiohang.setAdapter(giohangAdapter);
    }
    public void Tieptucmua()
    {
        buttontieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomePageActivity.bottomNavigationView.getMenu().findItem(R.id.btmorder).setChecked(true);
                HomePageActivity.navigationView.getMenu().findItem(R.id.order).setChecked(true);
                HomePageActivity.FCurrent=HomePageActivity.Forder;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                OrderFragment orderFragment = new OrderFragment();
                fragmentTransaction.replace(R.id.cartfragment, orderFragment);
                fragmentTransaction.commit();

            }
        });
    }
    public void Thanhtoan()
    {
        buttonthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                PaymentFragment paymentFragment = new PaymentFragment();
                fragmentTransaction.replace(R.id.cartfragment, paymentFragment);
                fragmentTransaction.commit();
            }
        });
    }
   
}