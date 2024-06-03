package com.android.giziku.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.BottomNavbar;
import com.android.giziku.R;
import com.android.giziku.models.AnakModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahAnak extends AppCompatActivity {

    EditText editTextName, editTextTglLahir, editTextBerat, editTextTinggi, editTextLingkarKepala;
    RadioButton radioButtonLakiLaki, radioButtonPerempuan, radiobtnAlergiYa, radiobtnAlergiTidak;
    Button btnSimpan;
    TextView tombolKeluar;
    ProgressBar progresBar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_anak);

        database = FirebaseDatabase.getInstance();
        tombolKeluar = findViewById(R.id.textViewTitle);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progresBar = findViewById(R.id.progresBar);
        editTextName = findViewById(R.id.editTextName);
        editTextTglLahir = findViewById(R.id.editTextTglLahir);
        editTextBerat = findViewById(R.id.editTextBerat);
        editTextTinggi = findViewById(R.id.editTextTinggi);
        editTextLingkarKepala = findViewById(R.id.editTextLingkarKepala);
        radioButtonLakiLaki = findViewById(R.id.radioButtonLakiLaki);
        radioButtonPerempuan = findViewById(R.id.radioButtonPerempuan);
        radiobtnAlergiYa = findViewById(R.id.radiobtnAlergiYa);
        radiobtnAlergiTidak = findViewById(R.id.radiobtnAlergiTidak);
        btnSimpan = findViewById(R.id.btnTambahProfil);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simpan data hanya jika semua inputan sudah terisi
                if (validateInput()) {
                    progresBar.setVisibility(View.VISIBLE);
                    btnSimpan.setVisibility(View.GONE);
                    simpanData();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progresBar.setVisibility(View.GONE);
                            btnSimpan.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(getApplicationContext(), BottomNavbar.class);
                            intent.putExtra("selected_fragment", "profil"); // Menetapkan fragment Profil sebagai default
                            startActivity(intent);
                        }
                    }, 2000);
                }
            }

            private boolean validateInput() {
                String nama = editTextName.getText().toString().trim();
                String tanggalLahir = editTextTglLahir.getText().toString().trim();
                String beratBadan = editTextBerat.getText().toString().trim();
                String tinggiBadan = editTextTinggi.getText().toString().trim();
                String lingkarKepala = editTextLingkarKepala.getText().toString().trim();
                boolean memilikiAlergi = radiobtnAlergiYa.isChecked();
                boolean isValid = true;

                if (TextUtils.isEmpty(nama)) {
                    editTextName.setError("Nama Anak Tidak Boleh Kosong");
                    isValid = false;
                }
                if (TextUtils.isEmpty(tanggalLahir)) {
                    editTextTglLahir.setError("Tanggal Lahir Tidak Boleh Kosong");
                    isValid = false;
                }
                if (TextUtils.isEmpty(beratBadan)) {
                    editTextBerat.setError("Berat Badan Tidak Boleh Kosong");
                    isValid = false;
                }
                if (TextUtils.isEmpty(tinggiBadan)) {
                    editTextTinggi.setError("Tinggi Tidak Boleh Kosong");
                    isValid = false;
                }
                if (TextUtils.isEmpty(lingkarKepala)) {
                    editTextLingkarKepala.setError("Lingkar Kepala Tidak Boleh Kosong");
                    isValid = false;
                }
                if (!memilikiAlergi && radioButtonLakiLaki.isChecked() && radioButtonPerempuan.isChecked()) {
                    Toast.makeText(TambahAnak.this, "Pilih satu jenis kelamin atau alergi terlebih dahulu", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                return isValid;
            }

            private void simpanData() {
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Anak");

                String nama = editTextName.getText().toString().trim();
                String tanggalLahir = editTextTglLahir.getText().toString().trim();
                String beratBadan = editTextBerat.getText().toString().trim();
                String tinggiBadan = editTextTinggi.getText().toString().trim();
                String lingkarKepala = editTextLingkarKepala.getText().toString().trim();
                String jenisKelamin = null;
                boolean memilikiAlergi = radiobtnAlergiYa.isChecked();

                if (radioButtonLakiLaki.isChecked()) {
                    jenisKelamin = "Laki-laki";
                } else if (radioButtonPerempuan.isChecked()) {
                    jenisKelamin = "Perempuan";
                }


                AnakModel anak = new AnakModel(nama, tanggalLahir, beratBadan, tinggiBadan, lingkarKepala, jenisKelamin, memilikiAlergi);

                databaseRef.push().setValue(anak)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(TambahAnak.this, "Berhasil Menyimpan", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(TambahAnak.this, "Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
