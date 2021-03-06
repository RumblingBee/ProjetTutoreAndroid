package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cassa.entrainementprojettut.flag.FlagActivity;
import com.example.cassa.entrainementprojettut.flag.ReverseFlagActivity;
import com.example.cassa.entrainementprojettut.gameUtils.ActivityUtil;
import com.example.cassa.entrainementprojettut.geography.GeographyActivity;
import com.example.cassa.entrainementprojettut.mysteryWord.MysteryWordActivity;
import com.example.cassa.entrainementprojettut.operationGame.AdditionActivity;
import com.example.cassa.entrainementprojettut.pianoGame.PianoActivity;

public class MainActivity extends ActivityUtil {

    private AnimationDrawable mOwlAnimation;


    MediaPlayer playerEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mAddition = findViewById(R.id.activity_main_addition_btn);
        Button btnMysteryWord = findViewById(R.id.activity_main_mysteryWord_btn);
        Button btnFlagActivity = findViewById(R.id.activity_main_flagActivity_btn);
        Button btnReverseFlagActivity = findViewById(R.id.activity_reverse_flagActivity_btn);
        Button btnGeographyTag = findViewById(R.id.acivity_main_geographyTag);
        Button btnPiano = findViewById(R.id.acivity_main_piano);
        ImageView owlImg = findViewById(R.id.chouettes_menu);
        owlImg.setBackgroundResource(R.drawable.animation_chouettes_menu);
        mOwlAnimation = (AnimationDrawable) owlImg.getBackground();
        playerEvent = MediaPlayer.create(MainActivity.this,R.raw.envent_sound);
        music =R.raw.bensound_jazzyfrenchy;

        startBackgroundMusic(this, music);




        mAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent additionIntent = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(additionIntent);

                playerEvent.start();
                finish();
            }
        });

        btnMysteryWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mysteryWordIntent = new Intent(MainActivity.this, MysteryWordActivity.class);
                startActivity(mysteryWordIntent);

                playerEvent.start();
                finish();
            }
        });

        btnFlagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent flagActivityIntent = new Intent(MainActivity.this, FlagActivity.class);
                startActivity(flagActivityIntent);

                playerEvent.start();
                finish();
            }
        });


        btnReverseFlagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reverseFlagActivityIntent = new Intent(MainActivity.this, ReverseFlagActivity.class);
                startActivity(reverseFlagActivityIntent);
                finish();

            }
        });

        btnGeographyTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent geographyActivityIntent = new Intent(MainActivity.this, GeographyActivity.class);
                startActivity(geographyActivityIntent);

                playerEvent.start();
                finish();
            }
        });

        btnPiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pianoActivityIntent = new Intent(MainActivity.this, PianoActivity.class);
                startActivity(pianoActivityIntent);

                playerEvent.start();
                finish();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            mOwlAnimation.start();
        }
    }

}