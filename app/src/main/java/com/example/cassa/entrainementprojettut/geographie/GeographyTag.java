package com.example.cassa.entrainementprojettut.geographie;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cassa.entrainementprojettut.GameActivity;
import com.example.cassa.entrainementprojettut.R;

import java.util.List;

/**
 * Created by clement on 07/12/17.
 */

public class GeographyTag extends GameActivity {

    private ViewGroup       mainLayout;
    private List<Etiquette> etiquetteList;

    private int nbBonneReponse;
    private int xDelta;
    private int yDelta;


    private MediaPlayer playerEvent;
    private  TextView[] tabTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.main);

        afficherChoixNiveaux(GeographyTag.this,"",3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialogInterface) {
                                            if (niveauChoisi != 0) {
                                                EtiquetteBank etiquetteBank = new EtiquetteBank(niveauChoisi, retourTailleEcran(), getHauteurEcran());
                                                etiquetteList = etiquetteBank.getEtiquetteList();


                                                genererTextView();
                                                genererNomEtiquette();
                                                genererTagEtiquette();

                                                genererZoneSurImage();
                                                genererImageDeFond();
                                            } else {
                                                afficherChoixNiveaux(GeographyTag.this, "", 3);
                                            }


                                        }
                                    });

            nbBonneReponse=0;

            lancerBgMusique(GeographyTag.this, R.raw.geography_music);

            playerEvent=MediaPlayer.create(GeographyTag.this,R.raw.envent_sound);





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
                        toast=Toast.makeText(getApplicationContext(),"x="+((x-xDelta)*12)/retourTailleEcran()+"/12 y="+((y-yDelta)*12)/getHauteurEcran()+"/12",Toast.LENGTH_SHORT);
                        toast.show();

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
            if(nbBonneReponse==etiquetteList.size()){
                afficherEcranFin(GeographyTag.this,true,false,0);
            }
            return true;
        }


        return false;
    }
    private void genererTextView(){
        int nbEtiquetteColonne = getNombreMaxEtiquetteParColonne();
        int numeroColonne = 0;


         tabTextView = new TextView[etiquetteList.size()];
        ;
        for(int i = 0; i<etiquetteList.size(); i++){

            RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            tabTextView[i] = new TextView(this);
            tabTextView[i].setPadding(10,10,10,10);

            if (((i+1) % nbEtiquetteColonne) == 0 ) {
                numeroColonne +=1;
            }
            textViewParams.setMargins((numeroColonne*350)+10, ((i-(numeroColonne*(nbEtiquetteColonne-1))) * 100) + 10, 0, 0);

            tabTextView[i].setGravity(Gravity.LEFT);
            tabTextView[i].setLayoutParams(textViewParams);
            tabTextView[i].setBackgroundColor(Color.BLUE);
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

    private void genererImageDeFond() {
        if(niveauChoisi == 1) {
            mainLayout.setBackgroundResource(R.drawable.continent);
        }
        else {
            mainLayout.setBackgroundResource(R.drawable.occitanie);
        }
    }

    private void genererZoneSurImage(){


    }
    private int getNombreMaxEtiquetteParColonne(){
        int i = 0;
        while(i*100<= getHauteurEcran() * 0.8){
            i++;
        }

        return i;
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
