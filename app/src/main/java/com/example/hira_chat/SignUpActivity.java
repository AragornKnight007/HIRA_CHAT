package com.example.hira_chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.hira_chat.Models.Users;
import com.example.hira_chat.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.MoreObjects;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    FirebaseDatabase database;

    private FirebaseAuth auth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        auth= FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(SignUpActivity.this);

        progressDialog.setTitle("Creaing Account");

        progressDialog.setMessage("We Are Creating Your Account");

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();

                auth.createUserWithEmailAndPassword
                        (binding.etEmail.getText().toString(),binding.etPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressDialog.dismiss();

                                if(task.isSuccessful()){

                                    Users user = new Users(binding.userName.getText().toString(),
                                            binding.etEmail.getText().toString(),binding.etPassword.getText().toString());

                                    String id=task.getResult().getUser().getUid();

                                    database.getReference().child("Users").child(id).setValue(user);

                                    Toast.makeText(SignUpActivity.this ,"User Created Sucessfull",Toast.LENGTH_SHORT).show();
                                }

                                else{

                                    Toast.makeText(SignUpActivity.this , task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        binding.tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);

                startActivity(intent);

            }
        });
    }
}