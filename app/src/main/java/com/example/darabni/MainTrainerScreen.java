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

public class MainTrainerScreen extends AppCompatActivity {
    CircleImageView userimage;
    TextView name,phone,mail;
    DrawerLayout drawerMenuForScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trainer_screen);
                userimage=(CircleImageView)findViewById(R.id.profile_image);
                name=(TextView)findViewById(R.id.username) ;
                phone=(TextView)findViewById(R.id.userphone) ;
                mail=(TextView)findViewById(R.id.usermail) ;
                drawerMenuForScreen=(DrawerLayout)findViewById(R.id.mainTrainerDrawer);
    }

    public void editUserInfo(View view) {
        // with method we allow the user to edit there own information
        // by moving to new screen called dataModifiedScreen via intent object
        Intent moveToEditScreen=new Intent(MainTrainerScreen.this,ModifyTrainerInfo.class);
        moveToEditScreen.putExtra("uname",name.getText().toString());
        moveToEditScreen.putExtra("uphone",phone.getText().toString());
        moveToEditScreen.putExtra("uemail",mail.getText().toString());
        startActivity(moveToEditScreen);
    }
    //Drawer Mange Methods
    public void onMenuClicked(View view) {
        openDrawer(drawerMenuForScreen);
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

    public void StartSearchingforCoach(View view) {
        Toast.makeText(this, "whats Happened", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainTrainerScreen.this
                ,CenterListItem.class);
        startActivity(intent);
    }
}