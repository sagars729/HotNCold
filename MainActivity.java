package com.example.android.bounce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.TextView;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;

import com.example.android.bounce.CustomDrawableView;

import static android.R.attr.width;
import static android.R.attr.x;
import static android.R.attr.y;

public class MainActivity extends FragmentActivity implements endFrag.DataFromActivityToFragment{
    private int finX;
    private int finY;
    private int width;
    private int length;
    private int goalInc=5;
    private Vibrator v;
    CustomDrawableView cv;
    private int score = 0;
    private long countdownPeriod;
    private boolean end = false;
    CountDownTimer mCountDownTimer;
    private int scoreGoal = 5;

    private void createCountDownTimer(int t) {
        mCountDownTimer = new CountDownTimer(countdownPeriod + t, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(score>=scoreGoal)
                {
                    mCountDownTimer.cancel();
                    if(goalInc<8){
                        goalInc++;
                        scoreGoal=scoreGoal +goalInc;}
                    else
                        scoreGoal=scoreGoal +goalInc;
                    cv.changeText(scoreGoal+"",2);
                    createCountDownTimer(25000);
                }
                else {
                    countdownPeriod = millisUntilFinished;
                    cv.changeText("" + millisUntilFinished / 1000, 1);
                    setContentView(cv);
                }
            }

            @Override
            public void onFinish() {
                setContentView(R.layout.activity_main);
                Bundle bundle = new Bundle();
                bundle.putString("score", score + "");
                SharedPreferences prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                if(prefs.contains("highScore"))
                {
                    int temp = prefs.getInt("highScore", 0);
                    if(score>temp) {
                        temp = score;
                        editor.putInt("highScore",temp);
                        editor.commit();
                    }
                }
                else
                {
                    editor.putInt("highScore", score);
                    editor.commit();
                }
                bundle.putString("highScore", prefs.getInt("highScore",0) + "");
                end = true;
                endFrag fragObj = new endFrag();
                fragObj.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_container, fragObj);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }.start();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        length = size.y;

        finX = (int) (Math.random()*(width-140))+120;
        finY = (int) (Math.random()*(length-140))+120;
        super.onCreate(savedInstanceState);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        cv = new CustomDrawableView(this);
        countdownPeriod=0;
        cv.retSize(width,length);
        setContentView(cv);
        createCountDownTimer(30000);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (end == false) {
            ///initial values////
            int x = (int) event.getX();
            int y = (int) event.getY();
            double d = Math.pow(Math.pow(y - finY, 2) + Math.pow(x - finX, 2), .5);
            //////////////////////Find Color Values////////////////////
            //View rootView = (View) findViewById(R.id.activity_main);
            Log.i("my tag", x + " " + y + " " + d);
            if (d > 1224) d = 1224;
            int g = 51;
            int r = 51;
            int b = 255;
            /////adjusted for one color///////
            int j = 6;
            r += (1224 - d) / j;
            int RGB = android.graphics.Color.rgb(r, g, b);
            ///////////////////////////////////
            if (d < 50) {
                v.vibrate(130);
                finX = (int) (Math.random()*(width-140))+120;
                finY = (int) (Math.random()*(length-140))+120;
                score++;
                cv.changeText(score + "", 0);
            }
            //rootView.setBackgroundColor(RGB);
            ////////set Background Color///////
            cv.changeColor(RGB);
            cv.changeBounds(x, y, 50, 50);
            setContentView(cv);

        }
        return false;
    }

    @Override
    public void first() {
        finX = (int) (Math.random()*(width-140))+120;
        finY = (int) (Math.random()*(length-140))+120;
        cv = new CustomDrawableView(this);
        cv.retSize(width,length);
        setContentView(cv);
        createCountDownTimer(29000);
        goalInc=5;
        score = 0;
        scoreGoal=5;
        cv.changeText(score + "", 0);
        end=false;
    }
}
//adjusted for two colors//////////
        /*int j = 3; ///so i tried the two color thing it doesn't flow well, but it works. uncomment to see
        if(d > 612){
            b = 51;
            r+= (1224-d)/j;
        }else{
            r = 255;
            b -= (612-d)/j;
        }*/
/////////////////////////////////