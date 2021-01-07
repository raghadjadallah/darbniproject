package com.example.darabni;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class parseDataServer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("myappID")
                .clientKey("myhhwJE2ydyx")
                .server("http://ec2-3-138-142-192.us-east-2.compute.amazonaws.com/parse/")
                .build());
        //Parse.enableLocalDatastore(this);
        ParseUser.enableAutomaticUser();
    }
}
