package com.android.giziku.konten;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.adapter.VideoAdapter;
import com.android.giziku.models.ArtikelModel;
import com.android.giziku.models.VideoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Video_fragment extends Fragment {

    RecyclerView videoRecyclerView;
    FirebaseFirestore db;
    VideoAdapter videoAdapter;
    List<VideoModel> videoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_video_fragment, container, false);

        db = FirebaseFirestore.getInstance();

        videoRecyclerView = root.findViewById(R.id.pop_video);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        videoRecyclerView.setLayoutManager(layoutManager);

        // Menambahkan DividerItemDecoration untuk mengatur jarak antar item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(videoRecyclerView.getContext(), layoutManager.getOrientation());
        videoRecyclerView.addItemDecoration(dividerItemDecoration);

        videoList = new ArrayList<>();
        videoAdapter = new VideoAdapter(getActivity(), videoList);
        videoRecyclerView.setAdapter(videoAdapter);

        db.collection("Video").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                // Membuat objek VideoModel
                                VideoModel videoModel = document.toObject(VideoModel.class);

                                // Menambahkan videoModel ke daftar video
                                videoList.add(videoModel);
                                videoAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Menangani klik item di RecyclerView
        videoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                VideoModel video = videoList.get(position);
                // Buka video di aplikasi YouTube atau browser
                watchYoutubeVideo(video.getVideoUrl());
            }
        });

        return root;
    }

    // Method untuk membuka video di aplikasi YouTube atau browser
    private void watchYoutubeVideo(String videoUrl) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        try {
            startActivity(appIntent);
        } catch (Exception ex) {
            // Jika tidak ada aplikasi YouTube di perangkat, buka video di browser
            startActivity(webIntent);
        }
    }
}