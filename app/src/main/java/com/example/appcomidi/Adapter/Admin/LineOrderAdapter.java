package com.example.appcomidi.Adapter.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.appcomidi.Model.Order;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class LineOrderAdapter extends BaseAdapter implements Filterable {
    List<Order> orderList;
    List<Order> orderListold;
    Context context;

    public LineOrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.orderListold = orderList;
        this.context = context;
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
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search=charSequence.toString();
                if (search.isEmpty())
                {
                    orderList=orderListold;
                }
                else
                {
                    List<Order> TorderList=new ArrayList<>();
                    for (Order order:orderList)
                    {
                        Users user= UserViewModel.getUserIdById(order.getUserid());
                        if (user.getName().toLowerCase().contains(search.toLowerCase()))
                        {
                            TorderList.add(order);
                        }
                    }
                    orderList=TorderList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=orderList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                orderList= (List<Order>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder
    {
        TextView ma,ten,ngay,tongtien,tinhtrang;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (viewHolder==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.amoline,null);
            viewHolder.ma=view.findViewById(R.id.AMOmaHD);
            viewHolder.ten=view.findViewById(R.id.AMOtenHD);
            viewHolder.ngay=view.findViewById(R.id.AMOngayHD);
            viewHolder.tongtien=view.findViewById(R.id.AMOtongtienHD);
            viewHolder.tinhtrang=view.findViewById(R.id.AMOtinhtrangHD);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Order order= (Order) getItem(i);
        viewHolder.ma.setText(""+order.getId());
        Users user= UserViewModel.getUserIdById(order.getUserid());
        viewHolder.ten.setText(user.getName());
        viewHolder.ngay.setText(order.getDate());
        viewHolder.tongtien.setText(""+order.getTongtien());
        viewHolder.tinhtrang.setText(order.getTinhtrang());
        return view;
    }
}
