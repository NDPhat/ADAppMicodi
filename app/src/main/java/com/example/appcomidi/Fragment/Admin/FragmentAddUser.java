package com.example.appcomidi.Fragment.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcomidi.Model.Account;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.View.RegisterActivity;
import com.example.appcomidi.View.Veryfyphonenumber;
import com.example.appcomidi.ViewModel.AccountViewModel;

public class FragmentAddUser extends Fragment {


    private View view;
    private Button btnCreate;
    private EditText emailR,passR,repassR;
    public static Account acc;
    private Spinner spinnerR;
    private String[] rolelist=new String[2];
    private String RoleName;
    private AdminActivity adminActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_add_user, container, false);
       Anhxa();
       RoleSpinnerClick();
       CreateClick();
       return view;
    }
    public void Anhxa()
    {
        adminActivity= (AdminActivity) getActivity();
        btnCreate=view.findViewById(R.id.btnAdCreate);
        emailR=view.findViewById(R.id.emailAdCreate);
        passR=view.findViewById(R.id.passAdCreate);
        repassR=view.findViewById(R.id.repassAdCreate);
        spinnerR=view.findViewById(R.id.AAUspinnerRole);
        rolelist[0]="User";
        rolelist[1]="Shipper";
        ArrayAdapter adaptercate = new ArrayAdapter(adminActivity,android.R.layout.simple_spinner_dropdown_item,rolelist);
        spinnerR.setAdapter(adaptercate);


    }

    public void CreateClick()
    {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailR.getText().toString();
                String pass=passR.getText().toString();
                String repass=repassR.getText().toString();
                acc=new Account(email,pass,repass);
                String role=RoleName;
                int rid=1;
                if (RoleName.equals("User"))
                {
                    rid=1;
                }
                else
                {
                    rid=2;
                }
                if (AccountViewModel.CheckDataRegister(adminActivity,acc))
                {
                    AccountViewModel.CreateAccount(acc,rid);
                    Toast.makeText(adminActivity,"Register Successful!!",Toast.LENGTH_LONG).show();
                    clear();

                }
                else {
                    Toast.makeText(adminActivity,"Data Register Fail!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void clear() {
        emailR.setText("");
        repassR.setText("");
        passR.setText("");
    }
    public void RoleSpinnerClick()
    {
        spinnerR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RoleName=rolelist[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                RoleName=rolelist[0];
            }
        });
    }
}