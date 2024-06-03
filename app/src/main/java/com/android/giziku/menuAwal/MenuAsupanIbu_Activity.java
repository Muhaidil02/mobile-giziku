package com.android.giziku.menuAwal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.R;
import com.android.giziku.activity.DetailMenu_AsupanIbu;
import com.android.giziku.adapter.MenuAsupanIbuAdapter;
import com.android.giziku.models.MenuAsupanIbuModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MenuAsupanIbu_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    MenuAsupanIbuAdapter menuAsupanIbuAdapter;
    List<MenuAsupanIbuModel>menuAsupanIbuModelList;
    FirebaseFirestore db;
    TextView tombolKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_asupan_ibu);

        db = FirebaseFirestore.getInstance();
        tombolKeluar = findViewById(R.id.textView1);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.pop_menuAsupanIbu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        menuAsupanIbuModelList = new ArrayList<>();
        menuAsupanIbuAdapter = new MenuAsupanIbuAdapter(getApplicationContext(), menuAsupanIbuModelList);
        recyclerView.setAdapter(menuAsupanIbuAdapter);

        db.collection("AsupanIbu").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                MenuAsupanIbuModel menuAsupanIbuModel = document.toObject(MenuAsupanIbuModel.class);
                                menuAsupanIbuModelList.add(menuAsupanIbuModel);
                                menuAsupanIbuAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(MenuAsupanIbu_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

        menuAsupanIbuAdapter.setOnItemClickListener(new MenuAsupanIbuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(MenuAsupanIbu_Activity.this, DetailMenu_AsupanIbu.class);
                i.putExtra("detail", menuAsupanIbuModelList.get(position));
                startActivity(i);
            }
        });

    }
}