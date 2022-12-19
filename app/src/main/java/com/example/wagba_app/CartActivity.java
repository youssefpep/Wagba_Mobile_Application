package com.example.wagba_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Adapters.CartAdapter;
import com.example.wagba_app.Models.CartData;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<CartData> cardsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.horizontalRV);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navbar_open, R.string.navbar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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


        cardsData=new ArrayList<>();

        //added data to array list
        //cardsData.add(new RestaurantData("Big Mac",R.drawable.Bigmac));
        //cardsData.add(new RestaurantData("Chicken Macdo",R.drawable.macdo));
        //cardsData.add(new RestaurantData("McRoyale",R.drawable.mcroyal));
        //cardsData.add(new RestaurantData("Nuggets",R.drawable.mcnuggets));
        cardsData.add(new CartData("McFlurry",R.drawable.mcflurry, "100.00 EGP",1));
        cardsData.add(new CartData("Cocacola",R.drawable.cocacola, "100.00 EGP",2));


        //added data from arraylist to adapter class.
        CartAdapter adapter=new CartAdapter(cardsData,this);
        //setting grid layout manager to implement grid view.
        // in this method '2' represents number of colums to be displayed in grid view.

        //at last set adapter to recycler view.
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyItemInserted(cardsData.size());
    }

    public void reviewOrderRedirect (View view){
        startActivity(new Intent(getApplicationContext(), ReviewOrder.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.archives:
                startActivity(new Intent(getApplicationContext(), PreviousOrders.class));
                return true;
            case R.id.home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;

        }
        return true;
    }



}