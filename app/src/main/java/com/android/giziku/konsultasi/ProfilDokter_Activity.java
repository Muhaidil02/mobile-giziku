package com.android.giziku.konsultasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.android.giziku.models.KonsultasiModel;
import com.bumptech.glide.Glide;

public class ProfilDokter_Activity extends AppCompatActivity {
    TextView detailNama, detailPengalaman, detailJarak, detailHarga
            ,lihatLokasiRs1, lihatLokasiRs2, lihatLokasiRs3, btnKeluar;
    ImageView gambarDokter;
    Button btnLanjut;
    KonsultasiModel konsultasiModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_dokter);

        btnLanjut = findViewById(R.id.btnLanjut);
        btnKeluar = findViewById(R.id.textView1);
        detailNama = findViewById(R.id.detailNamaDokter);
        detailPengalaman = findViewById(R.id.detailPengalamanDokter);
        detailJarak = findViewById(R.id.detailJarakDokter);
        detailHarga = findViewById(R.id.detailHargaDokter);
        gambarDokter = findViewById(R.id.detailGambarDokter);
        lihatLokasiRs1 = findViewById(R.id.lihatLokasiDokter);
        lihatLokasiRs2 = findViewById(R.id.lihatLokasiLabuangBaji);
        lihatLokasiRs3 = findViewById(R.id.lihatLokasiHaji);

        konsultasiModel = (KonsultasiModel) getIntent().getSerializableExtra("detail");
        if (konsultasiModel != null){
            Glide.with(this).load(konsultasiModel.getImg_url()).into(gambarDokter);
            detailNama.setText(konsultasiModel.getNama());
            detailPengalaman.setText(konsultasiModel.getPengalaman());
            detailJarak.setText(konsultasiModel.getJarak());
            detailHarga.setText(konsultasiModel.getHarga());
        }else {
            Toast.makeText(this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
        }

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSheetPembayaran();
            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        String maps1 = "https://maps.app.goo.gl/DTxzjXo4Cqx3mqGn8";
        String maps2 = "https://maps.app.goo.gl/15HBimy5wBfTvF8P9";
        String maps3 = "https://maps.app.goo.gl/o65SkkD8sttzgtuZ7";
        lihatLokasiRs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lihatMaps(maps1);
            }
        });
        lihatLokasiRs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lihatMaps(maps2);
            }
        });

        lihatLokasiRs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lihatMaps(maps3);
            }
        });
    }

    private void showSheetPembayaran() {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintPesan);
        View view = LayoutInflater.from(ProfilDokter_Activity.this).inflate(R.layout.sheet_pesan_dokter, constraintLayout);
        Button btnLanjut = view.findViewById(R.id.btnLanjut);

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfilDokter_Activity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();


        btnLanjut.findViewById(R.id.btnLanjut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), PembayaranDokter_Activity.class);
                intent.putExtra("detailPembayaran", konsultasiModel);
                startActivity(intent);
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


    private void lihatMaps(String linkMaps) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkMaps));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkMaps));
        try {
            startActivity(appIntent);
        }catch (Exception ex){
            startActivity(webIntent);
        }
    }
}