package com.example.appcomidi.View;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcomidi.Model.Account;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.AccountViewModel;


public class RegisterActivity extends AppCompatActivity {

    Button btnRegis;
    TextView txtLoginback;
    EditText emailR,passR,repassR;
    public static  Account acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Anhxa();
        LoginBack();
        RegisterClick();

    }
    public void Anhxa()
    {
        btnRegis=findViewById(R.id.btnRegister);
        txtLoginback=findViewById(R.id.txtLoginBack);
        emailR=findViewById(R.id.emailRegister);
        passR=findViewById(R.id.passRegister);
        repassR=findViewById(R.id.repassRegister);


    }
    public void RegisterClick()
    {
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailR.getText().toString();
                String pass=passR.getText().toString();
                String repass=repassR.getText().toString();
                acc=new Account(email,pass,repass);
                if (AccountViewModel.CheckDataRegister(RegisterActivity.this,acc))
                {
                    Intent intent=new Intent(RegisterActivity.this,Veryfyphonenumber.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Data Register Fail",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void LoginBack()
    {
        txtLoginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLoginback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}