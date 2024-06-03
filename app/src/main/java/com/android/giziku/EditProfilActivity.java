package com.android.giziku;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfilActivity extends AppCompatActivity {

    EditText editUsername, editPasswordBaru, editKonfirmasiPassword;
    ProgressBar progressBar;
    Button btnEdit;
    FirebaseDatabase database;
    DatabaseReference reference;
    String userId;
    TextView tombolKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        tombolKeluar = findViewById(R.id.textViewTitle);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        editUsername = findViewById(R.id.editUsername);
        editPasswordBaru = findViewById(R.id.editPasswordBaru);
        editKonfirmasiPassword = findViewById(R.id.editKonfirmasiPassword);
        btnEdit = findViewById(R.id.btnEditProfil);
        progressBar = findViewById(R.id.progresBar);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            // User not authenticated, handle this case
            Toast.makeText(this, "User not authenticated, please login.", Toast.LENGTH_SHORT).show();
            // Optionally, redirect to login activity
            // Intent intent = new Intent(EditProfilActivity.this, LoginActivity.class);
            // startActivity(intent);
            finish(); // Close the activity
            return;
        }

        userId = currentUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameBaru = editUsername.getText().toString().trim();
                String passwordBaru = editPasswordBaru.getText().toString().trim();
                String passwordLagi = editKonfirmasiPassword.getText().toString().trim();

                if (validasiInput(usernameBaru, passwordBaru, passwordLagi)) {
                    // Tombol Edit ditekan, atur tampilan tombol dan progress bar
                    btnEdit.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);

                    updateUserData(usernameBaru, passwordBaru);
                }
            }
        });
    }

    private void updateUserData(String usernameBaru, String passwordBaru) {
        reference.child("username").setValue(usernameBaru);
        reference.child("password").setValue(passwordBaru);

        // Tampilkan pesan toast dan atur tampilan tombol dan progress bar setelah 3 detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Sembunyikan progress bar dan tampilkan tombol Edit
                progressBar.setVisibility(View.GONE);
                btnEdit.setVisibility(View.VISIBLE);

                // Tampilkan pesan toast
                Toast.makeText(EditProfilActivity.this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
                finish(); // Tutup activity setelah selesai memperbarui data
            }
        }, 3000);
    }

    private boolean validasiInput(String usernameBaru, String passwordBaru, String passwordLagi) {
        if (TextUtils.isEmpty(usernameBaru)) {
            editUsername.setError("Username Tidak Boleh Kosong");
            return false;
        }
        if (TextUtils.isEmpty(passwordBaru)) {
            editPasswordBaru.setError("Password Tidak Boleh Kosong");
            return false;
        }
        if (TextUtils.isEmpty(passwordLagi)) {
            editKonfirmasiPassword.setError("Password Tidak Boleh Kosong");
            return false;
        }
        if (passwordBaru.length() < 8) {
            editPasswordBaru.setError("Password harus lebih dari 8 karakter");
            return false;
        }
        if (!passwordBaru.equals(passwordLagi)) {
            editKonfirmasiPassword.setError("Password Harus Sama");
            return false;
        }
        return true;
    }
}
