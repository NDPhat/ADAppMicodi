package com.example.appcomidi.Fragment.Admin;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcomidi.Adapter.Admin.LineFoodAdapter;
import com.example.appcomidi.Adapter.EmptyAdapter;
import com.example.appcomidi.Adapter.User.PaymentAdapter;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.R;
import com.example.appcomidi.View.Admin.AdminActivity;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.ViewModel.CategoryViewModel;
import com.example.appcomidi.ViewModel.FoodViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ManagePAdminFragment extends Fragment {

    private static final String TAG = AdminActivity.class.getName();
    private static final int REQUESTCODE =10 ;
    private View view;
    private View viewDT;
    private ListView listviewF;
    private List<Food> foodList;
    private EditText tenF,priceF;
    private String[] catelist;
    private Spinner spinnerCateF;
    private Button btnupload,btnsubmit;
    private ImageView ava;
    private String cateName;
    private LineFoodAdapter lineFoodAdapter;
    private AdminActivity adminActivity;
    private Food food;
    private String[] listempty= new String[]{"Empty History"};
    private androidx.appcompat.widget.SearchView searchView;
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
                            ava.setImageBitmap(bitmap);
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
        view= inflater.inflate(R.layout.fragment_manage_p_admin, container, false);
        Anhxa();
        SearchViewClick();
        return view;
    }
    public void Anhxa()
    {
        adminActivity= (AdminActivity) getActivity();
        listviewF=view.findViewById(R.id.listviewAMF);
        searchView=view.findViewById(R.id.searchViewF);
        if (FoodViewModel.getAllListFood()!=null)
        {
            foodList=FoodViewModel.getAllListFood();
            lineFoodAdapter=new LineFoodAdapter(foodList, adminActivity, new LineFoodAdapter.IFDetailClick() {
                @Override
                public void btnDetailClick(int foodid) {
                    AlertDialog.Builder alertDialog = new
                            AlertDialog.Builder(adminActivity);
                    food=FoodViewModel.getFoobById(foodid);
                    viewDT = getLayoutInflater().inflate(R.layout.activity_amfdetail, null);
                    AnhxaCT();
                    setDataCT();
                    ArrayAdapter adaptercate = new ArrayAdapter(adminActivity,android.R.layout.simple_spinner_dropdown_item,catelist);
                    spinnerCateF.setAdapter(adaptercate);
                    CateSpinnerClick();
                    btnsubmitClick();
                    alertDialog.setView(viewDT);
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();
                }
            });
            listviewF.setAdapter(lineFoodAdapter);
        }
        else {
            EmptyAdapter emptyAdapter=new EmptyAdapter(adminActivity,listempty);
            listviewF.setAdapter(emptyAdapter);
        }

    }
    public void  AnhxaCT() {
        tenF=viewDT.findViewById(R.id.AMFDname);
        spinnerCateF=viewDT.findViewById(R.id.AMFDspinnerCate);
        priceF=viewDT.findViewById(R.id.AMFDPrice);
        btnsubmit=viewDT.findViewById(R.id.AMFDbtnsubmit);
        btnupload=viewDT.findViewById(R.id.AMFDbtnupload);
        ava=viewDT.findViewById(R.id.AMFDimageViewAva);
        catelist= CategoryViewModel.getNameCate();
        ClickUploadImage();
    }

    public void  setDataCT() {
        tenF.setText(food.getName());
        String catename= CategoryViewModel.getCate(food.getIdcate()).getName();
        String[] catelistnow={catename};
        ArrayAdapter adaptercate = new ArrayAdapter(adminActivity,android.R.layout.simple_spinner_dropdown_item,catelistnow);
        spinnerCateF.setAdapter(adaptercate);
        priceF.setText(""+food.getPrice());
        Bitmap bitmap= BitmapFactory.decodeByteArray(food.getImage(),0, food.getImage().length);
        ava.setImageBitmap(bitmap);

    }

    public boolean SearchViewClick() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lineFoodAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lineFoodAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
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
    public void btnsubmitClick()
    {
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=tenF.getText().toString();
                int price= Integer.parseInt((priceF.getText().toString()));
                food.setName(name);
                food.setPrice(price);
                food.setImage(imageViewtoByte(ava));
                FoodViewModel.updateFood(food);
                lineFoodAdapter.notifyDataSetChanged();;
                Toast.makeText(adminActivity,"Update thanh cong",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void CateSpinnerClick()
    {
        spinnerCateF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private byte[] imageViewtoByte(ImageView avatar) {
        Bitmap bitmap= ((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytearray=stream.toByteArray();
        return bytearray;
    }

}