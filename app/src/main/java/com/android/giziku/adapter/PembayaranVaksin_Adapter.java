package com.android.giziku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.R;
import com.android.giziku.models.PembayaranVaksiin_Model;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class PembayaranVaksin_Adapter extends RecyclerView.Adapter<PembayaranVaksin_Adapter.ViewHolder> {
    Context context;
    List<PembayaranVaksiin_Model> list;
    private int selectedItem = RecyclerView.NO_POSITION;
    public void setSelectedPosition(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }


    public PembayaranVaksin_Adapter(Context context, List<PembayaranVaksiin_Model> list) {
        this.context = context;
        this.list = list;
    }

    private PembayaranVaksin_Adapter.OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(PembayaranVaksin_Adapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pembayaran_vaksin_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.logo_bank);
        holder.nama_bank.setText(list.get(position).getNama_bank());

        if (position == selectedItem){
            holder.itemView.setBackgroundColor(context.getColor(R.color.warnakedua));
            holder.logo_ceklis.setVisibility(View.VISIBLE);
        }else {
            holder.itemView.setBackgroundColor(context.getColor(android.R.color.transparent));
            holder.logo_ceklis.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!= null){
                    selectedItem = position;
                    notifyDataSetChanged();
                    listener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_bank;
        ImageView logo_bank, logo_ceklis;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_bank = itemView.findViewById(R.id.nama_bank);
            logo_bank = itemView.findViewById(R.id.logo_Bank);
            logo_ceklis = itemView.findViewById(R.id.logo_ceklis);

        }

    }


    }
