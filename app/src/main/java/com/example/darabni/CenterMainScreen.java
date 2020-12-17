package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CenterMainScreen extends AppCompatActivity {
    DrawerLayout screendrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_main_screen);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerCenterMain);
    }
    //Drawer Mange Methods
    public void onMenuClicked(View view) {
        openDrawer(screendrawer);
    }
    //This method do the opening drawer operation
    private static void openDrawer(DrawerLayout draw) {
        draw.openDrawer(GravityCompat.START);
    }
    //This method do the closing drawer operation
    private static void closeDrawer(DrawerLayout draw) {
        if(draw.isDrawerOpen(GravityCompat.START)){
            draw.closeDrawer(GravityCompat.START);
        }
    }
    // All Following method that connected with drawer menu items
    // (1) Home Item
    public void CenterAdminHome(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (3 ) CenterNeedSupport
    public void CenterNeedSupport (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (4 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }

    public void goToseeLastRequest(View view) {
     Intent moveToSeeRequest=new Intent(CenterMainScreen.this,SeeLastRequest.class);
     startActivity(moveToSeeRequest);
    }

    public void goToSeeCoachInfo(View view) {
        Intent moveToSeeCoach=new Intent
                (CenterMainScreen.this,searchForCoach.class);
        startActivity(moveToSeeCoach);
    }
}