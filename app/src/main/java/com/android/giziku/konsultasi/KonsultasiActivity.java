package com.android.giziku.konsultasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.adapter.KonsultasiAdapter;
import com.android.giziku.models.KonsultasiModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class KonsultasiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    KonsultasiAdapter konsultasiAdapter;
    List<KonsultasiModel>konsultasiModelList;
    FirebaseFirestore db;
    TextView tombolKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        tombolKeluar = findViewById(R.id.textView1);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.pop_konsultasi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        konsultasiModelList = new ArrayList<>();
        konsultasiAdapter = new KonsultasiAdapter(getApplicationContext(), konsultasiModelList);
        recyclerView.setAdapter(konsultasiAdapter);

        db.collection("Konsultasi").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                KonsultasiModel konsultasiModel = document.toObject(KonsultasiModel.class);
                                konsultasiModelList.add(konsultasiModel);
                                konsultasiAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(KonsultasiActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        konsultasiAdapter.setOnItemClickListener(new KonsultasiAdapter.OnitemClicklistener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(KonsultasiActivity.this, ProfilDokter_Activity.class);
                i.putExtra("detail", konsultasiModelList.get(position));
                startActivity(i);
            }
        });
    }
}