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
import android.widget.CheckBox;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddNewCar extends AppCompatActivity {
    DrawerLayout screendrawer;
    CircleImageView profileImage;
    String gearbox,name,phone,address,cost;
    BootstrapEditText manufactor,model,modelyear;
    Uri selectedImage;
    Bitmap bitmap , bitmap2 ,bitmap3 ,bitmap4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerAddCar);
        profileImage=(CircleImageView)findViewById(R.id.carprofile_image);
        manufactor=(BootstrapEditText)findViewById(R.id.addcarmanufactor);
        model=(BootstrapEditText)findViewById(R.id.addcarmodel);
        modelyear=(BootstrapEditText)findViewById(R.id.addcaryear);
        bitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.img8);
        Intent saveCoash=getIntent();
        name=saveCoash.getStringExtra("cname");
        phone=saveCoash.getStringExtra("cphone");
        address=saveCoash.getStringExtra("caddress");
        cost=saveCoash.getStringExtra("ccost");
        bitmap2=AddNewCoach.coachImage;
    }
    public void ChangeCarImage(View view){
        Intent i=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null ){
            try {
                selectedImage=data.getData();
                InputStream inputStream = this.getContentResolver().openInputStream(selectedImage);
                bitmap3= BitmapFactory.decodeStream(inputStream);
                profileImage.setImageBitmap(bitmap3);
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
      Intent i=new Intent(AddNewCar.this,CenterMainScreen.class);
        startActivity(i);
    }
    // (2) CenterAddCoach
    public void CenterAddCoach(View view){
        Intent move=new Intent(AddNewCar.this,AddNewCoach.class);
        startActivity(move);
    }
    // (3 ) AdminClickLogout
    public void AdminClickLogout (View view){
        Intent backTomain=new Intent(AddNewCar.this,MainActivity.class);
        startActivity(backTomain);
    }
    public void registerNewCoach(View view) {
        if(bitmap3==null){
            bitmap4=bitmap;
        }else {
            bitmap4=bitmap3;
        }
        ParseObject carobject=new ParseObject("Car");
        carobject.put("manufactor",manufactor.getText().toString());
        carobject.put("modelname",model.getText().toString());
        carobject.put("model",modelyear.getText().toString());
        carobject.put("gear",gearbox);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap4.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[]byteofcarImage=outputStream.toByteArray();
        ParseFile image1=new ParseFile("image.png",byteofcarImage);
        carobject.put("image",image1);
        //
        ParseObject coachobject=new ParseObject("Coach");
        coachobject.put("centerid",CenterMainScreen.objectId);
        coachobject.put("name",name);
        coachobject.put("phone",phone);
        coachobject.put("address",address);
        coachobject.put("cost",cost);
        ByteArrayOutputStream outputStream2=new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG,100,outputStream2);
        byte[]byteofcoachImage=outputStream2.toByteArray();
        ParseFile image2=new ParseFile("image.png",byteofcoachImage);
        coachobject.put("image",image2);
        coachobject.put("car",carobject);
        coachobject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    displayCoachList.coachListAdapter2.notifyDataSetChanged();
                    Intent goback=new Intent(AddNewCar.this,CenterMainScreen.class);
                    startActivity(goback);
                }
            }
        });
    }
    public void ToggleCheckBox(View view) {
        int id=view.getId();
        CheckBox ch1=(CheckBox)findViewById(R.id.man1);
        CheckBox ch2=(CheckBox)findViewById(R.id.man2);
        if(id==R.id.man1){
            ch2.setChecked(false);
            gearbox="automatic";
        }else {
            ch1.setChecked(false);
            gearbox="manual";
        }
    }
}