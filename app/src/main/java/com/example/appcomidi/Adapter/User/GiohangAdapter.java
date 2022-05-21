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

import com.example.appcomidi.Fragment.User.CartFragment;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.R;

import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Giohang> giohangArrayList;
    private IFClickMinusItem ifClickMinusItem;
    private IFClickPlusItem ifClickPlusItem;
    private IFClickRemoveItem ifClickRemoveItem;
    public interface IFClickMinusItem{
        void OnClickMinusFood();
    }
    public interface IFClickRemoveItem{
        void OnClickRemoveFood(int position,View view);

    }
    public interface IFClickPlusItem{
        void OnClickPlusFood();
    }

    public GiohangAdapter(Context context, ArrayList<Giohang> giohangArrayList, IFClickPlusItem ifClickPlusItem,IFClickMinusItem ifClickMinusItem,IFClickRemoveItem ifClickRemoveItem) {
        this.context = context;
        this.giohangArrayList = giohangArrayList;
        this.ifClickMinusItem=ifClickMinusItem;
        this.ifClickPlusItem=ifClickPlusItem;
        this.ifClickRemoveItem=ifClickRemoveItem;
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
    public static class  ViewHolder
    {
        TextView txtTen;
        TextView price;
        ImageView img;
        EditText quantity;
        ImageButton btnplus;
        ImageButton btnminus;
        ImageButton btndelete;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.itemcart,null);
            viewHolder.txtTen=view.findViewById(R.id.textTenitemCart);
            viewHolder.price=view.findViewById(R.id.textDesitemCart);
            viewHolder.img=view.findViewById(R.id.imageAvaFooditemCart);
            viewHolder.quantity=view.findViewById(R.id.textquantityitemCart);
            viewHolder.btnplus=view.findViewById(R.id.imageButtonAdditemCart);
            viewHolder.btnminus=view.findViewById(R.id.imageButtonMinusitemCart);
            viewHolder.btndelete=view.findViewById(R.id.imageButtonRemoveitemCart);
            view.setTag(viewHolder);

        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Giohang giohang= (Giohang) getItem(position);
        viewHolder.txtTen.setText(giohang.getTensp());
        viewHolder.price.setText(""+giohang.getGiasp());
        Bitmap bitmap= BitmapFactory.decodeByteArray(giohang.getHinhsp(),0,giohang.getHinhsp().length);
        viewHolder.img.setImageBitmap(bitmap);
        viewHolder.quantity.setText(""+giohang.getSoluongsp());
        ViewHolder finalViewHolder = viewHolder;

        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slht= HomePageActivity.giohangArrayList.get(position).getSoluongsp();
                if (slht>1)
                {
                    int slm=Integer.parseInt(finalViewHolder.quantity.getText().toString().trim())-1;
                    finalViewHolder.quantity.setText(""+slm);
                    int giaht=HomePageActivity.giohangArrayList.get(position).getGiasp();
                    HomePageActivity.giohangArrayList.get(position).setSoluongsp(slm);
                    int giamoi=giaht*slm/slht;
                    HomePageActivity.giohangArrayList.get(position).setGiasp(giamoi);
                    finalViewHolder.price.setText(""+giamoi);
                    CartFragment.Sumprice();

                }
            }
        });
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slm=Integer.parseInt(finalViewHolder.quantity.getText().toString().trim())+1;
                finalViewHolder.quantity.setText(""+slm);
                int slht= HomePageActivity.giohangArrayList.get(position).getSoluongsp();
                int giaht=HomePageActivity.giohangArrayList.get(position).getGiasp();
                HomePageActivity.giohangArrayList.get(position).setSoluongsp(slm);
                int giamoi=giaht*slm/slht;
                HomePageActivity.giohangArrayList.get(position).setGiasp(giamoi);
                finalViewHolder.price.setText(""+giamoi);
                CartFragment.Sumprice();
            }
        });
        viewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifClickRemoveItem.OnClickRemoveFood(position,view);
            }
        });
        return view;
    }
}
