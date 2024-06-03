package com.android.giziku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.models.MenuAsupanIbuModel;
import com.bumptech.glide.Glide;

public class DetailMenu_AsupanIbu extends AppCompatActivity {

    ImageView imageArtikel;
    TextView tombolKeluar, tanggal, judul, kategori, waktumemasak, usia, porsi, funfact, bahan, caramembuat;

    MenuAsupanIbuModel menuAsupanIbuModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_asupan_ibu);

        tombolKeluar = findViewById(R.id.textView1);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageArtikel = findViewById(R.id.imageArtikel);
        tanggal = findViewById(R.id.tanggal);
        judul = findViewById(R.id.judul);
        kategori = findViewById(R.id.Kategori);
        waktumemasak = findViewById(R.id.Waktumemasak);
        usia = findViewById(R.id.Usia);
        porsi = findViewById(R.id.Porsi);
        funfact = findViewById(R.id.Funfact);
        bahan = findViewById(R.id.Bahan);
        caramembuat = findViewById(R.id.Caramembuat);

        menuAsupanIbuModel = (MenuAsupanIbuModel) getIntent().getSerializableExtra("detail");
        if (menuAsupanIbuModel != null) {
            Glide.with(getApplicationContext()).load(menuAsupanIbuModel.getImg_url()).into(imageArtikel);
            judul.setText(menuAsupanIbuModel.getJudul());
            tanggal.setText(menuAsupanIbuModel.getTanggal());
            kategori.setText(menuAsupanIbuModel.getKategori());
            waktumemasak.setText(menuAsupanIbuModel.getWaktu_memasak());
            usia.setText(menuAsupanIbuModel.getUsia());
            porsi.setText(menuAsupanIbuModel.getPorsi());
            funfact.setText(menuAsupanIbuModel.getFunfact());
            bahan.setText(menuAsupanIbuModel.getBahan());
            caramembuat.setText(menuAsupanIbuModel.getCara_membuat());
        }else {
            Toast.makeText(this, "Tidak Ada di database", Toast.LENGTH_SHORT).show();
        }
    }
}