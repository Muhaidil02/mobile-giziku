package com.android.giziku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.giziku.R;
import com.android.giziku.models.AsupanibuModel;
import com.android.giziku.models.MpasiModel;
import com.bumptech.glide.Glide;

public class DetailMpasi extends AppCompatActivity {
    ImageView imageMpasi;
    TextView   tombolKeluar,tanggal, judul, kategori, waktumemasak, usia, porsi, funfact, bahan, caramembuat;

    MpasiModel mpasiModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mpasi);


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

        tombolKeluar = findViewById(R.id.textView1);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof MpasiModel) {
            mpasiModel = (MpasiModel) object;
        }

        if (mpasiModel != null) {
            Glide.with(getApplicationContext()).load(mpasiModel.getImg_url()).into(imageMpasi);
            judul.setText(mpasiModel.getJudul());
            tanggal.setText(mpasiModel.getTanggal());
            kategori.setText(mpasiModel.getKategori());
            waktumemasak.setText(mpasiModel.getWaktu_memasak());
            usia.setText(mpasiModel.getUsia());
            porsi.setText(mpasiModel.getPorsi());
            funfact.setText(mpasiModel.getFunfact());
            bahan.setText(mpasiModel.getBahan());
            caramembuat.setText(mpasiModel.getCara_membuat());

        }
    }
}