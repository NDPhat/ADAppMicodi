package com.example.appcomidi.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.appcomidi.Fragment.User.CartFragment;
import com.example.appcomidi.Fragment.User.ChangePass;
import com.example.appcomidi.Fragment.User.HistoryFragment;
import com.example.appcomidi.Fragment.User.HomeFragment;
import com.example.appcomidi.Fragment.User.OrderFragment;
import com.example.appcomidi.Fragment.User.PaymentFragment;
import com.example.appcomidi.Fragment.User.UserFragment;

import com.example.appcomidi.Model.Giohang;

import com.example.appcomidi.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public static NavigationView navigationView;
    public static   BottomNavigationView bottomNavigationView;

    public static ArrayList<Giohang> giohangArrayList;
    public static final int Fhome = 0;
    public static final int Forder = 1;
    public static final int Fcart = 2;
    public static final int FHis=3;
    public static final int Fpay = 4;
    public static final int FAcc = 5;
    public static final int FChangePass = 6;
    public static final int Flogout=7;
    public static int FCurrent = Fhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        anhxa();
        ActionBar();
        ReplaceFragment(new HomeFragment());
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.home).setChecked(true);
        BottomnaviClick();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.homepage, new HomeFragment());
        fragmentTransaction.commit();
        bottomNavigationView.getMenu().findItem(R.id.btmhome).setChecked(true);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }
    //cmt backpress
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.backpress:
//                finish();
//                return true;
//            default:break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menutoolbar, menu);
//        return true;
//    }
    private void anhxa() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        bottomNavigationView = findViewById(R.id.bottomnavi);

        //khia bang gio hang
        if (giohangArrayList!=null)
        {
        }
        else {
            giohangArrayList=new ArrayList<>();
        }
    }
    private void openHomeFragment() {
        if (FCurrent != Fhome) {
            ReplaceFragment(new HomeFragment());

            FCurrent = Fhome;
        }
    }
    private void openorderFragment() {
        if (FCurrent != Forder) {
            ReplaceFragment(new OrderFragment());
            FCurrent = Forder;
        }

    }
    private void openCartFragment() {
        if (FCurrent != Fcart) {
            ReplaceFragment(new CartFragment());
            FCurrent = Fcart;
        }
    }

    private void openpayFragment() {
        if (FCurrent != Fpay) {
            ReplaceFragment(new PaymentFragment());
            FCurrent = Fpay;
        }
    }

    private void openHistory() {
        if (FCurrent != FHis) {
            ReplaceFragment(new HistoryFragment());
            FCurrent = FHis;
        }
    }

    private void openAccFragment() {
        if (FCurrent != FAcc) {
            ReplaceFragment(new UserFragment());
            FCurrent = FAcc;
        }

    }
    private void openChangePassFragment() {
        if (FCurrent != FChangePass) {
            ReplaceFragment(new ChangePass());
            FCurrent = FChangePass;
        }
    }



    public void ReplaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            openHomeFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmhome).setChecked(true);

        } else if (id == R.id.order) {
            openorderFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmorder).setChecked(true);


        } else if (id == R.id.cart) {
            openCartFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmcart).setChecked(true);

        } else if (id == R.id.history) {

            openHistory();
            bottomNavigationView.getMenu().findItem(R.id.btmhistory).setChecked(true);


        } else if (id==R.id.payment) {

            openpayFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmcart).setChecked(true);


        }
        else if (id == R.id.profile) {
            openAccFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmhistory).setChecked(true);


        } else if (id == R.id.changepass) {
            openChangePassFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmhistory).setChecked(true);

        } else if (id == R.id.logout) {
            Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.Thongtinungdung) {

        } else if (id == R.id.lienhe) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void BottomnaviClick() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.btmhome) {
                    openHomeFragment();
                    navigationView.getMenu().findItem(R.id.home).setChecked(true);

                } else if (id == R.id.btmorder) {
                    openorderFragment();
                    navigationView.getMenu().findItem(R.id.order).setChecked(true);

                } else if (id == R.id.btmcart) {
                    openCartFragment();
                    navigationView.getMenu().findItem(R.id.cart).setChecked(true);

                } else if (id == R.id.btmhistory) {
                    openHistory();
                    navigationView.getMenu().findItem(R.id.history).setChecked(true);
                }
                return true;
            }
        });
    }
}
