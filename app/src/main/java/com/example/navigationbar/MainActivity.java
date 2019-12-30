package com.example.navigationbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        requestPermission();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CategoriesFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_categories);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId()) {
            case R.id.nav_todayspecial:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TodaySpecialFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_deals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DealsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoriesFragment()).commit();
                break;
            case R.id.nav_suggestions:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SuggestionsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_mycart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyCartFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_onestar:
                Toast.makeText(this, "1 Star Poor Service!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_twostar:
                Toast.makeText(this, "2 Star Satisfactory Service!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_threestar:
                Toast.makeText(this, "3 Star Normal Service!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_fourstar:
                Toast.makeText(this, "4 Star Good Service!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_fivestar:
                Toast.makeText(this, "5 Star Excellent Service!", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @AfterPermissionGranted(123)
    private void requestPermission() {
        String[] perms = {Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)){
            Toast.makeText(this, "Granted!", Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}
