package com.example.appcomidi.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.AccountViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Veryfyphonenumber extends AppCompatActivity {

    private EditText editphone;
    private static final String TAG=Veryfyphonenumber.class.getName();
    private Button btnenterphone;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veryfyphonenumber);
        anhxa();
        setTitletoolbar();

        mAuth=FirebaseAuth.getInstance();
        btnenterphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=editphone.getText().toString().trim();
                onclickVerify(phone);
            }
        });
    }
    private void setTitletoolbar()
    {
        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle("Verify Phone Number");
        }
    }
    private  void anhxa()
    {
        editphone=findViewById(R.id.enterphone);
        btnenterphone=findViewById(R.id.buttonverifyphone);
    }
    public void onclickVerify(String phoneNumber)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                System.out.println("da vao complete");
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Veryfyphonenumber.this,"Failed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationid, forceResendingToken);
                                System.out.println("da vao send");
                                goEnterOTPActivity(phoneNumber,verifycationid);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            goTOHomePageActivity(user.getPhoneNumber());
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Veryfyphonenumber.this,"Invalid",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goEnterOTPActivity(String phoneNumber, String verifycationid) {
        Intent intent = new Intent(Veryfyphonenumber.this, EnterCodeOTPAcitivity.class);
        intent.putExtra("phonenumber", phoneNumber);
        intent.putExtra("verifyid", verifycationid);
        startActivity(intent);

    }
    private void goTOHomePageActivity(String phone)
    {
        AccountViewModel.RegisterAccount(RegisterActivity.acc);
        Intent intent=new Intent(Veryfyphonenumber.this, HomePageActivity.class);
        startActivity(intent);
    }
}