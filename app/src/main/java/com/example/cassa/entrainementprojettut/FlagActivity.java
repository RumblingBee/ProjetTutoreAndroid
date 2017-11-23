package com.example.cassa.entrainementprojettut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FlagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

      FlagBank flagBank = new FlagBank();

        ImageView drapeau3;

        drapeau3 = (ImageView)findViewById(R.id.flag3);


        drapeau3.setImageResource(flagBank.getFlag(0).getmRessource());


    }
}
