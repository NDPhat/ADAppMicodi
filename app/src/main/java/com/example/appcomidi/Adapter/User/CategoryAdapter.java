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

import com.example.appcomidi.Model.Category;
import com.example.appcomidi.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private List<Category> Catelist;
    private IFClickFoodItemCategory ifClickFoodItemcategory;

    public CategoryAdapter(List<Category> catelist,IFClickFoodItemCategory ifClickFoodItemcategory ){
        this.Catelist=catelist;
        this.ifClickFoodItemcategory=ifClickFoodItemcategory;

    }
    public interface IFClickFoodItemCategory{
        void OnClickItemAvaCate(Category category);
    }
    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.linecate,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        final Category category=Catelist.get(position);
        if (category==null)
        {
            return;
        }
        holder.txtten.setText(category.getName());
        Bitmap bitmap= BitmapFactory.decodeByteArray(category.getImage(),0,category.getImage().length);
        holder.imgHinh.setImageBitmap(bitmap);
        holder.txtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        if (Catelist !=null)
        {
            return Catelist.size();
        }
        return 0;
    }
    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtten;
        private ImageView imgHinh;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten=itemView.findViewById(R.id.textTenCategory);
            imgHinh=itemView.findViewById(R.id.imageAvaFoodCategory);

        }
    }
}
