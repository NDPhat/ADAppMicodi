package com.example.appcomidi.Fragment.User;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.appcomidi.Adapter.User.FoodAdapter;
import com.example.appcomidi.Adapter.User.ReviewAdapter;
import com.example.appcomidi.Model.Account;
import com.example.appcomidi.Model.Rating;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.R;
import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.ViewModel.AccountViewModel;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.FoodInfoViewModel;
import com.example.appcomidi.ViewModel.FoodViewModel;
import com.example.appcomidi.ViewModel.FragmentViewModel;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.Model.FoodInfo;
import com.example.appcomidi.ViewModel.RatingViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;

import java.util.List;


public class FoodDetailFragment extends Fragment {
    private TextView txtTen;

    private TextView price,diachiquan,saotongRV,thoigian,sokm,tongrv,xemrv;
    private EditText quantity,email,saoURV,cmt;
    private ImageButton btnplus,btnminus;
    private View view,viewRV;
    private HomePageActivity homePageActivity;
    private Button btnadd,guirv,huyrv;
    private ImageView img;
    RatingBar ratingBar;
    private  RecyclerView recyclerView;
    private  Bundle bundlereceive;
    private  Food food;
    private Users users;
    private Account account;
    private FoodInfo foodInfo;
    private ListView listViewURV;
    public FoodDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =inflater.inflate(R.layout.fragment_food_detail, container, false);
        HomePageActivity.bottomNavigationView.getMenu().findItem(R.id.btmorder).setChecked(true);
        HomePageActivity.navigationView.getMenu().findItem(R.id.order).setChecked(true);
        HomePageActivity.FCurrent=HomePageActivity.Forder;
        Anhxa();

        setData();
        FoodAdapter foodAdapter= new FoodAdapter(FoodViewModel.getListFoodorderbyidcate(food.getIdcate()), new FoodAdapter.IFClickFoodItem() {
            @Override
            public void OnClickItemFood(Food food) {
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                FragmentViewModel.ReplaceFragmentToFoodDetail(fragmentTransaction,food);

            }
        }, new FoodAdapter.IFClickAddFoodItem() {

            @Override
            public void OnClickAddItemFood(Food food) {
                int sl=Integer.parseInt(quantity.getText().toString().trim());
                CartViewModel.addItemtoCart(sl,food);
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(homePageActivity,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(foodAdapter);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(homePageActivity,DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(itemDecoration);
        Addgiohang();
        setBtnminus();
        setBtnadd();
        guiReview();
        XemRview();
        return  view;

    }

    public void setData()
    {

        bundlereceive=getArguments();
        if (bundlereceive !=null)
        {
            food= (Food) bundlereceive.get("FoodItem");
            if (food!=null)
            {
                txtTen.setText(food.getName());
                price.setText(""+food.getPrice());
                Bitmap bitmap= BitmapFactory.decodeByteArray(food.getImage(),0,food.getImage().length);
                img.setImageBitmap(bitmap);
                if (FoodInfoViewModel.getFoodinfoorderbyid(food.getIdfoodinfo())!=null)
                {
                    foodInfo= FoodInfoViewModel.getFoodinfoorderbyid(food.getIdfoodinfo());
                    diachiquan.setText(foodInfo.getDiachigannhat());
                    saotongRV.setText(""+foodInfo.getSosao());
                    sokm.setText(""+foodInfo.getSokm()+"km");
                    thoigian.setText(""+foodInfo.getThoigian()+"m");
                }

            }
        }
        if (account!=null)
        {
            email.setText(account.getEmail());
        }
        tongrv.setText(""+RatingViewModel.tongRVbyFid(food.getId()));
        ratingBar.setRating((float) RatingViewModel.tongStarbyFid(food.getId()));
        saotongRV.setText(""+RatingViewModel.tongStarbyFid(food.getId()));
    }
    public void Anhxa()
    {
        homePageActivity=(HomePageActivity) getActivity();
        txtTen=view.findViewById(R.id.textTenDetail);
        price=view.findViewById(R.id.textDesDetail);
        img=view.findViewById(R.id.imageAvaFoodDetail);
        quantity=view.findViewById(R.id.textquantity);
        btnplus=view.findViewById(R.id.imageButtonAddDetail);
        btnminus=view.findViewById(R.id.imageButtonMinus);
        recyclerView=view.findViewById(R.id.detailrecycle);
        btnadd=view.findViewById(R.id.addgiohang);
        ratingBar=view.findViewById(R.id.rating);
        saotongRV=view.findViewById(R.id.sosao);
        thoigian=view.findViewById(R.id.thoigian);
        diachiquan=view.findViewById(R.id.diachiquan);
        sokm=view.findViewById(R.id.sokm);
        email=view.findViewById(R.id.emailRV);
        saoURV=view.findViewById(R.id.saoRV);
        cmt=view.findViewById(R.id.cmtRV);
        tongrv=view.findViewById(R.id.tongRV);
        xemrv=view.findViewById(R.id.xemRV);
        guirv=view.findViewById(R.id.btnguiRV);
        huyrv=view.findViewById(R.id.btnhuyRV);

        users= UserViewModel.getUserIdById(MainActivity.user.getId());
        account=AccountViewModel.getDataAccountbyAccid(MainActivity.user.getAccid());
        viewRV=view.findViewById(R.id.reviewactivity);

    }
    public void Addgiohang()
    {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl=Integer.parseInt(quantity.getText().toString().trim());
                CartViewModel.addItemtoCart(sl,food);

            }
        });
    }
    public void setBtnminus()
    {
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl=Integer.parseInt(quantity.getText().toString().trim());
                if (sl>1)
                {
                    quantity.setText(""+(sl-1));
                }

            }
        });
    }
    public void setBtnadd()
    {
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl=Integer.parseInt(quantity.getText().toString().trim());
                quantity.setText(""+(sl+1));

            }
        });
    }
    public void guiReview()
    {
        guirv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail=email.getText().toString();

                String bl=cmt.getText().toString();
                if (mail.equals("")==true || bl.equals("")==true)
                {
                    Toast.makeText(homePageActivity,"Không bỏ trống dữ liệu!!",Toast.LENGTH_LONG).show();
                }
                else if (saoURV.getText().toString().equals(""))
                {
                    Toast.makeText(homePageActivity,"Số sao bỏ trống!!",Toast.LENGTH_LONG).show();
                }
                else {
                    int sao= Integer.parseInt(saoURV.getText().toString());
                    if (sao<0 ||sao >5 )
                    {
                        Toast.makeText(homePageActivity,"Số sao từ 1 đến 5!!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Rating rating=new Rating(mail,MainActivity.user.getId(),food.getId(),sao,bl);
                        RatingViewModel.insertReView(rating);
                        int tongrvht=RatingViewModel.tongRVbyFid(food.getId());
                        float sosao=RatingViewModel.tongStarbyFid(food.getId());
                        ratingBar.setRating((float) sosao);
                        tongrv.setText(""+tongrvht);
                        saoURV.setText("");
                        cmt.setText("");
                        Toast.makeText(homePageActivity,"Gửi bình luận thành công!!",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
    public void AnhxaLURV()
    {

        listViewURV=viewRV.findViewById(R.id.listDURV);
        List<Rating> listreviewFood=RatingViewModel.getAllReviewbyFid(food.getId());
        ReviewAdapter reviewAdapter=new ReviewAdapter(listreviewFood,homePageActivity);
        listViewURV.setAdapter(reviewAdapter);
    }
    public void XemRview()
    {
        xemrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new
                        AlertDialog.Builder(homePageActivity);
                viewRV = getLayoutInflater().inflate(R.layout.activity_review, null);
                alertDialog.setView(viewRV);
                AlertDialog dialog = alertDialog.create();
                AnhxaLURV();
                dialog.show();
            }
        });
    }


}