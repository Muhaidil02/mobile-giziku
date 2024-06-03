package com.android.giziku.vaksin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.giziku.PembayaranVaksin_Activity;
import com.android.giziku.R;
import com.android.giziku.models.JadwalModel;
import com.android.giziku.models.JenisVaksinModel;
import com.android.giziku.models.PembayaranVaksiin_Model;
import com.android.giziku.models.VaksinModel;

public class DetailVaksin extends AppCompatActivity {


    Button btnLanjut;
    VaksinModel vaksinModel = null;
    JadwalModel jadwalModel = null;
    JenisVaksinModel jenisVaksinModel = null;
    TextView detTempatVaksin, detJenisVaksin, detJadwalVaksin, detWaktuVaksin, detJenissVaksin, detHargaVaksin, detTotalHarga, detTotalKeseluruhan, btnKeluar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vaksin2);

        btnKeluar = findViewById(R.id.textView1);
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

        btnLanjut = findViewById(R.id.btnLanjut);


        detTempatVaksin = findViewById(R.id.tempatVaksinDetail);
        detJadwalVaksin = findViewById(R.id.jadwalVaksinDetail);

        detJenissVaksin = findViewById(R.id.jenisVaksinndetail);
        detJenisVaksin = findViewById(R.id.jenisVaksinDetail);

        detWaktuVaksin = findViewById(R.id.waktuVaksinDetail);
        detHargaVaksin = findViewById(R.id.hargaVaksinDetail);
        detTotalHarga = findViewById(R.id.totalHargaDetail);
        detTotalKeseluruhan = findViewById(R.id.totalHargaDetaill);


        vaksinModel = (VaksinModel) getIntent().getSerializableExtra("vaksinDetail");
        jenisVaksinModel = (JenisVaksinModel) getIntent().getSerializableExtra("jenisVaksinDetail");

        jadwalModel = (JadwalModel) getIntent().getSerializableExtra("jadwalVaksin");

        if (jadwalModel != null && jenisVaksinModel != null && vaksinModel != null) {
                detTempatVaksin.setText(vaksinModel.getTempatVaksin());
                detHargaVaksin.setText(jenisVaksinModel.getHarga());
                detJenisVaksin.setText(jenisVaksinModel.getNama());
                detJenissVaksin.setText(jenisVaksinModel.getNama());
                detTotalHarga.setText(jenisVaksinModel.getHarga());
                detTotalKeseluruhan.setText(jenisVaksinModel.getHarga());

                detJadwalVaksin.setText(jadwalModel.getTanggal() + " " + jadwalModel.getBulan() + " " + jadwalModel.getHari());

        }
        
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PembayaranVaksin_Activity.class);
                intent.putExtra("jenisVaksinBayar", jenisVaksinModel);
                intent.putExtra("vaksinBayar", vaksinModel);
                intent.putExtra("jadwalBayar", jadwalModel);
                startActivity(intent);
            }
        });
        }
    }




