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

public class ReviewsList extends AppCompatActivity {
    ListView problemList;
    ArrayList<Bitmap>image;
    ArrayList<String>trainersName;
    ArrayList<String>sendingDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);
        image=new ArrayList<Bitmap>();
        trainersName=new ArrayList<String>();
        sendingDate=new ArrayList<String>();
        // There The data will fetched from internet and displayed on the list
        //And the list have onClickListener when the User Click on list item
        // We Moving to new screen that called "Review Content"
        problemList=(ListView)findViewById(R.id.list3);

        //Now we add some data for applying the example
        trainersName.add("Ahmad Al-Ahmad");
        sendingDate.add("05/12/2020");
        //Create Adapter to manage list of review
        ArrayAdapter reviewListAdapter=new ArrayAdapter(ReviewsList.this
                ,R.layout.reviewlistdesign,R.id.review_sender_name,trainersName){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                TextView time=(TextView)view.findViewById(R.id.review_list_time);
                time.setText(sendingDate.get(position));
                return view;
            }
        };
       problemList.setAdapter(reviewListAdapter);
       /*
       Now When the user click on problem we going to fetch the problem id
       and just passing the id to the next screen
       in next screen we will create query to fetch all problem data by using the id
        */
       problemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent moveToSeeReviewContent=new Intent
                       (ReviewsList.this,ReviewContent.class);
               startActivity(moveToSeeReviewContent);
           }
       });
    }
}