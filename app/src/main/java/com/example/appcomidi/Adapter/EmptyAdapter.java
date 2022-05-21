package com.example.appcomidi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcomidi.R;

import java.util.List;

public class EmptyAdapter extends BaseAdapter {
    private Context context;
    private String[] list= new String[]{"Empty History"};
    public EmptyAdapter(Context context,String[] list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class  ViewHolder
    {
        TextView txtNone;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      ViewHolder viewHolder=null;
      if (view==null)
      {
          viewHolder=new ViewHolder();
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          view = inflater.inflate(R.layout.emptyadapter, null);
          viewHolder.txtNone=view.findViewById(R.id.textViewNone);
          view.setTag(viewHolder);
      }
      else {
          viewHolder= (ViewHolder) view.getTag();
      }
      viewHolder.txtNone.setText("Empty History");
        return view;

    }
}
