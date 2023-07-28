package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;

public class pop extends Dialog {
    private String text;
    private String win;
    private Button restart;
    private Button menu;
    private TextView finish;
    private TextView winner;

    public pop(Activity activity) {
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.pop);
        this.finish = (TextView) findViewById(R.id.finish);
        this.restart = (Button) findViewById(R.id.restart);
        this.menu = (Button) findViewById(R.id.menu);
        this.winner=(TextView) findViewById(R.id.vainqueur);
        this.win="Mon titre";

    }

    public void finish(String text){this.text=text;}
    public void getText(String win){this.win=win;}

    public  Button restartButton(){return restart;}

    public Button menuButton(){return menu;}

    public void launch(){
           show();
           winner.setText(win);

    }
}
