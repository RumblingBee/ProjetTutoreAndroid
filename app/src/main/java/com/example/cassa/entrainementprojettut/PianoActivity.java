package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cassa.entrainementprojettut.geography.Controler;
import com.example.cassa.entrainementprojettut.pianoGame.ControlerMusic;

import java.util.List;

public class PianoActivity extends GameActivity implements View.OnClickListener {

    Button mButtonDo;
    Button mButtonRe;
    Button mButtonMi;
    Button mButtonFa;
    Button mButtonSol;
    Button mButtonLa;
    Button mButtonSi;

    ControlerMusic controlerMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        setPianoButton();

        generatParty();

    }

    private void generatParty() {
        //controlerMusic = new ControlerMusic(levelChosen);
        controlerMusic = new ControlerMusic();

        showSequence();
    }

    private void showSequence() {
        List<Integer> listId = controlerMusic.getIdSequence();
        for (int id : listId){

        }
    }


    private void setPianoButton() {
        mButtonDo = (Button) findViewById(R.id.activity_piano_touche_do);
        mButtonRe = (Button) findViewById(R.id.activity_piano_touche_re);
        mButtonMi = (Button) findViewById(R.id.activity_piano_touche_mi);
        mButtonFa = (Button) findViewById(R.id.activity_piano_touche_fa);
        mButtonSol= (Button) findViewById(R.id.activity_piano_touche_sol);
        mButtonLa = (Button) findViewById(R.id.activity_piano_touche_la);
        mButtonSi = (Button) findViewById(R.id.activity_piano_touche_si);

        mButtonDo.setOnClickListener(this);
        mButtonRe.setOnClickListener(this);
        mButtonMi.setOnClickListener(this);
        mButtonFa.setOnClickListener(this);
        mButtonSol.setOnClickListener(this);
        mButtonLa.setOnClickListener(this);
        mButtonSi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int keyId = Integer.parseInt((String) v.getTag());
        boolean correctKey = controlerMusic.checkKey(keyId);
        System.out.println("valentin: "+ correctKey);
    }
}
