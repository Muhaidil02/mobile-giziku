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
import android.widget.Toolbar;

import com.android.giziku.R;
import com.android.giziku.activity.DetailAsupanIbu;
import com.android.giziku.adapter.Asupanibu_Adapter;
import com.android.giziku.models.AsupanibuModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Asupanibu_fragment extends Fragment {

    RecyclerView asupanibu;
    FirebaseFirestore db;
    Asupanibu_Adapter asupanibuAdapter;
    List<AsupanibuModel> asupanibuModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_asupanibu_fragment, container, false);



        db = FirebaseFirestore.getInstance();

        asupanibu = root.findViewById(R.id.pop_asupan);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        asupanibu.setLayoutManager(layoutManager);

        // Menambahkan DividerItemDecoration untuk mengatur jarak antar item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(asupanibu.getContext(), layoutManager.getOrientation());
        asupanibu.addItemDecoration(dividerItemDecoration);

        asupanibuModelList = new ArrayList<>();
        asupanibuAdapter = new Asupanibu_Adapter(getActivity(), asupanibuModelList);
        asupanibu.setAdapter(asupanibuAdapter);

        db.collection("AsupanIbu").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AsupanibuModel asupanibuModel = document.toObject(AsupanibuModel.class);
                                asupanibuModelList.add(asupanibuModel);
                                asupanibuAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        asupanibuAdapter.setOnItemClickListener(new Asupanibu_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Membuat Intent untuk DetailAsupanIbu
                Intent intent = new Intent(getActivity(), DetailAsupanIbu.class);
                // Melewatkan objek AsupanibuModel yang dipilih sebagai extra
                intent.putExtra("detail", asupanibuModelList.get(position));
                // Memulai DetailAsupanIbu dengan Intent
                startActivity(intent);
            }
        });
        return root;
    }
}
