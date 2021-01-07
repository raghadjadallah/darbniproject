package com.example.darabni;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddNewCoach extends AppCompatActivity {
    DrawerLayout screendrawer;
    CircleImageView profileImage2;
    BootstrapEditText name,phone,address,cost;
    Uri selectedImage2;
    public static Bitmap coachImage;
    Bitmap bitmap1,bitmap2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_coach);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerAddCoach);
        profileImage2=(CircleImageView)findViewById(R.id.coachprofile_image);
        name=(BootstrapEditText)findViewById(R.id.addcoachname);
        phone=(BootstrapEditText)findViewById(R.id.addcoachphone);
        address=(BootstrapEditText)findViewById(R.id.addcoachaddress);
        cost=(BootstrapEditText)findViewById(R.id.addcoachcost);
        bitmap1=BitmapFactory.decodeResource(this.getResources(),R.drawable.img1);
    }
    public void ChangeCoachImage(View view){
        Intent i=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null ){
            try {
                selectedImage2=data.getData();
                InputStream inputStream = this.getContentResolver().openInputStream(selectedImage2);
                bitmap2 = BitmapFactory.decodeStream(inputStream);
                profileImage2.setImageBitmap(bitmap2);
            }catch (Exception exception){
            }
        }else {
            Toast.makeText(this, "Smothing Worng !", Toast.LENGTH_SHORT).show();
        }
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
        Intent intent=new Intent(AddNewCoach.this,CenterMainScreen.class);
        startActivity(intent);
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Intent move=new Intent(AddNewCoach.this,AddNewCoach.class);
        startActivity(move);
    }
    // (3 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Intent backTomain=new Intent(AddNewCoach.this,MainActivity.class);
        startActivity(backTomain);
    }

    public void CompleteAddingCoach(View view) {
        // via this method we going to enter the new car information
        if(bitmap2==null){
            coachImage=bitmap1;
        }else {
            coachImage=bitmap2;
        }
        Intent move2=new Intent(AddNewCoach.this,AddNewCar.class);
        move2.putExtra("cname",name.getText().toString());
        move2.putExtra("cphone",phone.getText().toString());
        move2.putExtra("caddress",address.getText().toString());
        move2.putExtra("ccost",cost.getText().toString());
        startActivity(move2);
    }
}