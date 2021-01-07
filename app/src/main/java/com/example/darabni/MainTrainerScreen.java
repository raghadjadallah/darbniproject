package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainTrainerScreen extends AppCompatActivity {
    CircleImageView userimage;
    TextView name,phone,mail;
    DrawerLayout drawerMenuForScreen;
    public static String userId,userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trainer_screen);
        Intent getdata=getIntent();
        if (this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("oid")){
            String a=getdata.getStringExtra("oid");
            userId=a;
            //get data from server
            ParseQuery<ParseObject>userData=ParseQuery.getQuery("Trainee");
            userData.whereEqualTo("objectId",userId);
            userData.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    name.setText(object.getString("username"));
                    phone.setText(object.getString("phone"));
                    mail.setText(object.getString("email"));
                    userEmail=object.getString("email");
                    ParseFile image=object.getParseFile("image");
                    image.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if(e==null && data !=null){
                                Bitmap map= BitmapFactory.decodeByteArray
                                        (data,0,data.length);
                                userimage.setImageBitmap(map);
                            }
                        }
                    });
                }
            });

        } else {
            // Do something else
            onStart();
        }
        userimage=(CircleImageView)findViewById(R.id.profile_image);
                name=(TextView)findViewById(R.id.username) ;
                phone=(TextView)findViewById(R.id.userphone) ;
                mail=(TextView)findViewById(R.id.usermail) ;
        drawerMenuForScreen=(DrawerLayout)findViewById(R.id.mainTrainerDrawer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ParseQuery<ParseObject>userData=ParseQuery.getQuery("Trainee");
        userData.whereEqualTo("objectId",userId);
        userData.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                name.setText(object.getString("username"));
                phone.setText(object.getString("phone"));
                mail.setText(object.getString("email"));
                userEmail=object.getString("email");
                ParseFile image=object.getParseFile("image");
                image.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        if(e==null && data !=null){
                            Bitmap map= BitmapFactory.decodeByteArray
                                    (data,0,data.length);
                            userimage.setImageBitmap(map);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ParseQuery<ParseObject>userData=ParseQuery.getQuery("Trainee");
        userData.whereEqualTo("objectId",userId);
        userData.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                name.setText(object.getString("username"));
                phone.setText(object.getString("phone"));
                mail.setText(object.getString("email"));
                userEmail=object.getString("email");
                ParseFile image=object.getParseFile("image");
                image.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        if(e==null && data !=null){
                            Bitmap map= BitmapFactory.decodeByteArray
                                    (data,0,data.length);
                            userimage.setImageBitmap(map);
                        }
                    }
                });
            }
        });
    }

    public void editUserInfo(View view) {
        // with method we allow the user to edit there own information
        // by moving to new screen called dataModifiedScreen via intent object
        Intent moveToEditScreen=new Intent(MainTrainerScreen.this,ModifyTrainerInfo.class);
        moveToEditScreen.putExtra("uname",name.getText().toString());
        moveToEditScreen.putExtra("uphone",phone.getText().toString());
        moveToEditScreen.putExtra("uemail",mail.getText().toString());
        startActivity(moveToEditScreen);
    }
    //Drawer Mange Methods
    public void onMenuClicked(View view) {
        openDrawer(drawerMenuForScreen);
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
    // All Following method that connected with drawer menu items
    // (1) Home Item
    public void HomeItemClicked(View view){
        Intent backtoHome = new Intent(MainTrainerScreen.this,MainTrainerScreen.class);
        startActivity(backtoHome);
    }
    // (2) SeeMyRequest
    public void SendSpecialRequest (View view){
        Intent moveTosend=new Intent(MainTrainerScreen.this,sendRequest.class);
        moveTosend.putExtra("dir","random");
        startActivity(moveTosend);
    }
    // (3) ClickAboutUS
    public void ClickAboutUS (View view){
       Intent about=new Intent(MainTrainerScreen.this,AboutUs.class);
       startActivity(about);
    }
    // (4) ClickLogout
    public void ClickLogout (View view){
       Intent backTomain=new Intent(MainTrainerScreen.this,MainActivity.class);
       startActivity(backTomain);
    }

    public void StartSearchingforCoach(View view) {
        //Toast.makeText(this, "whats Happened", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainTrainerScreen.this
                ,CenterListItem.class);
        startActivity(intent);
    }
}