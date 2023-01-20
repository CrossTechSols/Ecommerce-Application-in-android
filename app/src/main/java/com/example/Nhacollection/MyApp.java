package com.example.Nhacollection;

import android.app.Application;

import co.paystack.android.PaystackSdk;


public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


            PaystackSdk.initialize(getApplicationContext());
        }
    }
