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
        public void SeeCustomizedRequest(View view) {
        //via this method we see the request info
        Intent moveToReqestList=new Intent
                (SeeLastRequest.this,requestList.class);
        moveToReqestList.putExtra("key","custom");
        startActivity(moveToReqestList);
    }

    public void seeRandomRequest(View view) {
        //also via this method we will see the request info but just thr random request
        // the different between the to method is the displayed requests
        Intent moveToReqestList=new Intent
                (SeeLastRequest.this,requestList.class);
        moveToReqestList.putExtra("key","random");
        startActivity(moveToReqestList);
    }
}