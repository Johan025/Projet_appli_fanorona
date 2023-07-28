package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView entrer;
    private MediaPlayer mediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.mediaplayer= MediaPlayer.create(getApplicationContext(),R.raw.touchsound);
        //animation texte

        this.entrer=(TextView) findViewById(R.id.entrer);
        Animation anim=new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(350);
        anim.getStartOffset();
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        entrer.startAnimation(anim);

        entrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity=new Intent(getApplicationContext(),MainActivity_SECONDAIRE.class);
                startActivity(otherActivity);
                finish();
                mediaplayer.start();
            }
        });


    }


}
