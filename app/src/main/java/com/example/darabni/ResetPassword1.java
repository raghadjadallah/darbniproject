package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Random;

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
                Random random=new Random();
                int num=random.nextInt(999999);
                String code=String.format("%06d",num);
                // if the condition is true that means we will send the code via email
                BackgroundMail bm=new BackgroundMail(ResetPassword1.this);
                bm.setGmailUserName("abeerkamel4646@gmail.com");
                bm.setGmailPassword("4646Abeer");
                 bm.setMailTo(codeResiver);
                 bm.setFormSubject("verification code For Reset password");
                 bm.setFormBody("this is the verification code"+"to reset your password in application : "+code+"");
                 bm.send();
                ParseObject parseObject=new ParseObject("Verification");
                parseObject.put("reciveremail",codeResiver);
                parseObject.put("code",code);
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            Intent moveToNext=new Intent(ResetPassword1.this,Resetpassword2.class);
                            moveToNext.putExtra("mail",codeResiver);
                            moveToNext.putExtra("vcode",code);
                            startActivity(moveToNext);
                        }
                    }
                });
                 Intent moveToComplete=new Intent(ResetPassword1.this,Resetpassword2.class);
                 moveToComplete.putExtra("rmail",codeResiver);
                 //In this place we will write some code to save the verification cod in server
                startActivity(moveToComplete);
            }else{
                // that's mean the code will sent via sms
            }
            Intent moveTOConfirmPassReset=new Intent(ResetPassword1.this,Resetpassword2.class);
            startActivity(moveTOConfirmPassReset);
        }
    }
}