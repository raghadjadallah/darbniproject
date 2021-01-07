package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class ModifyTrainerInfo extends AppCompatActivity {
    BootstrapEditText name,email,phone;
    DrawerLayout screenDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_trainer_info);
        Intent dataReceiver=getIntent();
        String rname,rphone;
        rname=dataReceiver.getStringExtra("uname");
        rphone=dataReceiver.getStringExtra("uphone");
        name=(BootstrapEditText)findViewById(R.id.txt_name);
        phone=(BootstrapEditText)findViewById(R.id.txt_phone);
        email=(BootstrapEditText)findViewById(R.id.txt_email);
        screenDrawer=(DrawerLayout)findViewById(R.id.modifyTrainerDrawer);
        name.setText(rname);
        phone.setText(rphone);
        email.setText(MainTrainerScreen.userEmail);
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
        ParseQuery<ParseObject> userData= ParseQuery.getQuery("Trainee");
        userData.whereEqualTo("objectId",MainTrainerScreen.userId);
        userData.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(object!=null&&e==null){
                    object.put("username",newName);
                    object.put("phone",newPhone);
                    object.put("email",newEmail);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null){
                                finish();
                            }
                        }
                    });
                }
            }
        });
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
        Intent backtoHome = new Intent(ModifyTrainerInfo.this,MainTrainerScreen.class);
        startActivity(backtoHome);
    }
    // (2) SeeMyRequest
    public void SendSpecialRequest (View view){
        Intent moveTosend=new Intent(ModifyTrainerInfo.this,sendRequest.class);
        moveTosend.putExtra("dir","random");
        startActivity(moveTosend);
    }
    // (3) ClickAboutUS
    public void ClickAboutUS (View view){
        Intent about=new Intent(ModifyTrainerInfo.this,AboutUs.class);
        startActivity(about);
    }
    // (4) ClickLogout
    public void ClickLogout (View view){
        Intent backTomain=new Intent(ModifyTrainerInfo.this,MainActivity.class);
        startActivity(backTomain);
    }
}