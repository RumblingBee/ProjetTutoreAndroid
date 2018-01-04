package com.example.cassa.entrainementprojettut;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FlagActivity extends GameActivity implements View.OnClickListener {
    ImageView mDrapeau1;
    ImageView mDrapeau2;
    ImageView mDrapeau3;
    ImageView mDrapeau4;

    TextView mNomPays;
    TextView mScore;

    String gBonneReponse;

    int gScore,gNbBonneReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);
        lancerBgMusique(FlagActivity.this, R.raw.bensound_funnysong);

        initialisationDrapeaux();

        initialisationNomPaysEtScore();

        afficherChoixNiveaux(FlagActivity.this,"listeNiveau",3);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (niveauChoisi != 0) {
                    genererPartie();
                } else {
                    FlagActivity.this.onStop();
                    dialog.show();
                }

            }
        });


    }



    private void initialisationNomPaysEtScore() {
        mNomPays = (TextView)findViewById(R.id.activity_flag_name01_txt);
        mScore = (TextView)findViewById(R.id.activity_flag_score_txt);

        // On initialise le score à 0
        gScore = 0;
        gNbBonneReponse = 0;
        mScore.setText("0");
    }



    private void initialisationDrapeaux() {
        mDrapeau1 = (ImageView)findViewById(R.id.activity_flag_flag01);
        mDrapeau2 = (ImageView)findViewById(R.id.activity_flag_flag02);
        mDrapeau3 = (ImageView)findViewById(R.id.activity_flag_flag03);
        mDrapeau4 = (ImageView)findViewById(R.id.activity_flag_flag04);

        mDrapeau1.setOnClickListener(this);
        mDrapeau2.setOnClickListener(this);
        mDrapeau3.setOnClickListener(this);
        mDrapeau4.setOnClickListener(this);
    }



    protected void genererPartie(){

        FlagBank flagBank = new FlagBank(niveauChoisi);
        ImageView listeDrapeau[] = {mDrapeau1,mDrapeau2,mDrapeau3,mDrapeau4};

        //On réactive tous les drapeaux
        reactivationDrapeaux();
        //On génère le nom du pays à trouver
        generationPaysATrouver(flagBank);

        //On génère les drapeaux
        generationChoixDrapeaux(flagBank, listeDrapeau);

    }



    private void generationChoixDrapeaux(FlagBank flagBank, ImageView[] listeDrapeau) {
        for(int i=0; i <4;i++) {
            listeDrapeau[i].setImageResource(flagBank.getFlag(i).getmRessource());
            listeDrapeau[i].setTag(flagBank.getFlag(i).getmNameCountry());
        }
    }



    private void generationPaysATrouver(FlagBank flagBank) {
        Random rand = new Random();
        int numPaysMystere = rand.nextInt(4);

        mNomPays.setText(flagBank.getFlag(numPaysMystere).getmNameCountry());
        gBonneReponse = flagBank.getFlag(numPaysMystere).getmNameCountry();
    }



    private void reactivationDrapeaux() {
        mDrapeau1.setEnabled(true);
        mDrapeau2.setEnabled(true);
        mDrapeau3.setEnabled(true);
        mDrapeau4.setEnabled(true);

        mDrapeau1.setColorFilter(0);
        mDrapeau2.setColorFilter(0);
        mDrapeau3.setColorFilter(0);
        mDrapeau4.setColorFilter(0);
    }



    protected  void verifierReponse(ImageView v,String pPays){

        if(pPays == gBonneReponse ){
            afficherTexte("Bravo");
            gScore = gScore + 5;
            gNbBonneReponse = gNbBonneReponse + 1;
            mScore.setText(""+gScore);
            genererPartie();
            if(gNbBonneReponse == 10 ){
                afficherEcranFin(FlagActivity.this,true,true,gScore);
            }
        }
        else{
            afficherTexte("Dommage");
            gScore = gScore - 2;
            v.setBackgroundColor(Color.argb(150,200,200,200));
            v.setEnabled(false);
            mScore.setText(""+gScore);
            v.setColorFilter(R.color.material_grey_600);
        }

    }


    @Override
    public void onClick(View view) {
        String paysSelectione = (String) view.getTag();

        verifierReponse((ImageView) view,paysSelectione);
    }

    @Override
    public void onBackPressed(){
        bgPlayer.stop();
        Intent ecranMenu = new Intent(FlagActivity.this, MainActivity.class);
        startActivity(ecranMenu);
        super.onBackPressed();
    }

    @Override
    public void onPause(){
        super.onPause();
        bgPlayer.stop();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        bgPlayer.start();
    }
    @Override
    protected void onResume() {
        if(bgPlayer != null){
            lancerBgMusique(FlagActivity.this, R.raw.bensound_funnysong);
        }
        super.onResume();
    }
}
