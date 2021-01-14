package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class sendRequest extends AppCompatActivity {
    DrawerLayout screendrawer;
    String directions,trainingTime="null";
    BootstrapEditText notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent recivedata=getIntent();
        directions=recivedata.getStringExtra("dir");
        setContentView(R.layout.activity_send_request);
        notes=(BootstrapEditText)findViewById(R.id.requestnote);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerRequest);
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
    public void HomeItemClicked(View view){
        Intent backtoHome = new Intent(sendRequest.this,MainTrainerScreen.class);
        startActivity(backtoHome);
    }
    // (2) SeeMyRequest
    public void SendSpecialRequest (View view){
        Intent moveTosend=new Intent(sendRequest.this,sendRequest.class);
        moveTosend.putExtra("dir","random");
        startActivity(moveTosend);
    }
    // (3) ClickAboutUS
    public void ClickAboutUS (View view){
        Intent about=new Intent(sendRequest.this,AboutUs.class);
        startActivity(about);
    }
    // (4) ClickLogout
    public void ClickLogout (View view){
        Intent backTomain=new Intent(sendRequest.this,MainActivity.class);
        startActivity(backTomain);
    }
    public void cancelSendRequest(View view) {
        // via this method we just cancel the request sending operation and back to
        // the previous screen
        finish();
    }

    public void onRequestSend(View view) {
        /*
        via this method we go to send the request to the selected center
         */
        if(directions.equals("random")){
            ParseObject randomRequest=new ParseObject("RandomRequest");
            randomRequest.put("userid",MainTrainerScreen.userId);
            randomRequest.put("time",trainingTime);
            randomRequest.put("notes",notes.getText().toString());
            randomRequest.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Intent bebo=new Intent(sendRequest.this,MainTrainerScreen.class);
                        startActivity(bebo);
                    }
                }
            });
        }else if(directions.equals("custom")){
        ParseObject request=new ParseObject("Request");
        request.put("userid",MainTrainerScreen.userId);
        request.put("centerid",searchForCoach.centerId);
        request.put("coachid",ShowCoachInfo.coachId);
        request.put("carid",ShowCoachInfo.carId);
        request.put("time",trainingTime);
        request.put("notes",notes.getText().toString());
        request.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Intent bebo=new Intent(sendRequest.this,MainTrainerScreen.class);
                    startActivity(bebo);
                }
            }
        });
    }
    }
    public void onTimeSelect(View view) {
        CheckBox checkBox1=(CheckBox)findViewById(R.id.time1);
        CheckBox  checkBox2=(CheckBox)findViewById(R.id.time2);
        CheckBox  checkBox3=(CheckBox)findViewById(R.id.time3);
        CheckBox  checkBox4=(CheckBox)findViewById(R.id.time4);
        int id=view.getId();
        if(id==R.id.time1){
            trainingTime="Morning";
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
        } else if(id==R.id.time2){
            trainingTime="Evening";
            checkBox1.setChecked(false);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
        }else if(id==R.id.time3){
            trainingTime="After Noon";
            checkBox1.setChecked(false);
            checkBox2.setChecked(false);
            checkBox4.setChecked(false);
        }else if(id==R.id.time4){
            trainingTime="All Times";
            checkBox1.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
        }else {
            trainingTime=null;
        }
    }
}