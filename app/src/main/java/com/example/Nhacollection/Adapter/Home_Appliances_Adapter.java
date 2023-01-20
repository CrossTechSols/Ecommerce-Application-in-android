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
import com.example.Nhacollection.Domain.Home_Appliances;
import com.example.Nhacollection.R;

import java.util.List;

public class Home_Appliances_Adapter extends RecyclerView.Adapter <Home_Appliances_Adapter.viewHolder> {
    Context context;
    List<Home_Appliances> home_appliancesList;
    public Home_Appliances_Adapter(Context context, List<Home_Appliances> home_appliancesList) {
        this.context=context;
        this.home_appliancesList=home_appliancesList;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_appliances_item,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mName.setText(home_appliancesList.get(position).getName());
        holder.mPrice.setText(home_appliancesList.get(position).getPrice()+"PKR");
        Glide.with(context).load(home_appliancesList.get(position).getImg_url()).into(holder.mImage);
        holder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Detail_Activity.class);
                intent.putExtra("detail",home_appliancesList.get(position));
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return  home_appliancesList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mPrice;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mImage=itemView.findViewById(R.id.Home_Appliance);
            mName=itemView.findViewById(R.id.Appliances_name);
            mPrice=itemView.findViewById(R.id.Appliances_cost);

        }
    }

}
