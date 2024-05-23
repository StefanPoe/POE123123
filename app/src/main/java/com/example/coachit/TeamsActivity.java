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

public class TeamsActivity extends AppCompatActivity {

    FloatingActionButton fabteams;
    RecyclerView recyclerViewTeams;
    List<DataClassTeams> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    TeamsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        fabteams = findViewById(R.id.fabTeams);
        recyclerViewTeams = findViewById(R.id.recyclerViewTeams);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(TeamsActivity.this, 1);
        recyclerViewTeams.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(TeamsActivity.this);
        builder.setCancelable(false);
        builder .setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new TeamsAdapter(TeamsActivity.this, dataList);
        recyclerViewTeams.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("CoachITTeams");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClassTeams dataClassTeams = itemSnapshot.getValue(DataClassTeams.class);
                    dataList.add(dataClassTeams);

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

        fabteams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamsActivity.this, UploadTeamsActivity.class);
                startActivity(intent);
            }
        });


    }

    public void searchList(String text){
        ArrayList<DataClassTeams> searchList = new ArrayList<>();
        for (DataClassTeams dataClassTeams: dataList){
            if (dataClassTeams.getDataTeamName().toLowerCase().contains(text.toLowerCase()) || dataClassTeams.getDataTeamCoach().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClassTeams);
            }

        }
        adapter.searchDataList(searchList);


    }
}