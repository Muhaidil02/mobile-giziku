package com.android.giziku.vaksin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.Home_fragment;
import com.android.giziku.R;
import com.android.giziku.adapter.VaksinAdapter;
import com.android.giziku.models.VaksinModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Vaksin_Fragment extends Fragment {

    RecyclerView recyclerView;
    TextView tombol;
    FirebaseFirestore db;
    VaksinAdapter vaksinAdapter;
    List<VaksinModel>vaksinModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_vaksin_, container, false);


        db = FirebaseFirestore.getInstance();
        tombol = root.findViewById(R.id.textView1);

        recyclerView = root.findViewById(R.id.pop_vaksin);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        vaksinModelList = new ArrayList<>();
        vaksinAdapter = new VaksinAdapter(getActivity(), vaksinModelList) ;
        recyclerView.setAdapter(vaksinAdapter);

        db.collection("Vaksin").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                VaksinModel vaksinModel = document.toObject(VaksinModel.class);
                                vaksinModelList.add(vaksinModel);
                                vaksinAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        vaksinAdapter.setOnItemClickListener(new VaksinAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), JenisVaksin.class);
                intent.putExtra("detail", vaksinModelList.get(position));
                startActivity(intent);
            }
        });

        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.frameLayout, new Home_fragment());
                fr.commit();

            }
        });


        return root;
    }
}