package com.example.wagba_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.R;
import com.example.wagba_app.Models.RestaurantData;


import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyHolder> {
    private ArrayList<RestaurantData> cardsData;
    private Context context;



    public RestaurantAdapter(ArrayList<RestaurantData> recyclerDataArrayList,  Context context) {
        this.cardsData = recyclerDataArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.restaurant_items, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.MyHolder holder, int position) {
        RestaurantData recyclerData = cardsData.get(position);
        holder.itemName.setText(recyclerData.getName());
        holder.itemPrice.setText(recyclerData.getPrice());
        String link = recyclerData.getImage();
        Picasso.get().load(link).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return cardsData.size();
    }




    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView itemName;
        public ImageView itemImage;
        public TextView itemPrice;
        public int mItems;
        public TextView mItemsText;
        public ImageButton button;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.increaseItems);
            itemName = itemView.findViewById(R.id.itemName);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemPrice = itemView.findViewById(R.id.itemprice);
            mItemsText = itemView.findViewById(R.id.itemsCount);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItems++;
                    mItemsText.setText(String.valueOf(mItems));
                }
            });
        }


    }

}
