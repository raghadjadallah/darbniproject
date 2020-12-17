package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuperVisorMainScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_visor_main_screen);

    }
    public void SeeLastReviews(View view) {
        // with this method we move to another activity to display the problem design
        Intent moveToReviewList=new Intent(SuperVisorMainScreen.this,ReviewsList.class);
        startActivity(moveToReviewList);
    }
    public void SeeCenterInfo(View view) {
        // with this method we move to another activity to display the available center in the
        // application
        Intent moveTocenterInfoList=new Intent(SuperVisorMainScreen.this,CenterInfoScreen.class);
        startActivity(moveTocenterInfoList);
    }
}