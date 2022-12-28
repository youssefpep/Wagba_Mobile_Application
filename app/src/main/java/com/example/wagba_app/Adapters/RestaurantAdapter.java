package com.example.wagba_app.Adapters;

import android.content.ClipData;
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

    public RestaurantAdapter(ArrayList<RestaurantData> recyclerDataArrayList, Context context, ItemClickListener clickListener) {
        this.cardsData = recyclerDataArrayList;
        this.context = context;
        this.clickListener = clickListener;
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
        holder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.click(holder.getAdapterPosition());
            }
        });
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.click(holder.getAdapterPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return cardsData.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
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
            itemPrice = itemView.findViewById(R.id.itemprice);
            cart = itemView.findViewById(R.id.addtocart);
            description = itemView.findViewById(R.id.viewdescription);
        }
    }

}