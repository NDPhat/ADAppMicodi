package com.example.appcomidi.Fragment.Admin;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appcomidi.Adapter.Admin.LineCateAdapter;
import com.example.appcomidi.Model.Category;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.ViewModel.CategoryViewModel;

import java.util.List;


public class ManageCateAdminFragment extends Fragment {

    private View view;
    private List<Category> categoryList;
    private LineCateAdapter lineCateAdapter;
    private androidx.appcompat.widget.SearchView searchView;
    private ListView listViewAMC;
    private AdminActivity adminActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_manage_cate_admin, container, false);
        Anhxa();
        SearchViewClick();
        return view;
    }
    public void Anhxa()
    {
        adminActivity= (AdminActivity) getActivity();
        categoryList= CategoryViewModel.getListCate();
        searchView=view.findViewById(R.id.searchViewCate);
        listViewAMC=view.findViewById(R.id.listviewMCA);
        lineCateAdapter =new LineCateAdapter(categoryList,adminActivity);
        listViewAMC.setAdapter(lineCateAdapter);
    }
    public boolean SearchViewClick() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lineCateAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lineCateAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}