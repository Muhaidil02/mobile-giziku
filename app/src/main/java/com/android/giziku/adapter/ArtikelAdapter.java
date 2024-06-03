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
import com.android.giziku.activity.DetailArtikel;
import com.android.giziku.activity.DetailMpasi;
import com.android.giziku.models.ArtikelModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ViewHolder> {

    private Context context;
    private List<ArtikelModel>artikelModelList;

    private  ArtikelAdapter.OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(ArtikelAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public ArtikelAdapter(Context context, List<ArtikelModel> artikelModelList) {
        this.context = context;
        this.artikelModelList = artikelModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artikel_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(artikelModelList.get(position).getImg_url()).into(holder.gambarArtikel1);
        holder.judulArtikel.setText(artikelModelList.get(position).getJudul());
        holder.keteranganArtikel.setText(artikelModelList.get(position).getKeterangan());
        holder.tanggal.setText(artikelModelList.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailArtikel.class);
                intent.putExtra("detaill", artikelModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artikelModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarArtikel1, imageArtikel;
        TextView keteranganArtikel, judulArtikel,tanggal, isi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gambarArtikel1 = itemView.findViewById(R.id.gambarArtikel1);
            judulArtikel = itemView.findViewById(R.id.textArtikel1);
            keteranganArtikel = itemView.findViewById(R.id.ketArtikel1);
            tanggal = itemView.findViewById(R.id.tanggal);
            isi = itemView.findViewById(R.id.isi);
            imageArtikel = itemView.findViewById(R.id.imageArtikel);
        }
    }
}
