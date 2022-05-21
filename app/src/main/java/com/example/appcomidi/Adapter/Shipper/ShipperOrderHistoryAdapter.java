package com.example.appcomidi.Adapter.Shipper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.appcomidi.Model.OrderShipper;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.OrderShipperViewModel;

import java.util.ArrayList;

public class ShipperOrderHistoryAdapter extends BaseAdapter {
    ArrayList<OrderShipper> orderShippers;
    Context context;

    public ShipperOrderHistoryAdapter(ArrayList<OrderShipper> orderShippers, Context context) {
        this.orderShippers = orderShippers;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (orderShippers!=null)
        {
            return orderShippers.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return orderShippers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder
    {
        TextView txtmahd,txtGiatien,txtnguoimua,txttinhtrang;
        Button buttonhoanthanh;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.lineitemshipperorderhs,null);
            viewHolder.txtGiatien=view.findViewById(R.id.tongtienSPODHR);
            viewHolder.txtmahd=view.findViewById(R.id.mahoadonSPODHR);
            viewHolder.txtnguoimua=view.findViewById(R.id.nguoimuaSPODHR);
            viewHolder.txttinhtrang=view.findViewById(R.id.tinhtrangSPODHR);
            viewHolder.buttonhoanthanh=view.findViewById(R.id.btnhoanthanhSPODHR);
            view.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        final OrderShipper orderShipper= (OrderShipper) getItem(position);
        viewHolder.txtmahd.setText(""+orderShipper.getOrderid());
        viewHolder.txtnguoimua.setText(orderShipper.getNguoimua());
        viewHolder.txtGiatien.setText(""+orderShipper.getTongtien());
        viewHolder.txttinhtrang.setText(orderShipper.getTinhtrang());
        if (orderShipper.getTinhtrang().equals("Done")==true)
        {
            viewHolder.buttonhoanthanh.setEnabled(false);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.buttonhoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Hoàn thành đơn hàng!!");
                builder.setMessage("Xác nhận hoàn thành đơn ?");
                builder.setPositiveButton("Hoàn thành", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OrderShipperViewModel.updateTinhtrang((Activity) context,orderShipper.getOrderid(),"Done");
                        CartViewModel.updateSaleOrder(orderShipper.getOrderid(),"Done");
                        notifyDataSetChanged();
                        finalViewHolder.buttonhoanthanh.setEnabled(false);
                    }
                });
                builder.setNegativeButton("Chưa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notifyDataSetChanged();
                    }
                });
                builder.show();

            }
        });
        return view;
    }
}
