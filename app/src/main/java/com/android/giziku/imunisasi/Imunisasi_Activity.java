package com.android.giziku.imunisasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.adapter.ImunisasiAdapter;
import com.android.giziku.models.ImunisasiModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Imunisasi_Activity extends AppCompatActivity {


        RecyclerView recyclerView1;
        RecyclerView recyclerView2;
        FirebaseFirestore db;
        ImunisasiAdapter imunisasiAdapter1;
        ImunisasiAdapter imunisasiAdapter2;
        List<ImunisasiModel> imunisasiModelList1;
        List<ImunisasiModel> imunisasiModelList2;
        TextView btnKeluar, lihatSemuanya;
        ImageView btnRefresh;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_imunisasi);

            lihatSemuanya = findViewById(R.id.lihatSemuaImunisasi);
            lihatSemuanya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), JadwalImunisasi_Activity.class);
                    startActivity(intent);
                }
            });

            btnKeluar = findViewById(R.id.textView1);
            btnKeluar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            btnRefresh = findViewById(R.id.btnRefresh);
            btnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshData();
                }
            });

            db = FirebaseFirestore.getInstance();
            recyclerView1 = findViewById(R.id.pop_imunisasi1);
            recyclerView2 = findViewById(R.id.pop_imunisasi2);

            LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            recyclerView1.setLayoutManager(layoutManager1);
            recyclerView2.setLayoutManager(layoutManager2);

            DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(recyclerView1.getContext(), layoutManager1.getOrientation());
            recyclerView1.addItemDecoration(dividerItemDecoration1);

            DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(recyclerView2.getContext(), layoutManager2.getOrientation());
            recyclerView2.addItemDecoration(dividerItemDecoration1);

            imunisasiModelList1 = new ArrayList<>();
            imunisasiModelList2 = new ArrayList<>();

            imunisasiAdapter1 = new ImunisasiAdapter(this, imunisasiModelList1);
            imunisasiAdapter2 = new ImunisasiAdapter(this, imunisasiModelList2);

            recyclerView1.setAdapter(imunisasiAdapter1);
            recyclerView2.setAdapter(imunisasiAdapter2);

            fetchData();
        }

        private void fetchData() {
            db.collection("Imunisasi1").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                imunisasiModelList1.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ImunisasiModel imunisasiModel = document.toObject(ImunisasiModel.class);
                                    imunisasiModelList1.add(imunisasiModel);
                                }
                                imunisasiAdapter1.notifyDataSetChanged();
                            } else {
                                Toast.makeText(Imunisasi_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            db.collection("Imunisasi2").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                imunisasiModelList2.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ImunisasiModel imunisasiModel = document.toObject(ImunisasiModel.class);
                                    imunisasiModelList2.add(imunisasiModel);
                                }
                                imunisasiAdapter2.notifyDataSetChanged();
                            } else {
                                Toast.makeText(Imunisasi_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            imunisasiAdapter1.setOnItemClickListener(new ImunisasiAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getApplicationContext(), DetailImunisasi_Activity.class);
                    intent.putExtra("detail", imunisasiModelList1.get(position));
                    startActivity(intent);
                }
            });
            imunisasiAdapter2.setOnItemClickListener(new ImunisasiAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getApplicationContext(), DetailImunisasi_Activity.class);
                    intent.putExtra("detail", imunisasiModelList2.get(position));
                    startActivity(intent);
                }
            });
        }

        private void refreshData() {
            fetchData();
        }
    }
