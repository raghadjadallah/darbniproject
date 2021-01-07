package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
public class ShowCoachInfo extends AppCompatActivity {
    CircleImageView profile;
    TextView name,phone,address,cost;
    DrawerLayout screendarwer;
    public static String coachId,carId;
    public void getFullInfo(String id){
        ParseQuery<ParseObject> coachInfo=ParseQuery.getQuery("Coach");
        coachInfo.whereEqualTo("objectId",id);
        coachInfo.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null&&object!=null){
                    ParseFile image=object.getParseFile("image");
                    image.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if(e==null && data !=null){
                                Bitmap map= BitmapFactory.decodeByteArray
                                        (data,0,data.length);
                                profile.setImageBitmap(map);
                            }
                        }
                    });
                    name.setText(object.getString("name"));
                    phone.setText(object.getString("phone"));
                    address.setText(object.getString("address"));
                    cost.setText(object.getString("cost"));
                    carId=object.getParseObject("car").getObjectId();
                    Log.i("car",carId);
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coach_info);
        screendarwer=(DrawerLayout)findViewById(R.id.drawerCoachInfo);
        profile=(CircleImageView)findViewById(R.id.coachimage);
        name=(TextView)findViewById(R.id.coachname);
        phone=(TextView)findViewById(R.id.coachphone);
        address=(TextView)findViewById(R.id.coachaddress);
        cost=(TextView)findViewById(R.id.coachcost);
        Intent inme=getIntent();
        coachId=inme.getStringExtra("coachid");
        getFullInfo(coachId);
    }
    public void cancelCoachInfoView(View view) {
        // via this method we just back to the coach list
        finish();
    }
    public void viewCarInfo(View view) {
        /*
         via this method we cross forward to new screen that allow users to see
         car information before send training request
         */
        Intent moveToCarInfo=new Intent(ShowCoachInfo.this,CarInfo.class);

        startActivity(moveToCarInfo);
    }
    //Drawer Mange Methods
    public void onMenuClicked(View view) {
        openDrawer(screendarwer);
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
        Intent backtoHome = new Intent(ShowCoachInfo.this,MainTrainerScreen.class);
        startActivity(backtoHome);
    }
    // (2) SeeMyRequest
    public void SendSpecialRequest (View view){
        Intent moveTosend=new Intent(ShowCoachInfo.this,sendRequest.class);
        moveTosend.putExtra("dir","random");
        startActivity(moveTosend);
    }
    // (3) ClickAboutUS
    public void ClickAboutUS (View view){
        Intent about=new Intent(ShowCoachInfo.this,AboutUs.class);
        startActivity(about);
    }
    // (4) ClickLogout
    public void ClickLogout (View view){
        Intent backTomain=new Intent(ShowCoachInfo.this,MainActivity.class);
        startActivity(backTomain);
    }
}