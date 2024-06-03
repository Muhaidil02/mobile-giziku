package com.android.giziku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.konsultasi.ProfilDokter_Activity;
import com.android.giziku.R;
import com.android.giziku.models.KonsultasiModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class KonsultasiAdapter extends RecyclerView.Adapter<KonsultasiAdapter.ViewHolder> {

    Context context;
    List<KonsultasiModel>konsultasiModelList;
    KonsultasiAdapter.OnitemClicklistener listener;

    public void setOnItemClickListener(OnitemClicklistener listener) {
        this.listener = listener;
    }

    public KonsultasiAdapter(Context context, List<KonsultasiModel> konsultasiModelList) {
        this.context = context;
        this.konsultasiModelList = konsultasiModelList;
    }
    public interface OnitemClicklistener {
        void onItemClick(View view, int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.konsultasi_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(konsultasiModelList.get(position).getImg_url()).into(holder.imageKonsultasi);
        holder.namaKonsultasi.setText(konsultasiModelList.get(position).getNama());
        holder.pengalamanKonsultasi.setText(konsultasiModelList.get(position).getPengalaman());
        holder.jarakKonsultasi.setText(konsultasiModelList.get(position).getJarak());
        holder.hargaKonsultasi.setText(konsultasiModelList.get(position).getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfilDokter_Activity.class);
                intent.putExtra("detail", konsultasiModelList.get(position));
                if (!(context instanceof AppCompatActivity) || listener != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        listener.onItemClick(v, position);// Tambahkan flag ini hanya jika context bukan instance dari Activity
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return konsultasiModelList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageKonsultasi;
        TextView namaKonsultasi, pengalamanKonsultasi, jarakKonsultasi, hargaKonsultasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageKonsultasi = itemView.findViewById(R.id.imageKonsultasi);
            namaKonsultasi = itemView.findViewById(R.id.namaKonsultasi);
            pengalamanKonsultasi = itemView.findViewById(R.id.pengalamanKonsultasi);
            jarakKonsultasi = itemView.findViewById(R.id.jarakKonsultasi);
            hargaKonsultasi = itemView.findViewById(R.id.hargaKonsultasi);
        }
    }
}
