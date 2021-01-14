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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CenterInfoScreen extends AppCompatActivity {
    ListView centerListInfo;
    ArrayList<String>centerName, centerAddress, centerId, centerPhone;
    ArrayAdapter centerInfoList;

    public void getCenterInformation(){
        ParseQuery<ParseObject>allCenter=ParseQuery.getQuery("Center");
        allCenter.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&&objects!=null&&objects.size()>0){
                    for(ParseObject center:objects){
                        centerId.add(center.getObjectId());
                        centerName.add(center.getString("centername"));
                        centerAddress.add(center.getString("address"));
                        centerPhone.add(center.getString("phone"));
                        centerInfoList.notifyDataSetChanged();
                    }

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_info_screen);
           centerListInfo=(ListView)findViewById(R.id.list6);
           // as soon as possible the above list view will connected with item click listener
          // when the admin click on item (center) we going to move to new screen
          // that allow the admin to manage the centers accounts
         //Define Array List
        centerName=new ArrayList<String>();
        centerAddress=new ArrayList<String>();
        centerId=new ArrayList<String>();
        centerPhone=new ArrayList<String>();
        // Create Adapter
        centerInfoList=new ArrayAdapter(CenterInfoScreen.this
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
                moveToManageScreen.putExtra("cid",centerId.get(position));
                moveToManageScreen.putExtra("cphone",centerPhone.get(position));
                moveToManageScreen.putExtra("cname",centerName.get(position));
                startActivity(moveToManageScreen);
            }
        });
        getCenterInformation();
    }
}