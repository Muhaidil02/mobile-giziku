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
import com.android.giziku.menuAwal.MenuVideoModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class MenuVideoAdapter extends RecyclerView.Adapter<MenuVideoAdapter.ViewHolder> {

    Context context;
    List<MenuVideoModel> menuVideoModelList;
    private OnItemClickListener listener; // Inner interface untuk menangani klik item


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public MenuVideoAdapter(Context context, List<MenuVideoModel> menuVideoModelList) {
        this.context = context;
        this.menuVideoModelList = menuVideoModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_video_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuVideoModel video = menuVideoModelList.get(position);
        holder.textVideo.setText(menuVideoModelList.get(position).getJudul());
        Glide.with(context).load(menuVideoModelList.get(position).getThumbnail()).into(holder.thumbnaiilImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuVideoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textVideo;
        ImageView thumbnaiilImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textVideo = itemView.findViewById(R.id.textVideo);
            thumbnaiilImageView = itemView.findViewById(R.id.thumbnailImageView);
        }
    }
}
