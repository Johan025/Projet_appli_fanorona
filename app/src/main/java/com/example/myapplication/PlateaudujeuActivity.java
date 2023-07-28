package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class PlateaudujeuActivity extends AppCompatActivity {
    private Button[][] buttons = new Button[3][3];
    private ImageView[] pion1 = new ImageView[3];
    private ImageView[] pion2 = new ImageView[3];
    private TextView textView3;
    private ImageView imageView;
    private int pion1X, pion1Y;
    private TextView Joueur1;
    private TextView Joueur2;

    private int tour = 1;
    private boolean poser = true;
    private int nPion = 0;
    private int[][] plateau = new int[3][3];
    private int[][] plateau2 = new int[3][3];
    private int dernierPion1 = 0;
    private int dernierPion2 = 0;
    private int iBouger = -1;
    private int jBouger = -1;
    private int gagne = -1;
    private PlateaudujeuActivity Activity;
    private ImageView Plateau;
    private boolean gamePaused = false;
    private MediaPlayer mediaplayer;
    private MediaPlayer errorClick;
    private MediaPlayer winnerSound;
    private ImageView pause;
    private ImageView restart;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plateaudujeu);
        getSupportActionBar().hide();

        this.mediaplayer=MediaPlayer.create(getApplicationContext(),R.raw.touchsound);
        this.errorClick=MediaPlayer.create(getApplicationContext(),R.raw.clickerror);
        this.winnerSound=MediaPlayer.create(getApplicationContext(),R.raw.winner_sound);
        this.restart=(ImageView) findViewById(R.id.restart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restart_activity = new Intent(getApplicationContext(), PlateaudujeuActivity.class);
                startActivity(restart_activity);
            }
        });


        this.Plateau = (ImageView) findViewById(R.id.Plateau);
        this.Joueur1 = (TextView) findViewById(R.id.Joueur1);
        this.Joueur2 = (TextView) findViewById(R.id.Joueur2);
        this.pion1[0] = (ImageView) findViewById(R.id.pion1A);
        this.pion1[1] = (ImageView) findViewById(R.id.pion2A);
        this.pion1[2] = (ImageView) findViewById(R.id.pion3A);
        this.pion2[0] = (ImageView) findViewById(R.id.pion1B);
        this.pion2[1] = (ImageView) findViewById(R.id.pion2B);
        this.pion2[2] = (ImageView) findViewById(R.id.pion3B);
        this.pause=(ImageView) findViewById(R.id.pause);

        Animation anim=new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(1050);
        anim.getStartOffset();


//trouver chaque boutons par leur ID

        buttons = new Button[3][3];
        buttons[0][0] = findViewById(R.id.case00);
        buttons[0][1] = findViewById(R.id.case01);
        buttons[0][2] = findViewById(R.id.case02);
        buttons[1][0] = findViewById(R.id.case10);
        buttons[1][1] = findViewById(R.id.case11);
        buttons[1][2] = findViewById(R.id.case12);
        buttons[2][0] = findViewById(R.id.case20);
        buttons[2][1] = findViewById(R.id.case21);
        buttons[2][2] = findViewById(R.id.case22);


        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                final int i = x;
                final int j = y;
                buttons[i][j].startAnimation(anim);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soundEffect();
                        Position posBouton = new Position(buttons[i][j].getX(), buttons[i][j].getY()); //position du bouton (case) cliqué

                        //générer les pions
                        if (caseVide(new Position(i, j)) && poser) {
                            if (tour == 1) {
                                poserPion(new Position(i, j), 1, dernierPion1);
                                deplacerPion(1, dernierPion1, posBouton);
                                pion1[dernierPion1].setAlpha(1f);
                                dernierPion1++;
                                soundEffect();


                            } else {
                                poserPion(new Position(i, j), 2, dernierPion2);
                               deplacerPion(2, dernierPion2, posBouton);
                                pion2[dernierPion2].setAlpha(1f);
                                dernierPion2++;
                                soundEffect();

                            }

                            nPion++;
                            if (nPion == 6) {
                                poser = false;//on ne peut plus poser

                            }

                            changerTour();
                            soundEffect();


                        } else if (!caseVide(new Position(i, j))) {
                            if (tour == 1 && plateau[i][j] == 1) {
                                iBouger = i;
                                jBouger = j;
                                soundEffect();
                            } else if (tour == 2 && plateau[i][j] == 2) {
                                iBouger = i;
                                jBouger = j;
                                soundEffect();
                            }

                        } else if (pionDejaSelectione() && caseVide(new Position(i, j)) && peutBouger(iBouger, jBouger, i, j)) {
                            bougerPion(new Position(i, j), new Position(iBouger, jBouger));
                            soundEffect();

                            if (tour == 1) {
                                deplacerPion(1, plateau2[i][j], posBouton);
                                soundEffect();
                                checkWinner(tour);


                            } else {
                                deplacerPion(2, plateau2[i][j], posBouton);
                                soundEffect();
                                checkWinner(tour);

                            }
                            iBouger = -1;
                            jBouger = -1;
                            changerTour();
                            soundEffect();

                        }
                        soundEffect();
                    }


                });
                boutonPause();

            }
        }

    }
      private boolean soundEffect(){
          mediaplayer.start();
          return true;
      }

    @Override
    protected void onPause() {
        super.onPause();
        gamePaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        gamePaused = false;
    }


    //méthode
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

    private void deplacerPion(int joueur, int numPion, Position p) {
        if (joueur == 1) {
            pion1[numPion].setX(p.getX());
            pion1[numPion].setY(p.getY());
        } else {
            pion2[numPion].setX(p.getX());
            pion2[numPion].setY(p.getY());
        }
    }

    private void poserPion(Position p, int joueur, int nPion) {
        plateau[(int) p.getX()][(int) p.getY()] = joueur;
        plateau2[(int) p.getX()][(int) p.getY()] = nPion;
    }

    private void bougerPion(Position p, Position bouger) {
        plateau[(int) p.getX()][(int) p.getY()] = plateau[(int) bouger.getX()][(int) bouger.getY()];
        plateau[(int) bouger.getX()][(int) bouger.getY()] = 0;
        plateau2[(int) p.getX()][(int) p.getY()] = plateau2[(int) bouger.getX()][(int) bouger.getY()];
        plateau2[(int) bouger.getX()][(int) bouger.getY()] = 0;
    }

    private int getPion(Position p) {
        return plateau[(int) p.getX()][(int) p.getY()];//plateau virtuel
    }

    private boolean caseVide(Position p) {//plateau virtuel sans pion
        if (getPion(p) != 1 && getPion(p) != 2)
            return true;
        else
            return false;
    }

    private boolean pionDejaSelectione() {
        return iBouger > -1;
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

        if (i == 1 && j == 1 && caseVide(new Position(newX, newY)))// nouveau position
            return true; // retourne vraie
        else {

            for (int k = 0; k < 3; k++) {
                if (d[i][j][k].equals(newStr) && caseVide(new Position(newX, newY)))
                    return true;
            }
        }
        errorClick.start();
        mediaplayer.pause();
        return false;
    }



    private boolean checkWinner(int joueur) {
            pop pop=new pop(this);
        for (int i = 0; i < 3; i++) {
            if (plateau[i][0] == joueur && plateau[i][1] == joueur && plateau[i][2] == joueur && poser==false && iBouger!=-1) {
                winnerSound.start();
                pop.finish("Partie terminée!");
                pop.getText( " Vainqueur: joueur "+tour);
                pop.launch();
                pop.restartButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent otherActivity=new Intent(getApplicationContext(),PlateaudujeuActivity.class);
                        startActivity(otherActivity);
                        mediaplayer.start();
                        finish();
                    }
                });

                pop.menuButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent otherActivity=new Intent(getApplicationContext(),MainActivity_SECONDAIRE.class);
                        startActivity(otherActivity);
                        mediaplayer.start();
                        finish();
                    }
                });

                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (plateau[0][j] == joueur && plateau[1][j] == joueur && plateau[2][j] == joueur && poser==false && iBouger!=-1) {
                winnerSound.start();
                onPause();
                pop.finish("Partie terminée!");
                pop.getText( " Vainqueur: joueur "+tour);
                pop.launch();
                pop.restartButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent otherActivity=new Intent(getApplicationContext(),PlateaudujeuActivity.class);
                        startActivity(otherActivity);
                        mediaplayer.start();
                        finish();
                    }
                });

                pop.menuButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent otherActivity=new Intent(getApplicationContext(),MainActivity_SECONDAIRE.class);
                        startActivity(otherActivity);
                        mediaplayer.start();
                        finish();
                    }
                });

                return true;
            }
        }

        if (plateau[0][0] == joueur && plateau[1][1] == joueur && plateau[2][2] == joueur && poser==false ) {
            winnerSound.start();
            onPause();
            pop.finish("Partie terminée!   Joueur "+tour+" a gagné");
            pop.launch();
            pop.getText( " Vainqueur: joueur "+tour);
            pop.restartButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent otherActivity=new Intent(getApplicationContext(),PlateaudujeuActivity.class);
                    startActivity(otherActivity);
                    mediaplayer.start();
                    finish();
                }
            });

            pop.menuButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent otherActivity=new Intent(getApplicationContext(),MainActivity_SECONDAIRE.class);
                    startActivity(otherActivity);
                    mediaplayer.start();
                    finish();
                }
            });


            return true;

        }

        if (plateau[2][0] == joueur && plateau[1][1] == joueur && plateau[0][2] == joueur && poser==false && iBouger!=-1) {
            winnerSound.start();
            onPause();
            pop.finish("Partie terminée!");
            pop.getText( " Vainqueur: joueur "+tour);
            pop.launch();
            pop.restartButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent otherActivity=new Intent(getApplicationContext(),PlateaudujeuActivity.class);
                    startActivity(otherActivity);
                    mediaplayer.start();
                    finish();
                }
            });

            pop.menuButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent otherActivity=new Intent(getApplicationContext(),MainActivity_SECONDAIRE.class);
                    startActivity(otherActivity);
                    mediaplayer.start();
                    finish();
                }
            });


            return true;

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
                        mediaplayer.start();
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
                        mediaplayer.stop();
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





