package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class ManageApp extends AppCompatActivity {
    private Button btnPhongBan,btnNhanVien,btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_app);
        initView();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.button_anim_1);
        btnPhongBan.startAnimation(animation);
        btnThoat.startAnimation(animation);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.butto_anim_2);
        btnNhanVien.startAnimation(animation2);
        btnPhongBan.setOnClickListener(v->{
            startActivity(new Intent(ManageApp.this, PhongBan.class));
        });
        btnNhanVien.setOnClickListener(v -> {
            startActivity(new Intent(ManageApp.this,NhanVien.class));
        });
        btnThoat.setOnClickListener(v -> {
            System.exit(0);

        });

    }



    private void initView() {
        btnPhongBan = findViewById(R.id.btnPhongBan);
        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnThoat = findViewById(R.id.btnThoat);
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