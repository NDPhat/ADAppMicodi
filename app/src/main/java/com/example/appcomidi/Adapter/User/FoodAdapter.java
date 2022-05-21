package com.example.appcomidi.Adapter.User;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcomidi.Model.Food;
import com.example.appcomidi.R;

import java.util.List;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{



    private List<Food> foodList;
    private IFClickFoodItem ifClickFoodItem;
    private IFClickAddFoodItem ifClickAddFoodItem;

    public void setData(List<Food> list)
    {
        this.foodList=list;
        notifyDataSetChanged();
    }
    public interface IFClickFoodItem{
        void OnClickItemFood(Food food);

    }
    public interface IFClickAddFoodItem{
        void OnClickAddItemFood(Food food);

    }


    public FoodAdapter(List<Food> listFoodMenu, IFClickFoodItem ifClickFoodItem) {
        this.foodList=listFoodMenu;
        this.ifClickFoodItem=ifClickFoodItem;

    }
    public FoodAdapter(List<Food> foodList,IFClickFoodItem mifClickFoodItem,IFClickAddFoodItem mifClickAddFoodItem) {

        this.foodList = foodList;
        this.ifClickFoodItem=mifClickFoodItem;
        this.ifClickAddFoodItem=mifClickAddFoodItem;

    }


    public class FoodViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtten;
        private ImageView imgHinh;
        private TextView price;
        private ImageButton btnadd;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten=itemView.findViewById(R.id.textTen);
            imgHinh=itemView.findViewById(R.id.imageAvaFood);
            price=itemView.findViewById(R.id.textDes);
            btnadd=itemView.findViewById(R.id.imageButtonAddDes);
        }
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem,parent,false);

        return new FoodViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
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
                ifClickFoodItem.OnClickItemFood(food);

            }
        });

        holder.imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifClickFoodItem.OnClickItemFood(food);
            }
        });

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifClickAddFoodItem.OnClickAddItemFood(food);
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


}
