package com.example.wagba_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.wagba_app.Activities.AboutActivity;
import com.example.wagba_app.Activities.CartActivity;
import com.example.wagba_app.Activities.ContactActivity;
import com.example.wagba_app.Activities.LoginActivity;
import com.example.wagba_app.Activities.MainActivity;
import com.example.wagba_app.Activities.OrderTracking;
import com.example.wagba_app.Activities.PreviousOrders;
import com.example.wagba_app.Activities.UserProfileActivity;
import com.example.wagba_app.Interfaces.UserDao;
import com.example.wagba_app.Models.UserDatabase;
import com.example.wagba_app.R;
import com.google.android.material.navigation.NavigationView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ReviewOrder extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private UserDatabase mUserDatabase;
    private UserDao mUserDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_review_order);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navbar_open, R.string.navbar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mUserDatabase = UserDatabase.getDatabase(getApplicationContext());
        mUserDao = mUserDatabase.userDao();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.archives:
                        startActivity(new Intent(getApplicationContext(), PreviousOrders.class));
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        return true;
                    case R.id.track:
                        startActivity(new Intent(getApplicationContext(), OrderTracking.class));
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        return true;
                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), ContactActivity.class));
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        return true;

                }
                return true;
            }
        });
    }

    public void orderStatusRedirect(View view){
        startActivity(new Intent(getApplicationContext(), OrderTracking.class));
    }
}