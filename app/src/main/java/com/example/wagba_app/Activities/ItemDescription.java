package com.example.wagba_app.Activities;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
    String id, name;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_description);
        itemName = findViewById(R.id.itemName);
        itemImage = findViewById(R.id.itemImage);
        itemDescription = findViewById(R.id.itemDescription);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        System.out.println(databaseReference);
        System.out.println(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    RestaurantData data = snapshot1.getValue(RestaurantData.class);
                    itemName.setText(data.getName());
                }
                String value = String.valueOf(snapshot.child("name").getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}