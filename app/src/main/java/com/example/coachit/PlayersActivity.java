package com.example.coachit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    PlayerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(PlayersActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(PlayersActivity.this);
        builder.setCancelable(false);
        builder .setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new PlayerAdapter(PlayersActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("CoachITPlayers");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);

                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayersActivity.this, UploadPlayerActivity.class);
                startActivity(intent);
            }
        });


    }

    public void searchList(String text){
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass: dataList){
            if (dataClass.getDataName().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataSurname().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataAge().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataCity().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataCoach().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataHeight().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataContact1().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataContact2().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataDomhand().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataNickname().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataWeight().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataVertical().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataPosition().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataSex().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataWingspan().toLowerCase().contains(text.toLowerCase()) || dataClass.getDataTeam().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }

        }
        adapter.searchDataList(searchList);


    }
}