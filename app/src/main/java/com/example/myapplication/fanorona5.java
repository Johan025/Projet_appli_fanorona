package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class fanorona5 extends AppCompatActivity {
    private int[][] plateau = new int[14][14];
    private int[][] plateau2 = new int[14][14];

    private Button[][] buttons = new Button[12][12];
    private ImageView[] Pion1 = new ImageView[15];
    private ImageView[] Pion2 = new ImageView[15];
    private int iBouger = -1;
    private int jBouger = -1;
    private TextView Joueur1;
    private TextView Joueur2;

    private int tour = 1;
    private int iCD, iCG, iLH, iLB, jCD, jCG, jLH, jLB;
    private boolean deplacer;
    private int nPlateau = 5;
    private boolean poser = false;
    private ImageView pause;
    private ImageView restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanorona5);
        getSupportActionBar().hide();
        this.Joueur1 = (TextView) findViewById(R.id.Joueur1);
        this.Joueur2 = (TextView) findViewById(R.id.Joueur2);
        this.restart=(ImageView) findViewById(R.id.restart);
        this.pause=(ImageView) findViewById(R.id.pause);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restart_activity = new Intent(getApplicationContext(), fanorona5.class);
                startActivity(restart_activity);
            }
        });



        //initialisena i=1 ny case
        this.Pion1[0] = (ImageView) findViewById(R.id.pionneutre);// pion neutre
        this.Pion1[1] = (ImageView) findViewById(R.id.pion11);
        this.Pion1[2] = (ImageView) findViewById(R.id.pion12);
        this.Pion1[3] = (ImageView) findViewById(R.id.pion13);
        this.Pion1[4] = (ImageView) findViewById(R.id.pion14);
        this.Pion1[5] = (ImageView) findViewById(R.id.pion15);
        this.Pion1[6] = (ImageView) findViewById(R.id.pion16);
        this.Pion1[7] = (ImageView) findViewById(R.id.pion17);
        this.Pion1[8] = (ImageView) findViewById(R.id.pion18);
        this.Pion1[9] = (ImageView) findViewById(R.id.pion19);
        this.Pion1[10] = (ImageView) findViewById(R.id.pion110);
        this.Pion1[11] = (ImageView) findViewById(R.id.pion112);
        this.Pion1[12] = (ImageView) findViewById(R.id.pion113);


        this.Pion2[0] = (ImageView) findViewById(R.id.pionneutre);// pion neutre
        this.Pion2[1] = (ImageView) findViewById(R.id.pion21);
        this.Pion2[2] = (ImageView) findViewById(R.id.pion22);
        this.Pion2[3] = (ImageView) findViewById(R.id.pion23);
        this.Pion2[4] = (ImageView) findViewById(R.id.pion24);
        this.Pion2[5] = (ImageView) findViewById(R.id.pion25);
        this.Pion2[6] = (ImageView) findViewById(R.id.pion26);
        this.Pion2[7] = (ImageView) findViewById(R.id.pion27);
        this.Pion2[8] = (ImageView) findViewById(R.id.pion28);
        this.Pion2[9] = (ImageView) findViewById(R.id.pion29);
        this.Pion2[10] = (ImageView) findViewById(R.id.pion210);
        this.Pion2[11] = (ImageView) findViewById(R.id.pion211);
        this.Pion2[12] = (ImageView) findViewById(R.id.pion212);


        //declenchement des boutons
        buttons = new Button[5][5];
        buttons[0][0] = findViewById(R.id.button_00);
        buttons[0][1] = findViewById(R.id.button_01);
        buttons[0][2] = findViewById(R.id.button_02);
        buttons[0][3] = findViewById(R.id.button_03);
        buttons[0][4] = findViewById(R.id.button_04);
        buttons[1][0] = findViewById(R.id.button_10);
        buttons[1][1] = findViewById(R.id.button_11);
        buttons[1][2] = findViewById(R.id.button_12);
        buttons[1][3] = findViewById(R.id.button_13);
        buttons[1][4] = findViewById(R.id.button_14);
        buttons[2][0] = findViewById(R.id.button_20);
        buttons[2][1] = findViewById(R.id.button_21);
        buttons[2][2] = findViewById(R.id.button_22);
        buttons[2][3] = findViewById(R.id.button_23);
        buttons[2][4] = findViewById(R.id.button_24);
        buttons[3][0] = findViewById(R.id.button_30);
        buttons[3][1] = findViewById(R.id.button_31);
        buttons[3][2] = findViewById(R.id.button_32);
        buttons[3][3] = findViewById(R.id.button_33);
        buttons[3][4] = findViewById(R.id.button_34);
        buttons[4][0] = findViewById(R.id.button_40);
        buttons[4][1] = findViewById(R.id.button_41);
        buttons[4][2] = findViewById(R.id.button_42);
        buttons[4][3] = findViewById(R.id.button_43);
        buttons[4][4] = findViewById(R.id.button_44);


        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < nPlateau; j++) {
                plateau[i][j] = 1;
            }
        }


        for (int i = 3; i < nPlateau; i++) {
            for (int j = 0; j < nPlateau; j++) {
                plateau[i][j] = 2;
            }
        }
        plateau[2][1] = 1;
        plateau[2][4] = 1;
        plateau[2][0] = 2;
        plateau[2][3] = 2;
        plateau[2][2] = 0;


//  if (tour==1) {
        plateau2[0][0] = 1;
        plateau2[0][1] = 2;
        plateau2[0][2] = 3;
        plateau2[0][3] = 4;
        plateau2[0][4] = 5;
        plateau2[1][0] = 6;
        plateau2[1][1] = 7;
        plateau2[1][2] = 8;
        plateau2[1][3] = 9;
        plateau2[1][4] = 10;
        plateau2[2][1] = 11;
        plateau2[2][4] = 12;

        //}
        //else if (tour==2) {
        plateau2[2][0] = 1;
        plateau2[2][3] = 2;
        plateau2[3][4] = 3;
        plateau2[3][3] = 4;
        plateau2[3][2] = 5;
        plateau2[3][1] = 6;
        plateau2[3][0] = 7;
        plateau2[4][0] = 12;
        plateau2[4][1] = 11;
        plateau2[4][2] = 10;
        plateau2[4][3] = 9;
        plateau2[4][4] = 8;
        // }
        plateau2[2][2] = 0;// plateau neutre


        //initialisena i=1 sy j=1 ny case
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                final int i = x;
                final int j = y;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        //positions x et y de la case cliquée
                        float ox = buttons[i][j].getX();
                        float oy = buttons[i][j].getY();


                        //deplacement
                        if ((plateau[i][j] == 1 || plateau[i][j] == 2)) {
                            //selectionner pion
                            if (tour == 1 && (plateau[i][j] == 1)) {
                                iBouger = i;
                                jBouger = j;
                                System.out.println("joueur 1 pion [" + plateau2[iBouger][jBouger] + "] cliqué");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("joueur" + plateau[i][j]);
                                System.out.println("");
                                System.out.println("");
                                System.out.println("plateau [" + iBouger + "], [" + jBouger + "] cliqué");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("plateau2[" + iBouger + "], [" + jBouger + "]=" + plateau2[iBouger][jBouger]);

                            } else if (tour == 2 && (plateau[i][j] == 2)) {
                                iBouger = i;
                                jBouger = j;
                                System.out.println("joueur 2 pion [" + plateau2[iBouger][jBouger] + "] cliqué");
                                System.out.println("joueur" + plateau[iBouger][jBouger]);
                                System.out.println("plateau [" + iBouger + "], [" + jBouger + "] cliqué");
                                System.out.println("plateau2[" + iBouger + "], [" + jBouger + "]=" + plateau2[iBouger][jBouger]);

                            }


                        } else if (iBouger > -1 && !(plateau[i][j] == 1 || plateau[i][j] == 2)) {
                            System.out.println("le mouvement est ok");
                            plateau[i][j] = plateau[iBouger][jBouger];
                            plateau[iBouger][jBouger] = 0;
                            plateau2[i][j] = plateau2[iBouger][jBouger];
                            plateau2[iBouger][jBouger] = 0;


                      //      System.out.println("plateau  du joueur adverse en 1er[" + iLH + "], [" + j + "]=" + plateau[iLH][jBouger]);

                            if (tour == 1) {

                                iLH = iBouger - 1;
                                iLB = iBouger + 1;
                                jLB = jBouger;
                                jLH = jBouger;
                                jCG = jBouger - 1;
                                jCD = jBouger + 1;
                                iCD = iBouger;
                                iCG = iBouger;
                                //int h = iLB + 1;
                                //int v = iLH;
                                Pion1[plateau2[i][j]].setX(ox);
                                Pion1[plateau2[i][j]].setY(oy);
                                System.out.println("plateau [" + i + "], [" + j + "] ok");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("plateau [" + iBouger + "], [" + jBouger + "] selectionné");
                                System.out.println("");
                                System.out.println("");

                                System.out.println("plateau vidé [" + iBouger + "], [" + jBouger + "]=" + plateau2[iBouger][jBouger]);
                                System.out.println("");
                                System.out.println("");
                                System.out.println("nouveau plateau [" + i + "], [" + j + "]=" + plateau2[i][j]);


                                //controlleur de pion adverse


                                System.out.println("plateau  du joueur adverse[" + iLB + "], [" + j + "]=" + plateau[iLB][jBouger]);

                                System.out.println("");
                                System.out.println("plateau  du joueur adverse en 2eme [" + iLB + "], [" + j + "]=" + plateau[iLB][jBouger]);
                                System.out.println("plateau  du joueur adverse en 1er[" + iLB + "], [" + j + "]=" + plateau[iLB][jBouger]);
                                System.out.println("");
                                System.out.println("");


                                //-------------deplacement vers le bas----------------
                                if ((i == iLB) && (j == jLB)) {
                                    System.out.println("joueur : " + plateau[i][j]);


                                    //--- pas de pion en haut mais en bas ---
                                    if ((plateau[iLB + 1][jLB] == 2) && (plateau[iLB + 2][jLB] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iLB + 1][jLB]].setAlpha(0f);
                                        Pion2[plateau2[iLB + 2][jLB]].setAlpha(0f);

                                        plateau2[iLB + 1][jLB] = 0;
                                        plateau[iLB + 1][jLB] = 0;

                                        plateau2[iLB + 2][jLB] = 0;
                                        plateau[iLB + 2][jLB] = 0;

                                    } else if ((plateau[iLB + 1][jLB] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iLB + 1][jLB]].setAlpha(0f);

                                        plateau2[iLB + 1][jLB] = 0;
                                        plateau[iLB + 1][jLB] = 0;

                                    } else if (plateau[iLH][jLH] == 2) {
                                        if (iLH > 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion2[plateau2[iLH][jLH]].setAlpha(0f);
                                            Pion2[plateau2[iLH - 1][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH][jLB] = 0;
                                            plateau[iLH][jLB] = 0;

                                            plateau2[iLH - 1][jLB] = 0;
                                            plateau[iLH - 1][jLB] = 0;

                                        } else if (iLH <= 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion2[plateau2[iLH][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH][jLB] = 0;
                                            plateau[iLH][jLB] = 0;

                                        }

                                    }


                                }

                            // ------------deplacement vers le haut------------

                               else  if ((i == iLH) && (j == jLH)) {
                                    System.out.println("joueur : " + plateau[i][j]);


                                    //--- pas de pion en haut mais en bas ---
                                    if ((plateau[iLB][jLB] == 2) && (plateau[iLB +1][jLB] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iLB ][jLB]].setAlpha(0f);
                                        Pion2[plateau2[iLB + 1][jLB]].setAlpha(0f);

                                        plateau2[iLB][jLB] = 0;
                                        plateau[iLB][jLB] = 0;

                                        plateau2[iLB + 1][jLB] = 0;
                                        plateau[iLB + 1][jLB] = 0;

                                    } else if ((plateau[iLB][jLB] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iLB][jLB]].setAlpha(0f);

                                        plateau2[iLB][jLB] = 0;
                                        plateau[iLB][jLB] = 0;

                                    } else if (plateau[iLH-1][jLH] == 2) {
                                        if (iLH > 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion2[plateau2[iLH-1][jLH]].setAlpha(0f);
                                            Pion2[plateau2[iLH - 2][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH-1][jLB] = 0;
                                            plateau[iLH-1][jLB] = 0;

                                            plateau2[iLH - 2][jLB] = 0;
                                            plateau[iLH - 2][jLB] = 0;

                                        } else if (iLH <= 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion2[plateau2[iLH-1][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH-1][jLB] = 0;
                                            plateau[iLH-1][jLB] = 0;

                                        }

                                    }


                                }



                                //---------------------deplacement vers la droite ------------------------


                                else if ((i == iCD) && (j == jCD)) {

                                    if ((plateau[iCG][jCG] == 2)) {

                                        if (jCG > 0) {  //verification en bas de la position à 2 pions
                                            Pion2[plateau2[iCG][jCG]].setAlpha(0f);
                                            Pion2[plateau2[iCG][jCG - 1]].setAlpha(0f);

                                            plateau2[iCG][jCG] = 0;
                                            plateau[iCG][jCG] = 0;

                                            plateau2[iCG][jCG - 1] = 0;
                                            plateau[iCG][jCG - 1] = 0;

                                        } else if (jCG <= 0) {  //verification en bas de la position à 2 pions
                                            Pion2[plateau2[iCG][jCG]].setAlpha(0f);

                                            plateau2[iCG][jCG] = 0;
                                            plateau[iCG][jCG] = 0;

                                        }

                                    }

                                    //  else if (!(plateau[iCG][jCG]==2) && (plateau[iCG][jCG-1]==2) ){ // cas ou il manque de pion sur i+1
                                    //tsy manao ininona lay pion
                                    //}

                                    else if ((plateau[iCD][jCD + 1] == 2) && (plateau[iCD][jCD + 2] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iCD][jCD + 1]].setAlpha(0f);
                                        Pion2[plateau2[iCD][jCD + 2]].setAlpha(0f);


                                        plateau2[iCD][jCD + 1] = 0;
                                        plateau[iCD][jCD + 1] = 0;

                                        plateau2[iCD][jCD + 2] = 0;
                                        plateau[iCD][jCD + 2] = 0;


                                    } else if ((plateau[iCD][jCD + 1] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iCD][jCD + 1]].setAlpha(0f);

                                        plateau2[iCD][jCD + 1] = 0;
                                        plateau[iCD][jCD + 1] = 0;

                                    }


                                }

                                //---------------------deplacement vers la gauche ------------------------


                                else if ((i == iCG) && (j == jCG)) {

                                    //  else if (!(plateau[iCG][jCG]==2) && (plateau[iCG][jCG-1]==2) ){ // cas ou il manque de pion sur i+1
                                    //tsy manao ininona lay pion
                                    //}
                                    if ((plateau[iCD][jCD] == 2) && (plateau[iCD][jCD + 1] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iCD][jCD]].setAlpha(0f);
                                        Pion2[plateau2[iCD][jCD + 1]].setAlpha(0f);


                                        plateau2[iCD][jCD] = 0;
                                        plateau[iCD][jCD] = 0;

                                        plateau2[iCD][jCD + 1] = 0;
                                        plateau[iCD][jCD + 1] = 0;


                                    } else if ((plateau[iCD][jCD] == 2)) {  //verification en bas de la position à 2 pions
                                        Pion2[plateau2[iCD][jCD]].setAlpha(0f);

                                        plateau2[iCD][jCD] = 0;
                                        plateau[iCD][jCD] = 0;

                                    } else if ((plateau[iCG][jCG - 1] == 2)) {


                                        System.out.println("jcg: " + jCG);
                                        if (jCG > 0) {  //verification en bas de la position à 2 pions
                                            Pion2[plateau2[iCG][jCG - 1]].setAlpha(0f);
                                            Pion2[plateau2[iCG][jCG - 2]].setAlpha(0f);

                                            plateau2[iCG][jCG - 1] = 0;
                                            plateau[iCG][jCG - 1] = 0;

                                            plateau2[iCG][jCG - 2] = 0;
                                            plateau[iCG][jCG - 2] = 0;

                                        } else if (jCG <= 0) {  //verification en bas de la position à 2 pions
                                            Pion2[plateau2[iCG][jCG - 1]].setAlpha(0f);

                                            plateau2[iCG][jCG - 1] = 0;
                                            plateau[iCG][jCG - 1] = 0;

                                        }


                                    }

                                }
                            } else if (tour == 2) {
                                iLH = iBouger - 1;
                                iLB = iBouger + 1;
                                jLB = jBouger;
                                jLH = jBouger;
                                jCG = jBouger - 1;
                                jCD = jBouger + 1;
                                iCD = iBouger;
                                iCG = iBouger;
                                Pion2[plateau2[i][j]].setX(ox);
                                Pion2[plateau2[i][j]].setY(oy);
                                System.out.println("mouvement reussi");
                                System.out.println("");
                                System.out.println("plateau [" + i + "], [" + j + "] est séléctionné");
                                System.out.println("plateau [" + iBouger + "], [" + jBouger + "] cliqué");
                                System.out.println("");
                                System.out.println("plateau [" + iBouger + "], [" + jBouger + "]=" + plateau2[iBouger][jBouger]);


                                System.out.println("plateau  du joueur adverse[" + iLB + "], [" + j + "]=" + plateau[iLB][jBouger]);

                                System.out.println("");
                                //  System.out.println("plateau  du joueur adverse en 2eme ["+h+"], ["+j+"]="+  plateau[h][jBouger]);
                                System.out.println("plateau  du joueur adverse en 1er[" + iLB + "], [" + j + "]=" + plateau[iLB][jBouger]);
                                System.out.println("");
                                System.out.println("");


                                //-------------deplacement vers le bas----------------
                                if ((i == iLB) && (j == jLB)) {
                                    System.out.println("joueur : " + plateau[i][j]);


                                    //--- pas de pion en haut mais en bas ---
                                    if ((plateau[iLB + 1][jLB] == 1) && (plateau[iLB + 2][jLB] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iLB + 1][jLB]].setAlpha(0f);
                                        Pion1[plateau2[iLB + 2][jLB]].setAlpha(0f);

                                        plateau2[iLB + 1][jLB] = 0;
                                        plateau[iLB + 1][jLB] = 0;

                                        plateau2[iLB + 2][jLB] = 0;
                                        plateau[iLB + 2][jLB] = 0;

                                    } else if ((plateau[iLB + 1][jLB] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iLB + 1][jLB]].setAlpha(0f);

                                        plateau2[iLB + 1][jLB] = 0;
                                        plateau[iLB + 1][jLB] = 0;

                                    } else if (plateau[iLH][jLH] == 1) {
                                        if (iLH > 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion1[plateau2[iLH][jLH]].setAlpha(0f);
                                            Pion1[plateau2[iLH - 1][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH][jLB] = 0;
                                            plateau[iLH][jLB] = 0;

                                            plateau2[iLH - 1][jLB] = 0;
                                            plateau[iLH - 1][jLB] = 0;

                                        } else if (iLH <= 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion1[plateau2[iLH][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH][jLB] = 0;
                                            plateau[iLH][jLB] = 0;

                                        }

                                    }


                                }

                                // ------------deplacement vers le haut------------

                                else  if ((i == iLH) && (j == jLH)) {
                                    System.out.println("joueur : " + plateau[i][j]);


                                    //--- pas de pion en haut mais en bas ---
                                    if ((plateau[iLB][jLB] == 1) && (plateau[iLB +1][jLB] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iLB ][jLB]].setAlpha(0f);
                                        Pion1[plateau2[iLB + 1][jLB]].setAlpha(0f);

                                        plateau2[iLB][jLB] = 0;
                                        plateau[iLB][jLB] = 0;

                                        plateau2[iLB + 1][jLB] = 0;
                                        plateau[iLB + 1][jLB] = 0;

                                    } else if ((plateau[iLB][jLB] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iLB][jLB]].setAlpha(0f);

                                        plateau2[iLB][jLB] = 0;
                                        plateau[iLB][jLB] = 0;

                                    } else if (plateau[iLH-1][jLH] == 1) {
                                        if (iLH > 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion1[plateau2[iLH-1][jLH]].setAlpha(0f);
                                            Pion1[plateau2[iLH - 2][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH-1][jLB] = 0;
                                            plateau[iLH-1][jLB] = 0;

                                            plateau2[iLH - 2][jLB] = 0;
                                            plateau[iLH - 2][jLB] = 0;

                                        } else if (iLH <= 0) {
                                            //on efface le pion en i+1 du pion meme si il n 'y a pas de  pion en i+2
                                            Pion1[plateau2[iLH-1][jLH]].setAlpha(0f);

                                            //vidage de plateau
                                            plateau2[iLH-1][jLB] = 0;
                                            plateau[iLH-1][jLB] = 0;

                                        }

                                    }


                                }



                                //---------------------deplacement vers la droite ------------------------


                                else if ((i == iCD) && (j == jCD)) {

                                    if ((plateau[iCG][jCG] == 1)) {

                                        if (jCG > 0) {  //verification en bas de la position à 2 pions
                                            Pion1[plateau2[iCG][jCG]].setAlpha(0f);
                                            Pion1[plateau2[iCG][jCG - 1]].setAlpha(0f);

                                            plateau2[iCG][jCG] = 0;
                                            plateau[iCG][jCG] = 0;

                                            plateau2[iCG][jCG - 1] = 0;
                                            plateau[iCG][jCG - 1] = 0;

                                        } else if (jCG <= 0) {  //verification en bas de la position à 2 pions
                                            Pion1[plateau2[iCG][jCG]].setAlpha(0f);

                                            plateau2[iCG][jCG] = 0;
                                            plateau[iCG][jCG] = 0;

                                        }

                                    }

                                    //  else if (!(plateau[iCG][jCG]==2) && (plateau[iCG][jCG-1]==2) ){ // cas ou il manque de pion sur i+1
                                    //tsy manao ininona lay pion
                                    //}

                                    else if ((plateau[iCD][jCD + 1] == 1) && (plateau[iCD][jCD + 2] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iCD][jCD + 1]].setAlpha(0f);
                                        Pion1[plateau2[iCD][jCD + 2]].setAlpha(0f);


                                        plateau2[iCD][jCD + 1] = 0;
                                        plateau[iCD][jCD + 1] = 0;

                                        plateau2[iCD][jCD + 2] = 0;
                                        plateau[iCD][jCD + 2] = 0;


                                    } else if ((plateau[iCD][jCD + 1] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iCD][jCD + 1]].setAlpha(0f);

                                        plateau2[iCD][jCD + 1] = 0;
                                        plateau[iCD][jCD + 1] = 0;

                                    }


                                }

                                //---------------------deplacement vers la gauche ------------------------


                                else if ((i == iCG) && (j == jCG)) {

                                    //  else if (!(plateau[iCG][jCG]==2) && (plateau[iCG][jCG-1]==2) ){ // cas ou il manque de pion sur i+1
                                    //tsy manao ininona lay pion
                                    //}
                                    if ((plateau[iCD][jCD] == 1) && (plateau[iCD][jCD + 1] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iCD][jCD]].setAlpha(0f);
                                        Pion1[plateau2[iCD][jCD + 1]].setAlpha(0f);


                                        plateau2[iCD][jCD] = 0;
                                        plateau[iCD][jCD] = 0;

                                        plateau2[iCD][jCD + 1] = 0;
                                        plateau[iCD][jCD + 1] = 0;


                                    } else if ((plateau[iCD][jCD] == 1)) {  //verification en bas de la position à 2 pions
                                        Pion1[plateau2[iCD][jCD]].setAlpha(0f);

                                        plateau2[iCD][jCD] = 0;
                                        plateau[iCD][jCD] = 0;

                                    } else if ((plateau[iCG][jCG - 1] == 1)) {


                                        System.out.println("jcg: " + jCG);
                                        if (jCG > 0) {  //verification en bas de la position à 2 pions
                                            Pion1[plateau2[iCG][jCG - 1]].setAlpha(0f);
                                            Pion1[plateau2[iCG][jCG - 2]].setAlpha(0f);

                                            plateau2[iCG][jCG - 1] = 0;
                                            plateau[iCG][jCG - 1] = 0;

                                            plateau2[iCG][jCG - 2] = 0;
                                            plateau[iCG][jCG - 2] = 0;

                                        } else if (jCG <= 0) {  //verification en bas de la position à 2 pions
                                            Pion1[plateau2[iCG][jCG - 1]].setAlpha(0f);

                                            plateau2[iCG][jCG - 1] = 0;
                                            plateau[iCG][jCG - 1] = 0;

                                        }


                                    }

                                }

                            }

                            iBouger = -1;
                            jBouger = -1;
                            changerTour();
                        }

                    }
                });
                boutonPause();


            }

        }


    }

    private void changerTour() {
        if (tour == 1) {
            tour = 2;
            Joueur1.setAlpha(0f);
            Joueur2.setAlpha(1f);
        } else {
            tour = 1;
            Joueur1.setAlpha(1f);
            Joueur2.setAlpha(0f);
        }
    }

    private boolean peutBouger(int i, int j, int newX, int newY) {
        String newStr = newX + "" + newY;
        String[][][] d = {
                {
                        {"01", "10", "11"},
                        {"00", "02", "11"},
                        {"01", "11", "12"}
                },
                {
                        {"00", "11", "20"},
                        {"11", "02", "22"},
                        {"11", "02", "22"},
                },
                {
                        {"10", "11", "21"},
                        {"20", "11", "22"},
                        {"21", "11", "12"}
                }
        };

        if (i == 1 && j == 1 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

       else  if (i == 0 && j == 3 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne

       else if (i == 1 && j == 2 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

       else if (i == 1 && j == 3 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

       else if (i == 2 && j == 1 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

       else if (i == 2 && j == 2 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

        else if (i == 2 && j == 3 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

        else if (i == 3 && j == 1 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

        else if (i == 3 && j == 2 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie

        else if (i == 3 && j == 3 && !((plateau[newX][newY]==1) && (plateau[newX][newY]==2)) )// nouveau position
            return true; // retourne vraie


        else {

            for (int k = 0; k < 3; k++) {
                if (d[i][j][k].equals(newStr) &&!((plateau[newX][newY]==1) && (plateau[newX][newY]==2)))
                    return true;
            }
        }

        return false;
    }

    private boolean boutonPause(){
        final pop_pause pauser=new pop_pause(this);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauser.afficherPop();
                pauser.menu().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent otherActivity = new Intent(getApplicationContext(), MainActivity_SECONDAIRE.class);
                        startActivity(otherActivity);

                        finish();
                    }
                });

                pauser.annuler().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauser.CACHERPop();
                        System.out.println("cliqué");
                    }
                });

                pauser.stop().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauser.sound();

                    }
                });

                pauser.go().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pauser.soundOff();
                    }
                });

                pauser.stop().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pauser.sound();
                    }
                });

                //  pauser.menu().setOnClickListener(new View.OnClickListener() {
                //     @Override
                //       public void onClick(View v) {
                //          Intent otherActivity = new Intent(getApplicationContext(), MainActivity_SECONDAIRE.class);
                //     startActivity(otherActivity);
                //   }
                //    });
            }

        });

        return true;
    }



}
