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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imgvGreen1, imgvGreen2, imgvRed1, imgvRed2, imgvRed3, imgvRed4, imgvRed5, imgvRed6;
    List<ImageView> listImgv = new ArrayList<ImageView>();

    TextView txtvScores, txtvLife;
    Animation animAlpha;
    Button btnPlay;
    CountDownTimer countDownTimer;
    //Timer timer = new Timer();
    Random rand;
    int minX = -200, maxX = 60;
    int minY = -100, maxY = 500;
    int scores = 0;
    int life = 3;
    int level;
    boolean click;
    int maxTime = 60000, stepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        animAlpha = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_alpha);
        final Animation animAlpha2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_alpha2);
        //startAnimation(animAlpha);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlay.setVisibility(View.GONE);
                click = false;
                scores = 0;
                life = 4;
                level = 1;
                stepTime = 3000;
                txtvScores.setText("0");
                txtvLife.setText("3");
                startAnimation(animAlpha);
                timer(stepTime);

            }
        });

        imgvGreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click == false) {
                    imgvGreen1.setVisibility(View.GONE);
                    tangDiem();

                }
                click = true;
            }
        });

        imgvGreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click == false) {
                    imgvGreen2.setVisibility(View.GONE);
                    tangDiem();
                }
                click = true;
            }
        });

//        imgvRed1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(click==false) {
//                    imgvRed1.setVisibility(View.GONE);
//                    truMang();
//
//                    if (life == 0)
//                        countDownTimer.cancel();
//                }click = true;
//            }
//        });



    }

    private void clearAnimation() {
        imgvGreen1.clearAnimation();
        imgvGreen1.setVisibility(View.GONE);
        imgvGreen2.clearAnimation();
        imgvGreen2.setVisibility(View.GONE);
        //imgvRed1.clearAnimation();
        //imgvRed1.setVisibility(View.GONE);
        for (int i = 0; i < level; i++) {
            listImgv.get(i).clearAnimation();
            listImgv.get(i).setVisibility(View.GONE);
        }
    }

    private void startAnimation(Animation animAlpha) {
        imgvGreen1.startAnimation(animAlpha);
        imgvGreen2.startAnimation(animAlpha);
        //imgvRed1.startAnimation(animAlpha);

        imgvGreen1.setVisibility(View.VISIBLE);
        imgvGreen2.setVisibility(View.VISIBLE);
        //imgvRed1.setVisibility(View.VISIBLE);

        for (int i = 0; i < level; i++) {
            listImgv.get(i).setVisibility(View.VISIBLE);
            listImgv.get(i).startAnimation(animAlpha);
        }
    }

    private void anhXa() {
        rand = new Random();
        imgvGreen1 = (ImageView) findViewById(R.id.imgvGreen1);
        imgvGreen2 = (ImageView) findViewById(R.id.imgvGreen2);
        imgvRed1 = (ImageView) findViewById(R.id.imgvRed1);
        txtvScores = (TextView) findViewById(R.id.txtvScores);
        txtvLife = (TextView) findViewById(R.id.txtvLife);
        btnPlay = (Button) findViewById(R.id.btnplay);
        imgvRed2 = (ImageView) findViewById(R.id.imgvRed2);
        imgvRed3 = (ImageView) findViewById(R.id.imgvRed3);
        imgvRed4 = (ImageView) findViewById(R.id.imgvRed4);
        imgvRed5 = (ImageView) findViewById(R.id.imgvRed5);
        imgvRed6 = (ImageView) findViewById(R.id.imgvRed6);

        listImgv.add(imgvRed1);
        listImgv.add(imgvRed2);
        listImgv.add(imgvRed3);
        listImgv.add(imgvRed4);
        listImgv.add(imgvRed5);
        listImgv.add(imgvRed6);


        imgvGreen1.setVisibility(View.GONE);
        imgvGreen2.setVisibility(View.GONE);
        imgvRed1.setVisibility(View.GONE);
        imgvRed2.setVisibility(View.GONE);
        imgvRed3.setVisibility(View.GONE);
        imgvRed4.setVisibility(View.GONE);
        imgvRed5.setVisibility(View.GONE);
        imgvRed6.setVisibility(View.GONE);

    }

    private void truMang() {
        life--;
        txtvLife.setText(life + "");
        if (life <= 0) {
            loose();
            return;
        }
    }

    private void tangDiem() {

        scores++;
        txtvScores.setText(scores + "");
        if(scores==30)
        {
            clearAnimation();
            countDownTimer.cancel();
            Toast.makeText(this, "You win !!! ", Toast.LENGTH_SHORT).show();
        }
        if (scores % 5 == 0) {
            level++;
            stepTime *= 0.9;
            imgvGreen1.setMaxHeight((int) (imgvGreen1.getHeight() * 0.5));
            animAlpha.setDuration(stepTime);
            startAnimation(animAlpha);
            countDownTimer.cancel();
            timer(stepTime);
        }

    }

    private void loose() {
        clearAnimation();
        btnPlay.setVisibility(View.VISIBLE);
        if (life == 0)
            Toast.makeText(MainActivity.this, "You loose", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Time out", Toast.LENGTH_LONG).show();
        countDownTimer.cancel();
    }

    private void timer(int stepTime) {

        countDownTimer = new CountDownTimer(maxTime, stepTime) {
            @Override
            public void onTick(long millisUntilFinished) {

                //Toast.makeText(MainActivity.this, click?"true":"false", Toast.LENGTH_SHORT).show();

                if (click == false) {
                    truMang();
                }
                click = false;

                int x = rand.nextInt(maxX - minX + 1) + minX;
                imgvGreen1.setTranslationX(rand.nextInt(maxX - minX + 1) + minX);
                imgvGreen1.setTranslationY(rand.nextInt(maxY - minY + 1) + minY);
                imgvGreen2.setTranslationX(rand.nextInt(maxX - minX + 1) + minX);
                imgvGreen2.setTranslationY(rand.nextInt(maxY - minY + 1) + minY);
//                imgvRed1.setTranslationX(rand.nextInt(maxX-minX+1)+minX);
//                imgvRed1.setTranslationY(rand.nextInt(maxY-minY+1)+minY);
                for (int i = 0; i < level; i++) {
                    listImgv.get(i).setTranslationX(rand.nextInt(maxX - minX + 1) + minX);
                    listImgv.get(i).setTranslationY(rand.nextInt(maxY - minY + 1) + minY);
                }

            }

            @Override
            public void onFinish() {

                countDownTimer.cancel();

            }
        };
        countDownTimer.start();
    }

    public void clickRed(View view) {
        if (click == false) {
            //view.setVisibility(View.GONE);
            truMang();

            if (life == 0)
                countDownTimer.cancel();
        }
        click = true;
    }
}
