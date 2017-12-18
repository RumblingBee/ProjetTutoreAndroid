package com.example.cassa.entrainementprojettut;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

    private int nbBonneReponse;
    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.main);

        mEtiquette1 = (TextView) findViewById(R.id.activity_geographytag_etiquette1_textView);
        mEtiquette2 = (TextView) findViewById(R.id.activity_geographytag_etiquette2_textView);
        mEtiquette3 = (TextView) findViewById(R.id.activity_geographytag_etiquette3_textView);
        mEtiquette4 = (TextView) findViewById(R.id.activity_geographytag_etiquette4_textView);

        nbBonneReponse=0;

        //On crée les étiquettes
        Etiquette etiquette1 = new Etiquette("Amerique du Nord",retourTailleEcran()/3,retourTailleEcran()/2,getHauteurEcran()/3,getHauteurEcran());
        Etiquette etiquette2 = new Etiquette("Afrique",retourTailleEcran()/3,retourTailleEcran()/2,getHauteurEcran()/3,getHauteurEcran());
        Etiquette etiquette3 = new Etiquette("Europe",retourTailleEcran()*5/12,retourTailleEcran()*7/12,getHauteurEcran()/6,getHauteurEcran()*3/12);
        Etiquette etiquette4 = new Etiquette("Asie",retourTailleEcran()/3,retourTailleEcran()/2,getHauteurEcran()/3,getHauteurEcran());


        //On attribue la zone de victoire à la textView
        mEtiquette1.setTag(etiquette1.getZoneVictoire());
        mEtiquette2.setTag(etiquette2.getZoneVictoire());
        mEtiquette3.setTag(etiquette3.getZoneVictoire());
        mEtiquette4.setTag(etiquette4.getZoneVictoire());

        //On leur met un nom
        mEtiquette1.setText(etiquette1.getNom());
        mEtiquette2.setText(etiquette2.getNom());
        mEtiquette3.setText(etiquette3.getNom());
        mEtiquette4.setText(etiquette4.getNom());


        //On lui met le nom de l'étiquette
        mEtiquette1.setText(etiquette2.getNom());
        mEtiquette2.setText(etiquette2.getNom());
        mEtiquette3.setText(etiquette3.getNom());
        mEtiquette4.setText(etiquette4.getNom());

        //On rend la textView "dragable"
        mEtiquette1.setOnTouchListener(onTouchListener());
        mEtiquette2.setOnTouchListener(onTouchListener());
        mEtiquette3.setOnTouchListener(onTouchListener());
        mEtiquette4.setOnTouchListener(onTouchListener());


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
                        Toast toast;
                        toast=Toast.makeText(getApplicationContext(),"x="+((x-xDelta)*12)/retourTailleEcran()+"/12 y="+((y-yDelta)*6)/getHauteurEcran()+"/6",Toast.LENGTH_SHORT);
                        toast.show();

                       if (verifierZone((float[])view.getTag(),(x-xDelta),(y-yDelta))){
                           view.setEnabled(false);

                           view.setBackgroundColor(Color.GREEN);

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
            if(nbBonneReponse==4){
                afficherEcranFin(GeographyTag.this,true,false,0);
            }
            return true;
        }


        return false;
    }
}
