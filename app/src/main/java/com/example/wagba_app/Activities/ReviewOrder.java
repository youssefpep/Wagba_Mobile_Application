package com.example.wagba_app.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.wagba_app.Interfaces.UserDao;
import com.example.wagba_app.Models.UserDatabase;
import com.example.wagba_app.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReviewOrder extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private UserDatabase mUserDatabase;
    private DatabaseReference databaseReference;
    private UserDao mUserDao;
    private Spinner spinner;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_review_order);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        spinner = findViewById(R.id.spinner2);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navbar_open, R.string.navbar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mUserDatabase = UserDatabase.getDatabase(getApplicationContext());
        mUserDao = mUserDatabase.userDao();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID= user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(userID);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        return true;
                    case R.id.track:
                        startActivity(new Intent(getApplicationContext(), PreviousOrders.class));
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
        Button check = findViewById(R.id.checktime);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredTime = spinner.getSelectedItem().toString();
                Time time = new Time(Time.getCurrentTimezone());
                time.setToNow();
                String currentTime = time.format("%k:%M");
                String time1 = "10:00";
                String time2 = "3:00";
                Boolean flag;
                if(enteredTime.equals("12:00PM")){
                    //Log.d("mDeviceTime", String.valueOf(currentTime));
                    //Log.d("mSpinnerTime", enteredTime);
                    flag = checktimings(currentTime, time1);
                    if (!flag){
                        Toast.makeText(ReviewOrder.this, "Cannot Order After 10:00AM", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ReviewOrder.this, "Order Confimed", Toast.LENGTH_SHORT).show();
                    }
                }else if(enteredTime.equals("3:00PM")){
                    flag = checktimings(currentTime, time2);
                    if (!flag){
                        Toast.makeText(ReviewOrder.this, "Cannot Order After 1:00PM", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ReviewOrder.this, "Order Confimed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checktimings(String time, String endtime) {
        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);
            if(date1.before(date2)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }

    public void CartRedirect (View view){
        startActivity(new Intent(getApplicationContext(), CartActivity.class));
    }
}