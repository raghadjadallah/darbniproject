package com.example.darabni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
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

public class displayCoachList extends AppCompatActivity {
    ListView coachList;
    DrawerLayout screendrawer;
    ArrayAdapter coachListAdapter2;
    ArrayList<String>coachNames1,coachSessionCost1,coachPhones1,coachId1;
    public void getCoachList(String logincenterId){
        ParseQuery<ParseObject> coachQuery=ParseQuery.getQuery("Coach");
        coachQuery.whereEqualTo("centerid",logincenterId);
        coachQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects!=null && e==null){
                    for(ParseObject coach:objects){
                        coachNames1.add(coach.getString("name"));
                        coachPhones1.add(coach.getString("phone"));
                        coachSessionCost1.add(coach.getString("cost"));
                        coachId1.add(coach.getObjectId());
                        coachListAdapter2.notifyDataSetChanged();
                    }
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_coach_list);
        coachList=(ListView)findViewById(R.id.list2);
                /*
        Define array list and fill the object
         */
        coachNames1=new ArrayList<String>();
        coachSessionCost1=new ArrayList<String>();
        coachPhones1=new ArrayList<String>();
        coachId1=new ArrayList<String>();
        screendrawer=(DrawerLayout)findViewById(R.id.drawerCoach);
        getCoachList(CenterMainScreen.objectId);
        coachListAdapter2 =new ArrayAdapter(displayCoachList.this
                ,R.layout.coachlistdesign,R.id.coachListName,coachNames1){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view3= super.getView(position, convertView, parent);
                TextView type=(TextView)view3.findViewById(R.id.coachListTraining);
                TextView phone=(TextView)view3.findViewById(R.id.coachListPhone);
                type.setText(coachSessionCost1.get(position));
                phone.setText(coachPhones1.get(position));
                return view3;
            }
        };
        coachList.setAdapter(coachListAdapter2);
        /*
        In this screen we  will display coach info and when click on one coach
        we going to move the user to another screen
        in the target screen we will display all coach information
         */
        coachList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveToSeeCoachFullInfo1=new Intent
                        (displayCoachList.this,showCoachInfoRelatedForCenter.class);
                moveToSeeCoachFullInfo1.putExtra("coachid",coachId1.get(position));
                startActivity(moveToSeeCoachFullInfo1);
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
        Intent i=new Intent(displayCoachList.this,CenterMainScreen.class);
        startActivity(i);
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Intent move=new Intent(displayCoachList.this,AddNewCoach.class);
        startActivity(move);
    }
    // (3 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Intent backTomain=new Intent(displayCoachList.this,MainActivity.class);
        startActivity(backTomain);
    }
}