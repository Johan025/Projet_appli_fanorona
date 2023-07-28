package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.ImageView;

public class pop_fanorona5 extends Dialog {
    private Button menu;
    private Button retour;
    private ImageView sound,sound_off, close_pause;

    public pop_fanorona5(Activity activity){
        super(activity,R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.pop_fanorona5);
        this.menu=(Button) findViewById(R.id.menu);
        this.retour=(Button) findViewById(R.id.annuler);
        this.sound=(ImageView) findViewById(R.id.sound);
        this.sound_off=(ImageView) findViewById(R.id.sound_off);
        this.close_pause=(ImageView) findViewById(R.id.close_pause);



    }

    public void afficherPop(){
        create();
        show();
    }

    public Button menu(){
        return menu;
    }

    public Button annuler(){
        return retour;
    }

    public ImageView quit(){
        return close_pause;
    }

    public ImageView stop(){
        return sound;

    }

    public ImageView go(){
        return sound_off;

    }

    public void sound(){
        sound_off.setAlpha(1f);
        sound.setAlpha(0f);
    }

    public void soundOff(){
        sound.setAlpha(1f);
        sound_off.setAlpha(0f);
    }
}
