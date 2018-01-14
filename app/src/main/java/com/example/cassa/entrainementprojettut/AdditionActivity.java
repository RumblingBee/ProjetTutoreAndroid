package com.example.cassa.entrainementprojettut;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cassa.entrainementprojettut.operationGame.OperationController;

public class AdditionActivity extends GameActivity implements View.OnClickListener{

    private TextView mNumber1;
    private TextView mNumber2;
    private TextView mSigne;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    private OperationController ctrl;

    private int gCorrectAnswers;

    private MediaPlayer playerEvent;

    protected Runnable activateButton=new Runnable() {
        @Override
        public void run() {
            activateButtons();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);



        initializeGame();
        music = R.raw.bensound_retrosoul;
        startBackgroundMusic(AdditionActivity.this, music);
        displayLevelChoice(AdditionActivity.this,"listeClasse",5);





    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            if (levelChosen != 0) {


                generateOperation();
                launchTimer(AdditionActivity.this,60000,R.id.acivity_addition_pos1_img,R.id.activity_addition_ordi_img);

                generateOperation();

            } else {
                AdditionActivity.this.onStop();
               dialog.show();
            }

        }
    });




        playerEvent= MediaPlayer.create(AdditionActivity.this,R.raw.envent_sound);

        gCorrectAnswers = 0;
// On recupère les widgets

        mNumber1 = (TextView) findViewById(R.id.activity_addition_nombre1_textview);
        mNumber2 = (TextView) findViewById(R.id.activity_addition_nombre2_textview);
        mSigne =(TextView)findViewById(R.id.activity_addition_operateur_textview);

        mButton1 = (Button)findViewById(R.id.activity_addition_rep1_btn);
        mButton2 = (Button)findViewById(R.id.activity_addition_rep2_btn);
        mButton3 = (Button)findViewById(R.id.activity_addition_rep3_btn);
        mButton4 = (Button)findViewById(R.id.activity_addition_rep4_btn);

    }

    @SuppressLint("SetTextI18n")
    protected void generateOperation(){


         ctrl = new OperationController(levelChosen);


        //Affichage de l'opération

        int[] termesOperation=ctrl.getTermesOperation();

        mNumber1.setText(""+termesOperation[0]);
        mNumber2.setText(""+termesOperation[1]);
        mSigne.setText(""+ctrl.getSigneOperation());

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        shuffleAnswers();

    }

    protected void shuffleAnswers(){

        int answerPosition=(int)(Math.random() * 4);

        Button tabButton[] = {mButton1,mButton2,mButton3,mButton4};
        int tabAnswer[] = new int[4];
        int i;

        for(i = 0;i<4;i++){
            if(i< answerPosition){
                tabAnswer[i] = ctrl.getAnswer() - (answerPosition - i);
            }
            else if(i == answerPosition){
                tabAnswer[i] = ctrl.getAnswer();
            }
            else{
               tabAnswer[i] = ctrl.getAnswer() + (i - answerPosition);
            }
        }

        for(i=0;i<4; i++) {
            //int indiceListe = (int) (Math.random() * listeReponses.size());
            tabButton[i].setTag(tabAnswer[i]);
            tabButton[i].setText("" + tabAnswer[i]);

        }
    }

    public boolean checkAnswer(int reponseEnvoyee){


        float screenWidth = getScreenWidth();


        if(ctrl.checkAnswer(reponseEnvoyee)){


            gCorrectAnswers++;
            showText("Bravo!");
            playerEvent.start();

            moveImage(playerImage,playerImagePosition+(screenWidth/10),600,playerImagePosition);
            playerImagePosition = playerImagePosition + (screenWidth/10);

            if(gCorrectAnswers == 10){
                showResultScreen(AdditionActivity.this,true,false,0);

            }
            else{
                generateOperation();
            }

            return true;

        }
        else{

            showText("Dommage, la réponse était " + ctrl.getAnswer());
            generateOperation();

            return false;
        }

    }

    protected void disableButton(){

        mButton1.setEnabled(false);
        mButton2.setEnabled(false);
        mButton3.setEnabled(false);
        mButton4.setEnabled(false);

        mButton1.setBackgroundColor(Color.rgb(99,99,99));
        mButton2.setBackgroundColor(Color.rgb(99,99,99));
        mButton3.setBackgroundColor(Color.rgb(99,99,99));
        mButton4.setBackgroundColor(Color.rgb(99,99,99));
    }

    protected void activateButtons(){

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
        disableButton();
       if(checkAnswer(reponseEnvoyee)) {

           handler.postDelayed(activateButton, 800);
       }
       else{
           handler.postDelayed(activateButton, 2100);

       }

    }
}