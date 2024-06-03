package com.android.giziku.imunisasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.models.ImunisasiModel;
import com.android.giziku.vaksin.Vaksin_Fragment;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetailImunisasi_Activity extends AppCompatActivity {

    ImunisasiModel model1 = null;
    ImunisasiModel model2 = null;
    ImunisasiModel model3 = null;
    ImunisasiModel model4 = null;
    TextView detailJudul, detailNamaImunisasi, detailDeskripsi;
    ImageView gambarStatus;
    Button btnEditImunisasi, btnBooking;
    String img_urlBelum = "https://firebasestorage.googleapis.com/v0/b/gizikuapp.appspot.com/o/statusImunisasi%2Fstatus_benar.png?alt=media&token=c3d4a378-4631-445e-99d4-70094414c529";
    String img_urlSudah = "https://firebasestorage.googleapis.com/v0/b/gizikuapp.appspot.com/o/statusImunisasi%2Fstatus_salah.png?alt=media&token=91aad5c4-8eea-4d7d-be00-7dd5378acb55";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_imunisasi);

        btnEditImunisasi = findViewById(R.id.btnEditStatus);
        btnBooking = findViewById(R.id.btnBookingVaksin);
        gambarStatus = findViewById(R.id.gambarStatus);

        model1 = (ImunisasiModel) getIntent().getSerializableExtra("detail");
        model2 = (ImunisasiModel) getIntent().getSerializableExtra("detail");
        model3 = (ImunisasiModel) getIntent().getSerializableExtra("detail");
        model4 = (ImunisasiModel) getIntent().getSerializableExtra("detail");



        btnEditImunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DetailImunisasi_Activity.this);
                View sheetView = LayoutInflater.from(DetailImunisasi_Activity.this).inflate(R.layout.bottomsheet_imunisasi, null);

                RadioGroup radioGroup = sheetView.findViewById(R.id.radioGroupStatus);
                RadioButton radioButtonSudah = sheetView.findViewById(R.id.radioButtonSudah);
                RadioButton radioButtonBelum = sheetView.findViewById(R.id.radioButtonBelum);
                Button btnSimpan = sheetView.findViewById(R.id.btnSimpanStatus);

                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        if (selectedId == radioButtonBelum.getId()) {
                            if (model1 != null && model1.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlBelum).into(gambarStatus);
                                saveStatusToDatabase(model1.getId(), img_urlBelum);
                            } else if (model2 != null && model2.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlBelum).into(gambarStatus);
                                saveStatusToDatabase(model2.getId(), img_urlBelum);
                            } else if (model3 != null && model3.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlBelum).into(gambarStatus);
                                saveStatusToDatabase(model3.getId(), img_urlBelum);
                            } else if (model4 != null && model4.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlBelum).into(gambarStatus);
                                saveStatusToDatabase(model4.getId(), img_urlBelum);
                            }
                        } else if (selectedId == radioButtonSudah.getId()) {
                            if (model1 != null && model1.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlSudah).into(gambarStatus);
                                saveStatusToDatabase(model1.getId(), img_urlSudah);
                            } else if (model2 != null && model2.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlSudah).into(gambarStatus);
                                saveStatusToDatabase(model2.getId(), img_urlSudah);
                            } else if (model3 != null && model3.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlSudah).into(gambarStatus);
                                saveStatusToDatabase(model3.getId(), img_urlSudah);
                            } else if (model4 != null && model4.getId() != null) {
                                Glide.with(DetailImunisasi_Activity.this).load(img_urlSudah).into(gambarStatus);
                                saveStatusToDatabase(model4.getId(), img_urlSudah);
                            }
                        }
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });

        detailJudul = findViewById(R.id.detailNamaImunisasiTitle);
        detailNamaImunisasi = findViewById(R.id.detailNamaImunisasi);
        detailDeskripsi = findViewById(R.id.detailDeskripsi);

        detailJudul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (model1 != null || model2 != null || model3 != null || model4 != null) {
            detailJudul.setText(model1.getNamaImunisasi());
            detailNamaImunisasi.setText(model1.getNamaImunisasi());
            detailDeskripsi.setText(model1.getDeskripsiImunisasi());
            Glide.with(this).load(model1.getImg_url()).into(gambarStatus);

            detailJudul.setText(model2.getNamaImunisasi());
            detailNamaImunisasi.setText(model2.getNamaImunisasi());
            detailDeskripsi.setText(model2.getDeskripsiImunisasi());
            Glide.with(this).load(model2.getImg_url()).into(gambarStatus);

            detailJudul.setText(model3.getNamaImunisasi());
            detailNamaImunisasi.setText(model3.getNamaImunisasi());
            detailDeskripsi.setText(model3.getDeskripsiImunisasi());
            Glide.with(this).load(model3.getImg_url()).into(gambarStatus);

            detailJudul.setText(model4.getNamaImunisasi());
            detailNamaImunisasi.setText(model4.getNamaImunisasi());
            detailDeskripsi.setText(model4.getDeskripsiImunisasi());
            Glide.with(this).load(model4.getImg_url()).into(gambarStatus);
        }
    }



    private void saveStatusToDatabase(String imunisasiId, String imgUrl) {
        // Mendapatkan referensi Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Membuat objek untuk data yang ingin disimpan
        Map<String, Object> statusData = new HashMap<>();
        statusData.put("img_url", imgUrl);


        DocumentReference docRef1 = db.collection("Imunisasi1").document(imunisasiId);
        docRef1.update(statusData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Jika berhasil disimpan, tampilkan pesan

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Jika terjadi kesalahan, tampilkan pesan error

                    }
                });

        // Menyimpan data ke Firestore untuk Imunisasi2
        DocumentReference docRef2 = db.collection("Imunisasi2").document(imunisasiId);
        docRef2.update(statusData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Jika berhasil disimpan, tampilkan pesan
                        Toast.makeText(DetailImunisasi_Activity.this, "", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Jika terjadi kesalahan, tampilkan pesan error
                        Toast.makeText(DetailImunisasi_Activity.this, "" , Toast.LENGTH_SHORT).show();
                    }
                });

        // Menyimpan data ke Firestore untuk Imunisasi3
        DocumentReference docRef3 = db.collection("Imunisasi3").document(imunisasiId);
        docRef3.update(statusData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Jika berhasil disimpan, tampilkan pesan
                        Toast.makeText(DetailImunisasi_Activity.this, "", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Jika terjadi kesalahan, tampilkan pesan error
                        Toast.makeText(DetailImunisasi_Activity.this, " " , Toast.LENGTH_SHORT).show();
                    }
                });

        // Menyimpan data ke Firestore untuk Imunisasi4
        DocumentReference docRef4 = db.collection("Imunisasi4").document(imunisasiId);
        docRef4.update(statusData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Jika berhasil disimpan, tampilkan pesan
                        Toast.makeText(DetailImunisasi_Activity.this, "", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Jika terjadi kesalahan, tampilkan pesan error
                        Toast.makeText(DetailImunisasi_Activity.this, " " , Toast.LENGTH_SHORT).show();
                    }
                });
    }
}