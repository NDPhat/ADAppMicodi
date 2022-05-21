package com.example.appcomidi.Adapter.User;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcomidi.Model.Rating;
import com.example.appcomidi.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends BaseAdapter {
    List<Rating> ratingArrayList;
    private Context context;

    public ReviewAdapter(List<Rating> ratingArrayList, Context context) {
        this.ratingArrayList = ratingArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (ratingArrayList!=null)
        {
            return ratingArrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return ratingArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder
    {
        TextView email,cmt;
        ImageView ava;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.lineitemreview,null);
            viewHolder.email=view.findViewById(R.id.textEmailDRV);
            viewHolder.cmt=view.findViewById(R.id.textcmtDRV);
            viewHolder.ava=view.findViewById(R.id.avaDRV);
            view.setTag(viewHolder);

        }
        else {
            viewHolder= (ViewHolder) view.getTag();

        }
        final Rating rating= (Rating) getItem(i);
        if (rating!=null)
        {
            if (rating.getPhoto()==null)
            {
                viewHolder.ava.setImageResource(R.drawable.avamacdinh);

            }
            else {
                Bitmap bitmap= BitmapFactory.decodeByteArray(rating.getPhoto(),0,rating.getPhoto().length);
                viewHolder.ava.setImageBitmap(bitmap);
            }
            viewHolder.email.setText(rating.getEmail());
            viewHolder.cmt.setText(rating.getCmt());
        }
        return view;

    }

}
