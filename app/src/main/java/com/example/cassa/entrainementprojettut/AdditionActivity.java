package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.graphics.Color;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdditionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mAffichage;
    private TextView mNombre1;
    private TextView mNombre2;
    private TextView mSigne;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    private ImageView mImagePos1;
    private ImageView mImageOrdi;

    private operation op;

    private int gNbReponsesCorectes;
    private int difficulte;



    private MediaPlayer bgPlayer;
    private MediaPlayer playerEvent;

    final Handler handler = new Handler();
    float positionImageJoueur;

    protected Runnable terminerActivite = new Runnable() {
        @Override
        public void run() {
            terminerActivite();

        }
    };
    protected Runnable activerBoutons=new Runnable() {
        @Override
        public void run() {
            activerBoutons();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        Intent intent = getIntent();
        difficulte = intent.getIntExtra("diff",1);

        // int difficulte = Integer.parseInt(mMessage);

        bgPlayer = MediaPlayer.create(AdditionActivity.this,R.raw.bensound_retrosoul);
        bgPlayer.start();

        playerEvent= MediaPlayer.create(AdditionActivity.this,R.raw.envent_sound);

        gNbReponsesCorectes = 1;
// On recupère les widgets

        mAffichage = (TextView)findViewById(R.id.activity_addition_affichage_textview);
        mNombre1 = (TextView) findViewById(R.id.activity_addition_nombre1_textview);
        mNombre2 = (TextView) findViewById(R.id.activity_addition_nombre2_textview);
        mSigne =(TextView)findViewById(R.id.activity_addition_operateur_textview);

        mButton1 = (Button)findViewById(R.id.activity_addition_rep1_btn);
        mButton2 = (Button)findViewById(R.id.activity_addition_rep2_btn);
        mButton3 = (Button)findViewById(R.id.activity_addition_rep3_btn);
        mButton4 = (Button)findViewById(R.id.activity_addition_rep4_btn);

        mImagePos1 = (ImageView)findViewById(R.id.acivity_addition_pos1_img);
        mImageOrdi = (ImageView)findViewById(R.id.activity_addition_ordi_img);


        //On récupère la taille de l'écran

        float largeurEcran = retourTailleEcran();
        int largeurImageOrdi = mImageOrdi.getDrawable().getIntrinsicWidth();

        genererAddition();

        //On lance le chrono, l'enfant perd s'il arrive au bout

        positionImageJoueur = mImagePos1.getX();

        handler.postDelayed(terminerActivite,27000);

        // On anime l'image représentant l'ordinateur

        bougerImage(mImageOrdi,largeurEcran-largeurImageOrdi,27000,0);

    }

    protected void bougerImage(ImageView pImage,float pDestination,int pDuration,float pPosDepart){

        TranslateAnimation animationTranslation=new TranslateAnimation(pPosDepart,pDestination,0,0);
        animationTranslation.setFillAfter(true);
        animationTranslation.setDuration(pDuration);
        pImage.startAnimation(animationTranslation);

    }

    protected void genererAddition(){


         op = new operation(difficulte);

        //Affichage de l'opération

        mNombre1.setText(""+op.getTerme1());
        mNombre2.setText(""+op.getTerme2());
        mSigne.setText(""+op.getSigne());

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        melangerReponse();

    }

    protected void melangerReponse(){

        Button tabButton[] = {mButton1,mButton2,mButton3,mButton4};
        ArrayList listeReponses = new ArrayList();

        listeReponses.add(op.getReponse()-1);
        listeReponses.add(op.getReponse());
        listeReponses.add(op.getReponse()+1);
        listeReponses.add(op.getReponse()+2);

        int i;


        for(i=0;i<4; i++) {
            int indiceListe = (int) (Math.random() * listeReponses.size());
            tabButton[i].setTag(listeReponses.get(indiceListe));
            tabButton[i].setText("" + listeReponses.get(indiceListe));
            listeReponses.remove(indiceListe);
        }
    }

    public boolean verifierReponse(int reponseEnvoyee,int reponseCorrecte ){

        float largeurEcran = retourTailleEcran();


        if(reponseCorrecte == reponseEnvoyee){
            gNbReponsesCorectes++;
            playerEvent.start();

            bougerImage(mImagePos1,positionImageJoueur+(largeurEcran/10),600,positionImageJoueur);
            positionImageJoueur = positionImageJoueur + (largeurEcran/10);

            if(gNbReponsesCorectes == 10){
                handler.postDelayed(terminerActivite,650);
            }

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

        verifierReponse(reponseEnvoyee,op.getReponse());
        griserBoutons();
        handler.postDelayed(activerBoutons,500);


    }

    public void  terminerActivite(){

        AdditionActivity.this.finish();
        overridePendingTransition(0,0);

        bgPlayer.stop();

        Intent ecranFin = new Intent(AdditionActivity.this, ResultActivity.class);

        handler.removeCallbacks(terminerActivite);
        ecranFin.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        if(gNbReponsesCorectes == 10) {
            ecranFin.putExtra("diff",difficulte);
            ecranFin.putExtra("resultat", "Gagné!");
        }
        else{
            ecranFin.putExtra("diff",difficulte);
            ecranFin.putExtra("resultat", "Perdu!");
        }

        startActivity(ecranFin);

    }

    //Enlève le flag qui bloque l'écran allumé

    protected void onDestroy(){
        super.onDestroy();
        bgPlayer.stop();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    @Override
    public void onBackPressed()
    {
        bgPlayer.stop();
        Intent ecranMenu = new Intent(AdditionActivity.this, MainActivity.class);
        startActivity(ecranMenu);
        handler.removeCallbacks(terminerActivite);
        super.onBackPressed();
    }

    public float retourTailleEcran(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    protected void griserBoutons(){

        mButton1.setEnabled(false);
        mButton2.setEnabled(false);
        mButton3.setEnabled(false);
        mButton4.setEnabled(false);

        mButton1.setBackgroundColor(Color.rgb(99,99,99));
        mButton2.setBackgroundColor(Color.rgb(99,99,99));
        mButton3.setBackgroundColor(Color.rgb(99,99,99));
        mButton4.setBackgroundColor(Color.rgb(99,99,99));


    }
    protected void activerBoutons(){

        mButton1.setEnabled(true);
        mButton2.setEnabled(true);
        mButton3.setEnabled(true);
        mButton4.setEnabled(true);

        mButton1.setBackgroundColor(Color.rgb(255,0,0));
        mButton2.setBackgroundColor(Color.rgb(0,255,0));
        mButton3.setBackgroundColor(Color.rgb(0,0,255));
        mButton4.setBackgroundColor(Color.rgb(255,255,0));


    }

}