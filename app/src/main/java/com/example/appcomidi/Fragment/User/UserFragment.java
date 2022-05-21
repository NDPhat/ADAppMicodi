package com.example.appcomidi.Fragment.User;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.AddressViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UserFragment extends Fragment {
    private static final int REQUESTCODE =10 ;
    private static final String TAG = MainActivity.class.getName();
    private View view;
    private EditText name,phone,sonha,duong,thanhpho;
    private Button btnupload,btnsubmit;
    private ImageView avatar;
    private Users users;
    private Address address;
    private HomePageActivity homePageActivity;
    private ActivityResultLauncher<Intent> mActivivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.e(TAG,"onActivityResult*");
            if (result.getResultCode()== Activity.RESULT_OK)
            {
                Intent data=result.getData();
                if (data==null)
                {
                    return;
                }
                Uri uri=data.getData();
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                    avatar.setImageBitmap(bitmap);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_user, container, false);
        Anhxa();
        setData();
        ClickUploadImage();
        setBtnsubmitClick();
        return view;

    }
    public void Anhxa()
    {
        homePageActivity= (HomePageActivity) getActivity();
        name=view.findViewById(R.id.usernameUS);
        phone=view.findViewById(R.id.userphoneUS);
        sonha=view.findViewById(R.id.sonhaUS);
        duong=view.findViewById(R.id.duongUS);
        thanhpho=view.findViewById(R.id.thanhphoUS);
        avatar=view.findViewById(R.id.imageViewAva);
        btnsubmit=view.findViewById(R.id.btnsubmit);
        btnupload=view.findViewById(R.id.btnupload);

    }
    public void setData()
    {
        homePageActivity= (HomePageActivity) getActivity();
        if (UserViewModel.getUserIdById(MainActivity.user.getId())!=null && AddressViewModel.getAddressbyuid(MainActivity.user.getId())!=null)
        {
            users=UserViewModel.getUserIdById(MainActivity.user.getId());
            address= AddressViewModel.getAddressbyuid(MainActivity.user.getId());
            name.setText(users.getName());
            phone.setText(users.getPhone());
            if (users.getImage()==null)
            {
                avatar.setImageResource(R.drawable.avamacdinh);

            }
            else {
                Bitmap bitmap= BitmapFactory.decodeByteArray(users.getImage(),0,users.getImage().length);
                avatar.setImageBitmap(bitmap);
            }

            sonha.setText(address.getHomenumber());
            duong.setText(address.getStreet());
            thanhpho.setText(address.getCity());

        }



    }
    public void ClickUploadImage()
    {
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT< Build.VERSION_CODES.M)
        {
            OpenGallery();
            return;
        }
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            OpenGallery();
        }
        else {
            String[]permission={Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,REQUESTCODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE)
        {
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                OpenGallery();
            }
        }
    }
    private void OpenGallery() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        mActivivityResultLauncher.launch(Intent.createChooser(intent,"Choose Picture"));
    }
    public void setBtnsubmitClick()
    {
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //update thongtin user
                String USname= name.getText().toString();
                String USphone=phone.getText().toString();
                //update dia chi user
                String sn=sonha.getText().toString();
                String d=duong.getText().toString();
                String tp=thanhpho.getText().toString();

                if (USname.equals("") || USphone.equals("")
                    || sn.equals("") || d.equals("") || tp.equals("") ||avatar.getDrawable().equals(""))
                {
                    Toast.makeText(homePageActivity,"Vui lòng điền đủ thông tin!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    byte[] USimg=imageViewtoByte(avatar);
                    Users users=new Users(MainActivity.user.getId(),USname,USphone,USimg,MainActivity.user.getIdrole());
                    address=AddressViewModel.getAddressbyuid(MainActivity.user.getId());
                    address.setCity(tp);
                    address.setStreet(d);
                    address.setHomenumber(sn);
                    UserViewModel.updateUserById(users);
                    AddressViewModel.updateAddbyId(address);
                    Toast.makeText(homePageActivity,"Update thành công!!",Toast.LENGTH_LONG).show();
                    setData();
                }
            }
        });
    }

    private byte[] imageViewtoByte(ImageView avatar) {
        Bitmap bitmap= ((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytearray=stream.toByteArray();
        return bytearray;
    }

}