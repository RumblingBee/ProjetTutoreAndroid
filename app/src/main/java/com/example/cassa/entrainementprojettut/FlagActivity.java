package com.example.cassa.entrainementprojettut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FlagActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mDrapeau1;
    ImageView mDrapeau2;
    ImageView mDrapeau3;
    ImageView mDrapeau4;

    TextView mNomPays;
    String gBonneReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);





        mDrapeau1 = (ImageView)findViewById(R.id.activity_flag_flag01);
        mDrapeau2 = (ImageView)findViewById(R.id.activity_flag_flag02);
        mDrapeau3 = (ImageView)findViewById(R.id.activity_flag_flag03);
        mDrapeau4 = (ImageView)findViewById(R.id.activity_flag_flag04);

        mDrapeau1.setOnClickListener(this);
        mDrapeau2.setOnClickListener(this);
        mDrapeau3.setOnClickListener(this);
        mDrapeau4.setOnClickListener(this);

        mNomPays = (TextView)findViewById(R.id.activity_flag_name01_txt);

        genererPartie();


    }
    protected void genererPartie(){

        FlagBank flagBank = new FlagBank();


        ImageView listeDrapeau[] = {mDrapeau1,mDrapeau2,mDrapeau3,mDrapeau4};

//On génère le nom du pays

        int numPaysMystere = (int)(Math.random() * (3) + 0);

        mNomPays.setText(flagBank.getFlag(numPaysMystere).getmNameCountry());
        gBonneReponse = flagBank.getFlag(numPaysMystere).getmNameCountry();

//On génère les drapeaux

        for(int i=0; i <4;i++) {
            listeDrapeau[i].setImageResource(flagBank.getFlag(i).getmRessource());
            listeDrapeau[i].setTag(flagBank.getFlag(i).getmNameCountry());

        }

    }
protected  void verifierReponse(String pPays){
    int duration = Toast.LENGTH_SHORT;
    Toast toast;
        if(pPays == gBonneReponse ){

           toast  = Toast.makeText(getApplicationContext(), "Bravo!", duration);



        }
        else{

            toast  = Toast.makeText(getApplicationContext(), "Dommage", duration);
        }
    toast.show();
    genererPartie();
}
    @Override
    public void onClick(View view) {

        String paysSelectione = (String) view.getTag();



        verifierReponse(paysSelectione);





    }
}
