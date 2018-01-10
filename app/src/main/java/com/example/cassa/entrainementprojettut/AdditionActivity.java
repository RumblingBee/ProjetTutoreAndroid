package com.example.cassa.entrainementprojettut;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cassa.entrainementprojettut.jeuDeCalcul.ControleurOperation;

public class AdditionActivity extends GameActivity implements View.OnClickListener{

    private TextView mNombre1;
    private TextView mNombre2;
    private TextView mSigne;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    private ControleurOperation ctrl;

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

        initializeGame();
        music = R.raw.bensound_retrosoul;
        startBackgroundMusic(AdditionActivity.this, music);
        showLevelChoice(AdditionActivity.this,"listeClasse",5);



    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            if (levelChosen != 0) {

                genererAddition();
                launchTimer(AdditionActivity.this,60000,R.id.acivity_addition_pos1_img,R.id.activity_addition_ordi_img);
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

    @SuppressLint("SetTextI18n")
    protected void genererAddition(){

         ctrl = new ControleurOperation(levelChosen);

        //Affichage de l'opération

        int[] termesOperation=ctrl.getTermesOperation();

        mNombre1.setText(""+termesOperation[0]);
        mNombre2.setText(""+termesOperation[1]);
        mSigne.setText(""+ctrl.getSigneOperation());

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
                tabReponse[i] = ctrl.getReponse() - (positionReponse - i);
            }
            else if(i == positionReponse){
                tabReponse[i] = ctrl.getReponse();
            }
            else{
               tabReponse[i] = ctrl.getReponse() + (i - positionReponse);
            }
        }

        for(i=0;i<4; i++) {
            //int indiceListe = (int) (Math.random() * listeReponses.size());
            tabButton[i].setTag(tabReponse[i]);
            tabButton[i].setText("" + tabReponse[i]);

        }
    }

    public boolean verifierReponse(int reponseEnvoyee){

        float largeurEcran = getScreenWidth();


        if(ctrl.verifierReponse(reponseEnvoyee)){

            gNbReponsesCorectes++;
            showText("Bravo!");
            playerEvent.start();

            moveImage(playerImage, playerImagePosition +(largeurEcran/10),600, playerImagePosition);
            playerImagePosition = playerImagePosition + (largeurEcran/10);

            if(gNbReponsesCorectes == 10){
                showResultScren(AdditionActivity.this,true,false,0);
            }
            else{
                genererAddition();
            }

            return true;

        }
        else{
            showText("Dommage, la réponse était " + ctrl.getReponse());
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
       if(verifierReponse(reponseEnvoyee)) {

           handler.postDelayed(activerBoutons, 800);
       }
       else{
           handler.postDelayed(activerBoutons, 2100);

       }

    }
}