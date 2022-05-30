package com.example.appcomidi.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.ViewModel.AccountViewModel;
import com.example.appcomidi.ViewModel.DBViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    public static Users user;
    private EditText email,pass;
    Button btnLogin;
    TextView txtRegis;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBViewModel.sqLiteDatabase= DB.initDatabase(MainActivity.this,DBViewModel.DBName);
        Anhxa();
        LoginClick();
        RegisterOn();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void LoginClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDatatoFireStore();
                int accid= AccountViewModel.CheckUserandPassword(email.getText().toString(),pass.getText().toString());
                if (accid!=0 )
                {
                    int userid= UserViewModel.getUserIdByAccId(accid);
                    user=UserViewModel.getUserIdById(userid);
                    if (user.getIdrole()==1)
                    {
                        Toast.makeText(MainActivity.this,"Login Succesfull",Toast.LENGTH_LONG).show();
                        // khoi tao cart

                        Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
                        startActivity(intent);
                    }
                    //shipper
                    else if (user.getIdrole()==2)
                    {
                        Intent intent=new Intent(MainActivity.this, ShipperActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent=new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Login Fail",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void Anhxa()
    {
        email=findViewById(R.id.loginemail);
        pass=findViewById(R.id.loginpass);
        btnLogin=findViewById(R.id.btnlogin);
        txtRegis=findViewById(R.id.txtRegisNow);
    }
    private void RegisterOn() {
        txtRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
            }
        });
    }
    public void addDatatoFireStore() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        HashMap<String, Object> data = new HashMap<>();
        data.put("Name", "Chiras");
        firebaseFirestore.collection("users")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplication(), "Data added", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception ->
                {
                    Toast.makeText(getApplication(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}