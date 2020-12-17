package com.example.darabni;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CenterInfoScreen extends AppCompatActivity {
    ListView centerListInfo;
    ArrayList<Bitmap>centerImage;
    ArrayList<String>centerName;
    ArrayList<String>centerAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_info_screen);
           centerListInfo=(ListView)findViewById(R.id.list6);
           // as soon as possible the above list view will connected with item click listener
          // when the admin click on item (center) we going to move to new screen
          // that allow the admin to manage the centers accounts
         //Define Array List
        centerImage=new ArrayList<Bitmap>();
        centerName=new ArrayList<String>();
        centerAddress=new ArrayList<String>();
        //Assign Value
        centerName.add("Salt Center");
        centerAddress.add("As-Salt city center");
        // Create Adapter
        ArrayAdapter centerInfoList=new ArrayAdapter(CenterInfoScreen.this
                ,R.layout.centerinfolistdesign,R.id.centerNameText,centerName){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                TextView address=(TextView)view.findViewById(R.id.centerAddressText);
                address.setText(centerAddress.get(position));
                return view;
            }
        };
        centerListInfo.setAdapter(centerInfoList);
        // Define Click listener for list view
        centerListInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                Now when user click on center name we passing the object id of center
                and in the next screen we going to do query
                to fetch all selected center info
                 */
                Intent moveToManageScreen=new Intent
                        (CenterInfoScreen.this,manageCenter.class);
                startActivity(moveToManageScreen);
            }
        });
    }
}