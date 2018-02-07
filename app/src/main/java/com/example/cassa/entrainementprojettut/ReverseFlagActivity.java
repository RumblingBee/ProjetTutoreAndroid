package com.example.cassa.entrainementprojettut;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cassa.entrainementprojettut.flag.ControllerFlagBank;

import java.util.Random;

public class ReverseFlagActivity extends GameActivity implements View.OnClickListener {

    private ImageView mFlag;
    private TextView mScore;

    private Button mNomPays1;
    private Button mNomPays2;
    private Button mNomPays3;
    private Button mNomPays4;

    private String gBonneReponse;
    private int gNbBonneReponse;

    private ControllerFlagBank controllerFlagBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_flag);

        music = R.raw.bensound_goinghigher;


        startBackgroundMusic(ReverseFlagActivity.this, music);


        initialisationNomPays();

        initialisationReponseEtScore();


        displayLevelChoice(ReverseFlagActivity.this, "listeNiveau", 3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(levelChosen != 0){
                    genererPartie();
                    launchTimer(ReverseFlagActivity.this,35000,R.id.acivity_flag_player_img,R.id.activity_flag_IA_img);

                }else{
                    ReverseFlagActivity.this.onStop();
                    dialog.show();
                }
            }
        });
    }



    private void initialisationReponseEtScore() {
        mFlag = (ImageView) findViewById(R.id.activity_reverse_flag_drapeau);
        mScore = (TextView) findViewById(R.id.activity_reverse_flag_score);

        scoreNumerique=0;
        gNbBonneReponse=0;
        mScore.setText("0");
    }



    private void initialisationNomPays() {
        mNomPays1 = (Button) findViewById(R.id.activity_reverse_flag_name1);
        mNomPays2 = (Button) findViewById(R.id.activity_reverse_flag_name2);
        mNomPays3 = (Button) findViewById(R.id.activity_reverse_flag_name3);
        mNomPays4 = (Button) findViewById(R.id.activity_reverse_flag_name4);

        mNomPays1.setOnClickListener(this);
        mNomPays2.setOnClickListener(this);
        mNomPays3.setOnClickListener(this);
        mNomPays4.setOnClickListener(this);
    }



    protected void genererPartie(){

        controllerFlagBank = new ControllerFlagBank(levelChosen);


        Button[] mListeButton = {mNomPays1, mNomPays2, mNomPays3, mNomPays4};

        reactiverNomPays();

        genererReponse(controllerFlagBank);

        genererChoix(controllerFlagBank, mListeButton);


    }


    private void genererChoix(ControllerFlagBank flagBank, Button[] mListeButton) {
        for(int i = 0; i<4; i++){
            mListeButton[i].setText(flagBank.getFlag(i).getmNameCountry());
            mListeButton[i].setTag(flagBank.getFlag(i).getmNameCountry());
        }
    }


    private void genererReponse(ControllerFlagBank flagBank) {
        Random mRand = new Random();
        int mNumReponse = mRand.nextInt(4);

        gBonneReponse = flagBank.getFlag(mNumReponse).getmNameCountry();
        mFlag.setImageResource(flagBank.getFlag(mNumReponse).getmRessource());
    }



    private void reactiverNomPays() {
        mNomPays1.setEnabled(true);
        mNomPays2.setEnabled(true);
        mNomPays3.setEnabled(true);
        mNomPays4.setEnabled(true);
    }



    public void verifierReponse(Button button, String mNomPays){
        if(gBonneReponse.equals(mNomPays)){
            moveImage(playerImage,playerImagePosition+(getScreenWidth()/10),600,playerImagePosition);
            playerImagePosition = playerImagePosition + (getScreenWidth()/10);
            showText("Bravo");

            scoreNumerique+=5;
            gNbBonneReponse+=1;
            mScore.setText(""+scoreNumerique);
            genererPartie();
            if(gNbBonneReponse == 10){
                unableLoose();
                //showResultScreen(ReverseFlagActivity.this, true, true, scoreNumerique);
            }
        }else{
            showText("Dommage");

            scoreNumerique-=2;
            button.setEnabled(false);
            mScore.setText(""+scoreNumerique);

        }
    }



    @Override
    public void onClick(View view){
        String mNomPays = (String) view.getTag();

        verifierReponse((Button) view, mNomPays);
    }

}
