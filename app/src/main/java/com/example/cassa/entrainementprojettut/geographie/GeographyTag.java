package com.example.cassa.entrainementprojettut.geographie;

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

public class GeographyTag extends GameActivity {

    private ViewGroup       mainLayout;
    private List<Etiquette> etiquetteList;

    private int nbBonneReponse;
    private int xDelta;
    private int yDelta;

    private MediaPlayer playerEvent;
    private  TextView[] tabTextView;
    private Controleur controleur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        music = R.raw.geography_music;
        modeFullscreen();
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.geographyTag_relativeLayout);

        showLevelChoice(GeographyTag.this,"",3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialogInterface) {
                                            if (levelChosen != 0) {

                                                controleur = new Controleur(levelChosen);
                                                etiquetteList = controleur.getListEtiquette();

                                                genererImageDeFond();
                                                genererEmplacementsSurImage();

                                                genererTextView();
                                                genererNomEtiquette();
                                                genererTagEtiquette();
                                            } else {
                                                showLevelChoice(GeographyTag.this, "", 3);
                                            }
                                        }
                                    });

        nbBonneReponse=0;

        startBackgroundMusic(GeographyTag.this, R.raw.geography_music);
        playerEvent=MediaPlayer.create(GeographyTag.this,R.raw.envent_sound);


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
                    xDelta=x-lParams.leftMargin;
                    yDelta=y-lParams.topMargin;

                    break;

                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin=x-xDelta;
                    layoutParams.topMargin=y-yDelta;
                    view.setLayoutParams(layoutParams);
                    break;

                case MotionEvent.ACTION_UP:
                    Toast toast;
                    toast=Toast.makeText(getApplicationContext(),"x="+((x-xDelta)*12)/ getScreenWidth()+"/12 y="+((y-yDelta)*12)/ getScreenHeight()+"/12",Toast.LENGTH_SHORT);
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
                       playerEvent.start();

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
         showText("Bravo!");
            nbBonneReponse++;
            if(nbBonneReponse==etiquetteList.size()){
                showResultScren(GeographyTag.this,true,false,0);
            }
            return true;
        }

        return false;
    }

    private void genererTextView(){
        int nbEtiquetteColonne = getNombreMaxEtiquetteParColonne();
        int numeroColonne = 0;

        tabTextView = new TextView[etiquetteList.size()];

        for(int i = 0; i<etiquetteList.size(); i++){

            RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            tabTextView[i] = new TextView(this);
            tabTextView[i].setPadding(10,10,10,10);

            if (((i+1) % nbEtiquetteColonne) == 0 ) {
                numeroColonne +=1;
            }
            textViewParams.setMargins((numeroColonne*350)+10, ((i-(numeroColonne*(nbEtiquetteColonne-1))) * 100) + 10, 0, 0);

            tabTextView[i].setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
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

            mainLayout.setBackgroundResource(controleur.getImageFond());

    }

    private void genererEmplacementsSurImage(){
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(85);

        Bitmap b = Bitmap.createBitmap((int) getScreenWidth(), (int) getScreenHeight(), Bitmap.Config.ARGB_8888);

        View conteneurRect = getLayoutInflater().inflate(R.layout.geographytag_conteneur_rect, mainLayout, false);
        mainLayout.addView(conteneurRect);

        ImageView drawRectangle = findViewById(R.id.geography_conteneur_rect);
        drawRectangle.setImageBitmap(b);

        Canvas canvas = new Canvas(b);
        RectF rect;
        float[] etiquetteCoordonnees;

        for(Etiquette etiquette : etiquetteList){

            etiquetteCoordonnees = resizeEtiquette(etiquette.getZoneVictoire());

            rect = new RectF(etiquetteCoordonnees[0], etiquetteCoordonnees[2], etiquetteCoordonnees[1],
                    etiquetteCoordonnees[3]);

            canvas.drawRect(rect, paint);

        }
    }

    private float[] resizeEtiquette(float[] tabValeurEtiquette) {



        tabValeurEtiquette[0] = tabValeurEtiquette[0] * getScreenWidth();
        tabValeurEtiquette[1] = tabValeurEtiquette[1] * getScreenWidth();
        tabValeurEtiquette[2]  = tabValeurEtiquette[2] * getScreenHeight();
        tabValeurEtiquette[3]  = tabValeurEtiquette[3] * getScreenHeight();


        return  tabValeurEtiquette;


    }

    private int getNombreMaxEtiquetteParColonne(){
        int i = 0;
        while(i*100<= getScreenHeight() * 0.8){
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
        super.onResume();
        modeFullscreen();
    }

    private void modeFullscreen(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
