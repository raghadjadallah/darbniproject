package com.example.darabni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        request=(ListView)findViewById(R.id.requestListView);
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
}