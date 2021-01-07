package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    public void backToHome(View view) {
        Intent move=new Intent(AboutUs.this,MainTrainerScreen.class);
        startActivity(move);
    }
}