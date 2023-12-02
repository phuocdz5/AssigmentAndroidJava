package com.example.assignmentandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.assignmentandroid.XFile.Read_Write;
import com.example.assignmentandroid.model.Account;

import java.util.ArrayList;
import java.util.List;

public class LoginLayout extends AppCompatActivity {
    private EditText user, pass;
    private LinearLayout linearLayout;
    private Button btnLogin, btnBackLogin;
    private Account account,userAc;
    private CheckBox rbCheck;
    private SharedPreferences sharedPreferences;

    String test = "test";
    String test1="dsadasdsa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
        sharedPreferences = getSharedPreferences("login.txt",MODE_PRIVATE);
        user.setText(sharedPreferences.getString("user",""));
        pass.setText(sharedPreferences.getString("pass",""));
        rbCheck.setChecked(sharedPreferences.getBoolean("checkBox",false));
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_login_signup);
        linearLayout.startAnimation(animation);
        btnLogin.setOnClickListener(v ->{
            check();
        });
        btnBackLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginLayout.this,SplashScreen.class);
            startActivity(intent);

        });

    }
//
//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }

    private void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        btnBackLogin = findViewById(R.id.btnBackLogin);
        linearLayout = findViewById(R.id.backgroundLogin);
        user = findViewById(R.id.loginUser);
        pass = findViewById(R.id.loginPass);
        rbCheck = findViewById(R.id.checkBoxLogin);
    }
    public void check(){
        Intent intent = getIntent();
        if(intent!=null){
            List<Account> lsAcount = new ArrayList<>();
            Read_Write read_write = new Read_Write(this);
            lsAcount=read_write.readUserData(this,"data.txt");
            boolean userCheck =lsAcount.get(0).getUser().equals(user.getText().toString());
            boolean passCheck=lsAcount.get(0).getPass().equals(pass.getText().toString());
            if(userCheck&&passCheck&&lsAcount.get(0).getUser()!=null&&lsAcount.get(0).getPass()!=null){
                Intent intent1  = new Intent(LoginLayout.this,ManageApp.class);
                startActivity(intent1);
                if(rbCheck.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user",lsAcount.get(0).getUser());
                    editor.putString("pass",lsAcount.get(0).getPass());
                    editor.putBoolean("checkBox",true);
                    editor.apply();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("user");
                    editor.remove("pass");
                    editor.remove("checkBox");
                    editor.apply();

                }
            }else {
                Toast.makeText(this, "Vui lòng nhập đúng tài khoản mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }
    }



}