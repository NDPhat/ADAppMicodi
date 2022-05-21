package com.example.appcomidi.Adapter.Shipper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.R;

import java.util.List;

public class ShipperDetailFoodAdapter extends BaseAdapter {
    List<Giohang> giohangList;
    Context context;

    public ShipperDetailFoodAdapter(List<Giohang> giohangList, Context context) {
        this.giohangList = giohangList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (giohangList!=null)
        {
            return giohangList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return giohangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder
    {
        TextView ten,sl;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.lineitemshipperchitietdon,null);
            viewHolder.sl=view.findViewById(R.id.slSPSPOR);
            viewHolder.ten=view.findViewById(R.id.tenSPSPOR);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Giohang giohang= (Giohang) getItem(i);
        viewHolder.ten.setText(giohang.getTensp());
        viewHolder.sl.setText(""+giohang.getSoluongsp());
        return view;
    }
}
