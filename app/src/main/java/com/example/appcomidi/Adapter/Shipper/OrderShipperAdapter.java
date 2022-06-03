package com.example.appcomidi.Adapter.Shipper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.appcomidi.Model.Order;
import com.example.appcomidi.Model.OrderShipper;
import com.example.appcomidi.R;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.OrderShipperViewModel;
import com.example.appcomidi.ViewModel.OrderViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;

import java.util.List;

public class OrderShipperAdapter extends BaseAdapter {


    private Context context;
    List<Order> orderList;
    private IFChitietLClick ifChitietLClick;
    private IFClickPhoneCall ifClickPhoneCall;
    public interface IFChitietLClick
    {
        void IFBtnChitietClick(int position);
    }
    public interface IFClickPhoneCall
    {
        void ClickPhoenCall(Order order);
    }

    public OrderShipperAdapter(Context context, List<Order> orderList,IFChitietLClick ifChitietLClick,IFClickPhoneCall ifClickPhoneCall1) {
        this.context = context;
        this.orderList = orderList;
        this.ifChitietLClick=ifChitietLClick;
        this.ifClickPhoneCall=ifClickPhoneCall1;
    }

    public class  ViewHolder
    {
        TextView txtma,txtGiatien,txtnguoinhan;
        Button buttonnhandon,buttonchitiet;
        ImageButton btncall;
    }
    @Override
    public int getCount() {
        if (orderList!=null)
        {
            return orderList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return orderList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =null;
        if (view ==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lineshipperorder, null);
            viewHolder.txtma=view.findViewById(R.id.mahoadonORSP);
            viewHolder.txtGiatien=view.findViewById(R.id.tongtienORSP);
            viewHolder.txtnguoinhan=view.findViewById(R.id.nguoinhanORSP);
            viewHolder.buttonnhandon=view.findViewById(R.id.btnnhandonORSP);
            viewHolder.buttonchitiet=view.findViewById(R.id.btnchitietdonORSP);
            viewHolder.btncall=view.findViewById(R.id.btncallORSP);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Order order= (Order) getItem(position);
        viewHolder.txtma.setText(""+order.getId());
        viewHolder.txtGiatien.setText(""+order.getTongtien());
        String nguoimua=UserViewModel.getUserIdById(order.getUserid()).getName();
        viewHolder.txtnguoinhan.setText(nguoimua);
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.buttonnhandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OrderShipperViewModel.getStatusShipper((Activity) context,MainActivity.user.getId())==false)
                {
                    Toast.makeText(context,"Bạn đang thực hiện một đơn hàng khác!!Hãy quay lại sau!!",Toast.LENGTH_LONG).show();
                    finalViewHolder.buttonnhandon.setEnabled(false);
                }
                else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Nhận đơn hàng!!");
                    builder.setMessage("Bạn muốn nhận đơn này ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            orderList.remove(position);
                            CartViewModel.updateSaleOrder(order.getId(),"Delivery");
                            OrderShipper orderShipper=new OrderShipper(MainActivity.user.getId(),order.getUserid(),order.getId(),order.getTongtien(),nguoimua,"Delivery");
                            OrderShipperViewModel.insertShipperOrderDetail(orderShipper);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            notifyDataSetChanged();
                        }
                    });
                    builder.show();

                }
                }

        });
        viewHolder.buttonchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ifChitietLClick.IFBtnChitietClick(order.getId());
            }
        });
        viewHolder.btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ifClickPhoneCall.ClickPhoenCall(order);
            }
        });
        return view;
    }
}
