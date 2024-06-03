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

    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;

    public class Login extends AppCompatActivity {
        Button buttonLogin;
        EditText inputEmail, inputPassword;
        TextView lupaPassword, masuk1, belumAdaAkun, btnKeluar;
        ProgressBar progressBar;
        FirebaseAuth auth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_starter);

            auth = FirebaseAuth.getInstance();

            buttonLogin = findViewById(R.id.buttonLogin);
            masuk1 = findViewById(R.id.Masuk);
            belumAdaAkun = findViewById(R.id.belumadaAkun);
            lupaPassword = findViewById(R.id.lupapassword);
            inputEmail = findViewById(R.id.inputEmail);
            inputPassword = findViewById(R.id.inputPassword);
            progressBar = findViewById(R.id.progresBar);

            masuk1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            lupaPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LupaPassword_Activity.class);
                    startActivity(intent);
                }
            });

            belumAdaAkun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this, Daftar.class));
                }
            });

            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginUser();
                }
            });
        }

        private void loginUser() {
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
               inputEmail.setError("Email Tidak Boleh Kosong");
                return;
            }
            if (TextUtils.isEmpty(password)) {
              inputPassword.setError("Password Tidak Boleh Kosong");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            buttonLogin.setVisibility(View.INVISIBLE);

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, BottomNavbar.class));



                                        finish();  // Close the current activity
                                    }
                                }, 500);  // 3 seconds delay before navigating to BottomNavbar
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setVisibility(View.GONE);
                                        buttonLogin.setVisibility(View.VISIBLE);
                                        Toast.makeText(Login.this, "Email/Password Salah", Toast.LENGTH_SHORT).show();
                                    }
                                }, 500);
                            }
                        }
                    });
        }
    }
