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

public class CarInfo extends AppCompatActivity {
    CircleImageView imageView;
    TextView manufactor,type,gear,model;
    DrawerLayout screendrawer;
    public void getCarInfo(String carid){
        ParseQuery<ParseObject>carQuery=ParseQuery.getQuery("Car");
        carQuery.whereEqualTo("objectId",carid);
        carQuery.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null&&object!=null){
                    ParseFile image=object.getParseFile("image");
                    image.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if (e == null && data != null) {
                                Bitmap map= BitmapFactory.decodeByteArray(data,0,data.length);
                                imageView.setImageBitmap(map);
                            }
                        }
                    });
                    manufactor.setText(object.getString("manufactor"));
                    type.setText(object.getString("modelname"));
                    model.setText(object.getString("model"));
                    gear.setText(object.getString("gear"));
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerCarInfo);
        imageView=(CircleImageView)findViewById(R.id.carimage);
        manufactor=(TextView)findViewById(R.id.carmanufactor);
        type=(TextView)findViewById(R.id.carname);
        gear=(TextView)findViewById(R.id.cargear);
        model=(TextView)findViewById(R.id.carmodel);
        getCarInfo(ShowCoachInfo.carId);
    }

    public void goToSubmitRequest(View view) {
        /*
           by click on next button the application move us to new screen
           that allow user to send a training request
         */
        Intent moveToSendRequest=new Intent
                (CarInfo.this,sendRequest.class);
        moveToSendRequest.putExtra("dir","custom");
        startActivity(moveToSendRequest);

    }

    public void cancelCarInfoView(View view) {
        /*
          back to previous screen
         */
        finish();
    }
    //Drawer Mange Methods
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
    // All Following method that connected with drawer menu items
    // (1) Home Item
    public void HomeItemClicked(View view){
        Intent backtoHome = new Intent(CarInfo.this,MainTrainerScreen.class);
        startActivity(backtoHome);
    }
    // (2) SeeMyRequest
    public void SendSpecialRequest (View view){
        Intent moveTosend=new Intent(CarInfo.this,sendRequest.class);
        moveTosend.putExtra("dir","random");
        startActivity(moveTosend);
    }
    // (3) ClickAboutUS
    public void ClickAboutUS (View view){
        Intent about=new Intent(CarInfo.this,AboutUs.class);
        startActivity(about);
    }
    // (4) ClickLogout
    public void ClickLogout (View view){
        Intent backTomain=new Intent(CarInfo.this,MainActivity.class);
        startActivity(backTomain);
    }
}