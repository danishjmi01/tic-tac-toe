package com.example.danish.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.attr.delay;

//import static com.example.danish.myapplication.R.id.textView;

public class MainActivity extends AppCompatActivity {
    int player = 0;
    int[] gameState = {1,1,1,1,1,1,1,1,1};
    int[][] wins = {
                    {-1, -1, -1},
                    {-1, -1, -1},
                    {-1, -1, -1}
                   };
    int winType = -1;
    boolean gameover = false;

    public void replay(View view){
        //this.recreate();
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
    public void dropIn(View view ){

        //this place is alredy played, so return
        if (gameState[Integer.parseInt(view.getTag().toString())] == 0 || gameover == true){
            return;
        }
        LinearLayout linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        linearlayout.setVisibility(View.INVISIBLE);
        ImageView box = (ImageView) view;//findViewById(R.id.imageView4);
        box.setTranslationY(-1000f);
        if( player == 0 ) {

            box.setImageResource(R.drawable.zero);
            box.animate().translationYBy(1000f).rotation(3600).setDuration(500);
            Log.i("HELLO", "HELLO");//view.getId();
            int tag = Integer.parseInt(view.getTag().toString());
            gameState[tag] = 0;
            wins[getRowIndex(tag)][getColIndex(tag)] = 0;
            winType = checkWin();
            if(finishOrContinue(winType) != -1){
                gameover = true;
                TextView result = (TextView) findViewById(R.id.resultMessageTextView);
                result.setText("Player 0 has won!");
                //Button resultButton = (Button) findViewById(R.id.ReplayButton);

                linearlayout.setVisibility(View.VISIBLE);
            }

            player = 1;

            //Log.i("Info", Integer.toString(id));
        }
        else
        {
            box.setImageResource(R.drawable.cross);
            box.animate().translationYBy(1000f).rotation(3600).setDuration(500);
            int tag = Integer.parseInt(view.getTag().toString());
            gameState[tag] = 0;
            wins[getRowIndex(tag)][getColIndex(tag)] = 1;
            winType = checkWin();
            if(finishOrContinue(winType) != -1){
                gameover = true;
                TextView result = (TextView) findViewById(R.id.resultMessageTextView);
                result.setText("Player X has won!");
                TextView resultButton = (TextView) findViewById(R.id.ReplayButton);
                linearlayout.setVisibility(View.VISIBLE);
            }
            player = 0;
        }


    }

    public int getRowIndex( int tag){
        int row = -1;
        switch(tag){

            case 0:
                row = 0;
                break;
            case 1:
                row = 0;
                break;
            case 2:
                row = 0;
                break;
            case 3:
                row = 1;
                break;
            case 4:
                row = 1;
                break;
            case 5:
                row = 1;
                break;
            case 6:
                row = 2;
                break;
            case 7:
                row = 2;
                break;
            case 8:
                row = 2;
                break;

        }
        return row;
    }

    public int getColIndex( int tag){
        int col = -1;
        switch(tag){

            case 0:
                col = 0;
                break;
            case 1:
                col = 1;
                break;
            case 2:
                col = 2;
                break;
            case 3:
                col = 0;
                break;
            case 4:
                col = 1;
                break;
            case 5:
                col = 2;
                break;
            case 6:
                col = 0;
                break;
            case 7:
                col = 1;
                break;
            case 8:
                col = 2;
                break;

        }
        return col;
    }
    public int checkWin(){

        //horizontal wins

        //Horizontal-1
        if( wins[0][0] == 0 && wins[0][1] == 0 && wins[0][2] == 0 )
        {
            return 0;
        }
        //Horizontal-2
        if( wins[1][0] == 0 && wins[1][1] == 0 && wins[1][2] == 0 )
        {
            return 1;
        }
        //Horizontal-3
        if( wins[2][0] == 0 && wins[2][1] == 0 && wins[2][2] == 0 )
        {
            return 2;
        }
        //Horizontal-1
        if( wins[0][0] == 1 && wins[0][1] == 1 && wins[0][2] == 1 )
        {
            return 0;
        }
        //Horizontal-2
        if( wins[1][0] == 1 && wins[1][1] == 1 && wins[1][2] == 1 )
        {
            return 1;
        }
        //Horizontal-3
        if( wins[2][0] == 1 && wins[2][1] == 1 && wins[2][2] == 1 )
        {
            return 2;
        }

        //-------------------vertical wins-----------------------
        //Vertical-1
        if( wins[0][0] == 0 && wins[1][0] == 0 && wins[2][0] == 0 )
        {
            return 3;
        }
        //Vertical-2
        if( wins[0][1] == 0 && wins[1][1] == 0 && wins[2][1] == 0 )
        {
            return 4;
        }
        //Vertical-3
        if( wins[0][2] == 0 && wins[1][2] == 0 && wins[2][2] == 0 )
        {
            return 5;
        }
        //Vertical-1
        if( wins[0][0] == 1 && wins[1][0] == 1 && wins[2][0] == 1 )
        {
            return 3;
        }
        //Vertical-2
        if( wins[0][1] == 1 && wins[1][1] == 1 && wins[2][1] == 1 )
        {
            return 4;
        }
        //Vertical-3
        if( wins[0][2] == 1 && wins[1][2] == 1 && wins[2][2] == 1 )
        {
            return 5;
        }

        //-----------Digonal Wins-----------------------
        if( wins[0][0] == 1 && wins[1][1] == 1 && wins[2][2] == 1 )
        {
            return 6;
        }
        if( wins[0][2] == 1 && wins[1][1] == 1 && wins[2][0] == 1 )
        {
            return 7;
        }
        if( wins[0][0] == 0 && wins[1][1] == 0 && wins[2][2] == 0 )
        {
            return 6;
        }
        if( wins[0][2] == 0 && wins[1][1] == 0 && wins[2][0] == 0 )
        {
            return 7;
        }
        return -1;
    }

    public int finishOrContinue(int winType){
        int background = R.drawable.background;
        if(winType == -1)
            return -1;
        switch (winType){

            case 0:
                background = R.drawable.horizontal1;
                break;
            case 1:
                background = R.drawable.horizontal2;
                break;
            case 2:
                background = R.drawable.horizontal3;
                break;
            case 3:
                background = R.drawable.vertical1;
                break;
            case 4:
                background = R.drawable.vertical2;
                break;
            case 5:
                background = R.drawable.vertical3;
                break;
            case 6:
                background = R.drawable.diagonal1;
                break;
            case 7:
                background = R.drawable.diagonal2;
                break;
        }
        final GridLayout gl = (GridLayout) findViewById(R.id.gridlayout);
        //gl.animate().alpha(0.0f).setDuration(1);
        //gl.setBackgroundResource(background);
        //gl.animate().alpha(1.0f).setDuration(1000);
        //gl.animate().alpha(1.0f).setDuration(1000);
        final int  tempbg = background;
        new CountDownTimer(600, 1000) {
            public void onFinish() {
               // gl.setBackgroundResource(background);
                //gl.animate().alpha(1.0f).setDuration(1000);
                gl.setBackgroundResource(tempbg);
            }
            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
        //gl.setAlpha(0).;
        return 999;
    }



    /*@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void buttonClick(View Button){
        Log.i("Info","Button clicked" );
        TextView vt = (TextView) findViewById(R.id.textView);

        if (vt.getText().toString().equals("Hello Miss!")){
            vt.setText("This is Lovely app...");
        }
        else
        vt.setText("Hello Miss!");


        vt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        linearlayout.setVisibility(View.INVISIBLE);
    }
}
