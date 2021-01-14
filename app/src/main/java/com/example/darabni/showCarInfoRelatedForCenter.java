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

public class showCarInfoRelatedForCenter extends AppCompatActivity {
    CircleImageView imageView;
    TextView manufactor,type,gear,model;
    DrawerLayout screendrawer;
    public void getMyCarInfo(String carid){
        ParseQuery<ParseObject> carQuery=ParseQuery.getQuery("Car");
        carQuery.whereEqualTo("objectId",carid);
        carQuery.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(object!=null && e==null){
                    ParseFile image=object.getParseFile("image");
                    image.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if(e==null && data !=null){
                                Bitmap map= BitmapFactory.decodeByteArray
                                        (data,0,data.length);
                                imageView.setImageBitmap(map);
                            }
                        }
                    });
                    manufactor.setText("Manufactor :"+object.getString("manufactor"));
                    type.setText("Type : "+object.getString("modelname"));
                    model.setText("Model : "+object.getString("model"));
                    gear.setText("GearBox : "+object.getString("gear"));
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car_info_related_for_center);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerCarInfo);
        imageView=(CircleImageView)findViewById(R.id.carimage);
        manufactor=(TextView)findViewById(R.id.carmanufactor);
        type=(TextView)findViewById(R.id.carname);
        gear=(TextView)findViewById(R.id.cargear);
        model=(TextView)findViewById(R.id.carmodel);
        getMyCarInfo(showCoachInfoRelatedForCenter.carIdRelated2);
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
    public void CenterAdminHome(View view){
        Intent i=new Intent(showCarInfoRelatedForCenter.this,CenterMainScreen.class);
        startActivity(i);
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Intent move=new Intent(showCarInfoRelatedForCenter.this,AddNewCoach.class);
        startActivity(move);
    }
    // (3 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Intent backTomain=new Intent(showCarInfoRelatedForCenter.this,MainActivity.class);
        startActivity(backTomain);
    }

    public void cancelCarInfoView(View view) {
        finish();
    }
}