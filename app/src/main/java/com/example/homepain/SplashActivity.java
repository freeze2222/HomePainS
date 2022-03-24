package com.example.homepain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homepain.auth.CreateUserActivity;
import com.example.homepain.services.AuthService;
import com.example.homepain.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AuthService.isSignIn()) {
            startActivity(new Intent(this, MainActivity.class));
        } else  {
            startActivity(new Intent(this, CreateUserActivity.class));
        }
        finish();
    }
}