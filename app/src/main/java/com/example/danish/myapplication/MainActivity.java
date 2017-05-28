package com.example.danish.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//import static com.example.danish.myapplication.R.id.textView;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void buttonClick(View Button){
        Log.i("Info","Button clicked" );
        TextView vt = (TextView) findViewById(R.id.textView);

        if (vt.getText().toString().equals("Hello Miss!")){
            vt.setText("This is Lovely app...");
        }
        else
        vt.setText("Hello Miss!");


        vt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
