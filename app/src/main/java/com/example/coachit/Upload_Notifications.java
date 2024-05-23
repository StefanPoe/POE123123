package com.example.coachit;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Upload_Notifications extends AppCompatActivity {

    Button sendButton;
    EditText uploadTitle,uploadMessage,uploadFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notifications);

        uploadTitle = findViewById(R.id.uploadTitle);
        uploadMessage = findViewById(R.id.uploadMessage);
        uploadFrom = findViewById(R.id.uploadFrom);

        sendButton = findViewById(R.id.sendButton);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(Upload_Notifications.this,
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Upload_Notifications.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
                makeNotification();
            }
        });
    }

    public void makeNotification()
    {
        String chanelID="CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),chanelID);
        builder.setSmallIcon(R.drawable.baseline_notifications_active_24)
       .setContentTitle("NEW NOTIFICATION FROM COACH")
        .setContentText("Your coach sent you a new notification")
                .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), NotificationActivity_2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data" , "Some val to be passed here");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        NotificationChannel notificationChannel =
                notificationManager.getNotificationChannel(chanelID);
        if (notificationChannel == null){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            notificationChannel = new NotificationChannel(chanelID,
                    "Some description", importance);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }

        notificationManager.notify(0, builder.build());

    }
    public void sendData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Upload_Notifications.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        uploadData();
        dialog.dismiss();
    }

    public void uploadData(){
        String title = uploadTitle.getText().toString();
        String message = uploadMessage.getText().toString();
        String from = uploadFrom.getText().toString();

        DataClassNotifications dataClassNotifications = new DataClassNotifications(title,message,from);

        FirebaseDatabase.getInstance().getReference("Notifications").child(title)
                .setValue(dataClassNotifications).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Upload_Notifications.this, "Saved" , Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Upload_Notifications.this, e.getMessage().toString() , Toast.LENGTH_SHORT).show();

                    }
                });

    }
}