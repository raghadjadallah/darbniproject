package com.example.darabni;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class requestList extends AppCompatActivity {
    ListView request;
    ArrayList<String>senderNames,senderPhone,requestId;
    DrawerLayout screendrawer;
    String directions;
    public static ArrayAdapter reviewListAdapter;
    public void getUserInfo(String objectId){
        ParseQuery<ParseObject>user=ParseQuery.getQuery("Trainee");
        user.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null && object!=null){
                    senderNames.add(object.getString("username"));
                    senderPhone.add(object.getString("phone"));
                    reviewListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    public void getCustomRequest(){
        ParseQuery<ParseObject>custom=ParseQuery.getQuery("Request");
        custom.whereEqualTo("centerid",CenterMainScreen.objectId);
        custom.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects!=null && e==null){
                    for(ParseObject req:objects){
                        requestId.add(req.getObjectId());
                        getUserInfo(req.getString("userid"));
                    }
                }
            }
        });
    }
    public void getRandomRequest(){
        ParseQuery<ParseObject>go=ParseQuery.getQuery("RandomRequest");
        go.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects!=null && e==null){
                    for(ParseObject req:objects){
                        requestId.add(req.getObjectId());
                        getUserInfo(req.getString("userid"));
                    }
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        request=(ListView)findViewById(R.id.requestListView);
        screendrawer=(DrawerLayout)findViewById(R.id.drawer11);
        senderNames=new ArrayList<String>();
        senderPhone =new ArrayList<String>();
        requestId=new ArrayList<String>();
        if (this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("key")){
            Intent getter=getIntent();
            directions =getter.getStringExtra("key");
            if(directions.equals("custom")){
                getCustomRequest();
            }else {
                getRandomRequest();
            }
        }
        reviewListAdapter=new ArrayAdapter(requestList.this,R.layout.requestlistdesign
                ,R.id.tvn,senderNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                TextView date=(TextView)view.findViewById(R.id.tvadd);
                date.setText(senderPhone.get(position));
                return view;
            }
        };
        request.setAdapter(reviewListAdapter);
        request.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent reqInfo=new Intent(requestList.this,RequestInfo.class);
                reqInfo.putExtra("rid",requestId.get(position));
                reqInfo.putExtra("dir",directions);
                startActivity(reqInfo);
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
        Intent intent=new Intent(requestList.this,CenterMainScreen.class);
        startActivity(intent);
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Intent move=new Intent(requestList.this,AddNewCoach.class);
        startActivity(move);
    }
    // (3 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Intent backTomain=new Intent(requestList.this,MainActivity.class);
        startActivity(backTomain);
    }
}