package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;

public class ResetPassword1 extends AppCompatActivity {
    BootstrapEditText emailOrphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password1);
        emailOrphone=(BootstrapEditText)findViewById(R.id.mainorphone);
    }
    public void cancelResetPass(View view) {
        // Via this method the user will canceled the reset password operation
        // And back to the previous screen
        emailOrphone.setText(null);
        finish();
    }
    public void SendVerificationCode(View view) {
        // via this method we will get the email or phone
        // And send a code for user via either sms or email
        // Then move forward to second Reset Password Screen
        String codeResiver=emailOrphone.getText().toString();
        if(codeResiver.matches("")){
            Toast.makeText(this, "Please Enter email or Phone", Toast.LENGTH_SHORT).show();
        }else {
            if(codeResiver.contains("@")||codeResiver.contains(".com")){
                // if the condition is true that means we will send the code via email
            }else{
                // that's mean the code will sent via sms
            }
            Intent moveTOConfirmPassReset=new Intent(ResetPassword1.this,Resetpassword2.class);
            startActivity(moveTOConfirmPassReset);
        }
    }
}