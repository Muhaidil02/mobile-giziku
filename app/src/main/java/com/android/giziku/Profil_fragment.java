package com.android.giziku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.giziku.activity.TambahAnak;
import com.android.giziku.adapter.AnakAdapter;
import com.android.giziku.models.AnakModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Profil_fragment extends Fragment {

    // Deklarasi variabel global
    private TextView username, useremail, textView1, tambahAnak, keluarAkun, hapusAkun;
    private ImageView imageProfil, btnEdit, iconKamera;
    private RecyclerView recyclerView;
    private List<AnakModel> anakModelList;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    private AnakAdapter anakAdapter;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    StorageReference storageReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profil, container, false);


        recyclerView = root.findViewById(R.id.recylerViewAnak);
        btnEdit = root.findViewById(R.id.btnEdit);
        keluarAkun = root.findViewById(R.id.keluarAkun);
        username = root.findViewById(R.id.username);
        useremail = root.findViewById(R.id.email);
        textView1 = root.findViewById(R.id.textView1);
        tambahAnak = root.findViewById(R.id.TambahAnak);
        imageProfil = root.findViewById(R.id.imageProfil);
        iconKamera = root.findViewById(R.id.gantiProfil);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        // Setup RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        anakModelList = new ArrayList<>();
        anakAdapter = new AnakAdapter(getActivity(), anakModelList);
        recyclerView.setAdapter(anakAdapter);

        // Ambil user saat ini
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            reference = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();

            // Ambil data pengguna dari database
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String userName = snapshot.child("username").getValue(String.class);
                        String email = snapshot.child("email").getValue(String.class);

                        // Set text views
                        username.setText(userName);
                        useremail.setText(email);
                    } else {
                        Toast.makeText(getActivity(), "User tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show();
                }
            });

            storageReference= FirebaseStorage.getInstance().getReference();

            StorageReference profileRef = storageReference.child("users/" + auth.getCurrentUser().getUid()+"/profile.jpg");
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(imageProfil);
                }
            });

            iconKamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(openGaleri, 500);
                }
            });


            // Setup tambahAnak button
            tambahAnak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), TambahAnak.class);
                    startActivity(intent);
                }
            });

            // Setup btnEdit button
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditProfilActivity.class);
                    startActivity(intent);
                }
            });

            // Setup Firebase Database Reference untuk anak
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Anak");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    anakModelList.clear();
                    for (DataSnapshot anakSnapshot : snapshot.getChildren()) {
                        AnakModel anak = anakSnapshot.getValue(AnakModel.class);
                        if (anak != null) {
                            anakModelList.add(anak);
                        }
                    }
                    anakAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Gagal mengambil data anak", Toast.LENGTH_SHORT).show();
                }
            });

            // Setup keluarAkun button
            keluarAkun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSuccessDialog();
                }
            });

        } else {
            Toast.makeText(getActivity(), "Tidak ada pengguna yang masuk", Toast.LENGTH_SHORT).show();
        }
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profile_picture.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }
    }
    private void uploadImageToFirebase(Uri imageUri) {
        //Upload Image to Firebase
        StorageReference fileRef = storageReference.child("users/" + auth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageProfil);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed. ", Toast.LENGTH_SHORT).show();
            }
        });

    }

        public void showSuccessDialog() {
        ConstraintLayout constraintLayout = getView().findViewById(R.id.constraintLogout);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sheet_logout, constraintLayout);
        Button btnKeluar = view.findViewById(R.id.btnKeluar);
        Button btnBatal = view.findViewById(R.id.btnBatal);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Pertama.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    // Metode untuk mendapatkan adapter anak
    public AnakAdapter getAnakAdapter() {
        return anakAdapter;
    }

    // Metode untuk menampilkan dialog konfirmasi hapus akun
//    public void showDeleteAccountDialog() {
//        ConstraintLayout constraintLayout = getView().findViewById(R.id.constraintHapus);
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sheet_hapus_akun, constraintLayout);
//        Button btnHapus = view.findViewById(R.id.btnHapus);
//        Button btnBatal = view.findViewById(R.id.btnBatal);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(view);
//        final AlertDialog alertDialog = builder.create();
//
//        btnHapus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteUserAccount(alertDialog);
//            }
//        });

//        btnBatal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//
//        if (alertDialog.getWindow() != null) {
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        }
//        alertDialog.show();
//    }

    // Metode untuk menghapus akun pengguna
//    private void deleteUserAccount(AlertDialog alertDialog) {
//        if (user != null) {
//            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
//            // Menghapus data pengguna dari Realtime Database
//            userRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        // Menghapus akun dari Firebase Authentication
//                                // Jika berhasil menghapus akun
//                                Toast.makeText(getActivity(), "Akun berhasil dihapus", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getActivity(), Pertama.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                getActivity().finish();
//                            // Tutup dialog
//                            alertDialog.dismiss();
//                    } else {
//                        Toast.makeText(getActivity(), "Gagal menghapus data pengguna. Silakan coba lagi.", Toast.LENGTH_SHORT).show();
//                        alertDialog.dismiss();
//                    }
//                }
//            });
//        }
//    }

    // Metode untuk menampilkan dialog logout

}
