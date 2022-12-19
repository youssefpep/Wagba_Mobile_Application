package com.example.wagba_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Models.PreviousOrdersData;
import com.example.wagba_app.R;

import java.util.ArrayList;

public class PreviousOrdersAdapter extends RecyclerView.Adapter<PreviousOrdersAdapter.MyHolder> {
    private ArrayList<PreviousOrdersData> cardsData;
    private Context mcontext;

    public PreviousOrdersAdapter(ArrayList<PreviousOrdersData> recyclerDataArrayList, Context mcontext) {
        this.cardsData = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.previousorders_items, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PreviousOrdersAdapter.MyHolder holder, int position) {
        PreviousOrdersData recyclerData = cardsData.get(position);
        holder.itemName.setText(recyclerData.getTitle());
        holder.itemPrice.setText(recyclerData.getPrice());
        holder.itemdescription.setText(recyclerData.getDescription());


    }

    @Override
    public int getItemCount() {
        return cardsData.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemdescription;
        public TextView itemPrice;
        public int mItems;
        public TextView mItemsText;
        public ImageButton button;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemdescription = itemView.findViewById(R.id.itemdescription);
            itemPrice = itemView.findViewById(R.id.itemprice);
        }
    }

}
