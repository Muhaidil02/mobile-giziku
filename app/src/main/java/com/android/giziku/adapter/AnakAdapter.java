package com.android.giziku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.R;
import com.android.giziku.models.AnakModel;

import java.util.List;

public class AnakAdapter extends RecyclerView.Adapter<AnakAdapter.ViewHolder> {
    Context context;
    List<AnakModel>listAnak;

    public AnakAdapter(Context context, List<AnakModel> listAnak) {
        this.context = context;
        this.listAnak = listAnak;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anak_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnakModel anakModel = listAnak.get(position);
        holder.nama.setText(anakModel.getNama());
        holder.beratBadan.setText(anakModel.getBeratBadan());
        holder.tinggiBadan.setText(anakModel.getTinggiBadan());
        holder.lingkarKepala.setText(anakModel.getLingkarKepala());
    }

    @Override
    public int getItemCount() {
        return listAnak.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, beratBadan,tinggiBadan,lingkarKepala, tanggalLahir, namaSheet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namaAnak);
            namaSheet = itemView.findViewById(R.id.namaAnakSheetVaksin);
            tanggalLahir = itemView.findViewById(R.id.tanggalSheetVaksin);
            beratBadan = itemView.findViewById(R.id.beratAnak);
            tinggiBadan = itemView.findViewById(R.id.tinggiAnak);
            lingkarKepala = itemView.findViewById(R.id.lingkarAnak);

        }
    }
}
