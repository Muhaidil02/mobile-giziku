package com.android.giziku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.R;
import com.android.giziku.models.JenisVaksinModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class JenisVaksinadapter extends RecyclerView.Adapter<JenisVaksinadapter.ViewHolder> {

     BottomSheetDialog bottomSheetDialog;
    Context context;
    List<JenisVaksinModel> jenisVaksinModelList;
    private  JenisVaksinadapter.OnItemClickListener listener;

    public JenisVaksinadapter(Context context, List<JenisVaksinModel> jenisVaksinModelList) {
        this.context = context;
        this.jenisVaksinModelList = jenisVaksinModelList;
    }

     public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(JenisVaksinadapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jenis_vaksin_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama.setText(jenisVaksinModelList.get(position).getNama());
        holder.harga.setText(jenisVaksinModelList.get(position).getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onItemClick(v, position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return jenisVaksinModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,harga, deskripsi;
        Button btnPesanVaksin;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namaVaksin);
            harga = itemView.findViewById(R.id.hargaVaksin);
            btnPesanVaksin = itemView.findViewById(R.id.btnPesanVaksin);



        }
    }
}
