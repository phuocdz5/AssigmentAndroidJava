package com.example.assignmentandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignmentandroid.adapter.NhanVienApdater;
import com.example.assignmentandroid.model.DAONhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVien extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    private NhanVienApdater apdater;
    private DAONhanVien nhanVien,daoNhanVien;
    private List<DAONhanVien>list;
    private int position1;
    private final int REQUEST_CODE=1,REQUEST_CODE_2=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        initView();
        initData();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(listView);
                return false;
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        String[] maNV ={"NV001","NV002","NV003","NV004","NV005","NV006","NV007","NV008","NV009","NV010"};
        String[] name ={"Cù Duy Đức","Nguyễn Bá Nghị","Nguyễn Khắc Trung","Nguyễn Phương Nam","Lê Xuân Thảo","Nguyễn Tiến Phước","Nguyễn Văn A","Nguyễn Văn B","Nguyễn Văn C","Nguyễn Văn D"};
        String[] phongBan={"Đào Tạo","Đào Tạo", "Hành chính","Hành Chính","Hành Chính","Nhân Sự","Hành chính","Hành Chính","Hành Chính","Nhân Sự"};
        list = new ArrayList<>();
        for (int i=0;i<maNV.length;i++){
            list.add(new DAONhanVien(maNV[i],name[i],phongBan[i]));
        }
        apdater = new NhanVienApdater(this,list);
        listView.setAdapter(apdater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position1= position;
                List<DAONhanVien> nhanViens = new ArrayList<>();
                for (DAONhanVien daoNhanVien:apdater.getListBackup()) {
                    nhanViens.add(daoNhanVien);
                }
                Intent intent = new Intent(NhanVien.this, Profile.class);
                DAONhanVien daoNhanVien = new DAONhanVien(nhanViens.get(position).getMaNV(),nhanViens.get(position).getTenNV(),nhanViens.get(position).getPhongBan());
                intent.putExtra("dataNhanVien", daoNhanVien);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolBarNV);
        listView = findViewById(R.id.lView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_xoa,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete){
            apdater.remove(position1);
            Toast.makeText(NhanVien.this, "Xóa thành công", Toast.LENGTH_SHORT).show();

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tools_bar,menu);
        MenuItem menuItem =menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                apdater.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                apdater.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent = new Intent(NhanVien.this,AddStaff.class);
            startActivityForResult(intent,REQUEST_CODE_2);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK){
            if(data!=null){
                nhanVien = (DAONhanVien) data.getSerializableExtra("dataUpdate");
                apdater.update(position1,nhanVien);
            }else {
                apdater.remove(position1);
            }
        }else {
            nhanVien=null;
        }
        if(requestCode==REQUEST_CODE_2&&resultCode==RESULT_OK){
            if(data!=null) {
                daoNhanVien = (DAONhanVien) data.getSerializableExtra("dataAdd");
                apdater.add(daoNhanVien);
            }
        }


    }

}