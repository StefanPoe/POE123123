package com.example.coachit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView detailName ,detailSurname,detailNickname,detailAge,detailSex,detailCity,detailCoach,detailDomhand,detailHeight,detailWeight,detailWingspan,detailVertical,detailContact1,detailContact2,detailPosition,detailTeam;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailSurname = findViewById(R.id.detailSurname);
        detailNickname = findViewById(R.id.detailNickname);
        detailAge = findViewById(R.id.detailAge);
        detailSex = findViewById(R.id.detailSex);
        detailCity = findViewById(R.id.detailCity);
        detailCoach = findViewById(R.id.detailCoach);
        detailDomhand = findViewById(R.id.detailDomhand);
        detailHeight = findViewById(R.id.detailHeight);
        detailWeight = findViewById(R.id.detailWeight);
        detailWingspan = findViewById(R.id.detailWingspan);
        detailVertical = findViewById(R.id.detailVertical);
        detailContact1 = findViewById(R.id.detailContact1);
        detailContact2 = findViewById(R.id.detailContact2);
        detailPosition = findViewById(R.id.detailPosition);
        detailTeam = findViewById(R.id.detailTeam);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);

            detailName.setText(bundle.getString("Name"));
            detailSurname.setText(bundle.getString( "Surname"));
            detailNickname.setText(bundle.getString("Nickname"));
            detailAge.setText(bundle.getString("Age"));
            detailSex.setText(bundle.getString("Sex"));
            detailCity.setText("City :  "+bundle.getString("City"));
            detailTeam.setText("Team :  "+bundle.getString("Team"));
            detailCoach.setText("Coach :  "+bundle.getString("Coach"));
            detailPosition.setText("Position :  "+bundle.getString("Position"));
            detailHeight.setText("Height :  "+ bundle.getString("Height") + "  CM");
            detailWeight.setText("Weight :  "+bundle.getString("Weight") + "  CM");
            detailDomhand.setText("Dominant Hand / Foot :  "  +bundle.getString("Domhand"));
            detailWingspan.setText("Wingspan :  "+bundle.getString("Wingspan")+ "  CM");
            detailVertical.setText("Vertical :  "+bundle.getString("Vertical")+ "  CM");
            detailContact1.setText("Phone Number :  "+bundle.getString("Contact1"));
            detailContact2.setText("Email :  "+bundle.getString("Contact2"));


        }


    }
}