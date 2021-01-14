package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

import static com.example.darabni.searchForCoach.centerId;

public class ReviewContent extends AppCompatActivity {
    TextView centerName,problemType,problemDescription;
    String resiverCenterName,centerPhone,centerId;
    String problemTypeText;
    String problemDescriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_content);
        //here we will receive data from the first screen and display it there
        centerName=(TextView)findViewById(R.id.ttv1);
        problemType=(TextView)findViewById(R.id.ttv2);
        problemDescription=(TextView)findViewById(R.id.ttv3);
        //There we will fill the screen with required data
        Intent to=getIntent();
        problemTypeText=to.getStringExtra("type");
        problemDescriptionText=to.getStringExtra("desc");
        centerId=to.getStringExtra("center");
        ParseQuery<ParseObject>qrt=ParseQuery.getQuery("Center");
        qrt.whereEqualTo("objectId",centerId);
        qrt.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (object != null && e == null) {
                    resiverCenterName=object.getString("centername");
                    centerPhone=object.getString("phone");
                    centerName.setText(resiverCenterName);
                    problemType.setText(problemTypeText);
                    problemDescription.setText(problemDescriptionText);
                }
            }
        });
     }
    public void process(View view) {
        Intent moveToManageScreen=new Intent
                (ReviewContent.this,manageCenter.class);
        moveToManageScreen.putExtra("cid",centerId);
        moveToManageScreen.putExtra("cphone",centerPhone);
        moveToManageScreen.putExtra("cname",resiverCenterName);
        startActivity(moveToManageScreen);
    }
}