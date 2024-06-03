package com.android.giziku.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.R;
import com.android.giziku.models.VideoModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    List<VideoModel>videoModelList;
    private OnItemClickListener listener; // Inner interface untuk menangani klik item


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public VideoAdapter(Context context, List<VideoModel> videoModelList) {
        this.context = context;
        this.videoModelList = videoModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoModel video = videoModelList.get(position);
        holder.textVideo.setText(videoModelList.get(position).getJudul());
        Glide.with(context).load(videoModelList.get(position).getThumbnail()).into(holder.thumbnaiilImageView);

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
        return videoModelList.size();
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
