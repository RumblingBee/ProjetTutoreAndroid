package com.example.cassa.entrainementprojettut;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
    int diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

        mDrapeau1 = (ImageView)findViewById(R.id.activity_flag_flag01);
        mDrapeau2 = (ImageView)findViewById(R.id.activity_flag_flag02);
        mDrapeau3 = (ImageView)findViewById(R.id.activity_flag_flag03);
        mDrapeau4 = (ImageView)findViewById(R.id.activity_flag_flag04);

        mDrapeau1.setOnClickListener(this);
        mDrapeau2.setOnClickListener(this);
        mDrapeau3.setOnClickListener(this);
        mDrapeau4.setOnClickListener(this);

        mNomPays = (TextView)findViewById(R.id.activity_flag_name01_txt);
        mScore = (TextView)findViewById(R.id.activity_flag_score_txt);

        diff = getIntent().getIntExtra("diff", 1);

        // On initialise le score à 0
        gScore = 0;
        gNbBonneReponse = 0;

        mScore.setText("0");

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
    protected void genererPartie(){

        FlagBank flagBank = new FlagBank(niveauChoisi);


        ImageView listeDrapeau[] = {mDrapeau1,mDrapeau2,mDrapeau3,mDrapeau4};

//On réactive tous les drapeaux

        mDrapeau1.setEnabled(true);
        mDrapeau2.setEnabled(true);
        mDrapeau3.setEnabled(true);
        mDrapeau4.setEnabled(true);

        mDrapeau1.setColorFilter(0);
        mDrapeau2.setColorFilter(0);
        mDrapeau3.setColorFilter(0);
        mDrapeau4.setColorFilter(0);

//On génère le nom du pays
        Random rand = new Random();
        int numPaysMystere = rand.nextInt(4);

        mNomPays.setText(flagBank.getFlag(numPaysMystere).getmNameCountry());
        gBonneReponse = flagBank.getFlag(numPaysMystere).getmNameCountry();

//On génère les drapeaux

        for(int i=0; i <4;i++) {
            listeDrapeau[i].setImageResource(flagBank.getFlag(i).getmRessource());
            listeDrapeau[i].setTag(flagBank.getFlag(i).getmNameCountry());

        }

    }
protected  void verifierReponse(ImageView v,String pPays){
    int duration = Toast.LENGTH_SHORT;
    Toast toast;
        if(pPays == gBonneReponse ){

           toast  = Toast.makeText(getApplicationContext(), "Bravo!", duration);
           gScore = gScore + 5;
           gNbBonneReponse = gNbBonneReponse + 1;
           mScore.setText(""+gScore);
            genererPartie();

           if(gNbBonneReponse == 10 ){

               afficherEcranFin(FlagActivity.this,true,true,gScore);
           }



        }
        else{

            toast  = Toast.makeText(getApplicationContext(), "Dommage", duration);
            gScore = gScore - 2;
            v.setBackgroundColor(Color.argb(150,200,200,200));
            v.setEnabled(false);
            mScore.setText(""+gScore);
            v.setColorFilter(R.color.material_grey_600);
        }
    toast.show();

}


    @Override
    public void onClick(View view) {

        String paysSelectione = (String) view.getTag();

        verifierReponse((ImageView) view,paysSelectione);
    }
}