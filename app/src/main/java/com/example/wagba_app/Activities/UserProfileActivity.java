package com.example.wagba_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wagba_app.Interfaces.UserDao;
import com.example.wagba_app.Models.User;
import com.example.wagba_app.Models.UserDatabase;
import com.example.wagba_app.R;
import com.google.android.material.navigation.NavigationView;

public class UserProfileActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private UserDatabase mUserDatabase;
    private UserDao mUserDao;
    private TextView memail;
    private EditText musername;
    private EditText mphone;
    private Button updateUser;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_profile);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        memail = findViewById(R.id.email);
        musername = findViewById(R.id.username);
        mphone = findViewById(R.id.phoneNumber);
        updateUser = findViewById(R.id.update);
        mUserDatabase = UserDatabase.getDatabase(getApplicationContext());
        mUserDao = mUserDatabase.userDao();


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navbar_open, R.string.navbar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mUserDao.deleteAll();
                            }
                        }).start();

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                User mUser = mUserDao.getUsers();
                String userName = mUser.getUsername();
                String email = mUser.getEmail();
                String phone = mUser.getPhone();
                if (mUser.getPhone() != null){
                    mphone.setText(phone);
                }
                if (mUser.getUsername() != null){
                    musername.setText(userName);
                }
                if (mUser.getEmail() != null){
                    memail.setText(email);
                }




            }
        }).start();

        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User mUser = mUserDao.getUsers();
                        String userName = musername.getText().toString();
                        String email = mUser.getEmail();
                        String phone = mphone.getText().toString();
                        mUserDao.deleteAll();
                        Log.d("updatephone", phone);
                        mUser = new User(userName, email, phone);
                        mUserDao.insert(mUser);
                    }
                }).start();
            }
        });
    }

    public void homeRedirect (View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}