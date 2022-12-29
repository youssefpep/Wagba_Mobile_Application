package com.example.wagba_app.Activities;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wagba_app.Models.RestaurantData;
import com.example.wagba_app.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemDescription extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView itemName;
    ImageView itemImage;
    TextView itemDescription;
    TextView itemPrice;
    String name, description, imageRef, price;
    int restaurantNo, dishNo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_description);
        itemName = findViewById(R.id.itemName);
        itemImage = findViewById(R.id.itemImage);
        itemDescription = findViewById(R.id.itemDescription);
        itemPrice = findViewById(R.id.itemPrice);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("Name");
        description = extras.getString("Description");
        imageRef = extras.getString("Image");
        price = extras.getString("Price");
        restaurantNo = extras.getInt("RestaurantNumber");
        Log.d("vRestaurantNo", String.valueOf(restaurantNo));
        dishNo = extras.getInt("DishNumber");


        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int iterator = 0;
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Log.d("vIterator", String.valueOf(iterator));
                        if (iterator == restaurantNo) {
                            String restaurantKey = ds.getKey();
                            Log.d("vRestaurantName", String.valueOf(restaurantKey));
                            databaseReference.child(restaurantKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        int iterator2 = 0;
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            if(iterator2 == dishNo){
                                                String dishKey = ds.getKey();
                                                Log.d("vDishName", dishKey);
                                                Object name = ds.child(dishKey).child("name").getValue();
                                                Object description = ds.child(dishKey).child("description").getValue();
                                                Object image = ds.child(dishKey).child("image").getValue();
                                                Object price = ds.child(dishKey).child("price").getValue();
                                                itemName.setText((CharSequence) name);
                                                itemDescription.setText((CharSequence) description);
                                                itemPrice.setText((CharSequence) price);
                                                Picasso.get().load((String) image).into(itemImage);
                                            }
                                            iterator2++;

                                        }

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        iterator++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}