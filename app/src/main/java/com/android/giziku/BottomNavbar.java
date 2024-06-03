package com.android.giziku;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavbar extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navbar);

        bottomNav = findViewById(R.id.bottomNav);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if (item.getItemId() == R.id.navHome) {
                    fragment = new Home_fragment();
                } else if (item.getItemId() == R.id.navProfil) {
                    fragment = new Profil_fragment();
                } else {
                    fragment = new Konten_fragment();
                }

                loadFragment(fragment);

                return true;
            }
        });

        // Memeriksa apakah intent memiliki data tambahan untuk menampilkan fragment tertentu
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_fragment")) {
            String selectedFragment = intent.getStringExtra("selected_fragment");
            if (selectedFragment.equals("profil")) {
                // Menampilkan Fragment Profil saat aktivitas dimulai
                Fragment profilFragment = new Profil_fragment();
                loadFragment(profilFragment);
                bottomNav.setSelectedItemId(R.id.navProfil); // Menyorot item menu Profil
            } else if (selectedFragment.equals("home")) {
                Fragment homeFragment = new Home_fragment();
                loadFragment(homeFragment);
                bottomNav.setSelectedItemId(R.id.navHome); // Menyorot item menu Home
            }
        } else {
            // Jika tidak ada data tambahan, tampilkan Fragment Home_fragment saat aktivitas dimulai
            Fragment homeFragment = new Home_fragment();
            loadFragment(homeFragment);
            bottomNav.setSelectedItemId(R.id.navHome); // Menyorot item menu Home
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }}