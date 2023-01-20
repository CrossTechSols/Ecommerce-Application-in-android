package com.example.Nhacollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();
    }

    public void GotoLogin(View view) {
        Intent intent = new Intent(MainActivity.this, Login_Activity.class);
        startActivity(intent);
    }

    public void GotoSignUp(View view) {
        Intent intent = new Intent(MainActivity.this, Register_Activity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,Home_Activity.class));
            finish();
        }
    }
}



