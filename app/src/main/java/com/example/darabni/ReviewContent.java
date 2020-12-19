package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReviewContent extends AppCompatActivity {
    TextView centerName,problemType,problemDescription;
    String resiverCenterName;
    String problemTypeText;
    String problemDescriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_content);
        //here we will receive data from the first screen and display it there
        centerName=(TextView)findViewById(R.id.ttv1);
        problemType=(TextView)findViewById(R.id.ttv2);
        problemDescription=(TextView)findViewById(R.id.ttv3);
        /*
        Store value for screen
         */
        resiverCenterName="AS-Salt Center";
        problemTypeText="Quality Problem";
        problemDescriptionText="The Car for This Center is so bad and dirty";
        //There we will fill the screen with required data
        centerName.setText(resiverCenterName);
        problemType.setText(problemTypeText);
        problemDescription.setText(problemDescriptionText);
       }
    public void process(View view) {
        // with this method we going to write code
        // To deal with problem information
        Toast.makeText(this, "move to center management screen ", Toast.LENGTH_SHORT).show();
    }
}