package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import com.beardedhen.androidbootstrap.BootstrapEditText;
public class Signup extends AppCompatActivity {
    BootstrapEditText username,useremail,userpassword,userphone;
    RadioButton asUseroption,asCenteroption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Create mirror object for all screen content
        username =(BootstrapEditText)findViewById(R.id.newnanme);
        useremail=(BootstrapEditText)findViewById(R.id.newemail);
        userphone=(BootstrapEditText)findViewById(R.id.newphone);
        userpassword=(BootstrapEditText)findViewById(R.id.newpass);
        asUseroption=(RadioButton)findViewById(R.id.Rbtn_user);
        asCenteroption=(RadioButton)findViewById(R.id.Rbtn_cente);
    }

    public void cancelSignup(View view) {
        // In this method the sign up will back to its default state
        // and back to the first screen
        username.setText(null);
        useremail.setText(null);
        userphone.setText(null);
        userpassword.setText(null);
        asCenteroption.setChecked(false);
        asUseroption.setChecked(false);
        finish();
    }

    public void continueSignup(View view) {
        // if the user decided to complete sign up operation and click continue via this method
        // we will get data such as (username , email , password , phone  ) and move it to next screen
        // to complete the operation either as center or trainer
        String name , pass , email , phone;
        name=username.getText().toString();
        pass=userpassword.getText().toString();
        email=useremail.getText().toString();
        phone=userphone.getText().toString();
        if (name.matches("") || pass.matches("") || email.matches("") || phone.matches("")){
            //now we will checked for all data if there is no data we will display a massage for user
            // other wise we will move the user to complete Sign up screen
            Toast.makeText(this, "Dear Customer All data are Required ", Toast.LENGTH_SHORT).show();
        }else{
            // if the user fill the required data
            // now we will redirect him depending into the selected registration option
            if(asUseroption.isChecked()){
                // now if the user decided to sign up as user this junk of code will run
                //This code have one functionality that this code will send the data to next screen
                //To complete registration
                Intent moveToTrainerSignup=new Intent(Signup.this,TrainerSignup.class);
                moveToTrainerSignup.putExtra("uname",name);
                moveToTrainerSignup.putExtra("uphone",phone);
                moveToTrainerSignup.putExtra("upass",pass);
                moveToTrainerSignup.putExtra("uemail",email);
                startActivity(moveToTrainerSignup);
            }else if (asCenteroption.isChecked()){
                // now if the user decided to sign up as center admin  this junk of code will run
                //This code have one functionality that this code will send the data to next screen
                //To complete registration
                Intent moveToCenterSignup=new Intent(Signup.this,CenterSignup.class);
                moveToCenterSignup.putExtra("uname",name);
                moveToCenterSignup.putExtra("uphone",phone);
                moveToCenterSignup.putExtra("upass",pass);
                moveToCenterSignup.putExtra("uemail",email);
                startActivity(moveToCenterSignup);
            }
            else{
                // other wise this massage will displayed if the user dose not select any type
                // of account options
                Toast.makeText(this, "Please select the type of you Account ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}