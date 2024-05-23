package com.example.coachit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailNotification extends AppCompatActivity {

    TextView detailTitle, detailFrom , detailMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

        detailTitle = findViewById(R.id.detailTitle);
        detailFrom = findViewById(R.id.detailFrom);
        detailMessage = findViewById(R.id.detailMessage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailTitle.setText(bundle.getString("Title"));
            detailMessage.setText(bundle.getString("Message"));
            detailFrom.setText(bundle.getString("From"));
        }
    }
}