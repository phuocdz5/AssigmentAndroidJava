package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignmentandroid.adapter.SpinnerAdapter;
import com.example.assignmentandroid.model.DAONhanVien;
import com.example.assignmentandroid.model.DAOSpinner;

import java.util.ArrayList;
import java.util.List;

public class AddStaff extends AppCompatActivity {
    private Button btnAdd,btnBack;dddddddddddddddddd
    private EditText edMaNV,edHovaTen;
    private Spinner spPhongBan;
    private SpinnerAdapter adapter;
    private List<DAOSpinner> list;
    private DAOSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        initView();
        initData();
        spPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner = list.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnBack.setOnClickListener(v->{
            finish();
        });
        btnAdd.setOnClickListener(v->{
            Intent intent = new Intent();
            DAONhanVien nhanVien = new DAONhanVien(edMaNV.getText().toString(),edHovaTen.getText().toString(),spinner.getTextPhongBan());
            intent.putExtra("dataAdd",nhanVien);
            setResult(RESULT_OK,intent);
            if(!edMaNV.getText().toString().isEmpty()&& !edHovaTen.getText().toString().isEmpty()){
                finish();
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        String[] textPhong={"Hành Chính","Đào Tạo","Nhân Sự"};
        list = new ArrayList<>();
        for (int i = 0; i < textPhong.length; i++) {
            list.add(new DAOSpinner(textPhong[i]));
        }
        adapter = new SpinnerAdapter(this,list);
        spPhongBan.setAdapter(adapter);
    }

    private void initView() {
        edMaNV = findViewById(R.id.edMaNV);
        edHovaTen = findViewById(R.id.edHoVaTen);
        spPhongBan = findViewById(R.id.spPhongBan);
        btnBack = findViewById(R.id.btnBackProfile);
        btnAdd = findViewById(R.id.btnAdd);
    }
}