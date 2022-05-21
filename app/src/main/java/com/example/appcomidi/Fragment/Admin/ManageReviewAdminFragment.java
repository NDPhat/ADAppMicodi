package com.example.appcomidi.Fragment.Admin;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appcomidi.Adapter.Admin.LineCateAdapter;
import com.example.appcomidi.Adapter.Admin.LineReviewAdapter;
import com.example.appcomidi.Adapter.EmptyAdapter;
import com.example.appcomidi.Model.Category;
import com.example.appcomidi.Model.Rating;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.ViewModel.RatingViewModel;

import java.util.List;


public class ManageReviewAdminFragment extends Fragment {

    private View view;
    private List<Rating> ratingList;
    private LineReviewAdapter lineReviewAdapter;
    private androidx.appcompat.widget.SearchView searchView;
    private ListView listViewAMR;
    private AdminActivity adminActivity;
    private String[] listempty= new String[]{"Empty History"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_manage_review_admin, container, false);
        Anhxa();
        SearchViewClick();
        return view;
    }
    public void Anhxa()
    {
        listViewAMR=view.findViewById(R.id.listviewMRA);
        searchView=view.findViewById(R.id.searchViewR);
        adminActivity= (AdminActivity) getActivity();
        if ( RatingViewModel.getAllReview()!=null)
        {
            ratingList=RatingViewModel.getAllReview();
            lineReviewAdapter=new LineReviewAdapter(ratingList,adminActivity);
            listViewAMR.setAdapter(lineReviewAdapter);

        }
        else
        {
            EmptyAdapter emptyAdapter=new EmptyAdapter(adminActivity,listempty);
            listViewAMR.setAdapter(emptyAdapter);
        }

    }
    public void SearchViewClick()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lineReviewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lineReviewAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}