package com.example.appcomidi.Fragment.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appcomidi.Model.Account;
import com.example.appcomidi.Model.Address;
import com.example.appcomidi.R;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.ViewModel.AccountViewModel;

public class ChangePass extends Fragment {

    private View view;
    private EditText email,pass,repass,oldpass;
    private Button btnupdate;
    private HomePageActivity homePageActivity;

    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_change_pass,container,false);
        Anhxa();
        btnupdateClick();
        return view;
    }
    public void Anhxa()
    {
        homePageActivity= (HomePageActivity) getActivity();
        email=view.findViewById(R.id.emailCP);
        pass=view.findViewById(R.id.passCP);
        repass=view.findViewById(R.id.repassCP);
        oldpass=view.findViewById(R.id.oldpassCP);
        btnupdate=view.findViewById(R.id.buttonUpdateCP);
    }
    public void btnupdateClick()
    {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e=email.getText().toString();
                String p=pass.getText().toString();
                String rp=repass.getText().toString();
                String op=oldpass.getText().toString();
                Account account=new  Account(MainActivity.user.getAccid(),e,p,rp);
                if (AccountViewModel.CheckUserandPassword(e,op)!=0)
                {
                    AccountViewModel.updateAccount(homePageActivity,account);
                }
                else
                {
                    Toast.makeText(homePageActivity,"Email hoăc mật khẩu cũ không trùng khớp!!",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}
