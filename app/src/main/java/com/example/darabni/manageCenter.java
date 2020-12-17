package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class manageCenter extends AppCompatActivity {
    CircleImageView centerimage;
    TextView centername,centerphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_center);
        // In this screen we will display the selected  center information
        // for this functionality we going to applying or activate 3 button
        // this button will produced an operation that affect the selected center account
         centerimage=(CircleImageView)findViewById(R.id.img1);
         centername=(TextView)findViewById(R.id.cename);
         centerphone=(TextView)findViewById(R.id.cephone);
    }
    /*
       All the following  method use the AlertDialog to make twice sure
       for all operation
     */

    public void sendAlertForCenter(View view) {
        // with this method we going to send an alert for specific center to tell them
        // that there are some problem you must to solve for more information
        // please call the support center
        new AlertDialog.Builder(manageCenter.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you Sure")
                .setMessage("Are you need to send Alert to this center")
                .setPositiveButton("Yes, I'm sure ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // if the admin click yes that means the alert will send

                     }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(manageCenter.this, "Ok !", Toast.LENGTH_SHORT).show();
                    }
                }).show();
      }

    public void disactivateCenterAccount(View view) {
        // with this method we dis Activate selected center account
        new AlertDialog.Builder(manageCenter.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you Sure")
                .setMessage("Are you need to dis activate this center account")
                .setPositiveButton("Yes, I'm sure ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(manageCenter.this, "Ok !", Toast.LENGTH_SHORT).show();
                    }
                }).show();
      }

    public void deleteCenterAccount(View view) {
        // with this method we going to delete selected center account
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you Sure")
                .setMessage("Are you need to delete this center account")
                .setPositiveButton("Yes, I'm sure ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(manageCenter.this, "Ok !", Toast.LENGTH_SHORT).show();
                    }
                });
       }
}