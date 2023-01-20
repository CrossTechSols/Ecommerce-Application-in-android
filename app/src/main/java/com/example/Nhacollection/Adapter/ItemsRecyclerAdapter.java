package com.example.Nhacollection.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Nhacollection.Detail_Activity;
import com.example.Nhacollection.Domain.Items;
import com.example.Nhacollection.R;

import java.util.List;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {
    Context applicationContext;
    List<Items> mItemsList;
    public ItemsRecyclerAdapter(Context applicationContext, List<Items> mItemsList) {
        this.applicationContext=applicationContext;
        this.mItemsList=mItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(applicationContext).inflate(R.layout.single_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mCost.setText("PKR"+mItemsList.get(position).getPrice());
        holder.mName.setText(mItemsList.get(position).getName());

            Glide.with(applicationContext).load(mItemsList.get(position).getImg_url()).into(holder.mItemImage);



        holder.mItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(applicationContext, Detail_Activity.class);
                intent.putExtra("detail",mItemsList.get(position));
                applicationContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mItemImage;
        private TextView mCost;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemImage=itemView.findViewById(R.id.item_image);
            mCost=itemView.findViewById(R.id.item_cost);
            mName=itemView.findViewById(R.id.item_nam);
        }
    }
}
