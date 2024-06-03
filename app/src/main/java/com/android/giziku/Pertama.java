package com.android.giziku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Pertama extends AppCompatActivity {
    Button buttonMasuk, buttonDaftar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertama);
        buttonMasuk = findViewById(R.id.buttonMasuk);
        buttonDaftar = findViewById(R.id.buttonDaftar);

        auth = FirebaseAuth.getInstance();

        // Cek apakah pengguna telah masuk dengan FirebaseAuth
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            // Pengguna telah masuk, arahkan ke halaman utama
            startActivity(new Intent(getApplicationContext(), BottomNavbar.class));
            finish();
        }

        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
            }
        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(getApplicationContext(), Daftar.class);
                startActivity(daftar);
            }
        });
    }
}
