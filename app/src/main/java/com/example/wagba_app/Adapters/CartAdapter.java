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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class CartAdapter extends FirebaseRecyclerAdapter<CartData, CartAdapter.MyHolder> {
    private Context mcontext;

    public CartAdapter(@NonNull FirebaseRecyclerOptions<CartData> options) {
        super(options);
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
    public void onBindViewHolder(@NonNull MyHolder holder, int position, @NonNull CartData model) {
        holder.itemName.setText(model.getTitle());
        holder.itemPrice.setText(model.getPrice());
        String link = model.getImage();
        Picasso.get().load(link).into(holder.itemImage);

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
            itemPrice = itemView.findViewById(R.id.status);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
        }
    }
}
