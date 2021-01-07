package com.example.darabni;
import android.app.Activity;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.darabni.MainTrainerScreen;
import com.example.darabni.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
public class problems extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    BootstrapEditText problemText;
    Spinner problemType;
    String text,type="null",cenid;
    ArrayAdapter spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
        Intent im=getIntent();
        cenid=im.getStringExtra("centerid");
        problemText=(BootstrapEditText)findViewById(R.id.reportDescription);
        problemType=(Spinner)findViewById(R.id.problem_spin);
        String[]problem_type=getResources().getStringArray(R.array.problems);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.problems,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        problemType.setAdapter(adapter);
        problemType.setOnItemSelectedListener(this);
        problemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type=problem_type[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type="null";
            }
        });
    }
    public void sendReport(View view){
        text=problemText.getText().toString();
        if(type.equals("null")&&text.equals(""))
        {
            Toast.makeText(this,"Enter Vaild Data",Toast.LENGTH_SHORT).show();
        }else{
            ParseObject problem = new ParseObject("Problem");
            problem.put("sender", MainTrainerScreen.userId);
            problem.put("reportedcenter", cenid);
            problem.put("problemtype", type);
            problem.put("description", text);
            problem.saveInBackground(new SaveCallback() {

                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        finish();

                    }

                }
            });

        }
    }
    public void cancelOperation(View view) {
        problemText.setText(null);
        finish();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}



