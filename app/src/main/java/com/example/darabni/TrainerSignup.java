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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrainerSignup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    BootstrapEditText userAge;
    String gender="",trainingTypeOption="";
    RadioButton male,female;
    Spinner trainingType;
    String usernameData,userphoneData,useremailData,userpasswordData;
    CircleImageView profileImage6;
    Uri selectedImage6;
    Bitmap bitmap6,defaultImage,uploadedImage;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_signup);
        // Create mirror object for age and training type
        male=(RadioButton)findViewById(R.id.Rbtn_male);
        female=(RadioButton)findViewById(R.id.Rbtn_female);
        userAge=(BootstrapEditText)findViewById(R.id.age);
        trainingType=(Spinner)findViewById(R.id.spn_trainType2);
        //define bitmap image hold the default image in application
        defaultImage=BitmapFactory.decodeResource(this.getResources(),
                R.drawable.img3);
        // Via this intent object we get all default sign up  information
        Intent getSignupdata2=getIntent();
        usernameData=getSignupdata2.getStringExtra("uname");
        userphoneData=getSignupdata2.getStringExtra("uphone");
        useremailData=getSignupdata2.getStringExtra("uemail");
        userpasswordData=getSignupdata2.getStringExtra("upass");
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.type,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        trainingType.setAdapter(adapter);
        trainingType.setOnItemSelectedListener(this);
        trainingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                trainingTypeOption=trainingType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                trainingTypeOption="null";
            }
        });
        /*
        when the user select an item from the spinner the query for fetching data
        will change automatically
         */
    }
    public void continueSignupAsTrainer(View view) {
        ParseObject trainerAccount=new ParseObject("Trainee");
        trainerAccount.put("username",usernameData);
        trainerAccount.put("email",useremailData);
        trainerAccount.put("password",userpasswordData);
        trainerAccount.put("phone",userphoneData);
        trainerAccount.put("gender",gender);
        trainerAccount.put("type",trainingTypeOption);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
       if(bitmap6==null){
           uploadedImage=defaultImage;
       }else {
           uploadedImage=bitmap6;
       }
        uploadedImage.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte [] bytes=stream.toByteArray();
        ParseFile parseFile=new ParseFile("imagepng",bytes);
        trainerAccount.put("image",parseFile);
        trainerAccount.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(TrainerSignup.this, "Done trainer!", Toast.LENGTH_SHORT).show();
                    finish();
                    Log.i("info3","Go");
                } else {
                    Toast.makeText(TrainerSignup.this, "Somthing Worng !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void genderSelection(View view) {
        //Via this method we assign a value for gender variable
        int id =view.getId();
        if(id == R.id.Rbtn_male){
            if(male.isChecked()){
                gender="Male";
            }
        }else {
            if (female.isChecked()){
                gender="Female";
            }
        }
    }

    public void ChangeTrainerImage(View view) {
        Intent i=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null ){
            try {
                selectedImage6=data.getData();
                InputStream inputStream = this.getContentResolver().openInputStream(selectedImage6);
                bitmap6 = BitmapFactory.decodeStream(inputStream);
                profileImage6=(CircleImageView)findViewById(R.id.profile_image);
                profileImage6.setImageBitmap(bitmap6);
            }catch (Exception exception){

            }
        }else {
            Toast.makeText(this, "Smothing Worng !", Toast.LENGTH_SHORT).show();
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