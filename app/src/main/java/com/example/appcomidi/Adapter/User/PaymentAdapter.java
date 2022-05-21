package com.example.appcomidi.Adapter.User;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcomidi.Model.Food;
import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Giohang> giohangArrayList;
    public PaymentAdapter(ArrayList<Giohang> giohangArrayList,Context context)
    {
        this.context=context;
        this.giohangArrayList=giohangArrayList;
    }
    public class  ViewHolder
    {
        TextView txtTen,txtGiatien,txtQuantity;
        ImageView img;

    }
    @Override
    public int getCount() {
        if (giohangArrayList !=null)
        {
            return giohangArrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return giohangArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder =new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.paymentitem, null);
            viewHolder.txtTen=view.findViewById(R.id.PaymenttextTen);
            viewHolder.txtGiatien=view.findViewById(R.id.Paymenttextgia);
            viewHolder.img=view.findViewById(R.id.imagePayment);
            viewHolder.txtQuantity=view.findViewById(R.id.Paymenttextsoluong);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Giohang giohang = (Giohang) getItem(i);
        viewHolder.txtTen.setText(giohang.getTensp());
        viewHolder.txtQuantity.setText(""+giohang.getSoluongsp());
        viewHolder.txtGiatien.setText(""+giohang.getSoluongsp()*giohang.getGiasp());
        Bitmap bitmap= BitmapFactory.decodeByteArray(giohang.getHinhsp(),0,giohang.getHinhsp().length);
        viewHolder.img.setImageBitmap(bitmap);
        return view;
    }
}
