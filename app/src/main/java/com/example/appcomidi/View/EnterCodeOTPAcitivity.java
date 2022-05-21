package com.example.appcomidi.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appcomidi.Model.Account;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.AccountViewModel;
import com.example.appcomidi.ViewModel.DBViewModel;
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

public class EnterCodeOTPAcitivity extends AppCompatActivity {

    private EditText editotp;
    private Button btnenterotp;
    private TextView sendagain;
    private  String phoneNumber;
    private FirebaseAuth mAuth;
    private  String verifycation;
    private static final String TAG=EnterCodeOTPAcitivity.class.getName();
    PhoneAuthProvider.ForceResendingToken mforceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code_otpacitivity);
        anhxa();
        getDataIntent();
        mAuth=FirebaseAuth.getInstance();
        btnenterotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp=editotp.getText().toString().trim();
                onclicksendotpcode(otp);
            }
        });
        sendagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicksendotpagain();
            }
        });
    }

    private void onclicksendotpagain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)
                        .setForceResendingToken(mforceResendingToken)// Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(EnterCodeOTPAcitivity.this,"Failed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationid, forceResendingToken);
                                verifycation=verifycationid;
                                mforceResendingToken=forceResendingToken;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void onclicksendotpcode(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifycation, otp);
        signInWithPhoneAuthCredential(credential);
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
                            // Update UI
                            goTOMainActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(EnterCodeOTPAcitivity.this,"Invalid",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void getDataIntent()
    {
        phoneNumber=getIntent().getStringExtra("phonenumber");
        verifycation=getIntent().getStringExtra("verifyid");
    }
    private  void anhxa()
    {
        editotp=findViewById(R.id.enterotp);
        btnenterotp=findViewById(R.id.buttonotp);
        sendagain=findViewById(R.id.textsendagain);
    }
    private void goTOMainActivity(String phoneNumber)
    {
        AccountViewModel.RegisterAccount(RegisterActivity.acc);
        Intent intent=new Intent(EnterCodeOTPAcitivity.this, MainActivity.class);
        startActivity(intent);
    }

}