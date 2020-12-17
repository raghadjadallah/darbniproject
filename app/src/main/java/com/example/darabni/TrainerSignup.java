package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.beardedhen.androidbootstrap.BootstrapEditText;
public class TrainerSignup extends AppCompatActivity {
    BootstrapEditText userAge;
    String gender="",trainingTypeOption="";
    RadioButton male,female;
    Spinner trainingType;
    String usernameData,userphoneData,useremailData,userpasswordData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_signup);
        // Create mirror object for age and training type
        male=(RadioButton)findViewById(R.id.Rbtn_male);
        female=(RadioButton)findViewById(R.id.Rbtn_female);
        userAge=(BootstrapEditText)findViewById(R.id.age);
        trainingType=(Spinner)findViewById(R.id.spn_trainType2);
        // Via this intent object we get all default sign up  information
        Intent getSignupdata2=getIntent();
        usernameData=getSignupdata2.getStringExtra("uname");
        userphoneData=getSignupdata2.getStringExtra("uphone");
        useremailData=getSignupdata2.getStringExtra("uemail");
        userpasswordData=getSignupdata2.getStringExtra("upass");
    }

    public void continueSignupAsTrainer(View view) {
        // This method allow trainer users to complete registration operation
        // Now after we get information from the previous screen
        // In this method we try to get the complement data from this screen
        // Then we trying to add a new record to the data base
    }
    public void ChangeTrainerImage(View view) {
        // This method allow trainer to change him profile image
    }
    public void genderSelection(View view) {
        //Via this method we assign a value for gender variable
        int id =view.getId();
        if(id == R.id.Rbtn_male){
            if(male.isChecked()){
                gender="Male";
            }
        }else {
            if (female.isChecked()){
                gender="Female";
            }
        }
    }
}