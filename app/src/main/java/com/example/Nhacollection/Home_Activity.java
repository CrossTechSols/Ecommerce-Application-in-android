package com.example.Nhacollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Nhacollection.Adapter.ItemsRecyclerAdapter;
import com.example.Nhacollection.Domain.Items;
import com.example.Nhacollection.fragment.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity {
    Fragment homeFragment;
    private Toolbar mToolbar;
    private ImageView imageView2;
    private  FirebaseAuth mAuth;
    private EditText mSearchtext;
    private FirebaseFirestore mStore;
    private ImageView addtocart;
    private ImageView logout;
    private List<Items> mItemsList;
    private RecyclerView mItemRecyclerView;
    private ItemsRecyclerAdapter itemsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeFragment=new HomeFragment();
        loadFragment(homeFragment);
        mAuth=FirebaseAuth.getInstance();
//        mToolbar=findViewById(R.id.home_toolbar);
//        setSupportActionBar(mToolbar);
        mSearchtext=findViewById(R.id.Searchview);
        addtocart=findViewById(R.id.addtocart);
        logout=findViewById(R.id.logout);
        mStore=FirebaseFirestore.getInstance();
        mItemsList=new ArrayList<>();
        mItemRecyclerView=findViewById(R.id.search_recycler);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemsRecyclerAdapter=new ItemsRecyclerAdapter(this,mItemsList);
        mItemRecyclerView.setAdapter(itemsRecyclerAdapter);
        imageView2=findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,myorders.class);
                startActivity(intent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home_Activity.this,Login_Activity.class));
                finish();
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart =new Intent(Home_Activity.this, Cart_Activity.class);
                startActivity(cart);
            }
        });
        mSearchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    mItemsList.clear();
                    itemsRecyclerAdapter.notifyDataSetChanged();
                }else{
                    searchItem(s.toString());
                }
                searchItem(s.toString());


            }
        });

  }

    private void searchItem(String text) {
        if(!text.isEmpty()){
            mStore.collection("All items").whereGreaterThanOrEqualTo("name",text).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful() && task.getResult()!=null){
                                for(DocumentSnapshot doc:task.getResult().getDocuments()){
                                    Items items=doc.toObject(Items.class);
                                    mItemsList.add(items);
                                    itemsRecyclerAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }


    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onBackPressed(){
        finish();
    }
    }


