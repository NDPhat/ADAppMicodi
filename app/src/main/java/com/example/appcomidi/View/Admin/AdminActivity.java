package com.example.appcomidi.View.Admin;

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

import com.example.appcomidi.Fragment.Admin.AddPAdminFragment;
import com.example.appcomidi.Fragment.Admin.FragmentAddUser;
import com.example.appcomidi.Fragment.Admin.HomeAdminFragment;
import com.example.appcomidi.Fragment.Admin.ManageCateAdminFragment;
import com.example.appcomidi.Fragment.Admin.ManageOrdersAdminFragment;
import com.example.appcomidi.Fragment.Admin.ManagePAdminFragment;
import com.example.appcomidi.Fragment.Admin.ManageReviewAdminFragment;
import com.example.appcomidi.Fragment.Admin.ManageUserAdminFragment;
import com.example.appcomidi.Fragment.User.HomeFragment;
import com.example.appcomidi.R;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.View.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static final int Fhome = 0;
    public static final int FMU = 1;
    public static final int FMP = 2;
    public static final int FAP=3;
    public static final int FMC= 4;
    public static final int FMR= 5;
    public static final int FMO = 6;


    public static final int Flogout=7;
    public static final int FAU = 8;
    public static int FCurrent = Fhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Anhxa();
        ActionBar();
        ReplaceFragment(new HomeAdminFragment());
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.homeA).setChecked(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.admin, new HomeAdminFragment());
        fragmentTransaction.commit();

    }
    public void Anhxa()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbaradmin);
        navigationView = (NavigationView) findViewById(R.id.navigationadmin);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayoutadmin);

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
    private void openHomeAFragment() {
        if (FCurrent != Fhome) {
            ReplaceFragment(new HomeAdminFragment());
               FCurrent = Fhome;
        }
    }
    private void openMUFragment() {
        if (FCurrent != FMU) {
            ReplaceFragment(new ManageUserAdminFragment());

            FCurrent = FMU;
        }
    }

    private void openMPFragment() {
        if (FCurrent != FMP) {
            ReplaceFragment(new ManagePAdminFragment());

            FCurrent = FMP;
        }
    }
    private void openAUFragment() {
        if (FCurrent != FAU) {
            ReplaceFragment(new FragmentAddUser());

            FCurrent = FAU;
        }
    }
    private void openAPFragment() {
        if (FCurrent != FAP) {
            ReplaceFragment(new AddPAdminFragment());

            FCurrent = FAP;
        }
    }
    private void openMCFragment() {
        if (FCurrent != FMC) {
            ReplaceFragment(new ManageCateAdminFragment());

            FCurrent = FMC;
        }
    }
    private void openMRFragment() {
        if (FCurrent != FMR) {
            ReplaceFragment(new ManageReviewAdminFragment());

            FCurrent = FMR;
        }
    }
    private void openMOFragment() {
        if (FCurrent != FMO) {
            ReplaceFragment(new ManageOrdersAdminFragment());

            FCurrent = FMO;
        }
    }
    public void ReplaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentadmin, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homeA) {
            openHomeAFragment();

        } else if (id == R.id.MuserA) {

            openMUFragment();

        } else if (id == R.id.MproductA) {
            openMPFragment();


        } else if (id == R.id.addproductA) {
            openAPFragment();

        } else if (id==R.id.McategoryA) {
            openMCFragment();

        }
        else if (id == R.id.MreviewA) {
            openMRFragment();


        } else if (id == R.id.MorderA) {
            openMOFragment();
        }
        else if (id==R.id.AuserA)
        {
            openAUFragment();
        }
        else if (id == R.id.logoutA) {
            Intent intent = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}