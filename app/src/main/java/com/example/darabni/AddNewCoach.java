package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddNewCoach extends AppCompatActivity {
    DrawerLayout screendrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_coach);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerAddCoach);
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

    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){

    }
    // (3 ) CenterNeedSupport
    public void CenterNeedSupport (View view){

    }
    // (4 ) AdminClickLogout
    public void AdminClickLogout (View view){

    }

    public void CompleteAddingCoach(View view) {
        // via this method we going to enter the new car information
        Intent move2=new Intent(AddNewCoach.this,AddNewCar.class);
        startActivity(move2);
    }
}