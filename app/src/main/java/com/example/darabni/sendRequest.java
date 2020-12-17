package com.example.darabni;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class sendRequest extends AppCompatActivity {
    DrawerLayout screendrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);
        screendrawer=(DrawerLayout)findViewById(R.id.drawerRequest);
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
    public void HomeItemClicked(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (2) Update Account Info
    public void UpdateAccountInfo(View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (3 ) SeeMyRequest
    public void SeeMyRequest (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (4 ) SeeMyRequest
    public void SendSpecialRequest (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (5 ) ClickAboutUS
    public void ClickAboutUS (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }
    // (6 ) ClickLogout
    public void ClickLogout (View view){
        Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT).show();
    }

    public void cancelSendRequest(View view) {
        // via this method we just cancel the request sending operation and back to
        // the previous screen
        finish();
    }

    public void onRequestSend(View view) {
        /*
        via this method we go to send the request to the selected center
         */
        Toast.makeText(this, "You Request has been send", Toast.LENGTH_SHORT).show();
    }
}