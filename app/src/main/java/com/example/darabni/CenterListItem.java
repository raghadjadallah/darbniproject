package com.example.darabni;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class CenterListItem extends AppCompatActivity {
    ListView centerlist;
    DrawerLayout screenDrawer;
    Spinner fillterOptionList;
    String fillterOption="null";
    ArrayList<String> centerNames,centerAddress,centerPhones,centerId;
    ArrayList<Bitmap>centerImages;
    ArrayAdapter arrayAdapter,arrayAdapter2;
    public void getCenterInfo(String fillter){
        if(fillter.equals("All City")){
            centerId.clear();
            centerAddress.clear();
            centerNames.clear();
            centerPhones.clear();
            ParseQuery<ParseObject>centerQuery=ParseQuery.getQuery("Center");
            centerQuery.whereEqualTo("activation",true);
            centerQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null&objects!=null&&objects.size()>0){
                        for(ParseObject center:objects){
                            //get Names ,address,id,phone
                            centerId.add(center.getObjectId().toString());
                            centerNames.add("Center name :"+center.getString("centername"));
                            centerAddress.add("Address : "+center.getString("address"));
                            centerPhones.add("Phone : "+center.getString("phone"));
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }else {
            String bodyOfQuery =fillter;
            centerId.clear();
            centerAddress.clear();
            centerNames.clear();
            centerPhones.clear();
            ParseQuery<ParseObject> centerQuery = ParseQuery.getQuery("Center");
            centerQuery.whereEqualTo("activation", true);
            centerQuery.whereEqualTo("address", bodyOfQuery);
            centerQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null & objects != null && objects.size() > 0) {
                        for (ParseObject center : objects) {
                            //get Names ,address,id,phone
                            centerId.add(center.getObjectId().toString());
                            centerNames.add(center.getString("centername"));
                            centerAddress.add(center.getString("address"));
                            centerPhones.add(center.getString("phone"));
                            arrayAdapter.notifyDataSetChanged();
                        }
                    } else if (objects.size() == 0) {
                        centerId.clear();
                        centerAddress.clear();
                        centerNames.clear();
                        centerPhones.clear();
                        Toast.makeText(CenterListItem.this, "There is no Center in" + fillter, Toast.LENGTH_SHORT).show();
                        getCenterInfo("All City");

                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_list_item);
        screenDrawer=(DrawerLayout)findViewById(R.id.drawercenterlist2);
                /*
        Define Array List
         */
        //IGnore this comment
        centerImages=new ArrayList<Bitmap>();
        centerNames=new ArrayList<String>();
        centerAddress=new ArrayList<String>();
        centerPhones=new ArrayList<String>();
        centerId=new ArrayList<String>();
        //In this screen we going to fetch all center information from server
        // and display it on the list view
        // and we have a drop down menu that allow the user do
        // special query (filter the result depending on city name and center address)
        //getCenterInfo(fillterOption);
        fillterOptionList=(Spinner)findViewById(R.id.fillterList);
        String [] city_name=getResources().getStringArray(R.array.City);
        arrayAdapter2=new ArrayAdapter
                (CenterListItem.this, android.R.layout.simple_list_item_1,city_name);
        fillterOptionList.setAdapter(arrayAdapter2);
        fillterOptionList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fillterOption=city_name[position];
                centerId.clear();
                centerAddress.clear();
                centerNames.clear();
                centerPhones.clear();
                getCenterInfo(fillterOption);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                centerId.clear();
                centerAddress.clear();
                centerNames.clear();
                centerPhones.clear();
                fillterOption="All City";
            }
        });
        /*
        when the user select an item from the spinner the query for fetching data
        will change automatically
         */
        centerlist=(ListView) findViewById(R.id.listcenteritemview);
        //Set the Adapter
        arrayAdapter=new ArrayAdapter(CenterListItem.this
                ,R.layout.detailedcenterlistitem,R.id.item1,centerNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view2= super.getView(position, convertView, parent);
                TextView address=(TextView)view2.findViewById(R.id.item2);
                TextView phone=(TextView)view2.findViewById(R.id.item3);
                address.setText(centerAddress.get(position));
                phone.setText(centerPhones.get(position));
                return view2;
            }
        };
        centerlist.setAdapter(arrayAdapter);
        // implement method for on click listener
        /*When the user click on specific center the application move the user
        to new screen for display coach list in special condition that is
        all coach will displayed on next screen
        must be associated with the selected center
        */
        centerlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveToCoachSearching=new Intent
                        (CenterListItem.this,searchForCoach.class);
                moveToCoachSearching.putExtra("centerid",centerId.get(position));
                startActivity(moveToCoachSearching);
            }
        });
        centerlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(CenterListItem.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you Sure ?")
                        .setMessage("Are you Need to Send Problem Report Againest this center ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent moveToSendReport=new Intent
                                        (CenterListItem.this,problems.class);
                                moveToSendReport.putExtra("centerid",centerId.get(i));
                                startActivity(moveToSendReport);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // here we don't do any thing
                    }
                }).show();
                return false;
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
        Intent backtoHome = new Intent(CenterListItem.this,MainTrainerScreen.class);
        startActivity(backtoHome);
    }
    // (2) SeeMyRequest
    public void SendSpecialRequest (View view){
        Intent moveTosend=new Intent(CenterListItem.this,sendRequest.class);
        moveTosend.putExtra("dir","random");
        startActivity(moveTosend);
    }
    // (3) ClickAboutUS
    public void ClickAboutUS (View view){
        Intent about=new Intent(CenterListItem.this,AboutUs.class);
        startActivity(about);
    }
    // (4) ClickLogout
    public void ClickLogout (View view){
        Intent backTomain=new Intent(CenterListItem.this,MainActivity.class);
        startActivity(backTomain);
    }
}