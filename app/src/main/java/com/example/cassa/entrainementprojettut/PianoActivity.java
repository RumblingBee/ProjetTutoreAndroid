package com.example.cassa.entrainementprojettut;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cassa.entrainementprojettut.pianoGame.FactoryMusicControler;
import com.example.cassa.entrainementprojettut.pianoGame.IControlerMusic;
import java.util.List;

public class PianoActivity extends GameActivity implements View.OnClickListener {

    private Button mButtonDo;
    private Button mButtonRe;
    private Button mButtonMi;
    private Button mButtonFa;
    private Button mButtonSol;
    private Button mButtonLa;
    private Button mButtonSi;

    private TextView life;

    private Button[] buttonsTab = {mButtonDo, mButtonRe, mButtonMi, mButtonFa, mButtonSol, mButtonLa, mButtonSi};
    private int idKey;

    IControlerMusic controlerMusic;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);


        music = R.raw.geography_music;
        //startBackgroundMusic(PianoActivity.this, music);

        setPianoButton();

        displayLevelChoice(PianoActivity.this,"listeNiveau",3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (levelChosen != 0) {
                    generatParty();
                } else {
                    PianoActivity.this.onStop();
                    dialog.show();
                }

            }
        });

    }

    private void generatParty() {
        //controlerMusic = new ControlerMusicEasy(levelChosen);
        controlerMusic = FactoryMusicControler.createControlerMusic(levelChosen);
        showSequence();
    }



    private void showSequence() {
        enableButton(false);
        List<Integer> listId = controlerMusic.getIdSequence();
        switchButtonGray();
        for (int id = 0; id<listId.size(); id++) {
            idKey = listId.get(id) - 1;

            //On redéfinit les Runnables
            int timeGreen = (id+1)*1000+200;
            int timeGray = ((id+1)*2000)-(id*1000);

            handler.postDelayed(runGreen(idKey, (View) buttonsTab[idKey]), timeGreen);
            handler.postDelayed(runGray(idKey), timeGray);
        }

        handler.postDelayed(enableButton(), listId.size() * 1500);

    }

    private void enableButton(boolean enable) {
        for(Button button: buttonsTab){
            button.setEnabled(enable);
        }
    }


    private Runnable runGray(final int pi){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                buttonsTab[pi].setBackgroundColor(Color.GRAY);

            }
        };
        return runnable;
    }


    private Runnable runGreen(final int pi, final View note){

        Runnable greenRunnable = new Runnable() {
            @Override
            public void run() {
                buttonsTab[pi].setBackgroundColor(Color.GREEN);
                controlerMusic.playSong(PianoActivity.this, note);
            }
        };
        return greenRunnable;
    }

    private Runnable enableButton(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                enableButton(true);
            }
        };

        return runnable;
    }


    private void setPianoButton() {

        life = (TextView) findViewById(R.id.activity_piano_life);

        buttonsTab[0] = (Button) findViewById(R.id.activity_piano_key_do);
        buttonsTab[1] = (Button) findViewById(R.id.activity_piano_key_re);
        buttonsTab[2] = (Button) findViewById(R.id.activity_piano_key_mi);
        buttonsTab[3] = (Button) findViewById(R.id.activity_piano_key_fa);
        buttonsTab[4] = (Button) findViewById(R.id.activity_piano_key_sol);
        buttonsTab[5] = (Button) findViewById(R.id.activity_piano_key_la);
        buttonsTab[6] = (Button) findViewById(R.id.activity_piano_key_si);

        buttonsTab[0].setOnClickListener(this);
        buttonsTab[1].setOnClickListener(this);
        buttonsTab[2].setOnClickListener(this);
        buttonsTab[3].setOnClickListener(this);
        buttonsTab[4].setOnClickListener(this);
        buttonsTab[5].setOnClickListener(this);
        buttonsTab[6].setOnClickListener(this);

        switchButtonGray();
    }

    private void switchButtonGray() {
        for (Button button: buttonsTab){
            button.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        int keyId = Integer.parseInt((String) v.getTag());
        int answer = controlerMusic.checkKey(keyId);
        controlerMusic.playSong(PianoActivity.this, v);
        v.setBackgroundColor(Color.YELLOW);

        if (answer>=0){
            showText("Bravo !");
            if(controlerMusic.songFinished()){
                showResultScreen(this,true,false,0);
            }else{
                if(answer==0){
                    showSequence();
                }
            }
        }else{
            showText("Dommage !");
            controlerMusic.falseAnswerConsequences();
            if(controlerMusic.isDead()){
                controlerMusic.setLife(life);
                showResultScreen(this,false,false,0);
            }else {
                controlerMusic.setLife(life);
                showSequence();
            }
        }


    }

}
