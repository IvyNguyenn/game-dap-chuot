package com.example.ivyng.demoanimation;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imgvGreen1,imgvGreen2,imgvRed1;
    TextView txtvScores,txtvTime;
    Button btnPlay;
    //Timer timer = new Timer();
    Random rand;
    int minX= -200,maxX=60;
    int minY= -100,maxY=500;
    int scores = 3;
    int maxTime=60000,stepTime=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        final Animation animAlpha = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_alpha);
        final Animation animAlpha2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_alpha2);
        startAnimation(animAlpha);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlay.setVisibility(View.GONE);
                scores=3;
                startAnimation(animAlpha);
                final CountDownTimer countDownTimer = new CountDownTimer(maxTime,stepTime) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //int x= rand.nextInt(maxX-minX+1)+minX;
                        //int y = rand.nextInt(maxY-minY+1)+minY;
                        //imgvGreen1.setTranslationX(x);
                        //imgvGreen1.setTranslationY(y);

                        //txtvTime.setText(x+" "+y+"");
                        /*
                        if(scores>10){
                            animAlpha.setDuration(1000);
                            stepTime=1000;
                            clearAnimation();
                            startAnimation(animAlpha);
                        }
                        else if(scores>10){
                            animAlpha.setDuration(500);
                            stepTime=500;
                            clearAnimation();
                            startAnimation(animAlpha);
                        }
                        */
                        if(scores==0){
                            onFinish();
                        }
                        imgvGreen1.setTranslationX(rand.nextInt(maxX-minX+1)+minX);
                        imgvGreen1.setTranslationY(rand.nextInt(maxY-minY+1)+minY);
                        imgvGreen2.setTranslationX(rand.nextInt(maxX-minX+1)+minX);
                        imgvGreen2.setTranslationY(rand.nextInt(maxY-minY+1)+minY);
                        imgvRed1.setTranslationX(rand.nextInt(maxX-minX+1)+minX);
                        imgvRed1.setTranslationY(rand.nextInt(maxY-minY+1)+minY);
                    }

                    @Override
                    public void onFinish() {
                        clearAnimation();
                        btnPlay.setVisibility(View.VISIBLE);
                        if(scores==0)
                            Toast.makeText(MainActivity.this, "You loose", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Time out", Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();

            }
        });

        imgvGreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvGreen1.setVisibility(View.GONE);
                scores++;
                txtvScores.setText(scores+"");
            }
        });

        imgvGreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvGreen2.setVisibility(View.GONE);
                scores++;
                txtvScores.setText(scores+"");
            }
        });

        imgvRed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvRed1.setVisibility(View.GONE);
                scores--;
                txtvScores.setText(scores+"");
            }
        });



    }

    private void clearAnimation(){
        imgvGreen1.clearAnimation();
        imgvGreen1.setVisibility(View.GONE);
        imgvGreen2.clearAnimation();
        imgvGreen2.setVisibility(View.GONE);
        imgvRed1.clearAnimation();
        imgvRed1.setVisibility(View.GONE);
    }

    private void startAnimation(Animation animAlpha){
        imgvGreen1.startAnimation(animAlpha);
        imgvGreen2.startAnimation(animAlpha);
        imgvRed1.startAnimation(animAlpha);
    }

    private void anhXa(){
        rand = new Random();
        imgvGreen1 = (ImageView) findViewById(R.id.imgvGreen1);
        imgvGreen2 = (ImageView) findViewById(R.id.imgvGreen2);
        imgvRed1 = (ImageView) findViewById(R.id.imgvRed1);
        txtvScores = (TextView) findViewById(R.id.txtvScores);
        txtvTime = (TextView) findViewById(R.id.txtvTime);
        btnPlay = (Button) findViewById(R.id.btnplay);
    }
}
