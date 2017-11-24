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

        ImageView drapeau;

        drapeau = (ImageView)findViewById(R.id.activity_flag_flag01);


        drapeau.setImageResource(flagBank.getFlag(0).getmRessource());


    }
}
