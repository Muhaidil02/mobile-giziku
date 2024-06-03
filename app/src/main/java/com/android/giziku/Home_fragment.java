package com.android.giziku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.giziku.imunisasi.Imunisasi_Activity;
import com.android.giziku.konsultasi.KonsultasiActivity;
import com.android.giziku.menuAwal.MenuAsupanIbu_Activity;
import com.android.giziku.menuAwal.MenuMPASI_Activity;
import com.android.giziku.vaksin.Vaksin_Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home_fragment extends Fragment {
    ImageView icon_vaksin, icon_imunisasi, icon_makananBayi, icon_ibuhamil, icon_video, icon_konsultaasi;

    Button btnTentangStunting;
    TextView lihatselengkapnyaArtikel, ucapanWelcome, username;
    FirebaseUser user;
    String userId;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ucapanWelcome = root.findViewById(R.id.hai);
        username = root.findViewById(R.id.username);
        icon_vaksin = root.findViewById(R.id.icon_vaksin);
        icon_ibuhamil = root.findViewById(R.id.icon_ibuhamil);
        icon_imunisasi = root.findViewById(R.id.icon_imunisasi);
        icon_video = root.findViewById(R.id.icon_video);
        icon_makananBayi = root.findViewById(R.id.icon_makananBayi);
        icon_konsultaasi = root.findViewById(R.id.icon_konsultasi);

        btnTentangStunting = root.findViewById(R.id.buttonTentangStunting);

        btnTentangStunting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Tentang Stunting")
                        .setMessage("Apakah Anda ingin mengetahui lebih lanjut tentang stunting?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                artikel();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userName = snapshot.child("username").getValue(String.class);
                    // Set text views
                    ucapanWelcome.setText("Hai, " + userName + "!");
                } else {
                    Toast.makeText(getActivity(), "User tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
            }
        });

        lihatselengkapnyaArtikel = root.findViewById(R.id.lihat_selengkapnya_artikel);
        lihatselengkapnyaArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.frameLayout, new Konten_fragment());
                fr.commit();
            }
        });

        icon_vaksin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.frameLayout, new Vaksin_Fragment());
                fr.commit();
            }
        });

        icon_konsultaasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KonsultasiActivity.class);
                startActivity(intent);
            }
        });

        icon_imunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Imunisasi_Activity.class);
                startActivity(intent);
            }
        });

        icon_ibuhamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuAsupanIbu_Activity.class);
                startActivity(intent);
            }
        });

        icon_makananBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuMPASI_Activity.class);
                startActivity(intent);
            }
        });

        icon_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuVideo_Activity.class);
                startActivity(intent);
            }
        });
        return root;
}
    private void artikel() {
        String linkArtikel = "https://www.alodokter.com/stunting";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkArtikel));
        startActivity(intent);
    }
}

