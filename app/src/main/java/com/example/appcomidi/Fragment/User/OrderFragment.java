package com.example.appcomidi.Fragment.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcomidi.Adapter.User.FoodAdapter;
import com.example.appcomidi.ViewModel.CartViewModel;

import com.example.appcomidi.ViewModel.FoodViewModel;
import com.example.appcomidi.ViewModel.FragmentViewModel;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.Model.Category;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {

    private RecyclerView recyclerViewFood;
    private View view;
    private BottomNavigationView bottomNavigationViewCate;
    private HomePageActivity homePageActivity;
    public static final int FAll = 1;
    public static final int FDrink = 2;
    public static final int FFastFood = 3;
    public static final int FCream = 4;
    public static final int FVnFood = 5;
    public static int FCurrent = FAll;
    private List<Category> catelist=new ArrayList<>();
    private List<Food> foodList=new ArrayList<>();

    public OrderFragment()
    {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragmentorder,container,false);
        recyclerViewFood=view.findViewById(R.id.recycleviewfood);
        bottomNavigationViewCate=view.findViewById(R.id.navigationviewtopcate);
        homePageActivity=(HomePageActivity) getActivity();
        setAdapter(FoodViewModel.getAllListFood());
        navigationMenuClick();
        bottomNavigationViewCate.getMenu().findItem(R.id.all).setChecked(true);
        return view;
    }

    public void OpenRecycleCateDetail(int cateid, int key, Activity homePageActivity)
    {
        if ( FCurrent != key )
        {
            List<Food> arrayList=new ArrayList<>();
            if (key==FAll)
            {
                arrayList=FoodViewModel.getAllListFood();
            }
            else {
                arrayList= FoodViewModel.getListFoodorderbyidcate(cateid);
            }
            setAdapter(arrayList);
            FCurrent=key;
        }

    }
    public void setAdapter( List<Food> arrayList)
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homePageActivity);
        recyclerViewFood.setLayoutManager(linearLayoutManager);
        FoodAdapter foodAdapter = new FoodAdapter(arrayList, new FoodAdapter.IFClickFoodItem() {
            @Override
            public void OnClickItemFood(Food food) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                FragmentViewModel.ReplaceFragmentToFoodDetail(fragmentTransaction,food);
            }
        }, new FoodAdapter.IFClickAddFoodItem() {
            @Override
            public void OnClickAddItemFood(Food food) {
                CartViewModel.addItemtoCart(1,food);
            }

        });
        recyclerViewFood.setAdapter(foodAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(homePageActivity, DividerItemDecoration.VERTICAL);
        recyclerViewFood.addItemDecoration(itemDecoration);

    }
    public void navigationMenuClick()
    {
        bottomNavigationViewCate.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.all)
                {
                    OpenRecycleCateDetail(0,FAll,homePageActivity);
                }
                else if (id == R.id.drinks)
                {
                    OpenRecycleCateDetail(1,FDrink,homePageActivity);
                }
                else if (id == R.id.fastfood)
                {
                    OpenRecycleCateDetail(2,FFastFood,homePageActivity);
                }
                else if (id == R.id.cream)
                {
                    OpenRecycleCateDetail(4,FCream,homePageActivity);

                }
                else if (id == R.id.vnfood)
                {
                    OpenRecycleCateDetail(3,FVnFood,homePageActivity);
                }
                return true;
            }
        });
    }
}
