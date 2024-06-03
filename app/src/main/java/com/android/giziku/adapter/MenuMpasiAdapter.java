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
import com.android.giziku.activity.DetailMenu_Mpasi;
import com.android.giziku.menuAwal.MenuMpasiModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class MenuMpasiAdapter extends RecyclerView.Adapter<MenuMpasiAdapter.ViewHolder> {

    private Context context;
    private List<MenuMpasiModel> menuMpasiModelList;

    private  MenuMpasiAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(MenuMpasiAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    public MenuMpasiAdapter(Context context, List<MenuMpasiModel> menuMpasiModelList) {
        this.context = context;
        this.menuMpasiModelList = menuMpasiModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(menuMpasiModelList.get(position).getImg_url()).into(holder.gambarItem);
        holder.judulItem.setText(menuMpasiModelList.get(position).getJudul());
        holder.keteranganItem.setText(menuMpasiModelList.get(position).getKeterangan());
        holder.tanggal.setText(menuMpasiModelList.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMenu_Mpasi.class);
                intent.putExtra("detail", menuMpasiModelList.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Menambahkan flag di sini
                context.startActivity(intent);
            }
        });
    };


    @Override
    public int getItemCount() {
        return menuMpasiModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarItem, imageItem;
        TextView keteranganItem, judulItem, tanggal, judul, kategori, waktumemasak,usia
                , porsi , funfact, bahan, caramembuat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gambarItem = itemView.findViewById(R.id.gambarItemMenu);
            imageItem = itemView.findViewById(R.id.gambarItemMenu);
            judulItem = itemView.findViewById(R.id.textItemMenu);
            keteranganItem = itemView.findViewById(R.id.keteranganItem);
            tanggal = itemView.findViewById(R.id.tanggalItemMenu);
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
