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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class SearchForCenter extends AppCompatActivity {
    Spinner fillter;
    ListView centerlist;
    DrawerLayout screenDrawer;
    String fillterOption="null";
    ArrayList<String>centerNames,centerAddress,centerPhones;
    ArrayList<Bitmap>centerImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_center);
        screenDrawer=(DrawerLayout)findViewById(R.id.drawerCenterSearch);
        /*
        Define Array List
         */
        centerImages=new ArrayList<Bitmap>();
        centerNames=new ArrayList<String>();
        centerAddress=new ArrayList<String>();
        centerPhones=new ArrayList<String>();
        /*
        Assign Value for array list for examples
         */
        centerNames.add("Heros Center");
        centerAddress.add("Amman");
        centerPhones.add("065338585");
        //In this screen we going to fetch all center information from server
        // and display it on the list view
        // and we have a drop down menu that allow the user do
        // special query (filter the result depending on city name and center address)
        fillter=(Spinner)findViewById(R.id.spin);
        fillter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fillterOption=fillter.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fillterOption="null";
            }
        });
        /*
        when the user select an item from the spinner the query for fetching data
        will change automatically
         */
        centerlist=(ListView)findViewById(R.id.listcenter);
        ArrayAdapter centerListAdapter=new ArrayAdapter(SearchForCenter.this
                ,R.layout.centerlistdesign,R.id.centerListName,centerNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view2= super.getView(position, convertView, parent);
                TextView address=(TextView)view2.findViewById(R.id.centerListAddress);
                TextView phone=(TextView)view2.findViewById(R.id.centerphone);
                address.setText(centerAddress.get(position));
                phone.setText(centerPhones.get(position));
               return view2;
            }
        };
        centerlist.setAdapter(centerListAdapter);
        /*
        When the user click on specific center the application move the user
        to new screen for display coach list in special condition that is
        all coach will displayed on next screen
        must be associated with the selected center
         */
       centerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent moveToCoachSearching=new Intent
                       (SearchForCenter.this,searchForCoach.class);
               startActivity(moveToCoachSearching);
           }
       });
    }
    //Drawer Mange Methods
    public void onMenuClicked(View view) {
        openDrawer(screenDrawer);
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