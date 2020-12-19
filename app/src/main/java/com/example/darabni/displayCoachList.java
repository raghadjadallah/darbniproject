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

public class displayCoachList extends AppCompatActivity {
    ListView coachList;
    DrawerLayout screendrawer;
    ArrayList<Bitmap> coachProfileImage;
    ArrayList<String>coachNames,coachTrainingType,coachPhones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_coach_list);
        coachList=(ListView)findViewById(R.id.list2);
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
        ArrayAdapter coachListAdapter=new ArrayAdapter(displayCoachList.this
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
                        (displayCoachList.this,ShowCoachInfo.class);
                startActivity(moveToSeeCoachFullInfo);
            }
        });

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
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (3 ) CenterNeedSupport
    public void CenterNeedSupport (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (4 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
}