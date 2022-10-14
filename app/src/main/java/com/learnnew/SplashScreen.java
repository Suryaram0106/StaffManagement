package com.learnnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static int timer =2000;
    ImageView splashimage;

    Animation sideanim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        splashimage = findViewById(R.id.splashimage);

        sideanim= AnimationUtils.loadAnimation(this,R.anim.slide_anim);
        splashimage.setAnimation(sideanim);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this,Login.class);
                startActivity(i);
                finish();
            }
        },timer);
    }
}