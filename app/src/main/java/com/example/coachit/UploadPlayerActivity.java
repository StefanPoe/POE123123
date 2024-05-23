package com.example.coachit;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadPlayerActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;
    EditText uploadName , uploadSurname , uploadNickname , uploadAge , uploadTeam, uploadCity,uploadCoach,uploadDomhand,uploadSex,uploadContact1,uploadContact2,uploadHeight,uploadWeight,uploadWingspan,uploadVertical,uploadPosition;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_player);

        uploadImage=findViewById(R.id.uploadImage);
        saveButton=findViewById(R.id.saveButton);
        uploadName=findViewById(R.id.uploadName);
        uploadSurname=findViewById(R.id.uploadSurname);
        uploadNickname=findViewById(R.id.uploadNickname);
        uploadAge=findViewById(R.id.uploadAge);
        uploadSex=findViewById(R.id.uploadSex);
        uploadCity=findViewById(R.id.uploadCity);
        uploadTeam=findViewById(R.id.uploadTeam);
        uploadCoach=findViewById(R.id.uploadCoach);
        uploadPosition=findViewById(R.id.uploadPosition);
        uploadHeight=findViewById(R.id.uploadHeight);
        uploadWeight=findViewById(R.id.uploadWeight);
        uploadDomhand=findViewById(R.id.uploadDomhand);
        uploadWingspan=findViewById(R.id.uploadWingspan);
        uploadVertical=findViewById(R.id.uploadVertical);
        uploadContact1=findViewById(R.id.uploadContact1);
        uploadContact2=findViewById(R.id.uploadContact2);



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);

                        }else{
                            Toast.makeText(UploadPlayerActivity.this, "No Image Selected" , Toast.LENGTH_SHORT);

                        }
                    }
                }


        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });



    }

    public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadPlayerActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();


        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });

    }

    public void uploadData(){

        String name = uploadName.getText().toString();
        String surname = uploadSurname.getText().toString();
        String nickname = uploadNickname.getText().toString();
        String age = uploadAge.getText().toString();
        String team = uploadSex.getText().toString();
        String sex = uploadCity.getText().toString();
        String vertical = uploadTeam.getText().toString();
        String city = uploadCoach.getText().toString();
        String wingspan = uploadPosition.getText().toString();
        String weight = uploadHeight.getText().toString();
        String domhand = uploadWeight.getText().toString();
        String coach = uploadDomhand.getText().toString();
        String contact2 = uploadWingspan.getText().toString();
        String contact1 = uploadVertical.getText().toString();
        String position = uploadContact1.getText().toString();
        String height = uploadContact2.getText().toString();




        DataClass dataClass = new DataClass(name, surname, nickname, age,sex,city,team,coach,position,height,weight,domhand,wingspan,vertical,contact1,contact2, imageURL);

        FirebaseDatabase.getInstance().getReference("CoachITPlayers").child(name)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UploadPlayerActivity.this, "Saved" , Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadPlayerActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT);
                    }
                });

    }
}