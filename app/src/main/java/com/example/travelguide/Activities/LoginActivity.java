package com.example.travelguide.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;


import com.example.travelguide.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
    }

    public void signin(View view){
        startActivity(new Intent(LoginActivity.this,SigninActivity.class));

    }
}