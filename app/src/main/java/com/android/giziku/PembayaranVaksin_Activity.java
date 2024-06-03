package com.android.giziku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.adapter.PembayaranVaksin_Adapter;
import com.android.giziku.models.JadwalModel;
import com.android.giziku.models.JenisVaksinModel;
import com.android.giziku.models.PembayaranVaksiin_Model;
import com.android.giziku.models.VaksinModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PembayaranVaksin_Activity extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    PembayaranVaksin_Adapter pvAdapter;
    PembayaranVaksiin_Model pvModel = null;
    JadwalModel jadwalModel = null;
    JenisVaksinModel jenisVaksinModel = null;
    VaksinModel vaksinModel = null;
    List<PembayaranVaksiin_Model>list;
    Button btnBayar;
    TextView totalHarga, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_vaksin);
        db = FirebaseFirestore.getInstance();
        btnBayar = findViewById(R.id.btnBayar);
        totalHarga = findViewById(R.id.totalHarga);
        btnKeluar = findViewById(R.id.textView1);

        recyclerView = findViewById(R.id.pop_pembayaran);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        pvAdapter = new PembayaranVaksin_Adapter(this, list);
        recyclerView.setAdapter(pvAdapter);

        db.collection("PembayaranVaksin").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PembayaranVaksiin_Model pvModel = document.toObject(PembayaranVaksiin_Model.class);
                                list.add(pvModel);
                            }pvAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(PembayaranVaksin_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        pvAdapter.setOnItemClickListener(new PembayaranVaksin_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pvAdapter.setSelectedPosition(position);
                pvModel = list.get(position);
            }
        });

        vaksinModel = (VaksinModel) getIntent().getSerializableExtra("vaksinBayar");
        jadwalModel = (JadwalModel) getIntent().getSerializableExtra("jadwalBayar");
        jenisVaksinModel = (JenisVaksinModel) getIntent().getSerializableExtra("jenisVaksinBayar");
        if (jenisVaksinModel != null) {
            totalHarga.setText(jenisVaksinModel.getHarga());
        }

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showSuccessDialog();
                }catch (Exception e){
                    Toast.makeText(PembayaranVaksin_Activity.this, "Pilih Salah Satu Pembayaran Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void showSuccessDialog() {
        ConstraintLayout constraintSukses = findViewById(R.id.constraintSukses);
        View view = LayoutInflater.from(PembayaranVaksin_Activity.this).inflate(R.layout.sukses_dialog_pembayaran, constraintSukses);
        Button btnBeranda = view.findViewById(R.id.btnBeranda);

        TextView tempatVaksin = view.findViewById(R.id.tempatVaksin);
        TextView namaBank = view.findViewById(R.id.namaBank);
        TextView jadwalVaksin = view.findViewById(R.id.jadwalVaksin);
        TextView jenisVaksin = view.findViewById(R.id.jenisVaksin);
        TextView hargaVaksin = view.findViewById(R.id.hargaVaksin);
        TextView totalHarga = view.findViewById(R.id.totalHarga);

        if (jadwalModel != null || vaksinModel != null || jenisVaksinModel != null || pvModel != null){
            tempatVaksin.setText(vaksinModel.getTempatVaksin());
            namaBank.setText(pvModel.getNama_bank());
            jadwalVaksin.setText (jadwalModel.getTanggal() + " " + jadwalModel.getBulan() + " " + jadwalModel.getHari());
            jenisVaksin.setText(jenisVaksinModel.getNama());
            hargaVaksin.setText(jenisVaksinModel.getHarga());
            totalHarga.setText(jenisVaksinModel.getHarga());
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(PembayaranVaksin_Activity.this);
        builder.setView(view);
        final  AlertDialog alertDialog = builder.create();

        btnBeranda.findViewById(R.id.btnBeranda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), BottomNavbar.class);

                // Menambahkan flag untuk menghapus aktivitas sebelumnya dari stack
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                // Mengirimkan informasi bahwa fragment home harus dipilih
                intent.putExtra("selected_fragment", "home");

                // Memulai aktivitas baru
                startActivity(intent);

                // Mengakhiri aktivitas saat ini
                finish();

                // Menampilkan pesan toast
                Toast.makeText(PembayaranVaksin_Activity.this, "Transaksi Berhasil", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}