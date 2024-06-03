package com.android.giziku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.giziku.Konten_fragment;
import com.android.giziku.R;
import com.android.giziku.models.AsupanibuModel;
import com.bumptech.glide.Glide;

public class DetailAsupanIbu extends AppCompatActivity {

    ImageView   imageAsupan, toolbar;
    TextView  tanggal, judul, kategori, waktumemasak,usia
            , porsi , funfact, bahan, caramembuat, tombolKeluar;

    AsupanibuModel asupanibuModel = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_asupan);



        imageAsupan = findViewById(R.id.imageAsupan);
        tanggal = findViewById(R.id.tanggal);
        judul = findViewById(R.id.judul);
        kategori =findViewById(R.id.Kategori);
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
        if (object instanceof AsupanibuModel){
            asupanibuModel = (AsupanibuModel) object;
        }

        if (asupanibuModel != null){
            Glide.with(getApplicationContext()).load(asupanibuModel.getImg_url()).into(imageAsupan);
            judul.setText(asupanibuModel.getJudul());
            tanggal.setText(asupanibuModel.getTanggal());
            kategori.setText(asupanibuModel.getKategori());
            waktumemasak.setText(asupanibuModel.getWaktu_memasak());
            usia.setText(asupanibuModel.getUsia());
            porsi.setText(asupanibuModel.getPorsi());
            funfact.setText(asupanibuModel.getFunfact());
            bahan.setText(asupanibuModel.getBahan());
            caramembuat.setText(asupanibuModel.getCara_membuat());

        }
//        toolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DetailAsupanIbu.this, Konten_fragment.class);
//                startActivity(intent);
//            }
//        });

    }
}