package com.example.cleme.myapplication;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int DIALOG_ALERT=10;
    private static final int DIALOG_ALERT_PERDU=11;
    private int score=0;
    private float positionImg;

    public void verifRep(Integer rep, Addition a,ImageView imageJoueur){
        if(rep==a.result()){
            score ++;
            Context c=getApplicationContext();
            int duration=Toast.LENGTH_SHORT;
            String s="Bonne Réponse ! Ton score est de "+score;
            Toast toast=Toast.makeText(c,s,duration);
            toast.show();
            TranslateAnimation animationTranslation=new TranslateAnimation(0,100,0,0);
            animationTranslation.setFillAfter(true);
            animationTranslation.setDuration(1000);
            imageJoueur.startAnimation(animationTranslation);
            positionImg+=100;
            imageJoueur.setX(positionImg);
            if(score==10){
                showDialog(DIALOG_ALERT);
            }
            a.initAdd();
        }else{
            Context c=getApplicationContext();
            int duration=Toast.LENGTH_SHORT;
            String s="Mauvaise Réponse, la bonne réponse est "+a.result();
            Toast toast=Toast.makeText(c,s,duration);
            toast.show();
            a.initAdd();
        }
    }
    public void initQuest(Button btnRep1, Button btnRep2, Button btnRep3, Button btnRep4,
                          Addition a, TextView addTerme1, TextView addTerme2){
        int nombreAleatoire = 1 + (int)(Math.random() * ((4 - 1) + 1));

        addTerme1.setText(Integer.toString(a.getTerme1()));
        addTerme2.setText(Integer.toString(a.getTerme2()));

        btnRep1.setText(Integer.toString(a.result()+5));
        btnRep2.setText(Integer.toString(a.result()+1));
        btnRep3.setText(Integer.toString(a.result()+2));
        btnRep4.setText(Integer.toString(a.result()-1));


        switch (nombreAleatoire) {
            case 1:
                btnRep1.setText(Integer.toString(a.result()));
                break;
            case 2:
                btnRep2.setText(Integer.toString(a.result()));
                break;
            case 3:
                btnRep3.setText(Integer.toString(a.result()));
                break;
            case 4:
                btnRep4.setText(Integer.toString(a.result()));
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        final Button btnRep1=(Button) findViewById(R.id.btnRep1);
        final Button btnRep2=(Button) findViewById(R.id.btnRep2);
        final Button btnRep3=(Button) findViewById(R.id.btnRep3);
        final Button btnRep4=(Button) findViewById(R.id.btnRep4);
        final TextView addTerme1=(TextView) findViewById(R.id.termeAdd1);
        final TextView addTerme2=(TextView) findViewById(R.id.termeAdd2);
        final Addition a=new Addition();
        final ImageView imageJoueur =(ImageView)findViewById(R.id.imageJoueur);
        ImageView imgCpu=(ImageView)findViewById(R.id.imageCpu);

        initQuest(btnRep1,btnRep2,btnRep3,btnRep4,a,addTerme1,addTerme2);

        btnRep1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Integer i=Integer.parseInt(btnRep1.getText().toString());
                verifRep(i,a,imageJoueur);
                initQuest(btnRep1,btnRep2,btnRep3,btnRep4,a,addTerme1,addTerme2);
            }
        });
        btnRep2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int i=Integer.parseInt(btnRep2.getText().toString());
                verifRep(i,a,imageJoueur);
                initQuest(btnRep1,btnRep2,btnRep3,btnRep4,a,addTerme1,addTerme2);
            }
        });
        btnRep3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int i=Integer.parseInt(btnRep3.getText().toString());
                verifRep(i,a,imageJoueur);
                initQuest(btnRep1,btnRep2,btnRep3,btnRep4,a,addTerme1,addTerme2);
            }
        });
        btnRep4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int i=Integer.parseInt(btnRep4.getText().toString());
                verifRep(i,a,imageJoueur);
                initQuest(btnRep1,btnRep2,btnRep3,btnRep4,a,addTerme1,addTerme2);
            }
        });

        ObjectAnimator.ofFloat(imgCpu,"translationX",0,1100).setDuration(30000).start();

        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog(DIALOG_ALERT_PERDU);
            }
        },30000);

    }

    protected Dialog onCreateDialog(int id){
        switch(id){
            case DIALOG_ALERT:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("Tu as gagné !Veux tu rejouer ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Oui !", new OkOnClickListener());
                builder.setNegativeButton("Non",new CancelOnClickListener());
                AlertDialog dialog=builder.create();
                dialog.show();
                break;
            case DIALOG_ALERT_PERDU:
                AlertDialog.Builder builderP=new AlertDialog.Builder(this);
                builderP.setMessage("Tu as Perdu ! Veux tu rejouer ?");
                builderP.setCancelable(true);
                builderP.setPositiveButton("Oui !", new OkOnClickListener());
                builderP.setNegativeButton("Non",new CancelOnClickListener());
                AlertDialog dialogP=builderP.create();
                dialogP.show();
        }
        return super.onCreateDialog(id);
    }
    private final class CancelOnClickListener implements DialogInterface.OnClickListener{
        public void onClick(DialogInterface dialog,int which){
            MainActivity.this.finish();
        }
    }
    private final class OkOnClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Intent intent=getIntent();
            overridePendingTransition(0,0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();

            overridePendingTransition(0,0);
            startActivity(intent);
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
