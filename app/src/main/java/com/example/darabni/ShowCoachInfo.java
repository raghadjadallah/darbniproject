package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
public class ShowCoachInfo extends AppCompatActivity {
    CircleImageView profile;
    TextView name,phone,address,cost,type;
    DrawerLayout screendarwer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coach_info);
        screendarwer=(DrawerLayout)findViewById(R.id.drawerCoachInfo);
        profile=(CircleImageView)findViewById(R.id.coachimage);
        name=(TextView)findViewById(R.id.coachname);
        phone=(TextView)findViewById(R.id.coachphone);
        address=(TextView)findViewById(R.id.coachaddress);
        cost=(TextView)findViewById(R.id.coachcost);
        type=(TextView)findViewById(R.id.coachtype);
    }
    public void cancelCoachInfoView(View view) {
        // via this method we just back to the coach list
        finish();
    }
    public void viewCarInfo(View view) {
        /*
         via this method we cross forward to new screen that allow users to see
         car information before send training request
         */
        Intent moveToCarInfo=new Intent(ShowCoachInfo.this,CarInfo.class);
        startActivity(moveToCarInfo);
    }
    //Drawer Mange Methods
    public void onMenuClicked(View view) {
        openDrawer(screendarwer);
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
    public void HomeItemClicked(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (2) Update Account Info
    public void UpdateAccountInfo(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (3 ) SeeMyRequest
    public void SeeMyRequest (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (4 ) SeeMyRequest
    public void SendSpecialRequest (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (5 ) ClickAboutUS
    public void ClickAboutUS (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (6 ) ClickLogout
    public void ClickLogout (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
}