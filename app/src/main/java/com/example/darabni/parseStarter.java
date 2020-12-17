package com.example.darabni;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
public class parseStarter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                .clientKey("YNB8vrqGhYDF")
                .server("http://ec2-3-131-85-178.us-east-2.compute.amazonaws.com/parse/")
                .build()
        );
        /*ParseObject obj=new ParseObject("Test");
        obj.put("Key","123");
        obj.put("name","jasser");
        obj.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(getApplicationContext(), "Go Jasser", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        ParseUser.enableAutomaticUser();
        ParseACL defo=new ParseACL();
        defo.setPublicReadAccess(true);
        defo.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defo,true);
    }
}