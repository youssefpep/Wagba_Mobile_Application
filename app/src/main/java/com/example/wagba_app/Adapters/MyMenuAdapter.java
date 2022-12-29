package com.example.wagba_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Activities.MenuActivity;
import com.example.wagba_app.Interfaces.ItemClickListener;
import com.example.wagba_app.Models.CardsData;
import com.example.wagba_app.R;

import java.util.ArrayList;

public class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.MyHolder> {
    private ArrayList<CardsData> cardsData;
    private Context context;
    private ItemClickListener itemClickListener;

    public MyMenuAdapter(ArrayList<CardsData> recyclerDataArrayList, Context context) {
        this.cardsData = recyclerDataArrayList;
        this.context = context;

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_items, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CardsData recyclerData = cardsData.get(position);
        holder.restaurantName.setText(recyclerData.getTitle());
        holder.restaurantImage.setImageResource(recyclerData.getImgid());
        holder.viewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity.class);
                intent.putExtra("title", recyclerData.getTitle());
                intent.putExtra("restaurantPosition", holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cardsData.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView restaurantName;
        public ImageView restaurantImage;
        public Button viewMenu;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            viewMenu = itemView.findViewById(R.id.viewmenu);
        }

    }





}
