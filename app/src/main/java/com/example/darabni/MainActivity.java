package com.example.darabni;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
public class MainActivity extends AppCompatActivity {
    //The  Screen number 1 in application
    BootstrapEditText username;
    BootstrapEditText password;
    CheckBox admin_Login_Option;
    TextView signUp,forgetPass;
    BootstrapButton loginOption,clearOption;
    DrawerLayout mainActivityDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create mirror object for drawer menu
        mainActivityDrawer=findViewById(R.id.drawer_main_activity);
        // Create mirror object for all screen content
        username= (BootstrapEditText) findViewById(R.id.usertv);
        password=(BootstrapEditText)findViewById(R.id.passtv);
        admin_Login_Option=(CheckBox)findViewById(R.id.cheko);
        signUp=(TextView)findViewById(R.id.sa);
        forgetPass=(TextView)findViewById(R.id.forpass);
        //Create 2 click listener for sign up and forget pass word page
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // this method will call when the user click on sign up text
                //we move the user to sign up screen via Intent Object
                Intent moveToSignup=new Intent(MainActivity.this,Signup.class);
                //no data will passing between this screens
                startActivity(moveToSignup);
            }
        });
      forgetPass.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              // this method will call when the user click on Forget Password text
              //we move the user to Reset Password screen via Intent Object
              Intent moveToResetPassword= new Intent(MainActivity.this,ResetPassword1.class);
              startActivity(moveToResetPassword);
          }
      });
    }
    public void loginMethod(View view) {
        String usernameValue=username.getText().toString();
        String passwordValue=password.getText().toString();
        // Check the edit text to get real data for username and password
        if(usernameValue.matches("") || passwordValue.matches("")){
            //now if there is no data the following massage will displayed for user
            Toast.makeText(this, "Username and Password are Required", Toast.LENGTH_SHORT).show();
        }else {
            //if the user enter a real inputs and real data for user name and pass
            // now we going to decided to redirect the code to match the login option
            // we have two login option (Admin for center and Normal user --Trainer --)
            if(admin_Login_Option.isChecked()){
                // if the user activate the check box then he decide to login as center
                    // There We will write all necessary code for login operation
                        // if the login operation done successfully the the user will
                            // moved into Center Screen via Intent Object
                if (usernameValue.equals("jasser")&&passwordValue.equals("0000")){
                    //Toast.makeText(this, "Login as Center Done", Toast.LENGTH_SHORT).show();
                    Intent movetoCenterScreen=new Intent(MainActivity.this,CenterMainScreen.class);
                    startActivity(movetoCenterScreen);
                }else {
                    Toast.makeText(this, "Invalid login info", Toast.LENGTH_SHORT).show();
                }
            }else {
                //the second option will be login as a normal user
                // There We will write all necessary code for login operation
                // if the login operation done successfully the the user will
                // moved into Trainer Screen via Intent Object
                if (usernameValue.equals("noor")&&passwordValue.equals("1234")){
                    //Toast.makeText(this, "Login as Trainer Done", Toast.LENGTH_SHORT).show();
                    Intent movetoTrainerScreen=new Intent(MainActivity.this,MainTrainerScreen.class);
                    startActivity(movetoTrainerScreen);
                }else {
                    Toast.makeText(this, "Invalid login info", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void clearMethod(View view) {
        //This method have one function that when user click on clear button
        // All Checked on button and all entered data will removed
        // The screen back to its default state
        admin_Login_Option.setChecked(false);
        username.setText(null);
        password.setText(null);
    }
    // All the following method is special for manage the drawer menu
    /*
    * The first method is connected with more icon
    * we the user click on more icon (...)
    * The Special Drawer will Displayed */
    public void onMenuClicked(View view) {
        openDrawer(mainActivityDrawer);
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
    /*
    * This method is connected with the menu opetions */
    public void loginAsAdminOptionClicked(View view) {
        // here we will ask user to confirm if there decided to login as
        // Admin by using Alert Dialog and if user click yes then we move to admin login
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you Sure ?")
                .setMessage("Are you Need to Login as Admin ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // There if user click yes we move to admin login via intent
                        Intent moveToSuperVisor= new Intent(MainActivity.this,SuperVisorLogin.class);
                        startActivity(moveToSuperVisor);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // here we don't do any thing
            }
        }).show();
    }
}