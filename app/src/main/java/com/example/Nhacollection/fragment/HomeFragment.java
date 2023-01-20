package com.example.Nhacollection.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Nhacollection.Adapter.BestSellAdapter;
import com.example.Nhacollection.Adapter.CategoryAdapter;
import com.example.Nhacollection.Adapter.FeatureAdapter;
import com.example.Nhacollection.Adapter.Home_Appliances_Adapter;
import com.example.Nhacollection.Domain.BestSell;
import com.example.Nhacollection.Domain.Category;
import com.example.Nhacollection.Domain.Feature;
import com.example.Nhacollection.Domain.Home_Appliances;
import com.example.Nhacollection.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


 public class HomeFragment extends Fragment {
    private FirebaseFirestore mStore;
    //Category
    private List<Category>mCategoryList;
    private CategoryAdapter mcategoryadapter;
    private RecyclerView mcategory_recycleview;
    //Featured
    private List<Feature>mFeatureList;
    private FeatureAdapter mFeatureAdapter;
    private RecyclerView mFeatureRecyclerView;

     //Home_Appliances

    private List<Home_Appliances> homeappliancesList;
     private Home_Appliances_Adapter homeAppliancesAdapter;
     private RecyclerView mHomeAppliancesRecyclerView;
     private TextView mHomeAppliancesSell;
    //BestSell Tab
    private List<BestSell> mBestSellList;
     private BestSellAdapter mBestSellAdapter;
     private RecyclerView mBestSellRecyclerView;
     private TextView mSeeAll;
     private TextView mFeature;
     private TextView mBestSell;
     //Banner
//     private List<Banner> bannersList;
//     private Banner_Adapter bannerAdapter;
//     private RecyclerView mBannerRecyclerView;


     public HomeFragment() {
     }


     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mStore=FirebaseFirestore.getInstance();
//        mFeature=view.findViewById(R.id.featured_seeALL);
//        mBestSell=view.findViewById(R.id.bEST_seeALL);
//        mHomeAppliancesSell=view.findViewById(R.id.seeAllHome);

        mcategory_recycleview=view.findViewById(R.id.category_recycler);
        mFeatureRecyclerView=view.findViewById(R.id.Feature_Recycler);
        mBestSellRecyclerView = view.findViewById(R.id.Best_sell_recycler);
        mHomeAppliancesRecyclerView=view.findViewById(R.id.Home_appliances_Recycler);
//        mBannerRecyclerView=view.findViewById(R.id.banner_recycler);
       //Category
        mCategoryList =new ArrayList<>();
        mcategoryadapter=new CategoryAdapter(getContext(),mCategoryList);
        mcategory_recycleview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mcategory_recycleview.setAdapter(mcategoryadapter);
        //Featured
        mFeatureList =new ArrayList<>();
        mFeatureAdapter=new FeatureAdapter(getContext(),mFeatureList);
        mFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mFeatureRecyclerView.setAdapter(mFeatureAdapter);
         //For BestSell
        mBestSellList=new ArrayList<>();
        mBestSellAdapter=new BestSellAdapter(getContext(),mBestSellList);
        mBestSellRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mBestSellRecyclerView.setAdapter(mBestSellAdapter);
        //Home Appliances
         homeappliancesList=new ArrayList<>();
        homeAppliancesAdapter=new Home_Appliances_Adapter(getContext(),homeappliancesList);
         mHomeAppliancesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
         mHomeAppliancesRecyclerView.setAdapter(homeAppliancesAdapter);
        //Banner
//        bannersList=new ArrayList<>();
//        bannerAdapter=new Banner_Adapter(getContext(),bannersList);
//        mBannerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
//        mBannerRecyclerView.setAdapter(bannerAdapter);
//
//         mStore.collection("Banner")
//                 .get()
//                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                     @Override
//                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                         if (task.isSuccessful()) {
//                             for (QueryDocumentSnapshot document : task.getResult()) {
//                                 Log.d(TAG, document.getId() + " => " + document.getData());
//                                 Banner banner =document.toObject(Banner.class);
//                                 bannersList.add(banner);
//                                 bannerAdapter.notifyDataSetChanged();
//                             }
//                         } else {
//                             Log.w(TAG, "Error getting documents.", task.getException());
//                         }
//                     }
//                 });

         mStore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Category category =document.toObject(Category.class);
                                mCategoryList.add(category);
                                mcategoryadapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        mStore.collection("Feature_Product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Feature feature =document.toObject(Feature.class);
                                mFeatureList.add(feature);
                                mFeatureAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

         mStore.collection("Home_Appliances")
                 .get()
                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         if (task.isSuccessful()) {
                             for (QueryDocumentSnapshot document : task.getResult()) {
                                 Log.d(TAG, document.getId() + " => " + document.getData());
                                Home_Appliances home_appliances =document.toObject(Home_Appliances.class);
                                 homeappliancesList.add(home_appliances);
                                 homeAppliancesAdapter.notifyDataSetChanged();
                             }
                         } else {
                             Log.w(TAG, "Error getting documents.", task.getException());
                         }
                     }
                 });
         mStore.collection("New Arrivals")
                 .get()
                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         if (task.isSuccessful()) {
                             for (QueryDocumentSnapshot document : task.getResult()) {
                                 BestSell bestSell=document.toObject(BestSell.class);
                                 mBestSellList.add(bestSell);
                                 mBestSellAdapter.notifyDataSetChanged();
                             }
                         } else {
                             Log.w("TAG", "Error getting documents.", task.getException());
                         }
                     }
                 });

//         mBestSell.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent intent=new Intent(getContext(), ItemsActivity.class);
//                 startActivity(intent);
//             }
//         });
//         mFeature.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent intent=new Intent(getContext(), ItemsActivity.class);
//                 startActivity(intent);
//             }
//         });
//         mHomeAppliancesSell.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 Intent intent=new Intent(getContext(), ItemsActivity.class);
//                 startActivity(intent);
//             }
//         });

       return view;

    }
}