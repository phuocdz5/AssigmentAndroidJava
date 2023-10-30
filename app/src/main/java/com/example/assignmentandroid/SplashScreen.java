package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    private Button btnLogin,btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        initView();
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SplashScreen.this,LoginLayout.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finishAffinity();
        });
        btnSignup.setOnClickListener(v ->{
            Intent intent = new Intent(SplashScreen.this,SIgnUpLayout.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finishAffinity();
        });


    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }

    private void initView() {
        btnLogin = findViewById(R.id.btnLoginSplash);
        btnSignup = findViewById(R.id.btnSignUpSplash);

    }
    boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }

        this.doubleBack = true;
        Toast.makeText(this, "Bấm thêm 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBack=false;
            }
        }, 2000);
    }

}