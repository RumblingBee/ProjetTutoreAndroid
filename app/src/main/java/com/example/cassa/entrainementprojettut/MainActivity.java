package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mAddition;

    MediaPlayer player;
    MediaPlayer playerEvent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player= MediaPlayer.create(MainActivity.this,R.raw.bensound_jazzyfrenchy);
        player.start();


        playerEvent= MediaPlayer.create(MainActivity.this,R.raw.envent_sound);

        mAddition = (Button)findViewById(R.id.activity_main_addition_btn);

        mAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                player.stop();
                Intent additionIntent = new Intent(MainActivity.this, choixDifficulteActivity.class);
                startActivity(additionIntent);



                playerEvent.start();
            }
        });
    }


}
