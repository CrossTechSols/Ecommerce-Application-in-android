package com.example.Nhacollection.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Nhacollection.Domain.Address;
import com.example.Nhacollection.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context applicationContext;
    List<Address> mAddressList;
    SelectedAddress selectedAddress;
    public AddressAdapter(Context applicationContext, List<Address> mAddressList,SelectedAddress selectedAddress ) {
        this.applicationContext=applicationContext;
        this.mAddressList= mAddressList;
        this.selectedAddress=selectedAddress;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(applicationContext).inflate(R.layout.single_address_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {
        holder.mAddress.setText(mAddressList.get(position).getAddress());
        holder.mRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(applicationContext, "Checked", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView mAddress;
            private RadioButton mRadio;

        public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mAddress=itemView.findViewById(R.id.address_add);
                mRadio=itemView.findViewById(R.id.select_address);
            }
        }
    public interface SelectedAddress{
        public void setAddress(String s);
    }

}

