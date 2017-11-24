package com.example.cassa.entrainementprojettut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FlagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

    FlagBank flagBank = new FlagBank();

        ImageView mDrapeau1;
        ImageView mDrapeau2;
        ImageView mDrapeau3;
        ImageView mDrapeau4;

        TextView mNomPays;

        mDrapeau1 = (ImageView)findViewById(R.id.activity_flag_flag01);
        mDrapeau2 = (ImageView)findViewById(R.id.activity_flag_flag02);
        mDrapeau3 = (ImageView)findViewById(R.id.activity_flag_flag03);
        mDrapeau4 = (ImageView)findViewById(R.id.activity_flag_flag04);

        mNomPays = (TextView)findViewById(R.id.activity_flag_name01_txt);


        ImageView listeDrapeau[] = {mDrapeau1,mDrapeau2,mDrapeau3,mDrapeau4};

//On génère le nom du pays

        int numPaysMystere = (int)(Math.random() * (3) + 0);

        mNomPays.setText(flagBank.getFlag(numPaysMystere).getmNameCountry());


//On génère les drapeaux

        for(int i=0; i <4;i++) {
            listeDrapeau[i].setImageResource(flagBank.getFlag(i).getmRessource());
            listeDrapeau[i].setTag(flagBank.getFlag(i).getmNameCountry());

        }
    }
}
