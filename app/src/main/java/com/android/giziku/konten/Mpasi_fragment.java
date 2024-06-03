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
import com.android.giziku.activity.DetailAsupanIbu;
import com.android.giziku.activity.DetailMpasi;
import com.android.giziku.adapter.Asupanibu_Adapter;
import com.android.giziku.adapter.MpasiAdapter;

import com.android.giziku.models.MpasiModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Mpasi_fragment extends Fragment {
    RecyclerView mpasi;
    FirebaseFirestore db;
    MpasiAdapter mpasiAdapter ;
    List<MpasiModel> mpasiModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mpasi_fragment, container, false);
        db = FirebaseFirestore.getInstance();

        mpasi = root.findViewById(R.id.pop_mpasi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mpasi.setLayoutManager(layoutManager);

        // Menambahkan DividerItemDecoration untuk mengatur jarak antar item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mpasi.getContext(), layoutManager.getOrientation());
        mpasi.addItemDecoration(dividerItemDecoration);

        mpasiModelList = new ArrayList<>();
        mpasiAdapter = new MpasiAdapter(getActivity(), mpasiModelList);
        mpasi.setAdapter(mpasiAdapter);

        db.collection("Mpasi").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MpasiModel mpasiModel = document.toObject(MpasiModel.class);
                                mpasiModelList.add(mpasiModel);
                                mpasiAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        mpasiAdapter.setOnItemClickListener(new MpasiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailMpasi.class);
                intent.putExtra("detail", mpasiModelList.get(position));
                startActivity(intent);
            }
        });

        return root;
    }
}
