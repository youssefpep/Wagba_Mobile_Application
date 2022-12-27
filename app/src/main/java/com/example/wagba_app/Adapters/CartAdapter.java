package com.example.wagba_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Models.CartData;
import com.example.wagba_app.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {
    private ArrayList<CartData> cardsData;
    private Context mcontext;

    public CartAdapter(ArrayList<CartData> recyclerDataArrayList, Context mcontext) {
        this.cardsData = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CartData recyclerData = cardsData.get(position);
        holder.itemName.setText(recyclerData.getTitle());
        holder.spinner.setSelection(recyclerData.getSpinnerValue());
        holder.itemPrice.setText(recyclerData.getPrice());


    }

    @Override
    public int getItemCount() {
        return cardsData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public ImageView itemImage;
        public TextView itemPrice;
        public Spinner spinner;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemPrice = itemView.findViewById(R.id.itemprice);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
        }
    }
}
