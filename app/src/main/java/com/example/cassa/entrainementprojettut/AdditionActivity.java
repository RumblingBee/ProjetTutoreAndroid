package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdditionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mAffichage;
    private TextView mNombre1;
    private TextView mNombre2;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    private ImageView mImagePos1;
    private ImageView mImageOrdi;



    private int gReponseCorrecte;
    private int gNbReponsesCorectes;

    private MediaPlayer bgPlayer;
    private MediaPlayer playerEvent;

    final Handler handler = new Handler();

   protected Runnable avancerOrdinateur = new Runnable() {
        @Override
        public void run() {
            terminerActivite();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);



        bgPlayer = MediaPlayer.create(AdditionActivity.this,R.raw.bensound_retrosoul);
        bgPlayer.start();

        playerEvent= MediaPlayer.create(AdditionActivity.this,R.raw.envent_sound);

        gNbReponsesCorectes = 1;
// On recupère les widgets

        mAffichage = (TextView)findViewById(R.id.activity_addition_affichage_textview);
        mNombre1 = (TextView) findViewById(R.id.activity_addition_nombre1_textview);
        mNombre2 = (TextView) findViewById(R.id.activity_addition_nombre2_textview);

        mButton1 = (Button)findViewById(R.id.activity_addition_rep1_btn);
        mButton2 = (Button)findViewById(R.id.activity_addition_rep2_btn);
        mButton3 = (Button)findViewById(R.id.activity_addition_rep3_btn);
        mButton4 = (Button)findViewById(R.id.activity_addition_rep4_btn);

        mImagePos1 = (ImageView)findViewById(R.id.acivity_addition_pos1_img);
        mImageOrdi = (ImageView)findViewById(R.id.activity_addition_ordi_img);

        //On récupère la taille de l'écran

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int largeurEcran = size.x;
        int largeurImageOrdi = mImageOrdi.getDrawable().getIntrinsicWidth();

             genererAddition();


        //On lance le chrono, l'enfant perd s'il arrive au bout

     /*   handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                terminerActivite();



            }
        }, 27000);*/

     handler.postDelayed(avancerOrdinateur,27000);

        // On anime l'image représentant l'ordinateur

        TranslateAnimation animationTranslation=new TranslateAnimation(0,largeurEcran - largeurImageOrdi,0,0);
        animationTranslation.setFillAfter(true);
        animationTranslation.setDuration(27000);
        mImageOrdi.startAnimation(animationTranslation);




    }


    protected void bougerImage(ImageView v,int margin_left,int margin_top,int margin_right,int margin_bottom){

        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(v.getLayoutParams());
        marginParams.setMargins(margin_left, margin_top, margin_right, margin_bottom);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
        v.setLayoutParams(layoutParams);
        gNbReponsesCorectes++;



    }
    protected void genererAddition(){

        int nombre1 = (int)(Math.random() * (9) + 1);
        int nombre2 = (int)(Math.random() * (9) + 1);

        mNombre1.setText(""+nombre1);
        mNombre2.setText(""+nombre2);

        gReponseCorrecte = nombre1 + nombre2;

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        melangerReponse();

    }


    protected void melangerReponse(){

        Button tabButton[] = {mButton1,mButton2,mButton3,mButton4};
        ArrayList listeReponses = new ArrayList();

        listeReponses.add(gReponseCorrecte-1);
        listeReponses.add(gReponseCorrecte);
        listeReponses.add(gReponseCorrecte+1);
        listeReponses.add(gReponseCorrecte+2);

            int i;


         for(i=0;i<4; i++) {
             int indiceListe = (int) (Math.random() * listeReponses.size());
            tabButton[i].setTag(listeReponses.get(indiceListe));
            tabButton[i].setText("" + listeReponses.get(indiceListe));
            listeReponses.remove(indiceListe);
         }


    }

    public boolean verifierReponse(int reponseEnvoyee,int reponseCorrecte ){
        int marginLeft = 50;

        if(reponseCorrecte == reponseEnvoyee){
            if(gNbReponsesCorectes == 10){

                terminerActivite();

            }

            marginLeft = gNbReponsesCorectes * marginLeft;


            playerEvent.start();

            bougerImage(mImagePos1,marginLeft,0,0,0);
            genererAddition();
            return true;

        }
        else{
            mAffichage.setText("Dommage!");
            genererAddition();
            return false;
        }

    }
    @Override
    public void onClick(View view) {
        int reponseEnvoyee = (int) view.getTag();

        verifierReponse(reponseEnvoyee,gReponseCorrecte);


    }

    public void  terminerActivite(){



        AdditionActivity.this.finish();
        overridePendingTransition(0,0);


        bgPlayer.stop();

        Intent ecranFin = new Intent(AdditionActivity.this, ResultActivity.class);

        handler.removeCallbacks(avancerOrdinateur);
        ecranFin.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        if(gNbReponsesCorectes == 10) {
            ecranFin.putExtra("resultat", "Gagné!");
        }
        else{
            ecranFin.putExtra("resultat", "Perdu!");
        }

        startActivity(ecranFin);


    }

    //Enlève le flag qui bloque l'écran allumé

    protected void onDestroy(){
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    @Override
    public void onBackPressed()
    {
        bgPlayer.stop();
        Intent ecranMenu = new Intent(AdditionActivity.this, MainActivity.class);
        startActivity(ecranMenu);
        handler.removeCallbacks(avancerOrdinateur);
        super.onBackPressed();
    }

}


