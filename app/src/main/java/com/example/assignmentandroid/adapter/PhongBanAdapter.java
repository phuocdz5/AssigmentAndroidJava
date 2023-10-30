package com.example.assignmentandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentandroid.R;
import com.example.assignmentandroid.model.DAONhanVien;
import com.example.assignmentandroid.model.DAOPhongBan;

import java.util.ArrayList;
import java.util.List;

public class PhongBanAdapter extends RecyclerView.Adapter<PhongBanAdapter.PhongBangHolder> implements Filterable {
    private List<DAOPhongBan>list,listSearch;


    public PhongBanAdapter(List<DAOPhongBan> list) {
        this.list = list;
        listSearch= list;
    }
    public List<DAOPhongBan> getListSearch(){
        return listSearch;
    }

    @NonNull
    @Override
    public PhongBangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent, false);
        return new PhongBangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongBangHolder holder, int position) {
        DAOPhongBan phongBan =list.get(position);
        if(phongBan==null)
            return;
        holder.img.setImageResource(phongBan.getImg());
        holder.textView.setText(phongBan.getTxtName());
    }

    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if(s.isEmpty()) {
                    list = listSearch;
                } else {
                    ArrayList<DAOPhongBan> listPB = new ArrayList<>();
                    for(DAOPhongBan ls : listSearch) {
                        if(ls.getTxtName().toLowerCase().contains(s.toLowerCase())) {
                            listPB.add(ls);
                        }
                    }
                    list = listPB;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<DAOPhongBan>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class PhongBangHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView textView;
        public PhongBangHolder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.tvPhongBan);
        }
    }
}
