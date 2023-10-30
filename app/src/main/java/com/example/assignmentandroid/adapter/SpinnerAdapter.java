package com.example.assignmentandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.model.DAOPhongBan;
import com.example.assignmentandroid.model.DAOSpinner;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<DAOSpinner> list;

    public SpinnerAdapter(Context context, List<DAOSpinner> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_spinner,parent,false);
        DAOSpinner daoSpinner = list.get(position);
        TextView textSpinner = view.findViewById(R.id.textPhongBan);
        textSpinner.setText(daoSpinner.getTextPhongBan());
        return view;
    }
}
