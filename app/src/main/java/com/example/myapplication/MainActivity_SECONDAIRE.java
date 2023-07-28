package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_SECONDAIRE extends AppCompatActivity {

    private Button play;
    private MediaPlayer mediaplayer;
    private MediaPlayer entree;
    private TextView propos;
    private TextView quitter;
    private TextView histoire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__s_e_c_o_n_d_a_i_r_e);
        getSupportActionBar().hide();
        this.mediaplayer=MediaPlayer.create(getApplicationContext(),R.raw.sound);
        this.entree=MediaPlayer.create(getApplicationContext(),R.raw.entree);
        mediaplayer.start();

        //this.quitter=(Button)findViewById(R.id.quitter);
        this.play=(Button)findViewById(R.id.button_play);
        this.propos=(TextView)findViewById(R.id.propos);
        this.quitter=(TextView)findViewById(R.id.quitt);
        this.histoire=(TextView) findViewById(R.id.histoire);

        propos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent proposActivity=new Intent(getApplicationContext(), About.class);
                 startActivity(proposActivity);
                 finish();
                mediaplayer.stop();
            }
        });

       quitter.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
             System.exit(0);
             finish();
              mediaplayer.stop();
           }});


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jeuactivity=new Intent(getApplicationContext(),grub.class);
                startActivity(jeuactivity);
                finish();
                mediaplayer.pause();
                entree.start();

            }
        });

        histoire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jeuactivity=new Intent(getApplicationContext(),Histoire.class);
                startActivity(jeuactivity);
                finish();
                mediaplayer.pause();
                entree.start();
            }
        });
    }


}