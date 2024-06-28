package com.example.travelguide.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.engine.Resource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.travelguide.R;

import java.util.Locale;

public class SigninActivity extends AppCompatActivity {
    Spinner spinner;
    public static final String [] languages = {"Select languages","English","Hindi"};
    EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        spinner= findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedlang = parent.getItemAtPosition(position).toString();
                if(selectedlang.equals("English")){
                    setLocal(SigninActivity.this,"en");
                    finish();
                    startActivity(getIntent());

                } else if(selectedlang.equals("Hindi")){
                    setLocal(SigninActivity.this,"hi");
                    finish();
                    startActivity(getIntent());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void signin(View view) {
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(userpassword)) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return;

        }
        if (useremail.length() < 6) {
            Toast.makeText(this, "email is too short", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SigninActivity.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                        } else {
                            Toast.makeText(SigninActivity.this, "Unsuccessful Registration" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());

    }
    public void login(View view){
        startActivity(new Intent(SigninActivity.this,LoginActivity.class));

    }
}