package com.example.wagba_app.Activities;

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

import com.example.wagba_app.Adapters.RestaurantAdapter;
import com.example.wagba_app.Interfaces.ItemClickListener;
import com.example.wagba_app.Interfaces.UserDao;
import com.example.wagba_app.Models.RestaurantData;
import com.example.wagba_app.Models.UserDatabase;
import com.example.wagba_app.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements ItemClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RestaurantData restaurantData;
    private DatabaseReference databaseReference;
    private RestaurantAdapter restaurantAdapter;
    private ItemClickListener clickListener;
    private ArrayList<RestaurantData> list;
    private String title;
    static int restaurantNumber;
    private UserDatabase mUserDatabase;
    private UserDao mUserDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);
        mUserDatabase = UserDatabase.getDatabase(getApplicationContext());
        mUserDao = mUserDatabase.userDao();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.horizontalRV);
        recyclerView.setHasFixedSize(true);


        list = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(list, this, this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navbar_open, R.string.navbar_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            title = extras.getString("title");
            if (title.equals("McDonald's")){
                databaseReference = FirebaseDatabase.getInstance().getReference("mac");
                System.out.println(databaseReference);
            }else if (title.equals("Arabiata")){
                databaseReference = FirebaseDatabase.getInstance().getReference("arabiata");
                System.out.println(databaseReference);
            }else if (title.equals("KFC")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("kfc");
                System.out.println(databaseReference);
            }else if (title.equals("Bazooka")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("bazooka");
                System.out.println(databaseReference);
            }else if (title.equals("Abo Mazen")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("abomazen");
                System.out.println(databaseReference);
            }else if (title.equals("Hardee's")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("hardees");
                System.out.println(databaseReference);
            }else if (title.equals("Cilantro")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("cilantro");
                System.out.println(databaseReference);
            }else if (title.equals("Cinnabon")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("cinnabon");
                System.out.println(databaseReference);
            }
            else if (title.equals("Papa John's")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("papajohns");
                System.out.println(databaseReference);
            }
            else if (title.equals("Pizza Hut")) {
                databaseReference = FirebaseDatabase.getInstance().getReference("pizzahut");
                System.out.println(databaseReference);
            }
            restaurantNumber = extras.getInt("restaurantPosition");
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(restaurantAdapter);
        recyclerView.getAdapter().notifyItemInserted(list.size());

        clickListener = new ItemClickListener() {
            @Override
            public void click(int position) {
                Intent intent = new Intent(MenuActivity.this, ItemDescription.class);
                intent.putExtra("Name", restaurantData.getName());
                intent.putExtra("Description", restaurantData.getDescription());
                intent.putExtra("Image", restaurantData.getImage());
                intent.putExtra("Price", restaurantData.getPrice());
                startActivity(intent);
            }
        };

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    RestaurantData data = dataSnapshot.getValue(RestaurantData.class);
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

    @Override
    public void click(int position) {


    }
}