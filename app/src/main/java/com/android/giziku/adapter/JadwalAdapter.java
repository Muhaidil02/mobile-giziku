package com.android.giziku.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.R;
import com.android.giziku.models.JadwalModel;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private Context context;
    private List<JadwalModel> jadwalModelList;
    private OnItemClickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    public JadwalAdapter(Context context, List<JadwalModel> jadwalModelList) {
        this.context = context;
        this.jadwalModelList = jadwalModelList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



    public int getSelectedPosition() {
        return selectedPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jadwal_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
        JadwalModel jadwalModel = jadwalModelList.get(position);
        holder.hari.setText(jadwalModel.getHari());
        holder.tanggal.setText(jadwalModel.getTanggal());
        holder.bulan.setText(jadwalModel.getBulan());

        if (position == selectedPosition) {
            holder.hari.setTextColor(ContextCompat.getColor(context, R.color.primary));
            holder.bulan.setTextColor(ContextCompat.getColor(context, R.color.primary));
            holder.tanggal.setTextColor(ContextCompat.getColor(context, R.color.primary));
            holder.imageView.setImageResource(R.drawable.background_btn_login);

        } else {
            holder.imageView.setImageResource(R.drawable.background_abu);
            holder.hari.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.bulan.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.tanggal.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    selectedPosition = position; // Simpan posisi jadwal yang dipilih
                    notifyDataSetChanged(); // Perbarui tampilan RecyclerView
                    listener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jadwalModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hari, tanggal, bulan;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.textHari);
            tanggal = itemView.findViewById(R.id.textTanggal);
            bulan = itemView.findViewById(R.id.textBulan);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
