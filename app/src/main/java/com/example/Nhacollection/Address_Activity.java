package com.example.Nhacollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Nhacollection.Adapter.AddressAdapter;
import com.example.Nhacollection.Domain.Address;
import com.example.Nhacollection.Domain.BestSell;
import com.example.Nhacollection.Domain.Feature;
import com.example.Nhacollection.Domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Address_Activity extends AppCompatActivity implements AddressAdapter.SelectedAddress {
    private RecyclerView mAddressRecyclerView;
    private AddressAdapter mAddressAdapter;
    private Button paymentBtn;
    private Button mAddAddress;
    private List<Address> mAddressList;
    private FirebaseFirestore mStore;
    private FirebaseAuth mAuth;
//    private Toolbar mToolbar;
    String address="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        final Object obj=getIntent().getSerializableExtra("item");
        List<Items> itemsList = (ArrayList<Items>) getIntent().getSerializableExtra("itemList");
        mAddressRecyclerView=findViewById(R.id.address_recycler);
        paymentBtn=findViewById(R.id.payment_btn);
        mAddAddress=findViewById(R.id.add_address_btn);
//        mToolbar=findViewById(R.id.address_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth=FirebaseAuth.getInstance();
        mStore=FirebaseFirestore.getInstance();
        mAddressList=new ArrayList<>();
        mAddressAdapter=new AddressAdapter(getApplicationContext(),mAddressList,this);
        mAddressRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAddressRecyclerView.setAdapter(mAddressAdapter);

        mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot doc:task.getResult().getDocuments()){
                        Address address=doc.toObject(Address.class);
                        mAddressList.add(address);
                        mAddressAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        mAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Address_Activity.this,Add_Address.class);
                startActivity(intent);
            }
        });
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount=0.0;
                double shippingcost=120;
                String url="";
                String name="";
                if(obj instanceof Feature){
                    Feature  f= (Feature) obj;
                    amount=f.getPrice();
                    url=f.getImg_url();
                    name=f.getName();
                    shippingcost=f.getShippingcost();
                }
                if(obj instanceof BestSell){
                    BestSell  b= (BestSell) obj;
                    amount=b.getPrice();
                    url=b.getImg_url();
                    name=b.getName();

                }
                if(obj instanceof Items){
                    Items  i= (Items) obj;
                    amount=i.getPrice();
                    url=i.getImg_url();
                    name=i.getName();

                }
                if(itemsList!=null && itemsList.size()>0){
                    Intent intent=new Intent(Address_Activity.this,AllCards.class);
                    intent.putExtra("itemsList", (Serializable) itemsList);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(Address_Activity.this,AllCards.class);
                    intent.putExtra("amount",amount);
                    intent.putExtra("shipping",shippingcost);
                    intent.putExtra("img_url",url);
                    intent.putExtra("name",name);
                    intent.putExtra("address",address);

                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void setAddress(String s) {
        address=s;
    }
}