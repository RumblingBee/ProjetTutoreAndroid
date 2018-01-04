package com.example.cassa.entrainementprojettut;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by clement on 07/12/17.
 */

public class GeographyTag extends GameActivity {

    private ViewGroup mainLayout;
    private List<Etiquette> etiquetteList;

    private int nbBonneReponse;
    private int nbEtiquette;
    private int xDelta;
    private int yDelta;


    private MediaPlayer playerEvent;
    private  TextView[] tabTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.main);


        EtiquetteBank etiquetteBank = new EtiquetteBank(1,retourTailleEcran(),getHauteurEcran());
        etiquetteList = etiquetteBank.getEtiquetteList();
        nbEtiquette = etiquetteList.size();

        genererTextView();
        genererNomEtiquette();
        genererTagEtiquette();



      /*  mEtiquette1 = (TextView) findViewById(R.id.activity_geographytag_etiquette1_textView);
        mEtiquette2 = (TextView) findViewById(R.id.activity_geographytag_etiquette2_textView);
        mEtiquette3 = (TextView) findViewById(R.id.activity_geographytag_etiquette3_textView);
        mEtiquette4 = (TextView) findViewById(R.id.activity_geographytag_etiquette4_textView);
        mEtiquette5 = (TextView) findViewById(R.id.activity_geographytag_etiquette5_textView);
        mEtiquette6 = (TextView) findViewById(R.id.activity_geographytag_etiquette6_textView);
        mEtiquette7 = (TextView) findViewById(R.id.activity_geographytag_etiquette7_textView);*/

        nbBonneReponse=0;

        //On crée les étiquettes


        //On attribue la zone de victoire à la textView
     /*   mEtiquette1.setTag(etiquette1.getZoneVictoire());
        mEtiquette2.setTag(etiquette2.getZoneVictoire());
        mEtiquette3.setTag(etiquette3.getZoneVictoire());
        mEtiquette4.setTag(etiquette4.getZoneVictoire());
        mEtiquette5.setTag(etiquette5.getZoneVictoire());
        mEtiquette6.setTag(etiquette6.getZoneVictoire());
        mEtiquette7.setTag(etiquette7.getZoneVictoire());

        //On leur met un nom
        mEtiquette1.setText(etiquette1.getNom());
        mEtiquette2.setText(etiquette2.getNom());
        mEtiquette3.setText(etiquette3.getNom());
        mEtiquette4.setText(etiquette4.getNom());
        mEtiquette5.setText(etiquette5.getNom());
        mEtiquette6.setText(etiquette6.getNom());
        mEtiquette7.setText(etiquette7.getNom());


        //On lui met le nom de l'étiquette
        mEtiquette1.setText(etiquette1.getNom());
        mEtiquette2.setText(etiquette2.getNom());
        mEtiquette3.setText(etiquette3.getNom());
        mEtiquette4.setText(etiquette4.getNom());
        mEtiquette5.setText(etiquette5.getNom());
        mEtiquette6.setText(etiquette6.getNom());
        mEtiquette7.setText(etiquette7.getNom());*/

        //On rend la textView "dragable"
        /*mEtiquette1.setOnTouchListener(onTouchListener());
        mEtiquette2.setOnTouchListener(onTouchListener());
        mEtiquette3.setOnTouchListener(onTouchListener());
        mEtiquette4.setOnTouchListener(onTouchListener());
        mEtiquette5.setOnTouchListener(onTouchListener());
        mEtiquette6.setOnTouchListener(onTouchListener());
        mEtiquette7.setOnTouchListener(onTouchListener());*/

        //On lance la musique
        lancerBgMusique(GeographyTag.this,R.raw.geography_music);


        playerEvent= MediaPlayer.create(GeographyTag.this,R.raw.envent_sound);


    }


    private View.OnTouchListener onTouchListener(){
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int x=(int) motionEvent.getRawX();
                final int y=(int) motionEvent.getRawY();

                switch(motionEvent.getAction()& MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams=(RelativeLayout.LayoutParams)view.getLayoutParams();

                        xDelta=x-lParams.leftMargin;
                        yDelta=y-lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin=x-xDelta;
                        layoutParams.topMargin=y-yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;

                    case MotionEvent.ACTION_UP:
                        /*Toast toast;
                        toast=Toast.makeText(getApplicationContext(),"x="+((x-xDelta)*12)/retourTailleEcran()+"/12 y="+((y-yDelta)*12)/getHauteurEcran()+"/12",Toast.LENGTH_SHORT);
                        toast.show();*/

                       if (verifierZone((float[])view.getTag(),(x-xDelta),(y-yDelta))){
                           view.setEnabled(false);

                           view.setBackgroundColor(Color.GREEN);
                           playerEvent.start();

                       }

                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

    private boolean verifierZone(float[]zoneVictoireEtiquette,int positionX, int positionY){
        if( positionX >= zoneVictoireEtiquette[0] && positionX <= zoneVictoireEtiquette[1] && positionY>= zoneVictoireEtiquette[2] && positionY <= zoneVictoireEtiquette[3]){
         afficherTexte("Bravo!");
            nbBonneReponse++;
            if(nbBonneReponse==7){
                afficherEcranFin(GeographyTag.this,true,false,0);
            }
            return true;
        }


        return false;
    }
    public void genererTextView(){

         tabTextView = new TextView[etiquetteList.size()];

        for(int i = 0; i<etiquetteList.size(); i++){

            RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textViewParams.setMargins(10, i*50, 0, 0);

            tabTextView[i] = new TextView(this);
            tabTextView[i].setLayoutParams(textViewParams);
            tabTextView[i].setBackgroundColor(Color.GREEN);
            tabTextView[i].setOnTouchListener(onTouchListener());



            mainLayout.addView(tabTextView[i],textViewParams);
        }
    }
    private void genererTagEtiquette() {
        for(int i = 0; i<etiquetteList.size(); i++){
            tabTextView[i].setTag(etiquetteList.get(i).getZoneVictoire());
        }
    }

    private void genererNomEtiquette() {
        for(int i = 0; i<etiquetteList.size(); i++){
             tabTextView[i].setText(etiquetteList.get(i).getNom());
        }
    }


    @Override
    public void onBackPressed()
    {
        bgPlayer.stop();

        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        bgPlayer.stop();

    }

    @Override
    protected void onResume() {
        if(bgPlayer != null){
            lancerBgMusique(GeographyTag.this,R.raw.geography_music);
        }
        super.onResume();
    }
}
