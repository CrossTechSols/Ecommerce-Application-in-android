package com.example.Nhacollection.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Nhacollection.Domain.Banner;
import com.example.Nhacollection.R;

import java.util.List;

public class Banner_Adapter  extends RecyclerView.Adapter<Banner_Adapter.ViewHolder> {
    private Context context;
    List <Banner> BannerList;
    public Banner_Adapter(Context context,List<Banner>bannerList){
        this.context=context;
        this.BannerList=bannerList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(BannerList.get(position).getImage_url()).into(holder.bannerimage);


    }

    @Override
    public int getItemCount() {
        return BannerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView bannerimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerimage=itemView.findViewById(R.id.banner_image);
        }
    }
}
