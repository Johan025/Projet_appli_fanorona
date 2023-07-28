package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class grub extends AppCompatActivity {

    private TextView opt1;
    private TextView opt2;
    private ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grub);
        getSupportActionBar().hide();

        this.opt1=(TextView) findViewById(R.id.opt1);
        this.opt2=(TextView) findViewById(R.id.opt2);
        this.close=(ImageView) findViewById(R.id.close_pause);

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fanorona3=new Intent(getApplicationContext(), PlateaudujeuActivity.class);
                startActivity(fanorona3);
                finish();
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fanorona5=new Intent(getApplicationContext(), fanorona5.class);
                startActivity(fanorona5);
                finish();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(getApplicationContext(), MainActivity_SECONDAIRE.class);
                startActivity(back);
                finish();
            }
        });

    }
}