package com.example.Nhacollection;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register_Activity extends AppCompatActivity {
    //    TextView Back;
    private Button Signupbutton;
    EditText PersonName, Email, Password, confirmPassword;
    private ProgressDialog loadingBar;
    FirebaseAuth fAuth;

    FirebaseFirestore Fstore;
    String emailpattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private AppCompatEditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Back = findViewById(R.id.BacktoLogin);
        Signupbutton = findViewById(R.id.Signupbutton);
        loadingBar = new ProgressDialog(this);
        PersonName = findViewById(R.id.Username);
        Email = findViewById(R.id.EMail);
        Password = findViewById(R.id.PassWord);
        confirmPassword = findViewById(R.id.ConFirmPass);
        fAuth = FirebaseAuth.getInstance();
        Fstore = FirebaseFirestore.getInstance();




        Signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = PersonName.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {



                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loadingBar.setTitle("Wait for Registration");
                           loadingBar.setMessage("Registration Completed ");
                            loadingBar.setCanceledOnTouchOutside(false);
                            loadingBar.show();
                            if (task.isSuccessful()) {
                                Toast.makeText(Register_Activity.this, "Account successfully created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent( Register_Activity.this,Login_Activity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(Register_Activity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register_Activity.this, "Please fill empty field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}