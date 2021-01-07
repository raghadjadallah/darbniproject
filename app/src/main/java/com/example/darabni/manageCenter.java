package com.example.darabni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import de.hdodenhof.circleimageview.CircleImageView;

public class manageCenter extends AppCompatActivity {
    CircleImageView centerimage;
    TextView centername,centerphone;
    String name,phone,id;
    BootstrapButton dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_center);
        Intent intent=getIntent();
        name=intent.getStringExtra("cname");
        phone=intent.getStringExtra("cphone");
        id=intent.getStringExtra("cid");
        // In this screen we will display the selected  center information
        // for this functionality we going to applying or activate 3 button
        // this button will produced an operation that affect the selected center account
         centerimage=(CircleImageView)findViewById(R.id.img1);
         centername=(TextView)findViewById(R.id.cename);
         centerphone=(TextView)findViewById(R.id.cephone);
         dis = (BootstrapButton) findViewById(R.id.disa);
         centername.setText(centername.getText().toString()+"\t"+name);
         centerphone.setText(centerphone.getText().toString()+"\t"+phone);
        ParseQuery<ParseObject> query4 =ParseQuery.getQuery("Center");
        query4.whereEqualTo("ojectId",id);
        query4.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null && object!=null){
                    if(object.getBoolean("activation")==true){
                    }else {
                        dis.setText("Activate This Center Account");
                    }
                }
            }
        });

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
                        ParseQuery<ParseObject>query=ParseQuery.getQuery("Center");
                        query.whereEqualTo("ojectId",id);
                        query.getFirstInBackground(new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject object, ParseException e) {
                                if(e==null && object!=null){
                                    BackgroundMail bm=new BackgroundMail(manageCenter.this);
                                    bm.setGmailUserName("abeerkamel4646@gmail.com");
                                    bm.setGmailPassword("4646Abeer");
                                    bm.setMailTo(object.getString("owneremail"));
                                    bm.setFormSubject("Alert");
                                    bm.setFormBody("This is final alert for your center " + "please correct your situation");
                                    bm.send();
                                }
                            }
                        });

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
                        ParseQuery<ParseObject>query2=ParseQuery.getQuery("Center");
                        query2.whereEqualTo("ojectId",id);
                        query2.getFirstInBackground(new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject object, ParseException e) {
                                if(e==null && object!=null){
                                    if(dis.getText().toString().equals("Activate This Center Account")){
                                        object.put("activation",true);
                                        object.saveInBackground();
                                    }else{
                                    object.put("activation",false);
                                    object.saveInBackground();
                                    }
                                }
                            }
                        });

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
                        ParseQuery<ParseObject>query3=ParseQuery.getQuery("Center");
                        query3.whereEqualTo("ojectId",id);
                        query3.getFirstInBackground(new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject object, ParseException e) {
                                if(e==null && object!=null){
                                    object.deleteInBackground(new DeleteCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if(e==null){
                                                Intent back=new Intent(manageCenter.this,SuperVisorMainScreen.class);
                                                startActivity(back);
                                            }
                                        }
                                    });
                                }
                            }
                        });

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(manageCenter.this, "Ok !", Toast.LENGTH_SHORT).show();
                    }
                }).show();
       }
}