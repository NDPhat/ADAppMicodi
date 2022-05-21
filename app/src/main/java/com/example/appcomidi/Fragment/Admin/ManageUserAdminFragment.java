package com.example.appcomidi.Fragment.Admin;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appcomidi.Adapter.Admin.LineUserAdapter;
import com.example.appcomidi.Adapter.EmptyAdapter;
import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.ViewModel.AddressViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;

import java.util.List;


public class ManageUserAdminFragment extends Fragment {


    private ListView listViewuser;
    private LineUserAdapter lineUserAdapter;
    private List<Users> usersList;
    private AdminActivity adminActivity;
    private EditText editten, editdienthoai, editsonha, editduong, editthanhpho;
    private String[] listempty= new String[]{"Empty History"};
    private View view,viewMAUCT;
    private androidx.appcompat.widget.SearchView searchView;
    private Users users;
    private Address address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_manage_user_admin, container, false);
        Anhxa();
        SearchViewClick();
        return view;

    }
    public void Anhxa()
    {
        adminActivity= (AdminActivity) getActivity();
        usersList= UserViewModel.getAllUser();
        searchView=view.findViewById(R.id.searchViewU);
        listViewuser=view.findViewById(R.id.listviewMUA);
        if (UserViewModel.getAllUser()!=null)
        {
            lineUserAdapter=new LineUserAdapter(usersList, adminActivity, new LineUserAdapter.IFChitietClick() {
                @Override
                public void IFButtonChitietClick(int uid) {
                    if (UserViewModel.getUserIdById(uid)!=null &  AddressViewModel.getAddressbyuid(uid)!=null) {
                        users =UserViewModel.getUserIdById(uid);
                        address = AddressViewModel.getAddressbyuid(uid);
                        AlertDialog.Builder alertDialog = new
                                AlertDialog.Builder(adminActivity);
                        viewMAUCT = getLayoutInflater().inflate(R.layout.activity_chi_tiet_mua, null);
                        AnhxaAMUCT();
                        setDataAMUCT();
                        alertDialog.setView(viewMAUCT);
                        alertDialog.show();
                    }
                    else {
                        AlertDialog.Builder alertDialog = new
                                AlertDialog.Builder(adminActivity);
                        viewMAUCT = getLayoutInflater().inflate(R.layout.activity_chi_tiet_mua, null);
                        AnhxaAMUCT();
                        alertDialog.setView(viewMAUCT);
                        AlertDialog dialog = alertDialog.create();
                        dialog.show();
                    }
                }
            });
            listViewuser.setAdapter(lineUserAdapter);
        }
        else {
            EmptyAdapter emptyAdapter=new EmptyAdapter(adminActivity,listempty);
            listViewuser.setAdapter(emptyAdapter);
        }

    }

    private void setDataAMUCT() {
       editten.setText(users.getName());
       editdienthoai.setText(users.getPhone());
       editsonha.setText(address.getHomenumber());
       editduong.setText(address.getStreet());
       editthanhpho.setText(address.getCity());
    }

    public void AnhxaAMUCT()
    {
        editten=viewMAUCT.findViewById(R.id.MAUtenCT);
        editdienthoai=viewMAUCT.findViewById(R.id.MAUdienthoaiCT);
        editsonha=viewMAUCT.findViewById(R.id.MAUsonhaCT);
        editduong=viewMAUCT.findViewById(R.id.MAUduongCT);
        editthanhpho=viewMAUCT.findViewById(R.id.MAUthanhphoCT);


    }
    public boolean SearchViewClick() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lineUserAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lineUserAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}