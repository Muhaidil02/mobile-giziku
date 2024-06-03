package com.android.giziku.konten;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.activity.DetailArtikel;
import com.android.giziku.activity.DetailMpasi;
import com.android.giziku.adapter.ArtikelAdapter;
import com.android.giziku.adapter.MpasiAdapter;
import com.android.giziku.models.ArtikelModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Artikel_fragment extends Fragment {

    RecyclerView artikel;
    FirebaseFirestore db;
    ArtikelAdapter artikelAdapter;
    List<ArtikelModel> artikelModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_artikel_fragment, container, false);
        db = FirebaseFirestore.getInstance();

        artikel = root.findViewById(R.id.pop_artikel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        artikel.setLayoutManager(layoutManager);

        // Menambahkan DividerItemDecoration untuk mengatur jarak antar item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(artikel.getContext(), layoutManager.getOrientation());
        artikel.addItemDecoration(dividerItemDecoration);

        artikelModelList = new ArrayList<>();
        artikelAdapter = new ArtikelAdapter(getActivity(), artikelModelList);
        artikel.setAdapter(artikelAdapter);

        db.collection("Artikel").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ArtikelModel artikelModel = document.toObject(ArtikelModel.class);
                                artikelModelList.add(artikelModel);
                                artikelAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        artikelAdapter.setOnItemClickListener(new ArtikelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailArtikel.class);
                intent.putExtra("detaill", artikelModelList.get(position));
                startActivity(intent);
            }
        });

        return root;
    }
}