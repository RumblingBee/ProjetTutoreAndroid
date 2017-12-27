package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mAddition;
    private Button btnMysteryWord;
    private Button btnFlagActivity;
    private Button btnReverseFlagActivity;
     private Button btnGeographyTag;


    MediaPlayer player;
    MediaPlayer playerEvent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(MainActivity.this, R.raw.bensound_jazzyfrenchy);
        player.start();


        playerEvent = MediaPlayer.create(MainActivity.this, R.raw.envent_sound);

        mAddition = (Button) findViewById(R.id.activity_main_addition_btn);
        btnMysteryWord = (Button) findViewById(R.id.activity_main_mysteryWord_btn);
        btnFlagActivity = (Button) findViewById(R.id.activity_main_flagActivity_btn);

        btnReverseFlagActivity = (Button) findViewById(R.id.activity_reverse_flagActivity_btn);

        btnGeographyTag = findViewById(R.id.acivity_main_geographyTag);


        mAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                player.stop();
                Intent additionIntent = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(additionIntent);


                playerEvent.start();
            }
        });

        btnMysteryWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent mysteryWordIntent = new Intent(MainActivity.this, MysteryWordActivity.class);
                startActivity(mysteryWordIntent);

                playerEvent.start();
            }
        });

        btnFlagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent flagActivityIntent = new Intent(MainActivity.this, FlagActivity.class);
                startActivity(flagActivityIntent);

                playerEvent.start();
            }
        });


        btnReverseFlagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent reverseFlagActivityIntent = new Intent(MainActivity.this, ReverseFlagActivity.class);
                startActivity(reverseFlagActivityIntent);


            }
        });
        btnGeographyTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent geographyActivityIntent = new Intent(MainActivity.this, GeographyTag.class);
                startActivity(geographyActivityIntent);


                playerEvent.start();
            }
        });
    }
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