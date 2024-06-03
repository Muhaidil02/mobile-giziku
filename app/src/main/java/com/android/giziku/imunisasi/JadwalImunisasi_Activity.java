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

public class JadwalImunisasi_Activity extends AppCompatActivity {

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    FirebaseFirestore db;
    ImunisasiAdapter imunisasiAdapter1;
    ImunisasiAdapter imunisasiAdapter2;
    ImunisasiAdapter imunisasiAdapter3;
    ImunisasiAdapter imunisasiAdapter4;
    List<ImunisasiModel> imunisasiModelList1;
    List<ImunisasiModel> imunisasiModelList2;
    List<ImunisasiModel> imunisasiModelList3;
    List<ImunisasiModel> imunisasiModelList4;
    TextView btnKeluar;
    ImageView btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_imunisasi);

        btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        btnKeluar = findViewById(R.id.textView1);
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        db = FirebaseFirestore.getInstance();
        recyclerView1 = findViewById(R.id.pop_imunisasi1);
        recyclerView2 = findViewById(R.id.pop_imunisasi2);
        recyclerView3 = findViewById(R.id.pop_imunisasi3);
        recyclerView4 = findViewById(R.id.pop_imunisasi4);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView4.setLayoutManager(layoutManager4);

        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(recyclerView1.getContext(), layoutManager1.getOrientation());
        recyclerView1.addItemDecoration(dividerItemDecoration1);
        recyclerView2.addItemDecoration(dividerItemDecoration1);
        recyclerView3.addItemDecoration(dividerItemDecoration1);
        recyclerView4.addItemDecoration(dividerItemDecoration1);

        imunisasiModelList1 = new ArrayList<>();
        imunisasiModelList2 = new ArrayList<>();
        imunisasiModelList3 = new ArrayList<>();
        imunisasiModelList4 = new ArrayList<>();

        imunisasiAdapter1 = new ImunisasiAdapter(this, imunisasiModelList1);
        imunisasiAdapter2 = new ImunisasiAdapter(this, imunisasiModelList2);
        imunisasiAdapter3 = new ImunisasiAdapter(this, imunisasiModelList3);
        imunisasiAdapter4 = new ImunisasiAdapter(this, imunisasiModelList4);

        recyclerView1.setAdapter(imunisasiAdapter1);
        recyclerView2.setAdapter(imunisasiAdapter2);
        recyclerView3.setAdapter(imunisasiAdapter3);
        recyclerView4.setAdapter(imunisasiAdapter4);

        fetchData();
    }

    private void fetchData() {
        db.collection("Imunisasi1").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            imunisasiModelList1.clear();  // Clear the list before adding new data
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ImunisasiModel imunisasiModel = document.toObject(ImunisasiModel.class);
                                imunisasiModelList1.add(imunisasiModel);
                            }
                            imunisasiAdapter1.notifyDataSetChanged();
                        } else {
                            Toast.makeText(JadwalImunisasi_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        db.collection("Imunisasi2").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            imunisasiModelList2.clear();  // Clear the list before adding new data
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ImunisasiModel imunisasiModel = document.toObject(ImunisasiModel.class);
                                imunisasiModelList2.add(imunisasiModel);
                            }
                            imunisasiAdapter2.notifyDataSetChanged();
                        } else {
                            Toast.makeText(JadwalImunisasi_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        db.collection("Imunisasi3").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            imunisasiModelList3.clear();  // Clear the list before adding new data
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ImunisasiModel imunisasiModel = document.toObject(ImunisasiModel.class);
                                imunisasiModelList3.add(imunisasiModel);
                            }
                            imunisasiAdapter3.notifyDataSetChanged();
                        } else {
                            Toast.makeText(JadwalImunisasi_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        db.collection("Imunisasi4").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            imunisasiModelList4.clear();  // Clear the list before adding new data
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ImunisasiModel imunisasiModel = document.toObject(ImunisasiModel.class);
                                imunisasiModelList4.add(imunisasiModel);
                            }
                            imunisasiAdapter4.notifyDataSetChanged();
                        } else {
                            Toast.makeText(JadwalImunisasi_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
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
        imunisasiAdapter3.setOnItemClickListener(new ImunisasiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailImunisasi_Activity.class);
                intent.putExtra("detail", imunisasiModelList3.get(position));
                startActivity(intent);
            }
        });
        imunisasiAdapter4.setOnItemClickListener(new ImunisasiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailImunisasi_Activity.class);
                intent.putExtra("detail", imunisasiModelList4.get(position));
                startActivity(intent);
            }
        });
    }

    private void refreshData() {
        fetchData();
    }
}
