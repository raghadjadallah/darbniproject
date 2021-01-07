package com.example.darabni;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class CenterSignup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    CircleImageView profile;
    CircleImageView profileImage4;
    Uri selectedImage4;
    Bitmap bitmap4,defaultImage1,uploadedImage1;
    Spinner selectAddress;
    String usernameData,userphoneData,useremailData,userpasswordData;
    BootstrapEditText centerName,centerPhone,centerLicence;
    String nameCenter,phoneCenter,licenceCenter,addressCenter;
    ArrayAdapter spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_signup);
        // Create mirror object for age and training type
        selectAddress=(Spinner)findViewById(R.id.addressList);
        centerName=(BootstrapEditText)findViewById(R.id.centername);
        centerPhone=(BootstrapEditText)findViewById(R.id.centerphone);
        centerLicence=(BootstrapEditText)findViewById(R.id.centerlicence);
        defaultImage1=BitmapFactory.decodeResource(this.getResources(), R.drawable.img2);
        // Via this intent object we get all default sign up  information
        Intent getSignupdata=getIntent();
        usernameData=getSignupdata.getStringExtra("uname");
        userphoneData=getSignupdata.getStringExtra("uphone");
        useremailData=getSignupdata.getStringExtra("uemail");
        userpasswordData=getSignupdata.getStringExtra("upass");
        String [] city_name_list=getResources().getStringArray(R.array.Address);
        String temp="Select Address from here ";
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.Address,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        selectAddress.setAdapter(adapter);
        selectAddress.setOnItemSelectedListener(this);
        //spinnerAdapter=new ArrayAdapter
              //  (CenterSignup.this, android.R.layout.simple_list_item_1,city_name_list);
       // selectAddress.setPrompt("Select Address from here ");
        //selectAddress.setAdapter(spinnerAdapter);
        selectAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addressCenter=city_name_list[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addressCenter="";
            }
        });
    }
    public void changeCenterImage(View view) {
        //this method allow users to change the center default image
        Toast.makeText(this, "Pick image from storage", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i,1);
     }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null ){
            try {
                selectedImage4=data.getData();
                InputStream inputStream = this.getContentResolver().openInputStream(selectedImage4);
                bitmap4= BitmapFactory.decodeStream(inputStream);
                profileImage4=(CircleImageView)findViewById(R.id.profile_image);
                profileImage4.setImageBitmap(bitmap4);
            }catch (Exception exception){

            }
        }else {
            Toast.makeText(this, "Smothing Worng !", Toast.LENGTH_SHORT).show();
        }

    }
    public void continueSignupAsCenter(View view) {
        // This method allow Center admin users to complete registration operation
        // Now after we get information from the previous screen
        // In this method we try to get the complement data from this screen
        // Then we trying to add a new record to the data base
        //nameCenter,phoneCenter,licenceCenter,addressCenter
        nameCenter=centerName.getText().toString();
        phoneCenter=centerPhone.getText().toString();
        licenceCenter=centerLicence.getText().toString();
        if(nameCenter.matches("")&&phoneCenter.matches("")
        &&licenceCenter.matches("")&&addressCenter.matches("")){
            Toast.makeText(this, "All data Are Required", Toast.LENGTH_SHORT).show();
        }else{
            ParseObject centerAccount=new ParseObject("Center");
            centerAccount.put("centername",nameCenter);
            centerAccount.put("owneremail",useremailData);
            centerAccount.put("phone",phoneCenter);
            centerAccount.put("owner",usernameData);
            centerAccount.put("address",addressCenter);
            centerAccount.put("activation",true);
            centerAccount.put("licencenumber",licenceCenter);
            centerAccount.put("ownerphone",userphoneData);
            centerAccount.put("password",userpasswordData);
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            if(bitmap4==null){
                uploadedImage1=defaultImage1;
            }else {
                uploadedImage1=bitmap4;
            }
            uploadedImage1.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte [] bytes=stream.toByteArray();
            ParseFile parseFile=new ParseFile("imagepng",bytes);
            centerAccount.put("image",parseFile);
            //now we will check for validate licence number
            ParseQuery<ParseObject>parseQuery=ParseQuery.getQuery("Licence");
            parseQuery.whereEqualTo("number",licenceCenter);
            parseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if(e==null && object !=null){
                        //that means the licence number is already available
                        //then we can't save this center on data base
                        Toast.makeText(CenterSignup.this, "Sorry This Center Licence number is Already Available", Toast.LENGTH_SHORT).show();
                    }else{
                        ParseObject oo2=new ParseObject("Licence");
                        oo2.put("number",licenceCenter);
                        oo2.saveInBackground();
                        centerAccount.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                                if (e == null) {
                                    Toast.makeText(CenterSignup.this, "Done!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(CenterSignup.this, "Somthing Worng !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

