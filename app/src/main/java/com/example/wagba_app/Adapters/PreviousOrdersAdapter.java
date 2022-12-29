package com.example.wagba_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_app.Models.CardsData;
import com.example.wagba_app.Models.CartData;
import com.example.wagba_app.Models.PreviousOrdersData;
import com.example.wagba_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class PreviousOrdersAdapter extends RecyclerView.Adapter<PreviousOrdersAdapter.MyHolder> {
    private ArrayList<PreviousOrdersData> previousOrdersData;
    private Context mcontext;

    public PreviousOrdersAdapter(ArrayList<PreviousOrdersData> recyclerDataArrayList, Context context) {
        this.previousOrdersData = recyclerDataArrayList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.previousorders_items, parent, false);
        return new PreviousOrdersAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        PreviousOrdersData recyclerData = previousOrdersData.get(position);
        holder.itemName.setText("Order #" +recyclerData.getTitle());
        holder.itemStatus.setText(recyclerData.getStatus());
    }

    @Override
    public int getItemCount() {
        return previousOrdersData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemStatus;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemStatus = itemView.findViewById(R.id.status);
        }
    }

}
