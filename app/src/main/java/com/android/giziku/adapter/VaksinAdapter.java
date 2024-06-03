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
import com.android.giziku.vaksin.JenisVaksin;
import com.android.giziku.models.VaksinModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class VaksinAdapter extends RecyclerView.Adapter<VaksinAdapter.ViewHolder> {

    Context context;
    List<VaksinModel>vaksinModelList;
    private  VaksinAdapter.OnItemClickListener listener;

    public VaksinAdapter(Context context, List<VaksinModel> vaksinModelList) {
        this.context = context;
        this.vaksinModelList = vaksinModelList;
    }

    public void setOnItemClickListener(VaksinAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vaksin_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(vaksinModelList.get(position).getimg_url()).into(holder.gambar_vaksin);
        holder.judul.setText(vaksinModelList.get(position).getTempatVaksin());
        holder.lokasi.setText(vaksinModelList.get(position).getLokasiVaksin());
        holder.jarak.setText(vaksinModelList.get(position).getJarakLokasi());
        holder.rating.setText(vaksinModelList.get(position).getRatingVaksin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JenisVaksin.class);
                intent.putExtra("detail", vaksinModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vaksinModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul,lokasi,jarak,rating;
        ImageView gambar_vaksin, imageDetailvaksin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.textTempatVaksin);
            lokasi = itemView.findViewById(R.id.textLokasiVaksin);
            jarak = itemView.findViewById(R.id.textJarakLokasi);
            rating = itemView.findViewById(R.id.textRatingVaksin);
            gambar_vaksin = itemView.findViewById(R.id.imageitemVaksin);
            imageDetailvaksin = itemView.findViewById(R.id.imageDetailVaksin);
        }
    }
}
