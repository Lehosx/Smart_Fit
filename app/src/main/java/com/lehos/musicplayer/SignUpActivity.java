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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    Button signup_btn;
    EditText signup_name,signup_email,signup_password,signup_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //getting iDS
        signup_btn = findViewById(R.id.signup_btn);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_number = findViewById(R.id.signup_number);
        signup_name = findViewById(R.id.signup_name);

        //Create Account
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number =signup_number.getText().toString();
                String name =signup_name.getText().toString();
                String email=signup_email.getText().toString();
                String password =signup_password.getText().toString();

                progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setTitle("Creating");
                progressDialog.setMessage("Account");
                progressDialog.show();

                FirebaseAuth
                        .getInstance()
                        .createUserWithEmailAndPassword(email.trim(),password.trim())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();
                                FirebaseAuth.getInstance().getCurrentUser()
                                        .updateProfile(userProfileChangeRequest);
                                new MySharedPreferences(SignUpActivity.this).setMyData(number);
                                UserModel userModel=new UserModel();
                                userModel.setUserName(name);
                                userModel.setUserNumber(number);
                                userModel.setUserEmail(email);

                                FirebaseFirestore
                                        .getInstance()
                                        .collection("Users")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(userModel);
                                reset();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void reset() {
        progressDialog.cancel();
        Toast.makeText(this, "Account Created Login Please", Toast.LENGTH_SHORT).show();
//        FirebaseAuth.getInstance().signOut();
    }

    public void login_link(View view){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}