package com.example.cassa.entrainementprojettut;

import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cassa.entrainementprojettut.pianoGame.ControlerMusic;

import java.util.List;

public class PianoActivity extends GameActivity implements View.OnClickListener {

    private Button mButtonDo;
    private Button mButtonRe;
    private Button mButtonMi;
    private Button mButtonFa;
    private Button mButtonSol;
    private Button mButtonLa;
    private Button mButtonSi;

    private Button[] buttonsTab = {mButtonDo, mButtonRe, mButtonMi, mButtonFa, mButtonSol, mButtonLa, mButtonSi};
    private int idKey;

    ControlerMusic controlerMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);


        music = R.raw.geography_music;
        startBackgroundMusic(PianoActivity.this, music);

        setPianoButton();

        generatParty();

    }

    private void generatParty() {
        //controlerMusic = new ControlerMusic(levelChosen);
        controlerMusic = new ControlerMusic();
        showSequence();
    }
private Runnable runGray(final int pi){

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println(""+idKey);
            buttonsTab[pi].setBackgroundColor(Color.GRAY);

        }
    };
   return runnable;
}
    private Runnable runGreen(final int pi){


        Runnable greenRunnable = new Runnable() {
            @Override
            public void run() {
                buttonsTab[pi].setBackgroundColor(Color.GREEN);
            }
        };
        return greenRunnable;
    }
    private void showSequence() {
        List<Integer> listId = controlerMusic.getIdSequence();
        Handler handler = new Handler();




        for (int id = 0; id<listId.size(); id++){

            idKey = listId.get(id)-1;
            //On redéfinit les Runnables



            handler.postDelayed(runGreen(idKey), (id+1)*1000);
            handler.postDelayed(runGray(idKey), (id+1)*2000);}

    }


    private void setPianoButton() {

        buttonsTab[0] = (Button) findViewById(R.id.activity_piano_touche_do);
        buttonsTab[1] = (Button) findViewById(R.id.activity_piano_touche_re);
        buttonsTab[2] = (Button) findViewById(R.id.activity_piano_touche_mi);
        buttonsTab[3] = (Button) findViewById(R.id.activity_piano_touche_fa);
        buttonsTab[4] = (Button) findViewById(R.id.activity_piano_touche_sol);
        buttonsTab[5] = (Button) findViewById(R.id.activity_piano_touche_la);
        buttonsTab[6] = (Button) findViewById(R.id.activity_piano_touche_si);

        buttonsTab[0].setOnClickListener(this);
        buttonsTab[1].setOnClickListener(this);
        buttonsTab[2].setOnClickListener(this);
        buttonsTab[3].setOnClickListener(this);
        buttonsTab[4].setOnClickListener(this);
        buttonsTab[5].setOnClickListener(this);
        buttonsTab[6].setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int keyId = Integer.parseInt((String) v.getTag());
        controlerMusic.checkKey(keyId);
        showSequence();
        if(controlerMusic.isDead()){
            showResultScreen(this,false,false,0);
        }
        if (controlerMusic.songFinished()){
            showResultScreen(this,true,false,0);
        }

    }
}
