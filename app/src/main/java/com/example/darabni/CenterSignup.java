package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import de.hdodenhof.circleimageview.CircleImageView;

public class CenterSignup extends AppCompatActivity {
    CircleImageView profile;
    String usernameData,userphoneData,useremailData,userpasswordData;
    BootstrapEditText centerName,centerPhone,centerLicence,centerAddress;
    String nameCenter,phoneCenter,licenceCenter,addressCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_signup);
        // Create mirror object for age and training type
        centerName=(BootstrapEditText)findViewById(R.id.centername);
        centerPhone=(BootstrapEditText)findViewById(R.id.centerphone);
        centerLicence=(BootstrapEditText)findViewById(R.id.centerlicence);
        centerAddress=(BootstrapEditText)findViewById(R.id.centeraddress);
        // Via this intent object we get all default sign up  information
        Intent getSignupdata=getIntent();
        usernameData=getSignupdata.getStringExtra("uname");
        userphoneData=getSignupdata.getStringExtra("uphone");
        useremailData=getSignupdata.getStringExtra("uemail");
        userpasswordData=getSignupdata.getStringExtra("upass");
    }
    public void changeCenterImage(View view) {
        //this method allow users to change the center default image
        Toast.makeText(this, "Pick image from storage", Toast.LENGTH_SHORT).show();
     }

    public void continueSignupAsCenter(View view) {
        // This method allow Center admin users to complete registration operation
        // Now after we get information from the previous screen
        // In this method we try to get the complement data from this screen
        // Then we trying to add a new record to the data base
        //nameCenter,phoneCenter,licenceCenter,addressCenter
        nameCenter=centerName.getText().toString();
        phoneCenter=centerPhone.getText().toString();
        licenceCenter=centerLicence.getText().toString();
        addressCenter=centerAddress.getText().toString();
        if(nameCenter.matches("")&&phoneCenter.matches("")
        &&licenceCenter.matches("")&&addressCenter.matches("")){
            Toast.makeText(this, "All data Are Required", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sign up done", Toast.LENGTH_SHORT).show();
            finish();
        }
     }
}