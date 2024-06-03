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
import com.android.giziku.activity.DetailAsupanIbu;
import com.android.giziku.models.AsupanibuModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class Asupanibu_Adapter extends RecyclerView.Adapter<Asupanibu_Adapter.ViewHolder> {

    private Context context;
    private List<AsupanibuModel> asupanibuModelList;
    private Asupanibu_Adapter.OnItemClickListener listener; // Inner interface untuk menangani klik item


    public void setOnItemClickListener(Asupanibu_Adapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public Asupanibu_Adapter(Context context, List<AsupanibuModel> asupanibuModelList) {
        this.context = context;
        this.asupanibuModelList = asupanibuModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.asupan_ibu_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(asupanibuModelList.get(position).getImg_url()).into(holder.gambarAsupan1);
        holder.judulAsupan.setText(asupanibuModelList.get(position).getJudul());
        holder.keteranganAsupan.setText(asupanibuModelList.get(position).getKeterangan());
        holder.tanggal.setText(asupanibuModelList.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailAsupanIbu.class);
                intent.putExtra("detail", asupanibuModelList.get(position));
                context.startActivity(intent);
                    }
                });

        };


    @Override
    public int getItemCount() {
        return asupanibuModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarAsupan1, imageAsupan;
        TextView keteranganAsupan, judulAsupan, tanggal, judul, kategori, waktumemasak,usia
                , porsi , funfact, bahan, caramembuat;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            gambarAsupan1 = itemView.findViewById(R.id.gambar_asupan1);
            judulAsupan = itemView.findViewById(R.id.textAsupan1);
            imageAsupan = itemView.findViewById(R.id.imageAsupan);

            keteranganAsupan = itemView.findViewById(R.id.ketAsupan1);
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
