package com.android.giziku;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.giziku.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Daftar extends AppCompatActivity {
    Button buttonDaftar;
    EditText inputEmailDaftar, inputUsernameDaftar, inputPasswordDaftar, inputPasswordLagi;
    ProgressBar progressBar;
    TextView daftar, sudahPunyaAkun;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        setContentView(R.layout.activity_daftar);
        buttonDaftar = findViewById(R.id.buttonDaftar);
        daftar = findViewById(R.id.Daftar);
        sudahPunyaAkun = findViewById(R.id.sudahPunyaAkun);
        inputEmailDaftar = findViewById(R.id.inputEmailDaftar);
        inputUsernameDaftar = findViewById(R.id.inputUsernameDaftar);
        inputPasswordDaftar = findViewById(R.id.inputPasswordDaftar);
        inputPasswordLagi = findViewById(R.id.inputPasswordLagi);
        progressBar = findViewById(R.id.progresBar);

        progressBar.setVisibility(View.INVISIBLE);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Daftar.this, Login.class));
            }
        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String userName = inputUsernameDaftar.getText().toString();
        String email = inputEmailDaftar.getText().toString();
        String password = inputPasswordDaftar.getText().toString();
        String passwordLagi = inputPasswordLagi.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            inputUsernameDaftar.setError("Username Tidak Boleh Kosong");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            inputEmailDaftar.setError("Email Tidak Boleh Kosong");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            inputPasswordDaftar.setError("Password Tidak Boleh Kosong");
            return;
        }
        if (TextUtils.isEmpty(passwordLagi)) {
            inputPasswordLagi.setError("Password Tidak Boleh Kosong");
            return;
        }
        if (!password.equals(passwordLagi)) {
            inputPasswordLagi.setError("Password Harus Sama");
            return;
        }
        if (password.length() < 8) {
            inputPasswordDaftar.setError("Passowrd harus lebih dari 8 karakter");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        buttonDaftar.setVisibility(View.INVISIBLE);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    UserModel userModel = new UserModel(userName, email, password, passwordLagi);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(userModel);
                                    Toast.makeText(Daftar.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Daftar.this, Login.class));
                                    finish();  // Close the current activity
                                }
                            }, 3000);  // 3 seconds delay before navigating to BottomNavbar
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                    buttonDaftar.setVisibility(View.VISIBLE);
                                    Toast.makeText(Daftar.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }, 3000);  // 3 seconds delay before hiding the ProgressBar and showing the error message
                        }
                    }
                });
    }
}
