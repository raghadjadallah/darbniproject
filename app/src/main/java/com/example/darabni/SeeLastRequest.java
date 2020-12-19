package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class SeeLastRequest extends AppCompatActivity {
    DrawerLayout screendrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_last_request);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerRequestReview);
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

    public void SeeCustomizedRequest(View view) {
        //via this method we see the request info
        Intent moveToReqestList=new Intent
                (SeeLastRequest.this,requestList.class);
        startActivity(moveToReqestList);
    }

    public void seeRandomRequest(View view) {
        //also via this method we will see the request info but just thr random request
        // the different between the to method is the displayed requests
        Intent moveToReqestList=new Intent
                (SeeLastRequest.this,requestList.class);
        startActivity(moveToReqestList);
    }
}