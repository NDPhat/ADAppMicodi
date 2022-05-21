package com.example.appcomidi.Fragment.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcomidi.Adapter.User.FoodCateAdapter;
import com.example.appcomidi.Adapter.User.LineItemCateAdapter;

import com.example.appcomidi.ViewModel.CategoryViewModel;
import com.example.appcomidi.ViewModel.FoodViewModel;
import com.example.appcomidi.ViewModel.FragmentViewModel;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.R;


public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    int [] arrayHinh={R.drawable.anh6,R.drawable.v7,R.drawable.v8,R.drawable.anh4,};
    private RecyclerView homerecycleview,recyclerViewtoproduct;
    private View view;
    private HomePageActivity homePageActivity;
    LineItemCateAdapter lineItemCateAdapter;
    public HomeFragment()
    {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragmenthome,container,false);
        viewFlipper=view.findViewById(R.id.viewflipper);
        homerecycleview=view.findViewById(R.id.homerecycle);
        homePageActivity=(HomePageActivity) getActivity();
        ActionViewFlipper();


        lineItemCateAdapter=new LineItemCateAdapter(homePageActivity, new LineItemCateAdapter.IFClickLineFoodItemCate() {
            @Override
            public void OnClickItemFood(Food food) {
                FragmentTransaction fragmentTransaction=getParentFragmentManager().beginTransaction();
                FragmentViewModel.ReplaceFragmentToFoodDetail(fragmentTransaction,food);

            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(homePageActivity,RecyclerView.VERTICAL,false);
        homerecycleview.setLayoutManager(linearLayoutManager);
        lineItemCateAdapter.setData(CategoryViewModel.getListCate());

        homerecycleview.setAdapter(lineItemCateAdapter);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(homePageActivity,DividerItemDecoration.VERTICAL);
        homerecycleview.addItemDecoration(itemDecoration);

        recyclerViewtoproduct=view.findViewById(R.id.toproductrecycle);

        FoodCateAdapter foodCateAdapter=new FoodCateAdapter(FoodViewModel.getTopListFood(), new FoodCateAdapter.IFClickFoodItemCate() {
            @Override
            public void OnClickItemFood(Food food) {
                FragmentTransaction fragmentTransaction=getParentFragmentManager().beginTransaction();
                FragmentViewModel.ReplaceFragmentToFoodDetail(fragmentTransaction,food);

            }
        });
        recyclerViewtoproduct.setAdapter(foodCateAdapter);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(homePageActivity,RecyclerView.HORIZONTAL,false);
        recyclerViewtoproduct.setLayoutManager(linearLayoutManager2);
        recyclerViewtoproduct.addItemDecoration(itemDecoration);
        return  view;
    }
    private void ActionViewFlipper() {
        for (int i=0;i<arrayHinh.length;i++)
        {
            ImageView imageView=new ImageView(homePageActivity);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(arrayHinh[i]);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
    }


}
