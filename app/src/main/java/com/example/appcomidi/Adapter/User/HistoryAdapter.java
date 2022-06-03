package com.example.appcomidi.Adapter.User;

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

import com.example.appcomidi.Model.Order;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.CartViewModel;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private  List<Order> orderList;
    private IFChitietClick ifChitietClick;
    public HistoryAdapter(List<Order> orderList,Context context,IFChitietClick ifChitietClick)
    {
        this.orderList=orderList;
        this.context=context;
        this.ifChitietClick=ifChitietClick;
    }
    public interface IFChitietClick
    {
        public void IFBTNChitietClick(int position);
    }
    public class  ViewHolder
    {
        TextView txtid,txtGiatien,txtngay,txttinhtrang;
        Button buttonhoandon,butonchitiet;
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
        ViewHolder viewHolder=null;
        if (view ==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lineitemhistory, null);
            viewHolder.txtid=view.findViewById(R.id.mahoadonHR);
            viewHolder.txtGiatien=view.findViewById(R.id.tongtienHR);
            viewHolder.txtngay=view.findViewById(R.id.ngayHR);
            viewHolder.buttonhoandon=view.findViewById(R.id.btnhoandonHR);
            viewHolder.txttinhtrang=view.findViewById(R.id.tinhtrangHR);
            viewHolder.butonchitiet=view.findViewById(R.id.btnchitietHR);
            view.setTag(viewHolder);

        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Order order= (Order) getItem(position);
        viewHolder.txtid.setText(""+order.getId());
        viewHolder.txtGiatien.setText(""+order.getTongtien());
        viewHolder.txtngay.setText(tachtime(order.getDate()));
        viewHolder.txttinhtrang.setText(order.getTinhtrang());
        ViewHolder finalViewHolder = viewHolder;
        if (viewHolder.txttinhtrang.getText().toString().equals("Pending"))
        {
            viewHolder.buttonhoandon.setEnabled(true);
        }
        else
        {
            viewHolder.buttonhoandon.setEnabled(false);
        }
        viewHolder.buttonhoandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa sản phẩm!!");
                builder.setMessage("Bạn co chắc muốn xóa sản phẩm này khỏi giỏ hàng?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        orderList.remove(position);
                        CartViewModel.cancelSaleOrder(order.getId());
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
        });

        viewHolder.butonchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifChitietClick.IFBTNChitietClick(order.getId());
            }
        });
        return view;
    }
    public static String tachtime(String time) {
        int sum = 0;
        String moi ="";
        for (int i = 0; i < time.length(); i++) {
            char kq = time.charAt(i);
            moi = moi + kq;
            if (kq == ' ') {
                sum++;
                if (sum ==3 ) {
                    break;
                }
            }
        }
        return moi;
    }
}
