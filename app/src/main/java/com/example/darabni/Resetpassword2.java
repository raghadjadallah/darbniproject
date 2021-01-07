package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;

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

public class Resetpassword2 extends AppCompatActivity {
    BootstrapEditText code,pass,cpass;
    String vemail,vcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword2);
        Intent intent=getIntent();
        vemail=intent.getStringExtra("mail");
        vcode=intent.getStringExtra("vcode");
        code=(BootstrapEditText)findViewById(R.id.codetext);
        pass=(BootstrapEditText)findViewById(R.id.newpass);
        cpass=(BootstrapEditText)findViewById(R.id.confirmpass);
    }
    public void CancelReset(View view) {
        // Via this method we cancel the reset operation and back to enter email/phone screen
        code.setText(null);
        pass.setText(null);
        cpass.setText(null);
        finish();
    }
    public void ConfirmPassChange(View view) {
        // via this method we going to complete password changing and reset
        if(code.getText().toString().matches("")){
            Toast.makeText(this, "Enter The Verification code", Toast.LENGTH_SHORT).show();
        }else {
            if(pass.getText().toString().matches("")&&cpass.getText().toString().matches("")){
                Toast.makeText(this, "Enter A Valid password value", Toast.LENGTH_SHORT).show();
            }else {
                if(pass.getText().toString().equals(cpass.getText().toString())){
                    // now if the new password and confirm password is equals
                    // then we will check if the entered code is correct
                    //then if the code is correct we will reset the password
                    // other wise we Send A massage to tell user to check the entered value for code
                    if(code.getText().toString().equals(vcode)){
                        ParseQuery<ParseObject>findusers=ParseQuery.getQuery("Trainee");
                        findusers.whereEqualTo("email",vemail);
                        findusers.getFirstInBackground(new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject object, ParseException e) {
                                if(object!=null && e==null){
                                    object.put("password",cpass.getText().toString());
                                    object.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if(e==null){
                                                finish();
                                            }
                                        }
                                    });
                                }else{
                                    // search on center tabel
                                    ParseQuery<ParseObject>findusers2=ParseQuery.getQuery("Center");
                                    findusers2.whereEqualTo("owneremail",vemail);
                                    findusers2.getFirstInBackground(new GetCallback<ParseObject>() {
                                        @Override
                                        public void done(ParseObject object2, ParseException e2) {
                                            if(object2!=null && e2==null){
                                                object2.put("password",cpass.getText().toString());
                                                object2.saveInBackground(new SaveCallback() {
                                                    @Override
                                                    public void done(ParseException ex) {
                                                        if(ex==null){
                                                            finish();
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }

                }else {
                    // here we tell the user to edit the password and confirm password
                    // to be matches
                    Toast.makeText(this, "Please check the password values", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}