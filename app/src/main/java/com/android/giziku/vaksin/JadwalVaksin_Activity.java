package com.android.giziku.vaksin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.adapter.JadwalAdapter;
import com.android.giziku.models.JadwalModel;
import com.android.giziku.models.JenisVaksinModel;
import com.android.giziku.models.VaksinModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JadwalVaksin_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView hargaVaksin;
    JadwalAdapter jadwalAdapter;
    Button btnLanjut;
    JenisVaksinModel jenisVaksinModel = null;
    VaksinModel vaksinModel = null;
    JadwalModel jadwalModel = null;
    TextView btnKeluar;

    FirebaseFirestore db;
    List<JadwalModel> jadwalModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_vaksin);

        db = FirebaseFirestore.getInstance();

        btnKeluar = findViewById(R.id.textView1);
        btnLanjut = findViewById(R.id.btnLanjut);
        recyclerView = findViewById(R.id.pop_tanggal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        hargaVaksin = findViewById(R.id.hargaVaksin);

        jadwalModelList = new ArrayList<>();
        jadwalAdapter = new JadwalAdapter(this, jadwalModelList);
        recyclerView.setAdapter(jadwalAdapter);

        // Ambil vaksinModel dan jenisVaksinModel dari Intent

        if (jenisVaksinModel != null) {
            hargaVaksin.setText(jenisVaksinModel.getHarga());
        }

        db.collection("JadwalVaksin").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                JadwalModel jadwalModel = document.toObject(JadwalModel.class);
                                jadwalModelList.add(jadwalModel);
                            }
                            Collections.sort(jadwalModelList, new Comparator<JadwalModel>() {
                                @Override
                                public int compare(JadwalModel j1, JadwalModel j2) {
                                    try {
                                        int tanggal1 = Integer.parseInt(j1.getTanggal());
                                        int tanggal2 = Integer.parseInt(j2.getTanggal());
                                        return Integer.compare(tanggal1, tanggal2);
                                    } catch (NumberFormatException e) {
                                        return 0;
                                    }
                                }
                            });
                            jadwalAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(JadwalVaksin_Activity.this, "Error mengambil data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        jadwalAdapter.setOnItemClickListener(new JadwalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jadwalAdapter.setSelectedPosition(position);
                jadwalModel = jadwalModelList.get(position);
            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        vaksinModel = (VaksinModel) getIntent().getSerializableExtra("vaksinDetail");
        jenisVaksinModel = (JenisVaksinModel) getIntent().getSerializableExtra("jenisVaksinDetail");
        if (jenisVaksinModel != null) {
            hargaVaksin.setText(jenisVaksinModel.getHarga());
        }

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(JadwalVaksin_Activity.this, DetailVaksin.class);
                    intent.putExtra("vaksinDetail", vaksinModel);
                    intent.putExtra("jenisVaksinDetail", jenisVaksinModel);
                    intent.putExtra("jadwalVaksin", jadwalModel);
                    startActivity(intent);

            }
        });
    }
}
