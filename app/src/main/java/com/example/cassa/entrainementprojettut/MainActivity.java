package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cassa.entrainementprojettut.geography.GeographyActivity;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable mAnimationChouettes;

    MediaPlayer player;
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
        ImageView imgChouettes = findViewById(R.id.chouettes_menu);
        imgChouettes.setBackgroundResource(R.drawable.animation_chouettes_menu);
        mAnimationChouettes = (AnimationDrawable) imgChouettes.getBackground();

        player = MediaPlayer.create(MainActivity.this, R.raw.bensound_jazzyfrenchy);
        player.start();
        playerEvent = MediaPlayer.create(MainActivity.this, R.raw.envent_sound);

        mAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                player.stop();
                Intent additionIntent = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(additionIntent);

                playerEvent.start();
                finish();
            }
        });

        btnMysteryWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent mysteryWordIntent = new Intent(MainActivity.this, MysteryWordActivity.class);
                startActivity(mysteryWordIntent);

                playerEvent.start();
                finish();
            }
        });

        btnFlagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent flagActivityIntent = new Intent(MainActivity.this, FlagActivity.class);
                startActivity(flagActivityIntent);

                playerEvent.start();
                finish();
            }
        });


        btnReverseFlagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent reverseFlagActivityIntent = new Intent(MainActivity.this, ReverseFlagActivity.class);
                startActivity(reverseFlagActivityIntent);
                finish();

            }
        });

        btnGeographyTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent geographyActivityIntent = new Intent(MainActivity.this, GeographyActivity.class);
                startActivity(geographyActivityIntent);

                playerEvent.start();
                finish();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            mAnimationChouettes.start();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        player.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        player.stop();

    }

    @Override
    public void onRestart() {
        super.onRestart();
        player.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(player != null) {
            player.start();
        }
    }
    
}