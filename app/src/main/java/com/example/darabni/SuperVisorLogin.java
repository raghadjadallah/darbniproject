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

public class SuperVisorLogin extends AppCompatActivity {
    BootstrapEditText susername,spassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_visor_login);
        susername=(BootstrapEditText)findViewById(R.id.supername);
        spassword=(BootstrapEditText)findViewById(R.id.superpass);
    }
    public void CancelSuperVisorLogin(View view) {
        // Via this method we cancel the supervisor login
        // and back to the previous screen
        susername.setText(null);
        spassword.setText(null);
    }
    public void CompleteSuperVisorLogin(View view) {
        // with this method we going to complete supervisor login
        String username,pasword;
        username=susername.getText().toString();
        pasword=spassword.getText().toString();
        if (username.matches("") || pasword.matches("")){
            Toast.makeText(this, "Please Enter valid data", Toast.LENGTH_SHORT).show();
        }else {
            ParseQuery<ParseObject>loginQuery=ParseQuery.getQuery("Admin");
            loginQuery.whereEqualTo("username",username);
            loginQuery.whereEqualTo("password",pasword);
            loginQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException ex) {
                    if(object!=null&&ex==null){
                        Intent move=new Intent(SuperVisorLogin.this,SuperVisorMainScreen.class);
                        startActivity(move);
                    }else {
                        Toast.makeText(SuperVisorLogin.this, "Error While Login", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}