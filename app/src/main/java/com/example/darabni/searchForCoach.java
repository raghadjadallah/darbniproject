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

import java.util.ArrayList;

public class searchForCoach extends AppCompatActivity {
    ListView coachList;
    DrawerLayout screendrawer;
    ArrayList<Bitmap>coachProfileImage;
    ArrayList<String>coachNames,coachTrainingType,coachPhones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_coach);
        /*
        Define array list and fill the object
         */
        coachProfileImage=new ArrayList<Bitmap>();
        coachNames=new ArrayList<String>();
        coachTrainingType=new ArrayList<String>();
        coachPhones=new ArrayList<String>();
        //assign values
        coachNames.add("Raed Ayman AL-abadi");
        coachTrainingType.add("Personal cars");
        coachPhones.add("0787877001");
        //**********************************************************
        screendrawer=(DrawerLayout)findViewById(R.id.drawerCoach);
        coachList=(ListView)findViewById(R.id.list2);
        ArrayAdapter coachListAdapter=new ArrayAdapter(searchForCoach.this
                ,R.layout.coachlistdesign,R.id.coachListName,coachNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view3= super.getView(position, convertView, parent);
                TextView type=(TextView)view3.findViewById(R.id.coachListTraining);
                TextView phone=(TextView)view3.findViewById(R.id.coachListPhone);
                type.setText(coachTrainingType.get(position));
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
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (2) Update Account Info
    public void UpdateAccountInfo(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (3 ) SeeMyRequest
    public void SeeMyRequest (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (4 ) SeeMyRequest
    public void SendSpecialRequest (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (5 ) ClickAboutUS
    public void ClickAboutUS (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (6 ) ClickLogout
    public void ClickLogout (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
}