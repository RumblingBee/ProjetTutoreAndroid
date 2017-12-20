package com.example.cassa.entrainementprojettut;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ReverseFlagActivity extends GameActivity implements View.OnClickListener {

    private ImageView mFlag;
    private TextView mScore;

    private Button mNomPays1;
    private Button mNomPays2;
    private Button mNomPays3;
    private Button mNomPays4;

    private int diff;
    private String gBonneReponse;
    private int gScore, gNbBonneReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_flag);

        lancerBgMusique(ReverseFlagActivity.this, R.raw.bensound_goinghigher);

        mFlag = (ImageView) findViewById(R.id.activity_reverse_flag_drapeau);
        mScore = (TextView) findViewById(R.id.activity_reverse_flag_score);

        mNomPays1 = (Button) findViewById(R.id.activity_reverse_flag_name1);
        mNomPays2 = (Button) findViewById(R.id.activity_reverse_flag_name2);
        mNomPays3 = (Button) findViewById(R.id.activity_reverse_flag_name3);
        mNomPays4 = (Button) findViewById(R.id.activity_reverse_flag_name4);

        mNomPays1.setOnClickListener(this);
        mNomPays2.setOnClickListener(this);
        mNomPays3.setOnClickListener(this);
        mNomPays4.setOnClickListener(this);

        diff = getIntent().getIntExtra("diff", 1);

        gScore=0;
        gNbBonneReponse=0;
        mScore.setText("0");

        afficherChoixNiveaux(ReverseFlagActivity.this, "listeNiveau", 3);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(niveauChoisi != 0){
                    genererPartie();
                }else{
                    ReverseFlagActivity.this.onStop();
                    dialog.show();
                }
            }
        });
    }

    protected void genererPartie(){
        FlagBank flagBank = new FlagBank(niveauChoisi);

        Button[] mListeButton = {mNomPays1, mNomPays2, mNomPays3, mNomPays4};
        mNomPays1.setEnabled(true);
        mNomPays2.setEnabled(true);
        mNomPays3.setEnabled(true);
        mNomPays4.setEnabled(true);

        //mNomPays1.

        Random mRand = new Random();
        int mNumReponse = mRand.nextInt(4);

        gBonneReponse = flagBank.getFlag(mNumReponse).getmNameCountry();
        mFlag.setImageResource(flagBank.getFlag(mNumReponse).getmRessource());
        System.out.println("Reponse = "+ gBonneReponse);


        for(int i = 0; i<4; i++){
            mListeButton[i].setText(flagBank.getFlag(i).getmNameCountry());
            mListeButton[i].setTag(flagBank.getFlag(i).getmNameCountry());
        }


    }

    public void verifierReponse(Button button, String mNomPays){
        if(gBonneReponse.equals(mNomPays)){
            afficherTexte("Bravo");
            gScore+=5;
            gNbBonneReponse+=1;
            mScore.setText(""+gScore);
            genererPartie();
            if(gNbBonneReponse == 10){
                afficherEcranFin(ReverseFlagActivity.this, true, true, gScore);
            }
        }else{
            afficherTexte("Dommage");
            gScore-=2;
            button.setEnabled(false);
            mScore.setText(""+gScore);

        }
    }



    @Override
    public void onClick(View view){
        String mNomPays = (String) view.getTag();

        verifierReponse((Button) view, mNomPays);
    }

    @Override
    public void onPause(){
        super.onPause();
        bgPlayer.stop();
    }

    @Override
    public void onBackPressed(){
        bgPlayer.stop();
        Intent back = new Intent(ReverseFlagActivity.this, MainActivity.class);
        startActivity(back);
        super.onBackPressed();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        bgPlayer.start();
    }

}
