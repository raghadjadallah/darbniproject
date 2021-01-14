package com.example.darabni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class searchForCoach extends AppCompatActivity {
    ListView coachList;
    DrawerLayout screendrawer;
    ArrayList<Bitmap>coachProfileImage;
    ArrayList<String>coachNames,coachSessionCost,coachPhones,coachId;
    ArrayAdapter coachListAdapter;
    public static String centerId;
    public void getoachInfo (String id){
        ParseQuery<ParseObject> coachQuery=ParseQuery.getQuery("Coach");
        coachQuery.whereEqualTo("centerid",id);
        coachQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects!=null&&objects.size()>0&&e==null){
                    for (ParseObject coach:objects){
                        coachNames.add("Name : "+coach.getString("name"));
                        coachPhones.add("Phone : "+coach.getString("phone"));
                        coachSessionCost.add("Session Cost : "+coach.getString("cost"));
                        coachId.add(coach.getObjectId());
                        coachListAdapter.notifyDataSetChanged();
                    }
                }else if(objects.size()==0){
                    Toast.makeText(searchForCoach.this,"No Coah Available",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_coach);
        /*
        Define array list and fill the object
         */
        Intent ine=getIntent();
        centerId=ine.getStringExtra("centerid");
        getoachInfo(centerId);
        coachProfileImage=new ArrayList<Bitmap>();
        coachNames=new ArrayList<String>();
        coachSessionCost=new ArrayList<String>();
        coachPhones=new ArrayList<String>();
        coachId=new ArrayList<String>();
        //**********************************************************
        screendrawer=(DrawerLayout)findViewById(R.id.drawerCoach);
        coachList=(ListView)findViewById(R.id.list2);
        coachListAdapter=new ArrayAdapter(searchForCoach.this
                ,R.layout.coachlistdesign,R.id.coachListName,coachNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view3= super.getView(position, convertView, parent);
                TextView cost=(TextView)view3.findViewById(R.id.coachListTraining);
                TextView phone=(TextView)view3.findViewById(R.id.coachListPhone);
                cost.setText(coachSessionCost.get(position));
                phone.setText(coachPhones.get(position));
                return view3;
            }
        };
        coachList.setAdapter(coachListAdapter);
        /*
        In this screen we  will display coach info and when click on one coach
        we going to move the user to another screen
        in the target screen we will display all coach information
         */
        coachList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveToSeeCoachFullInfo=new Intent
                        (searchForCoach.this,ShowCoachInfo.class);
                moveToSeeCoachFullInfo.putExtra("coachid",coachId.get(position));
                startActivity(moveToSeeCoachFullInfo);
            }
        });

    }    //Drawer Mange Methods
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
        Intent backtoHome = new Intent(searchForCoach.this,MainTrainerScreen.class);
        startActivity(backtoHome);
    }
    // (2) SeeMyRequest
    public void SendSpecialRequest (View view){
        Intent moveTosend=new Intent(searchForCoach.this,sendRequest.class);
        moveTosend.putExtra("dir","random");
        startActivity(moveTosend);
    }
    // (3) ClickAboutUS
    public void ClickAboutUS (View view){
        Intent about=new Intent(searchForCoach.this,AboutUs.class);
        startActivity(about);
    }
    // (4) ClickLogout
    public void ClickLogout (View view){
        Intent backTomain=new Intent(searchForCoach.this,MainActivity.class);
        startActivity(backTomain);
    }
}