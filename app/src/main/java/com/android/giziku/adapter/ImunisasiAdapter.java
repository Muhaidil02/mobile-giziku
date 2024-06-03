package com.android.giziku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.R;
import com.android.giziku.models.ImunisasiModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ImunisasiAdapter extends RecyclerView.Adapter<ImunisasiAdapter.ViewHolder> {

    Context context;
    List<ImunisasiModel>imunisasiModelList;

    public ImunisasiAdapter(Context context, List<ImunisasiModel> imunisasiModelList) {
        this.context = context;
        this.imunisasiModelList = imunisasiModelList;
    }

    ImunisasiAdapter.OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(ImunisasiAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.imunisasi_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(imunisasiModelList.get(position).getImg_url()).into(holder.gambarStatus);
        holder.namaImunisasi.setText(imunisasiModelList.get(position).getNamaImunisasi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imunisasiModelList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaImunisasi;
        ImageView gambarStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaImunisasi = itemView.findViewById(R.id.nama_imunisasi);
            gambarStatus = itemView.findViewById(R.id.gambarStatus);
        }
    }
}
