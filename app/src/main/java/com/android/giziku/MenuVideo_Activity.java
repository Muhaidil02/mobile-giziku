package com.android.giziku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.giziku.adapter.MenuVideoAdapter;
import com.android.giziku.menuAwal.MenuVideoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MenuVideo_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore db;
    MenuVideoAdapter menuVideoAdapter;
    List<MenuVideoModel>menuVideoModelList;
    Button btnNonton;
    TextView tombolKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_video);

        db = FirebaseFirestore.getInstance();
        tombolKeluar = findViewById(R.id.textView1);
        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.pop_menuVideo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false );
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        menuVideoModelList = new ArrayList<>();
        menuVideoAdapter = new MenuVideoAdapter(getApplicationContext(), menuVideoModelList);
        recyclerView.setAdapter(menuVideoAdapter);

        db.collection("Video").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                MenuVideoModel menuVideoModel = document.toObject(MenuVideoModel.class);
                                menuVideoModelList.add(menuVideoModel);
                                menuVideoAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(MenuVideo_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

        menuVideoAdapter.setOnItemClickListener(new MenuVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MenuVideoModel video = menuVideoModelList.get(position);
                watchYoutube(video.getVideoUrl());
            }
        });

    }

    private void watchYoutube(String videoUrl) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        Intent webIntent= new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));

        try {
            startActivity(appIntent);
        }catch (Exception e){
            startActivity(webIntent);
        }
    }
}