package com.example.appcomidi.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.example.appcomidi.Fragment.Shipper.ShipperHisFragment;

import com.example.appcomidi.Fragment.Shipper.ShipperOrderFragment;
import com.example.appcomidi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class ShipperActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public static NavigationView navigationView;
    public static BottomNavigationView bottomNavigationView;
    public static final int Fordershipper = 0;
    public static final int Fhisshipper = 1;
    public static int FCurrent = Fordershipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper);
        anhxa();
        ActionBar();
        ReplaceFragment(new ShipperOrderFragment());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.shipperactivity, new ShipperOrderFragment());
        fragmentTransaction.commit();
        navigationView.getMenu().findItem(R.id.ordershipper).setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.btmordershipper).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
        bottomnaviclick();
    }

    private void anhxa() {

        toolbar = (Toolbar) findViewById(R.id.toolbarshipper);
        navigationView = (NavigationView) findViewById(R.id.navigationshipper);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayoutshipper);
        bottomNavigationView = findViewById(R.id.bottomnavishipper);

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
    private void openOrderShipperFragment() {
        if (FCurrent != Fordershipper) {
            ReplaceFragment(new ShipperOrderFragment());

            FCurrent = Fordershipper;
        }
    }

    private void openHisShipperFragment() {
        if (FCurrent != Fhisshipper) {
            ReplaceFragment(new ShipperHisFragment());

            FCurrent = Fhisshipper;
        }
    }


    public void ReplaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentshipper, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ordershipper) {
            openOrderShipperFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmordershipper).setChecked(true);

        } else if (id == R.id.historyshipper) {
            openHisShipperFragment();
            bottomNavigationView.getMenu().findItem(R.id.btmhistoryshipper).setChecked(true);
        }

        else if (id==R.id.shipperlogout)
        {
            Intent intent = new Intent(ShipperActivity.this, MainActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void bottomnaviclick()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.btmordershipper) {
                    openOrderShipperFragment();
                    navigationView.getMenu().findItem(R.id.ordershipper).setChecked(true);


                } else if (id == R.id.btmhistoryshipper) {
                    openHisShipperFragment();
                    navigationView.getMenu().findItem(R.id.historyshipper).setChecked(true);

                }
                return true;
            }
        });
    }
}