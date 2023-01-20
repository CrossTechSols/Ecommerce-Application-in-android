package com.example.Nhacollection;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Nhacollection.Adapter.ItemsRecyclerAdapter;
import com.example.Nhacollection.Domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {
    private FirebaseFirestore mStore;
    List<Items> mItemsList;
    private RecyclerView itemRecyclerView;
    private ItemsRecyclerAdapter itemsRecyclerAdapter;
//    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        String type = getIntent().getStringExtra("type");
        mStore = FirebaseFirestore.getInstance();
        mItemsList = new ArrayList<>();
//        mToolbar= findViewById(R.id.item_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Items");

        itemRecyclerView = findViewById(R.id.items_recycler);
        itemRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        itemsRecyclerAdapter = new ItemsRecyclerAdapter(this, mItemsList);
        itemRecyclerView.setAdapter(itemsRecyclerAdapter);
        if (type == null || type.isEmpty()) {
            mStore.collection("All items").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            Log.i("TAG", "onComplete: " + doc.toString());
                            Items items = doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if(type!=null && type.equalsIgnoreCase("Headphones")) {
            mStore.collection("All items").whereEqualTo("type", "Headphones").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            Log.i("TAG", "onComplete: " + doc.toString());
                            Items items = doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if(type!=null && type.equalsIgnoreCase("processors")) {
            mStore.collection("All items").whereEqualTo("type", "processors").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            Log.i("TAG", "onComplete: " + doc.toString());
                            Items items = doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}