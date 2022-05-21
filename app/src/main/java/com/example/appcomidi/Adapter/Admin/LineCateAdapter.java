package com.example.appcomidi.Adapter.Admin;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcomidi.Model.Category;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;

import java.util.ArrayList;
import java.util.List;

public class LineCateAdapter extends BaseAdapter implements Filterable {
    List<Category> mcategoryList;
    List<Category> categoryListold;
    Context context;

    public LineCateAdapter(List<Category> categoryList,Context context) {
        this.mcategoryList = categoryList;
        this.categoryListold =categoryList;
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search=charSequence.toString();
                if (search.isEmpty())
                {
                    mcategoryList=categoryListold;
                }
                else
                {
                    List<Category> TcateList=new ArrayList<>();
                    for (Category category:mcategoryList)
                    {
                        if (category.getName().toLowerCase().contains(search.toLowerCase()))
                        {
                            TcateList.add(category);
                        }
                    }
                    mcategoryList=TcateList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mcategoryList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mcategoryList= (List<Category>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder
    {
        TextView ten,id;
        ImageView ava;
    }
    @Override
    public int getCount() {
        if (mcategoryList!=null)
        {
            return mcategoryList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mcategoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null)
        {
            viewHolder =new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.amcateline,null);
            viewHolder.id=view.findViewById(R.id.AMCtextIdcate);
            viewHolder.ten=view.findViewById(R.id.AMCtextTenCate);
            viewHolder.ava=view.findViewById(R.id.AMCimageAvaCate);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Category category= (Category) getItem(i);
        viewHolder.id.setText(""+category.getId());
        viewHolder.ten.setText(category.getName());
        Bitmap bitmap= BitmapFactory.decodeByteArray(category.getImage(),0, category.getImage().length);
        viewHolder.ava.setImageBitmap(bitmap);
        return view;
    }
}
