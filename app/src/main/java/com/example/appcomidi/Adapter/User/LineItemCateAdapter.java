package com.example.appcomidi.Adapter.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcomidi.Model.Category;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class LineItemCateAdapter extends RecyclerView.Adapter<LineItemCateAdapter.LineItemCateAdapterViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private IFClickLineFoodItemCate ifClickLineFoodItemCate;
    public LineItemCateAdapter(Context context,IFClickLineFoodItemCate mifClickLineFoodItemCate)
    {
        this.context=context;
        this.ifClickLineFoodItemCate=mifClickLineFoodItemCate;
    }
    public void setData(List<Category> list)
    {
        this.categoryList=list;
        notifyDataSetChanged();
    }
    public interface IFClickLineFoodItemCate{
        void OnClickItemFood(Food Food);
    }
    @NonNull
    @Override
    public LineItemCateAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lineitemhome,parent,false);
        return new LineItemCateAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LineItemCateAdapterViewHolder holder, int position) {
        Category category=categoryList.get(position);
        if (category==null)
        {
            return;
        }

        holder.catename.setText(category.getName());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL);
        holder.recyclerView.addItemDecoration(itemDecoration);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        List<Food> foodlist=new ArrayList<>();
        foodlist= FoodViewModel.getListFoodorderbyidcate( category.getId());
        FoodCateAdapter foodAdapter=new FoodCateAdapter(foodlist, new FoodCateAdapter.IFClickFoodItemCate() {
            @Override
            public void OnClickItemFood(Food food) {

               ifClickLineFoodItemCate.OnClickItemFood(food);
            }
        });
        foodAdapter.setData(foodlist);
        holder.recyclerView.setAdapter(foodAdapter);

    }
    @Override
    public int getItemCount() {
        if (categoryList !=null)
        {
            return categoryList.size();
        }
        return 0;
    }
    public class LineItemCateAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView catename;
        private RecyclerView recyclerView;

        public LineItemCateAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            catename=itemView.findViewById(R.id.catename);
            recyclerView=itemView.findViewById(R.id.linerecycleviewcate);
        }
    }
}
