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

    private Button mCountryName1;
    private Button mCountryName2;
    private Button mCountryName3;
    private Button mCountryName4;

    private String gGoodAnswer;



    private ControllerFlagBank controllerFlagBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_flag);

        music = R.raw.bensound_goinghigher;
        initializeGame();

        startBackgroundMusic(ReverseFlagActivity.this, music);


        initializeCountryNames();

        initializeAnswersAndScore();


        displayLevelChoice(ReverseFlagActivity.this, "listeNiveau", 3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(levelChosen != 0){
                    generateParty();
                    launchTimer(ReverseFlagActivity.this,35000,R.id.acivity_flag_player_img,R.id.activity_flag_IA_img);

                }else{
                    ReverseFlagActivity.this.onStop();
                    dialog.show();
                }
            }
        });
    }



    private void initializeAnswersAndScore() {
        mFlag = (ImageView) findViewById(R.id.activity_reverse_flag_drapeau);
        mScore = (TextView) findViewById(R.id.activity_reverse_flag_score);

        mScore.setText("0");
    }



    private void initializeCountryNames() {
        mCountryName1 = (Button) findViewById(R.id.activity_reverse_flag_name1);
        mCountryName2 = (Button) findViewById(R.id.activity_reverse_flag_name2);
        mCountryName3 = (Button) findViewById(R.id.activity_reverse_flag_name3);
        mCountryName4 = (Button) findViewById(R.id.activity_reverse_flag_name4);

        mCountryName1.setOnClickListener(this);
        mCountryName2.setOnClickListener(this);
        mCountryName3.setOnClickListener(this);
        mCountryName4.setOnClickListener(this);
    }



    protected void generateParty(){

        controllerFlagBank = new ControllerFlagBank(levelChosen);


        Button[] buttonList = {mCountryName1, mCountryName2, mCountryName3, mCountryName4};

        enableCoutryNames();

        generateAnswer(controllerFlagBank);

        generateChoices(controllerFlagBank, buttonList);


    }


    private void generateChoices(ControllerFlagBank flagBank, Button[] buttonList) {
        for(int i = 0; i<4; i++){
            buttonList[i].setText(flagBank.getFlag(i).getmNameCountry());
            buttonList[i].setTag(flagBank.getFlag(i).getmNameCountry());
        }
    }


    private void generateAnswer(ControllerFlagBank flagBank) {
        Random random = new Random();
        int answerNumber = random.nextInt(4);

        gGoodAnswer = flagBank.getFlag(answerNumber).getmNameCountry();
        mFlag.setImageResource(flagBank.getFlag(answerNumber).getmRessource());
    }



    private void enableCoutryNames() {
        mCountryName1.setEnabled(true);
        mCountryName2.setEnabled(true);
        mCountryName3.setEnabled(true);
        mCountryName4.setEnabled(true);
    }




    public void checkAnswer(Button button, String mNomPays){
        if(gGoodAnswer.equals(mNomPays)){

            scoreNumerique+=5;
            mScore.setText(""+scoreNumerique);
            generateParty();
            if(scoreNumerique >= 50){
                showText("Bravo, tu as gagné!");
                unableLoose();
                showResultScreen(ReverseFlagActivity.this,true,true,scoreNumerique);

            }else {
                showText("Bravo");
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

        checkAnswer((Button) view, mNomPays);
    }

}
