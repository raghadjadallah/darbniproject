package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
public class ModifyTrainerInfo extends AppCompatActivity {
    BootstrapEditText name,email,phone;
    DrawerLayout screenDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_trainer_info);
        Intent dataReceiver=getIntent();
        String rname,rphone,rmail;
        rname=dataReceiver.getStringExtra("uname");
        rphone=dataReceiver.getStringExtra("uphone");
        rmail=dataReceiver.getStringExtra("umail");
        name=(BootstrapEditText)findViewById(R.id.txt_name);
        phone=(BootstrapEditText)findViewById(R.id.txt_phone);
        email=(BootstrapEditText)findViewById(R.id.txt_email);
        screenDrawer=(DrawerLayout)findViewById(R.id.modifyTrainerDrawer);
        name.setText(rname);
        phone.setText(rphone);
        email.setText(rmail);
    }

    public void cancelModifyOperation(View view) {
        //in this method we going to cancel the modify data operation and back to the
        // previous page
        name.setText(null);
        phone.setText(null);
        email.setText(null);
        finish();
    }
    public void modifyOperation(View view) {
        // in this method we going to confirm the modify data operation
        String newName,newPhone,newEmail;
        newName=name.getText().toString();
        newPhone=phone.getText().toString();
        newEmail=email.getText().toString();
        /*
        if the update information operation done successfully we print massage and
        back to previous screen .
        * */
        Toast.makeText(this, "Update info not Activate yet", Toast.LENGTH_SHORT).show();
    }
    //Drawer Mange Methods
    public void onMenuClicked(View view) {
        openDrawer(screenDrawer);
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