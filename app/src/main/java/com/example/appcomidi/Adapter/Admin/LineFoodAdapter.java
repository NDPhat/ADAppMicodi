package com.example.appcomidi.Adapter.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcomidi.Model.Food;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;

import java.util.ArrayList;
import java.util.List;

public class LineFoodAdapter extends BaseAdapter implements Filterable {
    List<Food> foodList;
    Context context;
    List<Food> foodListold;
    IFDetailClick mifDetailClick;
    public interface IFDetailClick
    {
        void btnDetailClick(int foodid);
    }

    public LineFoodAdapter(List<Food> mfoodList, Context context,IFDetailClick ifDetailClick) {
        this.foodList = mfoodList;
        this.context = context;
        this.foodListold =mfoodList;
        this.mifDetailClick=ifDetailClick;
    }

    @Override
    public int getCount() {
        if (foodList!=null)
        {
            return foodList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return foodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search=charSequence.toString();
                if (search.isEmpty())
                {
                    foodList=foodListold;
                }
                else
                {
                    List<Food> TfoodList=new ArrayList<>();
                    for (Food food:foodList)
                    {
                        if (food.getName().toLowerCase().contains(search.toLowerCase()))
                        {
                            TfoodList.add(food);
                        }
                    }
                    foodList=TfoodList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=foodList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
               foodList= (List<Food>) filterResults.values;
               notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder
    {
        TextView txtid,txtGiatien,txtten,txtcate;
        ImageButton btndetail;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.malinefood,null);
            viewHolder.txtid=view.findViewById(R.id.MAFidf);
            viewHolder.txtten=view.findViewById(R.id.MAFtenf);
            viewHolder.txtcate=view.findViewById(R.id.MAFcatef);
            viewHolder.txtGiatien=view.findViewById(R.id.MAFgiaf);
            viewHolder.btndetail=view.findViewById(R.id.MAFdetail);
            view.setTag(viewHolder);

        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Food food= (Food) getItem(i);
        viewHolder.txtid.setText(""+food.getId());
        viewHolder.txtten.setText(food.getName());
        viewHolder.txtcate.setText(""+food.getIdcate());
        viewHolder.txtGiatien.setText(""+food.getPrice());
        viewHolder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mifDetailClick.btnDetailClick(food.getId());
            }
        });
        return view;
    }
}
