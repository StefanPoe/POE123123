package com.example.coachit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_Notifications extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClassNotifications> dataClassNotificationsList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notifications);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(View_Notifications.this, 1 );
        recyclerView.setLayoutManager(gridLayoutManager);


        AlertDialog.Builder builder = new AlertDialog.Builder(View_Notifications.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataClassNotificationsList = new ArrayList<>();
        NotificationsAdapter adapter = new NotificationsAdapter(View_Notifications.this, dataClassNotificationsList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Notifications");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataClassNotificationsList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){

                    DataClassNotifications dataClassNotifications = itemSnapshot.getValue(DataClassNotifications.class);
                    dataClassNotificationsList.add(dataClassNotifications);

                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();

            }
        });
    }
}