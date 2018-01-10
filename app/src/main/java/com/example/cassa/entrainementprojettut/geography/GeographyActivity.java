package com.example.cassa.entrainementprojettut.geography;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cassa.entrainementprojettut.GameActivity;
import com.example.cassa.entrainementprojettut.R;

import java.util.List;

/**
 * Created by clement on 07/12/17.
 */

public class GeographyActivity extends GameActivity {

    private ViewGroup mainLayout;
    private List<Tag> tagList;

    private int rightAnswerCounter;
    private int deltaX;
    private int deltaY;

    private MediaPlayer mediaPlayer;
    private  TextView[] textViewTab;
    private Controler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        music = R.raw.geography_music;
        modePleinEcran();
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.geographyTag_relativeLayout);

        afficherChoixNiveaux(GeographyActivity.this,"",3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialogInterface) {
                                            if (levelChosen != 0) {

                                                controler = new Controler(levelChosen);
                                                tagList = controler.getTagList();

                                                genererImageDeFond();
                                                genererEmplacementsSurImage();

                                                genererTextView();
                                                genererNomEtiquette();
                                                genererTagEtiquette();
                                            } else {
                                                afficherChoixNiveaux(GeographyActivity.this, "", 3);
                                            }
                                        }
                                    });

        rightAnswerCounter =0;

        lancerBgMusique(GeographyActivity.this, R.raw.geography_music);
        mediaPlayer =MediaPlayer.create(GeographyActivity.this,R.raw.envent_sound);


    }

    private View.OnTouchListener onTouchListener(){
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

            final int x=(int) motionEvent.getRawX();
            final int y=(int) motionEvent.getRawY();

            switch(motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams=(RelativeLayout.LayoutParams)view.getLayoutParams();
                    deltaX =x-lParams.leftMargin;
                    deltaY =y-lParams.topMargin;

                    break;

                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin=x- deltaX;
                    layoutParams.topMargin=y- deltaY;
                    view.setLayoutParams(layoutParams);
                    break;

                case MotionEvent.ACTION_UP:
                    Toast toast;
                    toast=Toast.makeText(getApplicationContext(),"x="+((x- deltaX)*12)/retourTailleEcran()+"/12 y="+((y- deltaY)*12)/getHauteurEcran()+"/12",Toast.LENGTH_SHORT);
                    toast.show();

                    int coordonneesEtiquette[] = new int[2];
                    view.getLocationOnScreen(coordonneesEtiquette);
                    float coteGauche = coordonneesEtiquette[0];
                    float coteDroit = coteGauche + view.getWidth();
                    float coteSuperieur = coordonneesEtiquette[1];
                    float coteInferieur = coteSuperieur + view.getHeight();

                    if (verifierZone((float[])view.getTag(),coteGauche, coteDroit, coteSuperieur, coteInferieur)){
                       view.setEnabled(false);

                       view.setBackgroundColor(Color.GREEN);
                       mediaPlayer.start();

                    }

            }
            mainLayout.invalidate();
            return true;
            }
        };
    }

    private boolean verifierZone(float[]zoneVictoireEtiquette, float coteGaucheTxtView, float coteDroitTxtView,
                                 float coteSuperieurTxtView, float coteInferieurTxtView){


        if( coteGaucheTxtView >= zoneVictoireEtiquette[0] && coteGaucheTxtView <= zoneVictoireEtiquette[1]
                && coteDroitTxtView >= zoneVictoireEtiquette[0] && coteDroitTxtView <= zoneVictoireEtiquette[1]
                && coteSuperieurTxtView >= zoneVictoireEtiquette[2] && coteSuperieurTxtView <= zoneVictoireEtiquette[3]
                && coteInferieurTxtView >= zoneVictoireEtiquette[2] && coteInferieurTxtView <= zoneVictoireEtiquette[3]){
         afficherTexte("Bravo!");
            rightAnswerCounter++;
            if(rightAnswerCounter == tagList.size()){
                afficherEcranFin(GeographyActivity.this,true,false,0);
            }
            return true;
        }

        return false;
    }

    private void genererTextView(){
        int nbEtiquetteColonne = recupererNombreMaxEtiquetteParColonne();
        int numeroColonne = 0;

        textViewTab = new TextView[tagList.size()];

        for(int i = 0; i< tagList.size(); i++){

            RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            textViewTab[i] = new TextView(this);
            textViewTab[i].setPadding(10,10,10,10);

            if (((i+1) % nbEtiquetteColonne) == 0 ) {
                numeroColonne +=1;
            }
            textViewParams.setMargins((numeroColonne*350)+10, ((i-(numeroColonne*(nbEtiquetteColonne-1))) * 100) + 10, 0, 0);

            textViewTab[i].setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
            textViewTab[i].setLayoutParams(textViewParams);
            textViewTab[i].setBackgroundColor(Color.BLUE);
            textViewTab[i].setOnTouchListener(onTouchListener());

            mainLayout.addView(textViewTab[i],textViewParams);
        }
    }

    private void genererTagEtiquette() {
        for(int i = 0; i< tagList.size(); i++){
            textViewTab[i].setTag(tagList.get(i).getVictoryBox());
        }
    }

    private void genererNomEtiquette() {
        for(int i = 0; i< tagList.size(); i++){
            textViewTab[i].setText(tagList.get(i).getName());
        }
    }

    private void genererImageDeFond() {

            mainLayout.setBackgroundResource(controler.getBackgroundImage());

    }

    private void genererEmplacementsSurImage(){
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(85);

        Bitmap b = Bitmap.createBitmap((int)retourTailleEcran(), (int)getHauteurEcran(), Bitmap.Config.ARGB_8888);

        View conteneurRect = getLayoutInflater().inflate(R.layout.geographytag_conteneur_rect, mainLayout, false);
        mainLayout.addView(conteneurRect);

        ImageView drawRectangle = findViewById(R.id.geography_conteneur_rect);
        drawRectangle.setImageBitmap(b);

        Canvas canvas = new Canvas(b);
        RectF rect;
        float[] etiquetteCoordonnees;

        for(Tag tag : tagList){

            etiquetteCoordonnees = redimentionerEtiquette(tag.getVictoryBox());

            rect = new RectF(etiquetteCoordonnees[0], etiquetteCoordonnees[2], etiquetteCoordonnees[1],
                    etiquetteCoordonnees[3]);

            canvas.drawRect(rect, paint);

        }
    }

    private float[] redimentionerEtiquette(float[] tabValeurEtiquette) {



        tabValeurEtiquette[0] = tabValeurEtiquette[0] * retourTailleEcran();
        tabValeurEtiquette[1] = tabValeurEtiquette[1] * retourTailleEcran();
        tabValeurEtiquette[2]  = tabValeurEtiquette[2] * getHauteurEcran();
        tabValeurEtiquette[3]  = tabValeurEtiquette[3] * getHauteurEcran();


        return  tabValeurEtiquette;


    }

    private int recupererNombreMaxEtiquetteParColonne(){
        int mNombreEtiquetteParColonne = 0;
        while(mNombreEtiquetteParColonne*100<= getHauteurEcran() * 0.8){
            mNombreEtiquetteParColonne++;
        }

        return mNombreEtiquetteParColonne;
    }

    @Override
    protected void onResume() {
        super.onResume();
        modePleinEcran();
    }

    private void modePleinEcran(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
