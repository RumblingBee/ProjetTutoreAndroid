package com.example.cassa.entrainementprojettut;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by clement on 07/12/17.
 */

public class GeographyTag extends GameActivity {

    private ViewGroup mainLayout;
    private TextView mEtiquette1;
    private TextView mEtiquette2;
    private TextView mEtiquette3;
    private TextView mEtiquette4;
    private TextView mEtiquette5;
    private TextView mEtiquette6;
    private TextView mEtiquette7;

    private int nbBonneReponse;
    private int xDelta;
    private int yDelta;

    private MediaPlayer playerEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.main);

        mEtiquette1 = (TextView) findViewById(R.id.activity_geographytag_etiquette1_textView);
        mEtiquette2 = (TextView) findViewById(R.id.activity_geographytag_etiquette2_textView);
        mEtiquette3 = (TextView) findViewById(R.id.activity_geographytag_etiquette3_textView);
        mEtiquette4 = (TextView) findViewById(R.id.activity_geographytag_etiquette4_textView);
        mEtiquette5 = (TextView) findViewById(R.id.activity_geographytag_etiquette5_textView);
        mEtiquette6 = (TextView) findViewById(R.id.activity_geographytag_etiquette6_textView);
        mEtiquette7 = (TextView) findViewById(R.id.activity_geographytag_etiquette7_textView);

        nbBonneReponse=0;

        //On crée les étiquettes
        Etiquette etiquette1 = new Etiquette("Amerique du Nord",retourTailleEcran()*8/48,retourTailleEcran()*3/12,getHauteurEcran()*1/12,getHauteurEcran()*5/24);
        Etiquette etiquette2 = new Etiquette("Afrique",retourTailleEcran()/2,retourTailleEcran()*7/12,getHauteurEcran()*9/24,getHauteurEcran()*11/24);
        Etiquette etiquette3 = new Etiquette("Europe",retourTailleEcran()*11/24,retourTailleEcran()*13/24,getHauteurEcran()/12,getHauteurEcran()*2/12);
        Etiquette etiquette4 = new Etiquette("Asie",retourTailleEcran()*8/12,retourTailleEcran()*18/24,getHauteurEcran()*2/12,getHauteurEcran()*5/24);
        Etiquette etiquette5 = new Etiquette("Amerique du Sud",retourTailleEcran()*3/12,retourTailleEcran()*4/12,getHauteurEcran()*5/12,getHauteurEcran()*13/24);
        Etiquette etiquette6 = new Etiquette("Océanie",retourTailleEcran()*19/24,retourTailleEcran()*21/24,getHauteurEcran()*13/24,getHauteurEcran()*15/24);
        Etiquette etiquette7 = new Etiquette("Antarctique",retourTailleEcran()*3/12,retourTailleEcran()*17/24,getHauteurEcran()*10/12,getHauteurEcran()*11/12);


        //On attribue la zone de victoire à la textView
        mEtiquette1.setTag(etiquette1.getZoneVictoire());
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
        mEtiquette7.setText(etiquette7.getNom());

        //On rend la textView "dragable"
        mEtiquette1.setOnTouchListener(onTouchListener());
        mEtiquette2.setOnTouchListener(onTouchListener());
        mEtiquette3.setOnTouchListener(onTouchListener());
        mEtiquette4.setOnTouchListener(onTouchListener());
        mEtiquette5.setOnTouchListener(onTouchListener());
        mEtiquette6.setOnTouchListener(onTouchListener());
        mEtiquette7.setOnTouchListener(onTouchListener());

        //On lance la musique
        mMusique = R.raw.geography_music;
        lancerBgMusique(GeographyTag.this,mMusique);


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
}
