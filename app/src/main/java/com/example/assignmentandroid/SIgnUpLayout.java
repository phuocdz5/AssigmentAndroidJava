package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.assignmentandroid.XFile.Read_Write;
import com.example.assignmentandroid.model.Account;

public class SIgnUpLayout extends AppCompatActivity {
    private EditText user,pass,cofirmPass;
    private LinearLayout linearLayout;
    private Button btnSignup, btnBackSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);
        initView();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_login_signup);
        linearLayout.startAnimation(animation);
        btnSignup.setOnClickListener(v ->{
            Intent intent = new Intent(SIgnUpLayout.this,LoginLayout.class);
            Account account = new Account(user.getText().toString(),pass.getText().toString());
            intent.putExtra("dataLogin",account);
            if(user.getText().toString().equals("")||pass.getText().toString().equals("")||cofirmPass.getText().toString().equals("")){
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();

            }else {
                if(pass.getText().toString().equals(cofirmPass.getText().toString())){
                    Read_Write read_write= new Read_Write(this);
                    read_write.writeUserData(this,"data.txt",account);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finishAffinity();
                }else {
                    Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBackSignup.setOnClickListener(v -> {
            Intent intent = new Intent(SIgnUpLayout.this,SplashScreen.class);
            startActivity(intent);
        });
    }


    private void initView() {
        btnSignup = findViewById(R.id.btnSignUp);
        btnBackSignup = findViewById(R.id.btnBackSignUp);
        linearLayout = findViewById(R.id.backgroundSign);
        user = findViewById(R.id.signupUser);
        pass = findViewById(R.id.signupPass);
        cofirmPass =findViewById(R.id.signupConfirmPass);
    }
}