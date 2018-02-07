package com.example.cassa.entrainementprojettut;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cassa.entrainementprojettut.flag.ControllerFlagBank;

import java.util.Random;

public class FlagActivity extends GameActivity implements View.OnClickListener {
    private  ImageView mFlag1;
    private ImageView mFlag2;
    private ImageView mFlag3;
    private ImageView mFlag4;

    private  TextView mCountryName;
    private TextView mScore;

    private String gGoodAnswer;

    private ControllerFlagBank controllerFlagBank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);
        music = R.raw.bensound_funnysong;
        startBackgroundMusic(FlagActivity.this, music);

        initializeGame();
        initializeFlag();

        initializeCountryNameAndScore();

        displayLevelChoice(FlagActivity.this,"listeNiveau",3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (levelChosen != 0) {

                    generateParty();
                    launchTimer(FlagActivity.this,30000,R.id.acivity_flag_player_img,R.id.activity_flag_IA_img);

                } else {
                    FlagActivity.this.onStop();
                    dialog.show();
                }

            }
        });


    }


    private void initializeCountryNameAndScore() {
        mCountryName = (TextView)findViewById(R.id.activity_flag_name01_txt);
        mScore = (TextView)findViewById(R.id.activity_flag_score_txt);

        // On initialise le score à 0
        scoreNumerique = 0;
        mScore.setText("0");
    }


    private void initializeFlag() {
        mFlag1 = (ImageView)findViewById(R.id.activity_flag_flag01);
        mFlag2 = (ImageView)findViewById(R.id.activity_flag_flag02);
        mFlag3 = (ImageView)findViewById(R.id.activity_flag_flag03);
        mFlag4 = (ImageView)findViewById(R.id.activity_flag_flag04);

        mFlag1.setOnClickListener(this);
        mFlag2.setOnClickListener(this);
        mFlag3.setOnClickListener(this);
        mFlag4.setOnClickListener(this);
    }


    protected void generateParty(){

        controllerFlagBank = new ControllerFlagBank(levelChosen);

        ImageView listeDrapeau[] = {mFlag1, mFlag2, mFlag3, mFlag4};

        enableFlag();

        generateCountryToFind(controllerFlagBank);

        generateFlagChoice(controllerFlagBank, listeDrapeau);

    }



    private void generateFlagChoice(ControllerFlagBank controllerFlagBank, ImageView[] flagList) {
        for(int i=0; i <4;i++) {
            flagList[i].setImageResource(controllerFlagBank.getFlag(i).getmRessource());
            flagList[i].setTag(controllerFlagBank.getFlag(i).getmNameCountry());
        }
    }



    private void generateCountryToFind(ControllerFlagBank controllerFlagBank) {
        Random rand = new Random();
        int numPaysMystere = rand.nextInt(4);

        mCountryName.setText(controllerFlagBank.getFlag(numPaysMystere).getmNameCountry());
        gGoodAnswer = controllerFlagBank.getFlag(numPaysMystere).getmNameCountry();
    }



    private void enableFlag() {
        mFlag1.setEnabled(true);
        mFlag2.setEnabled(true);
        mFlag3.setEnabled(true);
        mFlag4.setEnabled(true);

        mFlag1.setColorFilter(0);
        mFlag2.setColorFilter(0);
        mFlag3.setColorFilter(0);
        mFlag4.setColorFilter(0);
    }



    protected  void checkAnswer(ImageView v, String country){

        if(country == gGoodAnswer){

            showText("Bravo");
            scoreNumerique = scoreNumerique + 5;
            mScore.setText(""+scoreNumerique);
            generateParty();
            if(scoreNumerique >= 50 ){
                unableLoose();
                showText("Bravo, tu as gagné!");
                showResultScreen(FlagActivity.this,true,true,scoreNumerique);
            }
        }
        else{
            showText("Dommage");

            scoreNumerique = scoreNumerique - 2;
            v.setBackgroundColor(Color.argb(150,200,200,200));
            v.setEnabled(false);
            mScore.setText(""+scoreNumerique);
            v.setColorFilter(R.color.material_grey_600);
        }

    }


    @Override
    public void onClick(View view) {
        String paysSelectione = (String) view.getTag();
        System.out.println("paysSelectione: "+paysSelectione);

        checkAnswer((ImageView) view,paysSelectione);
    }
}
