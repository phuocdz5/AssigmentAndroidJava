package com.example.assignmentandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.model.DAONhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienApdater extends BaseAdapter implements Filterable {
    private Context context;
    private List<DAONhanVien> nhanViens,listBackup;

    public NhanVienApdater(Context context, List<DAONhanVien> nhanViens) {
        this.context = context;
        this.nhanViens = nhanViens;
        this.listBackup = nhanViens;
    }
    public List<DAONhanVien>getListBackup(){
        return listBackup;
    }

    @Override
    public int getCount() {
        return nhanViens!=null?nhanViens.size():0;
    }

    @Override
    public Object getItem(int position) {
        return nhanViens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,null,false);
        TextView maNV = view.findViewById(R.id.maNV);
        TextView name = view.findViewById(R.id.tenNV);
        TextView phongBan = view.findViewById(R.id.chucVu);
        DAONhanVien ls = nhanViens.get(position);
        maNV.setText("Mã NV: "+ls.getMaNV());
        name.setText("Tên: "+ls.getTenNV());
        phongBan.setText("Phòng ban: "+ls.getPhongBan());
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_listview);
        view.startAnimation(animation);
        return view;
    }
    public void add(DAONhanVien nhanVien){
        nhanViens.add(nhanVien);
        notifyDataSetChanged();
    }
    public void update(int position, DAONhanVien nhanVien){
        nhanViens.set(position,nhanVien);
        notifyDataSetChanged();
    }
    public void remove(int position){
        nhanViens.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if(s.isEmpty()) {
                    nhanViens = listBackup;
                } else {
                    ArrayList<DAONhanVien> listNV = new ArrayList<>();
                    for(DAONhanVien ls : listBackup) {
                        if(ls.getTenNV().toLowerCase().contains(s.toLowerCase())) {
                            listNV.add(ls);
                        }
                    }
                    nhanViens = listNV;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = nhanViens;
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                nhanViens = (ArrayList<DAONhanVien>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
