package com.example.Nhacollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.Nhacollection.Domain.BestSell;
import com.example.Nhacollection.Domain.Feature;
import com.example.Nhacollection.Domain.Home_Appliances;
import com.example.Nhacollection.Domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Detail_Activity extends AppCompatActivity {
    private ImageView mImage;
    private TextView mItemName;
    private TextView mPrice;
    private TextView mItemDesc;
    private Button mAddToCart;
    private Button mBuyBtn;
    Feature feature = null;
    BestSell bestSell = null;
    Home_Appliances homeAppliances = null;
    Items items=null;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;

//    private Toolbar mToolbar;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        mToolbar=findViewById(R.id.detail_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mImage=findViewById(R.id.item_img);
//        mItemName=findViewById(R.id.item_name);
//        mPrice=findViewById(R.id.item_price);
        mItemDesc=findViewById(R.id.item_des);
        mAddToCart=findViewById(R.id.item_add_cart);
        mBuyBtn=findViewById(R.id.item_buy_now);
        mStore =FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Object obj=  getIntent().getSerializableExtra("detail");
        if (obj instanceof Feature) {
            feature = (Feature) obj;
        }else if(obj instanceof BestSell) {
            bestSell = (BestSell) obj;
        }else if (obj instanceof Home_Appliances){
            homeAppliances=(Home_Appliances)obj;

        } else if(obj instanceof Items){
            items= (Items) obj;
        }
        if (feature!= null) {
            Glide.with(getApplicationContext()).load(feature.getImg_url()).into(mImage);
//            mItemName.setText(feature.getName());
//            mPrice.setText(feature.getPrice() + "PKR");
            mItemDesc.setText(feature.getDescription());
        }

        if(bestSell!=null){
            Glide.with(getApplicationContext()).load(bestSell.getImg_url()).into(mImage);
//            mItemName.setText(bestSell.getName());
//            mPrice.setText(bestSell.getPrice()+"PKR");
            mItemDesc.setText(bestSell.getDescription());
        }
        if(items!=null){
            Glide.with(getApplicationContext()).load(items.getImg_url()).into(mImage);
//            mItemName.setText(items.getName());
//            mPrice.setText(items.getPrice()+"PKR");
            mItemDesc.setText(items.getDescription());

        }
        if (homeAppliances!= null) {
                Glide.with(getApplicationContext()).load(homeAppliances.getImg_url()).into(mImage);
//                mItemName.setText(homeAppliances.getName());
//                mPrice.setText(homeAppliances.getPrice() + "PKR");
                mItemDesc.setText(homeAppliances.getDescription());
        }




        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feature!=null){
                    mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                            .collection("Cart").add(feature).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Detail_Activity.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if(bestSell!=null){
                    mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                            .collection("Cart").add(bestSell).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Detail_Activity.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if (homeAppliances!=null){
                    mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                            .collection("Cart").add(homeAppliances).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Detail_Activity.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
                if(items!=null){
                    mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                            .collection("Cart").add(items).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Detail_Activity.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });



        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_Activity.this, Address_Activity.class);
                if(feature!=null){
                    intent.putExtra("item", feature);
                }
                if(bestSell!=null){
                    intent.putExtra("item", bestSell);
                }
                if(homeAppliances!=null) {
                    intent.putExtra("item", homeAppliances);
                }
                if(items!=null){
                    intent.putExtra("item", items);
                }

                startActivity(intent);
            }
        });
    }
}














