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
import com.example.assignmentandroid.model.Account;
import com.example.assignmentandroid.model.DAONhanVien;
import com.example.assignmentandroid.model.DAOSpinner;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    private Button btnUpdate, btnRemove,btnBack;
    private EditText edMaNV,edHovaTen;
    private Spinner spPhongBan;
    private List<DAOSpinner> list;
    private SpinnerAdapter adapter;
    private DAOSpinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        Intent intent = getIntent();
        DAONhanVien daoNhanVien = (DAONhanVien) intent.getSerializableExtra("dataNhanVien");
        edMaNV.setText(daoNhanVien.getMaNV());
        edHovaTen.setText(daoNhanVien.getTenNV());
        btnBack.setOnClickListener(v->{
            finish();
        });
        btnUpdate.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            DAONhanVien nhanVien = new DAONhanVien(edMaNV.getText().toString(),edHovaTen.getText().toString(),spinner.getTextPhongBan());
            intent1.putExtra("dataUpdate",nhanVien);
            setResult(RESULT_OK,intent1);
            if(!edMaNV.getText().toString().isEmpty()&& !edHovaTen.getText().toString().isEmpty()){
                finish();
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            }
        });
        btnRemove.setOnClickListener(v->{
            setResult(RESULT_OK,null);
            finish();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();

        });

    }

    private void initView() {
        edMaNV = findViewById(R.id.edMaNV);
        edHovaTen = findViewById(R.id.edHoVaTen);
        spPhongBan = findViewById(R.id.spPhongBanProfile);
        btnBack = findViewById(R.id.btnBackProfile);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRemove = findViewById(R.id.btnRemove);
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
}