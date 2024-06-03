package com.android.giziku.konsultasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.adapter.PembayaranDokter_Adapter;
import com.android.giziku.models.KonsultasiModel;
import com.android.giziku.models.PembayaranDokter_Model;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PembayaranDokter_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    PembayaranDokter_Adapter adapter;
    KonsultasiModel konsultasiModel = null;
    PembayaranDokter_Model model;
    List <PembayaranDokter_Model> modelList;
    TextView pembayaranNamaDokter, pembayaranJarak, pembayaranPengalaman
            ,pembayaranHarga, pembayaranTotalHarga, pembayaranTotalBayar, btnKeluar;
    Button btnBayar;
    ImageView detailGambarDokter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_dokter);

        btnKeluar = findViewById(R.id.textView1);
        pembayaranNamaDokter = findViewById(R.id.detailNamaDokter);
        pembayaranJarak = findViewById(R.id.detailJarakDokter);
        pembayaranPengalaman = findViewById(R.id.detailPengalamanDokter);
        pembayaranHarga = findViewById(R.id.hargaPembayaran);
        pembayaranTotalHarga = findViewById(R.id.totalPembayaran);
        pembayaranTotalBayar = findViewById(R.id.totalPembayaran2);
        btnBayar = findViewById(R.id.btnBayar);
        detailGambarDokter = findViewById(R.id.detailGambarDokter);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.pop_pembayaranDokter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(layoutManager);
        modelList = new ArrayList<>();
        adapter = new PembayaranDokter_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);



        db.collection("PembayaranVaksin").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                PembayaranDokter_Model model = document.toObject(PembayaranDokter_Model.class);
                                modelList.add(model);
                            }adapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(PembayaranDokter_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showSuccessDialog();
                }catch (Exception e){
                    Toast.makeText(PembayaranDokter_Activity.this, "Pilih Pembayaran Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adapter.setOnItemClickListener(new PembayaranDokter_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.setSelectedPosition(position);
                model = modelList.get(position);
            }
        });

        konsultasiModel = (KonsultasiModel) getIntent().getSerializableExtra("detailPembayaran");
        if (konsultasiModel != null){
            Glide.with(this).load(konsultasiModel.getImg_url()).into(detailGambarDokter);
            pembayaranNamaDokter.setText(konsultasiModel.getNama());
            pembayaranPengalaman.setText(konsultasiModel.getPengalaman());
            pembayaranJarak.setText(konsultasiModel.getJarak());
            pembayaranHarga.setText(konsultasiModel.getHarga());
            pembayaranTotalHarga.setText(konsultasiModel.getHarga());
            pembayaranTotalBayar.setText(konsultasiModel.getHarga());
        }else {
            Toast.makeText(this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSuccessDialog() {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintSuksesDokter);
        View view  = LayoutInflater.from(PembayaranDokter_Activity.this).inflate(R.layout.sukses_dokter_pembayaran, constraintLayout);
        Button btnHubungiDokter = view.findViewById(R.id.btnHubungiDOkter);
        Button btnTutup = view.findViewById(R.id.btnTutup);
        String wa = "https://wa.me/6281242866687";

        TextView namaBank = view.findViewById(R.id.namaBank);
        TextView namaKonsultasi = view.findViewById(R.id.namaKonsultasi);
        TextView tempatKonsultasi = view.findViewById(R.id.tempatKonsultasi);
        TextView hargaKonsultasi = view.findViewById(R.id.hargaKonsultasi);
        TextView totalHarga = view.findViewById(R.id.totalHargaKonsultasi);

        if (konsultasiModel != null){
            namaBank.setText(model.getNama_bank());
            namaKonsultasi.setText(konsultasiModel.getNama());
            tempatKonsultasi.setText(konsultasiModel.getJarak());
            hargaKonsultasi.setText(konsultasiModel.getHarga());
            totalHarga.setText(konsultasiModel.getHarga());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(PembayaranDokter_Activity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        btnHubungiDokter.findViewById(R.id.btnHubungiDOkter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(wa));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(wa));
            try {
                startActivity(appIntent);
            }catch (Exception e){
                startActivity(webIntent);
                }
            }
        });

        btnTutup.findViewById(R.id.btnTutup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}