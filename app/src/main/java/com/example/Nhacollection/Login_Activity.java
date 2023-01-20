package com.example.Nhacollection;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {
    private Button loginbtn;
    private EditText Email, Password;
    private TextView gotosignup;
    private Button signInwithGoogle;
    private TextView forgetpass;
//    private GoogleSignInClient client;
    //TextView SignLater;
    private ProgressDialog loadingBar;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String emailpattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Pass);
        loginbtn = findViewById(R.id.Login);
        gotosignup=findViewById(R.id.gotosignup);
//        signInRequest = findViewById(R.id.editTextTextPersonName);

        //SignLater = findViewById(R.id.Later);
        loadingBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        forgetpass=findViewById(R.id.forgetpass);


//        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        client = GoogleSignIn.getClient(this, options);
//        signInRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = client.getSignInIntent();
//                startActivityForResult(i, 1234);
//
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1234) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//
//                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//                FirebaseAuth.getInstance().signInWithCredential(credential)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
//                                    startActivity(intent);
//
//                                } else {
//                                    Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                        });
//
//            } catch (ApiException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//




        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                performlogin();
            }
        });
        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login_Activity.this,Register_Activity.class);
                startActivity(intent);
            }
        });
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this,Forget_Password.class);
                startActivity(intent);
            }
        });
//        signInRequest = BeginSignInRequest.builder()
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                .build();

//        Google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    private void performlogin() {
        if (!isConnected(this)){
            showCustomDialog();
        }
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        if (!email.matches(emailpattern)) {
            Toast.makeText(this, "Please Fill the fields", Toast.LENGTH_SHORT).show();
        } else if (pass.isEmpty() || pass.length() < 6) {
            Toast.makeText(this, "Enter  Password", Toast.LENGTH_SHORT).show();

        } else {
            loadingBar.setTitle("Wait for Login");
            loadingBar.setMessage("Login");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loadingBar.dismiss();
                        Toast.makeText(Login_Activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Login_Activity.this,Home_Activity.class);
                        startActivity(intent);

                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(Login_Activity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    private boolean isConnected(Login_Activity login_activity) {
        ConnectivityManager connectivityManager= (ConnectivityManager) login_activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ( wifiConn!=null&& wifiConn.isConnected()||(mobileConn!=null&&mobileConn.isConnected())){
            return true;
        }
        else {
            return false ;
        }

    }
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
        builder.setMessage("please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),SplashScreenActivity.class));
                        finish();

                    }
                });



    }


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(Login_Activity.this,Home_Activity.class));
            finish();
        }
    }
}


