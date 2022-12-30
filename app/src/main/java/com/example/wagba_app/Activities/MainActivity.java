package com.example.wagba_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Adapters.MyMenuAdapter;
import com.example.wagba_app.Interfaces.ItemClickListener;
import com.example.wagba_app.Interfaces.UserDao;
import com.example.wagba_app.Models.CardsData;
import com.example.wagba_app.Models.UserDatabase;
import com.example.wagba_app.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<CardsData> cardsData;
    private UserDatabase mUserDatabase ;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.horizontalRV);
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

        cardsData=new ArrayList<>();
        cardsData.add(new CardsData("Abo Mazen",R.drawable.abomazen));
        cardsData.add(new CardsData("Arabiata",R.drawable.arabiata));
        cardsData.add(new CardsData("Bazooka",R.drawable.bazooka));
        cardsData.add(new CardsData("Cilantro",R.drawable.cilantro));
        cardsData.add(new CardsData("Cinnabon",R.drawable.cinnabon));
        cardsData.add(new CardsData("Hardee\'s",R.drawable.hardees));
        cardsData.add(new CardsData("KFC",R.drawable.kfc));
        cardsData.add(new CardsData("McDonald\'s",R.drawable.mac));
        cardsData.add(new CardsData("Papa John\'s",R.drawable.papajohns));
        cardsData.add(new CardsData("Pizza Hut",R.drawable.pizzahut));
        MyMenuAdapter adapter=new MyMenuAdapter(cardsData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyItemInserted(cardsData.size());
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void backClicked(View view){
        onBackPressed();
    }
}
