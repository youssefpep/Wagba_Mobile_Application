package com.example.wagba_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Adapters.MyMenuAdapter;
import com.example.wagba_app.Adapters.RestaurantAdapter;
import com.example.wagba_app.Models.RestaurantData;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private RestaurantAdapter restaurantAdapter;
    private ArrayList<RestaurantData> list;
    private String title;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.horizontalRV);
        recyclerView.setHasFixedSize(true);


        list = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(list, this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navbar_open, R.string.navbar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            title = extras.getString("title");
            if (title.equals("McDonald's")){
                databaseReference = FirebaseDatabase.getInstance().getReference("mac");
            }else if (title.equals("Arabiata")){
                databaseReference = FirebaseDatabase.getInstance().getReference("arabiata");
            }else if (title.equals("KFC")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("kfc");
            }else if (title.equals("Bazooka")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("bazooka");
            }else if (title.equals("Abo Mazen")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("abomazen");
            }else if (title.equals("Hardee's")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("hardees");
            }else if (title.equals("Cilantro")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("cilantro");
            }else if (title.equals("Cinnabon")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("cinnabon");
            }
            else if (title.equals("Papa John's")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("papajohns");
            }
            else if (title.equals("Pizza Hut")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("pizzahut");
            }
        }

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(restaurantAdapter);
        recyclerView.getAdapter().notifyItemInserted(list.size());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    RestaurantData data = dataSnapshot.getValue(RestaurantData.class);
                    System.out.println("Name: " + data.getName());
                    System.out.println("Price: " + data.getPrice());
                    System.out.println("Description: " + data.getDescription());
                    System.out.println("Image: " + data.getImage());

                    list.add(data);
                }
                restaurantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }

    public void CartRedirect (View view){
        startActivity(new Intent(getApplicationContext(), CartActivity.class));
    }

    public void viewDescription (View view){
        startActivity(new Intent(getApplicationContext(), ItemDescription.class));
    }
}