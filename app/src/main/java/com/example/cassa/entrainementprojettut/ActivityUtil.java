package com.example.cassa.entrainementprojettut;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by prax on 16/01/2018.
 */

public class ActivityUtil extends AppCompatActivity {
    protected int music = 0;
    protected MediaPlayer bgPlayer;

    protected void startBackgroundMusic(Activity selfActivity, int idMusic){


        if(bgPlayer != null){ bgPlayer.stop();}

        bgPlayer = MediaPlayer.create(selfActivity,idMusic);
        bgPlayer.setLooping(true);
        bgPlayer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        bgPlayer.stop();
    }

    @Override
    public void onBackPressed(){
        bgPlayer.stop();
        Intent back = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        bgPlayer.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bgPlayer.start();
    }

    @Override
    protected void onResume() {
        if(bgPlayer != null){


            startBackgroundMusic(this, music);


        }
        super.onResume();
    }
}
