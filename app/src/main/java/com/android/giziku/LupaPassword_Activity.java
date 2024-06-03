package com.android.giziku;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPassword_Activity extends AppCompatActivity {
    TextView btnKeluar;
    Button btnKirimEmail;
    EditText editTextEmail;
    ProgressBar progressBar;
    FirebaseAuth auth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        btnKeluar = findViewById(R.id.textView1);
        btnKirimEmail = findViewById(R.id.btnKirimEmail);
        progressBar = findViewById(R.id.progresBar);
        editTextEmail = findViewById(R.id.inputEmailReset);

        auth = FirebaseAuth.getInstance();

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar.setVisibility(View.INVISIBLE);

        btnKirimEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(email)){
                    resetPassword();
                }else {
                    editTextEmail.setError("Email Tidak Boleh Kosong");
                }
            }
        });
    }

    private void resetPassword() {
        progressBar.setVisibility(View.VISIBLE);
        btnKirimEmail.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LupaPassword_Activity.this, "Link Reset Telah Dikirimkan ke Email Anda", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LupaPassword_Activity.this, "Error, gagal menemukan email", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                editTextEmail.setVisibility(View.VISIBLE);
                            }
                        });
            }
        },5000);


    }
}