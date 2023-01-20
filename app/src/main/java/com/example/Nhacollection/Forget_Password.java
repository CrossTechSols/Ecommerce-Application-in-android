package com.example.Nhacollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


public class Forget_Password extends AppCompatActivity {
     private EditText RegisteredEmail;
     private Button ResestPass;
     private ProgressBar progressBar;
     private FirebaseAuth ResetAuth;
     private final  static String TAG ="Forget_password_Activity";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        RegisteredEmail=findViewById(R.id.password_reset_email);
        ResestPass=findViewById(R.id.password_reset);
        progressBar=findViewById(R.id.progressBar);
        ResestPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=RegisteredEmail.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Forget_Password.this, "Please enter register Email ", Toast.LENGTH_SHORT).show();
                    RegisteredEmail.setError("Email is Required");
                    RegisteredEmail.requestFocus();
                } else   if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Toast.makeText(Forget_Password.this, "Please enter Valid Email ", Toast.LENGTH_SHORT).show();
                        RegisteredEmail.setError("Valid Email is Required");
                        RegisteredEmail.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    resetPassword(email);

                }

            }
        });


    }

    private void resetPassword(String email) {
        ResetAuth=FirebaseAuth.getInstance();
        ResetAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Forget_Password.this, "Please check your Gmail for password Reset link", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(Forget_Password.this,Login_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    try{
                        throw task.getException();

                    } catch (FirebaseAuthInvalidUserException e) {
                        RegisteredEmail.setError("User Does Not Exist.plasea register again");
                    } catch (Exception e){
                        Log.e(TAG,e.getMessage());
                    }
                }
                progressBar.setVisibility(View.GONE);

                
                
            }
        });
    }
}