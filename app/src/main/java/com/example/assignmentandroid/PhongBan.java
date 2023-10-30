package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.assignmentandroid.adapter.PhongBanAdapter;
import com.example.assignmentandroid.model.DAOPhongBan;

import java.util.ArrayList;
import java.util.List;


public class PhongBan extends AppCompatActivity {
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private PhongBanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        toolbar = findViewById(R.id.toolsBar);
        recyclerView = findViewById(R.id.rView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new PhongBanAdapter(getList());
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }


    public List<DAOPhongBan> getList() {
        List<DAOPhongBan> list = new ArrayList<>();
        list.add(new DAOPhongBan(R.drawable.baseline_home_work_24_white, "Nhân sự"));
        list.add(new DAOPhongBan(R.drawable.baseline_home_work_24_white, "Hành chính"));
        list.add(new DAOPhongBan(R.drawable.baseline_home_work_24_white, "Đào tạo"));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tools_bar_phongban, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}