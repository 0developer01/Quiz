package com.example.quizchannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText mEmail, mPassword;
    private TextView mRegister, mforgetpassword, madmin;
    private Button mloginbtn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegister = findViewById(R.id.register);
        mforgetpassword = findViewById(R.id.forgotPassword);
        madmin = findViewById(R.id.admin);
        mloginbtn = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressBar);

        mloginbtn.setOnClickListener(this);
        mAuth= FirebaseAuth.getInstance();


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

        madmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin.class);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(Login.this, Register.class));
                break;


            case R.id.loginBtn:
                userLogin();
                break;
        }



    }

    private void userLogin() {

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(email.isEmpty()){
            mEmail.setError("Enter Email");
            mEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Enter a valid Email");
            mEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            mPassword.setError("Enter Password");
            mPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            mPassword.setError("Min password length is 6 characters");
            mPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.GONE);




        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else{
                    Toast.makeText(Login.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });




    }

}