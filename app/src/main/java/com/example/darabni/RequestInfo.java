package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
public class RequestInfo extends AppCompatActivity {
    String userID,notes,time,massageContent="null",name , phone;
    String coachInformation,carInformation;
    BootstrapEditText massage;
    DrawerLayout screendrawer;
    public void getCustomRequestInformation(String id, String t, String n,String coach,String car){
        ParseQuery<ParseObject> testo =ParseQuery.getQuery("Trainee");
        testo.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null && object!=null){
                    name=object.getString("username");
                    phone=object.getString("phone");
                    massageContent="Hello my name is "+object.getString("username")+" and i'am looking to get " +
                            "Driving Licence Training as soon as possible and i leave my phone number " +
                            "to  Contact with me  \n "+"my phone :"+object.getString("phone")+"\n  and i hope to get Training " +
                            "session in This Time : ("+t+") \n"+
                            "With this Coach ("+coachInformation+") Using this Car ("+carInformation+") \n"
                            +"Notes :  \n"+n;

                    massage.setText(massageContent);

                }
            }

        });
    }
    public void getRandomRequestSenderInformation(String id, String t, String n){

        ParseQuery<ParseObject> testo =ParseQuery.getQuery("Trainee");
        testo.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null && object!=null){
                    name=object.getString("username");
                    phone=object.getString("phone");
                    massageContent="Hello my name is "+object.getString("username")+" and i'am looking to get " +
                            "Driving Licence Training as soon as possible and i leave my phone number " +
                            "to  Contact with me  \n "+"my phone :"+object.getString("phone")+"\n  and i hope to get Training " +
                            "session in "+t+"\n"+"Notes :  \n"+n;
                    phone=object.getString("phone");
                    massage.setText(massageContent);
                }
            }
        });
    }
    public void onMenuClicked(View view) {
        openDrawer(screendrawer);
    }
    //This method do the opening drawer operation
    private static void openDrawer(DrawerLayout draw) {
        draw.openDrawer(GravityCompat.START);
    }
    //This method do the closing drawer operation
    private static void closeDrawer(DrawerLayout draw) {
        if(draw.isDrawerOpen(GravityCompat.START)){
            draw.closeDrawer(GravityCompat.START);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);
        massage=(BootstrapEditText)findViewById(R.id.jasserTextBit);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerRquestinfo);
        Intent geto=getIntent();
        String id=geto.getStringExtra("rid");
        String direct=geto.getStringExtra("dir");
        if(direct.equals("custom")){
            getCustomRequest(id);
        }else {
            getRandomRequest(id);
        }
    }
    // All Following method that connected with drawer menu items
    // (1) Home Item
    public void CenterAdminHome(View view){
        Intent intent=new Intent(RequestInfo.this,CenterMainScreen.class);
        startActivity(intent);
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Intent move=new Intent(RequestInfo.this,AddNewCoach.class);
        startActivity(move);
    }
    // (3 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Intent backTomain=new Intent(RequestInfo.this,MainActivity.class);
        startActivity(backTomain);
    }
    private void getRandomRequest(String problemId) {
        ParseQuery<ParseObject>getterDate=ParseQuery.getQuery("RandomRequest");
        getterDate.getInBackground(problemId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject req, ParseException e) {
                if(e==null && req!=null){
                    userID=req.getString("userid");
                    notes=req.getString("notes");
                    time=req.getString("time");
                    getRandomRequestSenderInformation(req.getString("userid"),time,notes);
                }else {
                    massage.setText(e.getMessage());
                }
            }
        });
    }
    private void getCustomRequest(String problemId) {
        ParseQuery<ParseObject>getterDate=ParseQuery.getQuery("Request");
        getterDate.getInBackground(problemId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null && object!=null){
                    userID=object.getString("userid");
                    notes=object.getString("notes");
                    time=object.getString("time");
                    ParseQuery<ParseObject>coach=ParseQuery.getQuery("Coach");
                    coach.getInBackground(object.getString("coachid"), new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject coach, ParseException e) {
                            //get the Coach name and
                            if(coach!=null && e==null){
                                coachInformation=coach.getString("name")+"\t"+coach.getString("phone");

                            }
                        }
                    });
                    ParseQuery<ParseObject>car=ParseQuery.getQuery("Car");
                    car.getInBackground(object.getString("carid"), new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject caro, ParseException e) {
                            //get the Car name and
                            carInformation=caro.getString("manufactor")+"\t"+
                                    caro.getString("modelname")+"\t"+
                                    caro.getString("model");
                            getCustomRequestInformation(object.getString("userid"),time,notes,coachInformation,carInformation);
                        }
                    });
                }else {
                    massage.setText(e.getMessage());
                }
            }
        });
    }
    public void backToReviewList(View view) {
        finish();
    }
    public void Call_This_User(View view) {
        callPhoneNumber();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber();
            }
        }
    }
    public void callPhoneNumber()
    {
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RequestInfo.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);

            }
            else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}