package com.example.Nhacollection;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;

public class PushNotification extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseMessaging.getInstance().subscribeToTopic("sample_notification");


    }
}

