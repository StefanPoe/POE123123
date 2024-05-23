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
import java.util.logging.Filter;

public class Detail_Teams_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    List<DataClassTeams> dataListTeams;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    PlayerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setVisibility(View.GONE);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(Detail_Teams_Activity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(Detail_Teams_Activity.this);
        builder.setCancelable(false);
        builder .setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        dataListTeams = new ArrayList<>();

        adapter = new PlayerAdapter(Detail_Teams_Activity.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("CoachITPlayers" );
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
                dialog.dismiss();}

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

            public void searchList(String text){
                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    for (DataClassTeams dataClassTeams: dataListTeams) {
                        if(dataClassTeams.getDataTeamName().toLowerCase().contains(dataClass.getDataTeam().toLowerCase()) ){
                            System.out.print(dataClassTeams.getDataTeamName());
                        }
                    }

                }


                adapter.searchDataList(searchList);


            }
        });








    }










}