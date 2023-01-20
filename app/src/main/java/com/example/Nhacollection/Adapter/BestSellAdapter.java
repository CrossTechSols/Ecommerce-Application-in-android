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
import com.example.Nhacollection.Domain.BestSell;
import com.example.Nhacollection.R;

import java.util.List;


public class BestSellAdapter extends RecyclerView.Adapter<BestSellAdapter.ViewHolder> {
    Context context;
    List<BestSell> mBestSellList;
    public BestSellAdapter(Context context, List<BestSell> mBestSellList) {
        this.context=context;
        this.mBestSellList=mBestSellList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_bestsell_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mName.setText(mBestSellList.get(position).getName());
        holder.mPrice.setText(mBestSellList.get(position).getPrice()+"PKR");
        Glide.with(context).load(mBestSellList.get(position).getImg_url()).into(holder.BestSellImage);
        holder.BestSellImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Detail_Activity.class);
                intent.putExtra("detail",mBestSellList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBestSellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView BestSellImage;
        TextView mName;
        TextView mPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BestSellImage=itemView.findViewById(R.id.bs_image);
            mName=itemView.findViewById(R.id.bs_name);
            mPrice=itemView.findViewById(R.id.bs_cost);
        }
    }
}
