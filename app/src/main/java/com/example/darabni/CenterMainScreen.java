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
    public static String objectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_main_screen);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerCenterMain);
        Intent rec=getIntent();
        objectId=rec.getStringExtra("oid");
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
        closeDrawer(screendrawer);
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Intent move=new Intent(CenterMainScreen.this,AddNewCoach.class);
        startActivity(move);
    }
    // (3 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Intent backTomain=new Intent(CenterMainScreen.this,MainActivity.class);
        startActivity(backTomain);
    }
    public void goToseeLastRequest(View view) {
        Intent moveToSeeRequest = new Intent(CenterMainScreen.this, SeeLastRequest.class);
        startActivity(moveToSeeRequest);
    }

    public void goToSeeCoachInfo(View view) {
        Intent moveToSeeCoach=new Intent
                (CenterMainScreen.this,displayCoachList.class);
        startActivity(moveToSeeCoach);
    }
}