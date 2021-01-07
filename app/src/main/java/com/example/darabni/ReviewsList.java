package com.example.darabni;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ReviewsList extends AppCompatActivity {
    ListView problemList;
    ArrayList<String>problemId,problemType,problemDescription;
    ArrayAdapter reviewListAdapter;
    public void displayProblem(){
        ParseQuery<ParseObject>problemQuery=ParseQuery.getQuery("Problem");
        problemQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(ParseObject problem:objects){
                        problemId.add(problem.getString("reportedcenter"));
                        problemType.add(problem.getString("problemtype"));
                        problemDescription.add(problem.getString("description"));
                        reviewListAdapter.notifyDataSetChanged();
                        }

                    }
                }
            });
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);
        problemId=new ArrayList<String>();
        problemType=new ArrayList<String>();
        problemDescription=new ArrayList<String>();
        displayProblem();
        // There The data will fetched from internet and displayed on the list
        //And the list have onClickListener when the User Click on list item
        // We Moving to new screen that called "Review Content"
        problemList=(ListView)findViewById(R.id.list3);
        //Create Adapter to manage list of review
        reviewListAdapter=new ArrayAdapter(ReviewsList.this
                ,R.layout.reviewlistdesign,R.id.review_sender_name,problemType){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                TextView time=(TextView)view.findViewById(R.id.review_list_time);
                time.setText(problemDescription.get(position));
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
               moveToSeeReviewContent.putExtra("type",problemType.get(position));
               moveToSeeReviewContent.putExtra("desc",problemDescription.get(position));
               moveToSeeReviewContent.putExtra("center",problemId.get(position));
               startActivity(moveToSeeReviewContent);
           }
       });
    }
}