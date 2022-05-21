package com.example.appcomidi.Adapter.Admin;

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
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.appcomidi.Model.Rating;
import com.example.appcomidi.R;

import java.util.ArrayList;
import java.util.List;

public class LineReviewAdapter extends BaseAdapter implements Filterable {
    List<Rating> ratingList;
    Context context;
    List<Rating> ratingListold;

    public LineReviewAdapter(List<Rating> ratingList, Context context) {
        this.ratingList = ratingList;
        this.context = context;
        this.ratingListold=ratingList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String cmt=charSequence.toString();
                if (cmt.isEmpty())
                {
                    ratingList=ratingListold;
                }
                else
                {
                    List<Rating> TusersList=new ArrayList<>();
                    for (Rating rating:ratingList)
                    {
                        if (rating.getEmail().toLowerCase().contains(cmt.toLowerCase()))
                        {
                            TusersList.add(rating);
                        }
                    }
                    ratingList=TusersList;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=ratingList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ratingList= (List<Rating>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder
    {
        TextView email,cmt;
        RatingBar ratingBar;
        ImageView ava;
    }
    @Override
    public int getCount() {
        if (ratingList!=null)
        {
            return ratingList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return ratingList.get(i);
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
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.amreivewline,null);
            viewHolder.email=view.findViewById(R.id.AMRtextTen);
            viewHolder.ratingBar=view.findViewById(R.id.AMRrating);
            viewHolder.cmt=view.findViewById(R.id.AMRtextRV);
            viewHolder.ava=view.findViewById(R.id.AMRimageAva);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Rating rating= (Rating) getItem(i);
        if (rating.getPhoto()==null)
        {
            viewHolder.ava.setImageResource(R.drawable.avamacdinh);

        }
        else {
            Bitmap bitmap= BitmapFactory.decodeByteArray(rating.getPhoto(),0,rating.getPhoto().length);
            viewHolder.ava.setImageBitmap(bitmap);
        }
        viewHolder.email.setText(rating.getEmail());
        viewHolder.ratingBar.setRating(rating.getSosao());
        viewHolder.cmt.setText(rating.getCmt());
        return view;
    }
}
