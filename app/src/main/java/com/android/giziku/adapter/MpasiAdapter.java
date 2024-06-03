package com.android.giziku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.R;

import com.android.giziku.activity.DetailMpasi;
import com.android.giziku.models.MpasiModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class MpasiAdapter extends RecyclerView.Adapter<MpasiAdapter.ViewHolder> {

    private Context context;
    private List<MpasiModel> mpasiModelList;

    private  MpasiAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(MpasiAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    public MpasiAdapter(Context context, List<MpasiModel> mpasiModelList) {
        this.context = context;
        this.mpasiModelList = mpasiModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mpasi_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(mpasiModelList.get(position).getImg_url()).into(holder.gambarMpasi1);
        holder.judulMpasi.setText(mpasiModelList.get(position).getJudul());
        holder.keteranganMpasi.setText(mpasiModelList.get(position).getKeterangan());
        holder.tanggal.setText(mpasiModelList.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMpasi.class);
                intent.putExtra("detail", mpasiModelList.get(position));
                context.startActivity(intent);
            }
        });

    };


    @Override
    public int getItemCount() {
        return mpasiModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarMpasi1, imageMpasi;
        TextView keteranganMpasi, judulMpasi, tanggal, judul, kategori, waktumemasak,usia
                , porsi , funfact, bahan, caramembuat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gambarMpasi1 = itemView.findViewById(R.id.gambarMpasi1);
            imageMpasi = itemView.findViewById(R.id.imageMpasi);
            judulMpasi = itemView.findViewById(R.id.textMpasi1);
            keteranganMpasi = itemView.findViewById(R.id.ketMpasi1);
            tanggal = itemView.findViewById(R.id.tanggal);
            judul = itemView.findViewById(R.id.judul);
            kategori = itemView.findViewById(R.id.Kategori);
            waktumemasak = itemView.findViewById(R.id.Waktumemasak);
            usia = itemView.findViewById(R.id.Usia);
            porsi = itemView.findViewById(R.id.Porsi);
            funfact = itemView.findViewById(R.id.Funfact);
            bahan = itemView.findViewById(R.id.Bahan);
            caramembuat = itemView.findViewById(R.id.Caramembuat);

        }
    }
}
