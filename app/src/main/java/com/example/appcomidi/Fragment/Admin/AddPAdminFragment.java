package com.example.appcomidi.Fragment.Admin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcomidi.Model.Food;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.ViewModel.AddressViewModel;
import com.example.appcomidi.ViewModel.CategoryViewModel;
import com.example.appcomidi.ViewModel.FoodInfoViewModel;
import com.example.appcomidi.ViewModel.FoodViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class AddPAdminFragment extends Fragment {
    private static final int REQUESTCODE =10 ;
    private static final String TAG = MainActivity.class.getName();
    private View view;
    private Spinner catespin,infospin;
    private ImageView avatar;
    private EditText ten,gia;
    private Button btnup,btnadd;
    private String[] catelist;
    private Integer[] infolist;
    private String cateName;
    private int idinfo;
    private AdminActivity adminActivity;
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
       view= inflater.inflate(R.layout.fragment_add_p_admin, container, false);
       Anhxa();
       CateSpinnerClick();
       FoodInfoSpinnerClick();
       ClickUploadImage();
       btnAdd();
       return view;
    }
    public void Anhxa()
    {
        adminActivity= (AdminActivity) getActivity();
        ten=view.findViewById(R.id.AAFtenf);
        gia=view.findViewById(R.id.AAFgiaf);
        catespin=view.findViewById(R.id.AAFspinnerCate);
        infospin=view.findViewById(R.id.AAFspinnerInfo);
        btnup=view.findViewById(R.id.AAFbtnup);
        avatar=view.findViewById(R.id.AAFava);
        btnadd=view.findViewById(R.id.AAFbtnadd);
        catelist= CategoryViewModel.getNameCate();
        infolist= FoodInfoViewModel.getIdInfo();
        ArrayAdapter adaptercate = new ArrayAdapter(adminActivity,android.R.layout.simple_spinner_dropdown_item,catelist);
        catespin.setAdapter(adaptercate);
        ArrayAdapter adapterinfo = new ArrayAdapter(adminActivity,android.R.layout.simple_spinner_dropdown_item,infolist);
        infospin.setAdapter(adapterinfo);
    }

    public void ClickUploadImage()
    {
        btnup.setOnClickListener(new View.OnClickListener() {
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
    public void btnAdd()
    {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ten.getText().toString().equals("")|| gia.getText().toString().equals("")||avatar.getDrawable().equals(""))
                {
                    Toast.makeText(adminActivity,"Vui lòng điền đủ thông tin!!",Toast.LENGTH_LONG).show();
                }
                else {
                    String name=ten.getText().toString();
                    int money= Integer.parseInt(gia.getText().toString());
                    if (money<=0)
                    {
                        Toast.makeText(adminActivity,"Vui lòng điền đúng giá tiền!!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        byte[] fimg = imageViewtoByte(avatar);
                        int id = FoodViewModel.FindNextId();
                        int idcate = CategoryViewModel.getIDCatebyName(cateName);
                        Food food = new Food(id, name, money, idcate, fimg, 100, "Còn", idinfo);
                        FoodViewModel.insertFood(food);
                        Toast.makeText(adminActivity, "Thêm thành công!!", Toast.LENGTH_LONG).show();
                    }

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
    public void CateSpinnerClick()
    {
        catespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cateName=catelist[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cateName=catelist[0];
            }
        });
    }
    public void FoodInfoSpinnerClick() {
            infospin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    idinfo=infolist[i];

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    idinfo=infolist[0];

                }
            });
    }
}