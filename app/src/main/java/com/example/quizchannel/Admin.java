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

public class Admin extends AppCompatActivity implements View.OnClickListener{


    private EditText madminEmail, madminPassword;
    private Button madminLoginBtn;
    private ProgressBar adminProgressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        madminEmail = findViewById(R.id.adminEmail);
        madminPassword = findViewById(R.id.adminPassword);
        madminLoginBtn = findViewById(R.id.adminLoginBtn);
        adminProgressBar = findViewById(R.id.adminProgressBar);

        madminLoginBtn.setOnClickListener(this);
        mAuth= FirebaseAuth.getInstance();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.adminLoginBtn:
                adminLogin();
                break;
        }
    }

    private void adminLogin() {

        String adminEmail = madminEmail.getText().toString().trim();
        String adminPassword = madminPassword.getText().toString().trim();

        if(adminEmail.isEmpty()){
            madminEmail.setError("Enter Email");
            madminEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(adminEmail).matches()){
            madminEmail.setError("Enter a valid Email");
            madminEmail.requestFocus();
            return;
        }

        if(adminPassword.isEmpty()){
            madminPassword.setError("Enter Password");
            madminPassword.requestFocus();
            return;
        }
        if(adminPassword.length() < 6){
            madminPassword.setError("Min password length is 6 characters");
            madminPassword.requestFocus();
            return;
        }

        adminProgressBar.setVisibility(View.GONE);




        mAuth.signInWithEmailAndPassword(adminEmail, adminPassword).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(Admin.this, AdminPanel.class));
                }else{
                    Toast.makeText(Admin.this, "Failed to login! Please check your " +
                            "credentials", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}