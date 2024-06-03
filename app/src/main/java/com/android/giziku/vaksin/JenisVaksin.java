package com.android.giziku.vaksin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.android.giziku.R;
import com.android.giziku.adapter.JenisVaksinadapter;
import com.android.giziku.models.JenisVaksinModel;
import com.android.giziku.models.VaksinModel;

import java.util.ArrayList;
import java.util.List;

public class JenisVaksin extends AppCompatActivity implements JenisVaksinadapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private JenisVaksinadapter jenisVaksinadapter;
    private List<JenisVaksinModel> jenisVaksinModelList;
    private VaksinModel vaksinModel = null;
    private ImageView imgDetailVaksin;
    private TextView detTempatVaksin, detLokVaksin, detJarakVaksin, detRatingVaksin, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jenis_vaksin);

        btnKeluar = findViewById(R.id.textView1);
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.pop_jenisVaksin);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        jenisVaksinModelList = new ArrayList<>();
        jenisVaksinadapter = new JenisVaksinadapter(this, jenisVaksinModelList);
        recyclerView.setAdapter(jenisVaksinadapter);

        imgDetailVaksin = findViewById(R.id.imageDetailVaksin);
        detTempatVaksin = findViewById(R.id.detailTempatVaksin);
        detLokVaksin = findViewById(R.id.detailLokasiVaksin);
        detJarakVaksin = findViewById(R.id.detailJarakLokasi);
        detRatingVaksin = findViewById(R.id.detailRatingVaksin);


        db.collection("JenisVaksin").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                JenisVaksinModel jenisVaksinModel = document.toObject(JenisVaksinModel.class);
                                jenisVaksinModelList.add(jenisVaksinModel);
                            }
                            jenisVaksinadapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        vaksinModel = (VaksinModel) getIntent().getSerializableExtra("detail");
        if (vaksinModel != null) {
            Glide.with(this).load(vaksinModel.getimg_url()).into(imgDetailVaksin);
            detTempatVaksin.setText(vaksinModel.getTempatVaksin());
            detLokVaksin.setText(vaksinModel.getLokasiVaksin());
            detJarakVaksin.setText(vaksinModel.getJarakLokasi());
            detRatingVaksin.setText(vaksinModel.getRatingVaksin());
        }

        jenisVaksinadapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        JenisVaksinModel jenisVaksinModel = jenisVaksinModelList.get(position);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null);

        TextView bottomNama = sheetView.findViewById(R.id.bottomNamaVaksin);
        TextView bottomHarga = sheetView.findViewById(R.id.bottomhargaVaksin);
        TextView bottomDeskripsi = sheetView.findViewById(R.id.bottomdeskripsiVaksin);
        Button btnPesanVaksin = sheetView.findViewById(R.id.btnPesanVaksin);

        bottomNama.setText(jenisVaksinModel.getNama());
        bottomHarga.setText(jenisVaksinModel.getHarga());
        bottomDeskripsi.setText(jenisVaksinModel.getDeskripsi());

        btnPesanVaksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JenisVaksin.this, JadwalVaksin_Activity.class);
                intent.putExtra("jenisVaksinDetail", jenisVaksinModel);
                intent.putExtra("vaksinDetail", vaksinModel);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }
}
