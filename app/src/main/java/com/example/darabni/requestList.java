package com.example.darabni;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class requestList extends AppCompatActivity {
    ListView request;
    ArrayList<String>senderNames,sendingDate;
    DrawerLayout screendrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        request=(ListView)findViewById(R.id.requestListView);
        screendrawer=(DrawerLayout)findViewById(R.id.drawer11);
        senderNames=new ArrayList<String>();
        sendingDate=new ArrayList<String>();
        senderNames.add("Jasser");
        sendingDate.add("4/12/2020");
        ArrayAdapter reviewListAdapter=new ArrayAdapter(requestList.this,R.layout.requestlistdesign
        ,R.id.tvn,senderNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                TextView date=(TextView)view.findViewById(R.id.tvadd);
                date.setText(sendingDate.get(position));
                return view;
            }
        };
        request.setAdapter(reviewListAdapter);
        request.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(requestList.this, "move To see Request Info", Toast.LENGTH_SHORT).show();
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

    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){

    }
    // (3 ) CenterNeedSupport
    public void CenterNeedSupport (View view){

    }
    // (4 ) AdminClickLogout
    public void AdminClickLogout (View view){

    }
}