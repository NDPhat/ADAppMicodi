package com.example.appcomidi.Adapter.User;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcomidi.Model.Food;
import com.example.appcomidi.R;

import java.util.List;

public class FoodCateAdapter extends RecyclerView.Adapter<FoodCateAdapter.FoodViewHolder>{

    private List<Food> foodList;
    private IFClickFoodItemCate ifClickFoodItemcate;

    public FoodCateAdapter() {

    }

    public void setData(List<Food> list)
    {
        this.foodList=list;
        notifyDataSetChanged();
    }


    public interface IFClickFoodItemCate{
        void OnClickItemFood(Food food);


    }
    public FoodCateAdapter(List<Food> foodList,IFClickFoodItemCate ifClickFoodItem) {

        this.foodList = foodList;
        this.ifClickFoodItemcate=ifClickFoodItem;
    }
    @NonNull
    @Override
    public FoodCateAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.foodcate,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCateAdapter.FoodViewHolder holder, int position) {
        final Food food=foodList.get(position);
        if (food==null)
        {
            return;
        }
        holder.txtten.setText(food.getName());
        Bitmap bitmap= BitmapFactory.decodeByteArray(food.Image,0,food.Image.length);
        holder.imgHinh.setImageBitmap(bitmap);
        holder.price.setText(""+food.getPrice());

        holder.txtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifClickFoodItemcate.OnClickItemFood(food);

            }
        });

        holder.imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifClickFoodItemcate.OnClickItemFood(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foodList !=null)
        {
            return foodList.size();
        }
        return 0;
    }
    public class FoodViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtten;
        private ImageView imgHinh;
        private TextView price;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten=itemView.findViewById(R.id.textTenCate);
            imgHinh=itemView.findViewById(R.id.imageAvaFoodCate);
            price=itemView.findViewById(R.id.textPriceCate);

        }
    }
}

