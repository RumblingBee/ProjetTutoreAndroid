package com.example.cassa.entrainementprojettut;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AdditionActivity extends GameActivity implements View.OnClickListener{

    private TextView mNombre1;
    private TextView mNombre2;
    private TextView mSigne;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    private Operation op;

    private int gNbReponsesCorectes;

    private MediaPlayer playerEvent;

    protected Runnable activerBoutons=new Runnable() {
        @Override
        public void run() {
            activerBoutons();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        initialiserPartie();
        mMusique = R.raw.bensound_retrosoul;
        lancerBgMusique(AdditionActivity.this,mMusique);
        afficherChoixNiveaux(AdditionActivity.this,"listeClasse",5);



    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            if (niveauChoisi != 0) {

                genererAddition();
                lancerCourse(AdditionActivity.this,60000,R.id.acivity_addition_pos1_img,R.id.activity_addition_ordi_img);
            } else {
                AdditionActivity.this.onStop();
               dialog.show();
            }

        }
    });




        playerEvent= MediaPlayer.create(AdditionActivity.this,R.raw.envent_sound);

        gNbReponsesCorectes = 0;
// On recupère les widgets

        mNombre1 = (TextView) findViewById(R.id.activity_addition_nombre1_textview);
        mNombre2 = (TextView) findViewById(R.id.activity_addition_nombre2_textview);
        mSigne =(TextView)findViewById(R.id.activity_addition_operateur_textview);

        mButton1 = (Button)findViewById(R.id.activity_addition_rep1_btn);
        mButton2 = (Button)findViewById(R.id.activity_addition_rep2_btn);
        mButton3 = (Button)findViewById(R.id.activity_addition_rep3_btn);
        mButton4 = (Button)findViewById(R.id.activity_addition_rep4_btn);

    }

    protected void genererAddition(){

         op = new Operation(niveauChoisi);

        //Affichage de l'opération

        mNombre1.setText(""+op.getTerme1());
        mNombre2.setText(""+op.getTerme2());
        mSigne.setText(""+op.getSigne());

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        melangerReponse();

    }

    protected void melangerReponse(){

        int positionReponse=(int)(Math.random() * 4);

        Button tabButton[] = {mButton1,mButton2,mButton3,mButton4};
        int tabReponse[] = new int[4];
        int i;

        for(i = 0;i<4;i++){
            if(i< positionReponse){
                tabReponse[i] = op.getReponse() - (positionReponse - i);
            }
            else if(i == positionReponse){
                tabReponse[i] = op.getReponse();
            }
            else{
               tabReponse[i] = op.getReponse() + (i - positionReponse);
            }
        }

        for(i=0;i<4; i++) {
            //int indiceListe = (int) (Math.random() * listeReponses.size());
            tabButton[i].setTag(tabReponse[i]);
            tabButton[i].setText("" + tabReponse[i]);

        }
    }

    public boolean verifierReponse(int reponseEnvoyee,int reponseCorrecte ){

        float largeurEcran = retourTailleEcran();


        if(reponseCorrecte == reponseEnvoyee){

            gNbReponsesCorectes++;
            afficherTexte("Bravo!");
            playerEvent.start();

            bougerImage(mImagePos1,positionImageJoueur+(largeurEcran/10),600,positionImageJoueur);
            positionImageJoueur = positionImageJoueur + (largeurEcran/10);

            if(gNbReponsesCorectes == 10){
                afficherEcranFin(AdditionActivity.this,true,false,0);
            }
            else{
                genererAddition();
            }

            return true;

        }
        else{
            afficherTexte("Dommage, la réponse était " + reponseCorrecte);
            genererAddition();
            return false;
        }

    }

    protected void griserBoutons(){

        mButton1.setEnabled(false);
        mButton2.setEnabled(false);
        mButton3.setEnabled(false);
        mButton4.setEnabled(false);

        mButton1.setBackgroundColor(Color.rgb(99,99,99));
        mButton2.setBackgroundColor(Color.rgb(99,99,99));
        mButton3.setBackgroundColor(Color.rgb(99,99,99));
        mButton4.setBackgroundColor(Color.rgb(99,99,99));
    }

    protected void activerBoutons(){

        mButton1.setEnabled(true);
        mButton2.setEnabled(true);
        mButton3.setEnabled(true);
        mButton4.setEnabled(true);

        mButton1.setBackgroundColor(Color.rgb(255,0,0));
        mButton2.setBackgroundColor(Color.rgb(0,255,0));
        mButton3.setBackgroundColor(Color.rgb(0,0,255));
        mButton4.setBackgroundColor(Color.rgb(255,255,0));
    }

    @Override
    public void onClick(View view) {
        int reponseEnvoyee = (int) view.getTag();
        griserBoutons();
       if(verifierReponse(reponseEnvoyee,op.getReponse())) {

           handler.postDelayed(activerBoutons, 800);
       }
       else{
           handler.postDelayed(activerBoutons, 2100);

       }

    }
}