package com.android.giziku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.giziku.R;
import com.android.giziku.models.ArtikelModel;
import com.android.giziku.models.MpasiModel;
import com.bumptech.glide.Glide;

public class DetailArtikel extends AppCompatActivity {
    ImageView imageArtikel, toolbar;
    TextView tanggal, judul, keterangan, isi, tombolKeluar;

    ArtikelModel artikelModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);


        imageArtikel = findViewById(R.id.imageArtikel);
        tanggal = findViewById(R.id.tanggal);
        judul = findViewById(R.id.judul);
        keterangan = findViewById(R.id.keterangan);
        isi = findViewById(R.id.isi);
        tombolKeluar = findViewById(R.id.textView1);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final Object object = getIntent().getSerializableExtra("detaill");
        if (object instanceof ArtikelModel) {
            artikelModel = (ArtikelModel) object;
        }

        if (artikelModel != null) {
            Glide.with(getApplicationContext()).load(artikelModel.getImg_url()).into(imageArtikel);
            judul.setText(artikelModel.getJudul());
            tanggal.setText(artikelModel.getTanggal());
            keterangan.setText(artikelModel.getKeterangan());
            isi.setText(artikelModel.getIsi());

        }
    }
}