package com.example.wagba_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ItemDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_description);
    }
}