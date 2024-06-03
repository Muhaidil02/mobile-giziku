package com.android.giziku.menuAwal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.activity.DetailMenu_Mpasi;
import com.android.giziku.adapter.MenuMpasiAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MenuMPASI_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    MenuMpasiAdapter menuMpasiAdapter;
    List<MenuMpasiModel> menuMpasiModelList;
    FirebaseFirestore db;
    TextView tombolKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mpasi);

        tombolKeluar = findViewById(R.id.textView1);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.pop_menuMpasi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        menuMpasiModelList = new ArrayList<>();
        menuMpasiAdapter = new MenuMpasiAdapter(getApplicationContext(), menuMpasiModelList);
        recyclerView.setAdapter(menuMpasiAdapter);

        db.collection("Mpasi").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                MenuMpasiModel menuMpasiModel = document.toObject(MenuMpasiModel.class);
                                menuMpasiModelList.add(menuMpasiModel);
                                menuMpasiAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(MenuMPASI_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        menuMpasiAdapter.setOnItemClickListener(new MenuMpasiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(MenuMPASI_Activity.this, DetailMenu_Mpasi.class);
                i.putExtra("detail", menuMpasiModelList.get(position));
                startActivity(i);
            }
        });
    }
}