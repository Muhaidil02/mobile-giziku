package com.android.giziku.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.giziku.R;
import com.android.giziku.menuAwal.MenuMpasiModel;
import com.bumptech.glide.Glide;

public class DetailMenu_Mpasi extends AppCompatActivity {
    ImageView imageMpasi;
    TextView  tombolKeluar, tanggal, judul, kategori, waktumemasak, usia, porsi, funfact, bahan, caramembuat;

    MenuMpasiModel menuMpasiModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail_mpasi);

        tombolKeluar = findViewById(R.id.textView1);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageMpasi = findViewById(R.id.imageMpasi);
        tanggal = findViewById(R.id.tanggal);
        judul = findViewById(R.id.judul);
        kategori = findViewById(R.id.Kategori);
        waktumemasak = findViewById(R.id.Waktumemasak);
        usia = findViewById(R.id.Usia);
        porsi = findViewById(R.id.Porsi);
        funfact = findViewById(R.id.Funfact);
        bahan = findViewById(R.id.Bahan);
        caramembuat = findViewById(R.id.Caramembuat);


        menuMpasiModel = (MenuMpasiModel) getIntent().getSerializableExtra("detail");

        if (menuMpasiModel != null) {
            Glide.with(getApplicationContext()).load(menuMpasiModel.getImg_url()).into(imageMpasi);
            judul.setText(menuMpasiModel.getJudul());
            tanggal.setText(menuMpasiModel.getTanggal());
            kategori.setText(menuMpasiModel.getKategori());
            waktumemasak.setText(menuMpasiModel.getWaktu_memasak());
            usia.setText(menuMpasiModel.getUsia());
            porsi.setText(menuMpasiModel.getPorsi());
            funfact.setText(menuMpasiModel.getFunfact());
            bahan.setText(menuMpasiModel.getBahan());
            caramembuat.setText(menuMpasiModel.getCara_membuat());
        }
    }
}