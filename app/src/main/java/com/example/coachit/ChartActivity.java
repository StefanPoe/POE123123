package com.example.coachit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class ChartActivity extends AppCompatActivity {
    EditText xValue , yValue;
    Button insertBtn;
    LineChart lineChart ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        lineChart = findViewById(R.id.lineChart);
        insertBtn = findViewById(R.id.insertBtn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("ChartValues");
        insertData();


        lineDataSet.setLineWidth(4);





    }

    private void insertData() {

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = Integer.parseInt(xValue.getText().toString());
               int temp = x+1;
                String id = myRef.push().getKey();
                int y = Integer.parseInt(yValue.getText().toString());


                DataPoint dataPoint = new DataPoint(x,y);
                myRef.child(id).setValue(dataPoint);
                xValue.setText(String.valueOf(temp) );


                retriveData();

            }

        });
    }

    private void retriveData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> dataVals = new ArrayList<Entry>();

                if (snapshot.hasChildren()){
                    for (DataSnapshot myDataSnapshot : snapshot.getChildren()){

                        DataPoint dataPoint = myDataSnapshot.getValue(DataPoint.class);
                                dataVals.add(new Entry(dataPoint.getxValue() , dataPoint.getyValue()));

                    }
                    showChart(dataVals);
                }else{
                    lineChart.clear();
                    lineChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {

        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("DataSet 1");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();


    }


}