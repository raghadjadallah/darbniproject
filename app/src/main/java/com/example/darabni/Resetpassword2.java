package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
public class Resetpassword2 extends AppCompatActivity {
    BootstrapEditText code,pass,cpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword2);
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
        if(pass.getText().toString().equals(cpass.getText().toString())){
            // now if the new password and confirm password is equals
            // then we will check if the entered code is correct
                //then if the code is correct we will reset the password
                // other wise we Send A massage to tell user to check the entered value for code
            Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            // here we tell the user to edit the password and confirm password
            // to be matches
            Toast.makeText(this, "Please check the password values", Toast.LENGTH_SHORT).show();
        }
    }
}