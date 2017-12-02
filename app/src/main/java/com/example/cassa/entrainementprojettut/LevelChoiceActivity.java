package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LevelChoiceActivity extends AppCompatActivity {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;

    private MediaPlayer player;
    private MediaPlayer playerEvent;

    private ImageView mNuage;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_difficulte);

        Intent intent = getIntent();
        String activityToLaunch = intent.getStringExtra("Activity");

        player= MediaPlayer.create(LevelChoiceActivity.this,R.raw.bensound_jazzyfrenchy);
        player.start();

        playerEvent= MediaPlayer.create(LevelChoiceActivity.this,R.raw.envent_sound);

        mButton1 = (Button) findViewById(R.id.niveau1_btn);
        mButton2 = (Button)findViewById(R.id.niveau2_btn);
        mButton3 = (Button)findViewById(R.id.niveau3_btn);

        mNuage = findViewById(R.id.activity_choix_difficulte_nuage_imageView);



        if(activityToLaunch.equals("Addition"))
        {
            final Intent addition = new Intent(LevelChoiceActivity.this, AdditionActivity.class);
            addition.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addition.putExtra("diff",1);
                    startActivity(addition);
                    player.stop();
                    playerEvent.start();

                }
            });
            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addition.putExtra("diff",2);
                    startActivity(addition);
                    player.stop();
                    playerEvent.start();

                }
            });
            mButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addition.putExtra("diff",3);
                    startActivity(addition);
                    player.stop();
                    playerEvent.start();
                }
            });
        }
        else if(activityToLaunch.equals("MysteryWord"))
        {
            final Intent mysteryWord = new Intent(LevelChoiceActivity.this, MysteryWordActivity.class);
            mysteryWord.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mysteryWord.putExtra("diff",1);
                    startActivity(mysteryWord);

                }
            });
            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mysteryWord.putExtra("diff",2);
                    startActivity(mysteryWord);

                }
            });
            mButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mysteryWord.putExtra("diff",3);
                    startActivity(mysteryWord);
                }
            });
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        player.stop();
    }
    @Override
    public void onBackPressed()
    {
        player.stop();
        Intent ecranMenu = new Intent(LevelChoiceActivity.this, MainActivity.class);
        startActivity(ecranMenu);
        super.onBackPressed();
    }
    @Override
    public void onPause() {
        super.onPause();
        player.stop();
        LevelChoiceActivity.this.finish();


    }

    @Override
    public void onRestart() {
        super.onRestart();
        player.start();
    }
    @Override
    public void onResume() {
        super.onResume();
        player.start();
    }




}
