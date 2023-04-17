package com.lehos.musicplayer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {


//    FirebaseAuth auth;
    ProgressDialog progressDialog;
    Button login_btn;
    EditText login_email,login_password;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn = findViewById(R.id.login_btn);
        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);

//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
//        if(user != null){
//            Intent intent = new Intent(this,HomeActivity.class);
//            startActivity(intent);
//        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=login_email.getText().toString().trim();
                String password =login_password.getText().toString().trim();
                progressDialog=new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle("Login");
                progressDialog.setMessage("In Process");
                progressDialog.show();
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.cancel();
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();
                            }
                        });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }
    }

    public void signup_link(View view){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}