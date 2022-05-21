package com.example.appcomidi.ViewModel;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import com.example.appcomidi.Fragment.User.FoodDetailFragment;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.R;
public class FragmentViewModel {
    public static void ReplaceFragmentToFoodDetail( FragmentTransaction fragmentTransaction, Food food)
    {
        Bundle bundle=new Bundle();
        bundle.putSerializable("FoodItem",food);
        FoodDetailFragment foodDetailFragment=new FoodDetailFragment();
        foodDetailFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.content, foodDetailFragment);
        fragmentTransaction.commit();
    }
}
