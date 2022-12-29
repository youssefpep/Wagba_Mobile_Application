package com.example.wagba_app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Interfaces.ItemClickListener;
import com.example.wagba_app.R;
import com.example.wagba_app.Models.RestaurantData;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyHolder> {
    ArrayList<RestaurantData> cardsData;
    Context context;
    ItemClickListener clickListener;
    ItemClickListener clickListener1;

    public RestaurantAdapter(ArrayList<RestaurantData> recyclerDataArrayList, Context context, ItemClickListener clickListener, ItemClickListener clickListener1) {
        this.cardsData = recyclerDataArrayList;
        this.context = context;
        this.clickListener = clickListener;
        this.clickListener1 = clickListener1;
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
        String name = holder.itemName.getText().toString();
        String price = holder.itemPrice.getText().toString();
        holder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.click(holder.getAdapterPosition());
            }
        });
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener1.addToCart(name, price, link);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardsData.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView itemName;
        public ImageView itemImage;
        public TextView itemPrice;
        public Button description, cart;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemPrice = itemView.findViewById(R.id.status);
            cart = itemView.findViewById(R.id.cartBtn);
            description = itemView.findViewById(R.id.viewdescription);
        }
    }

}