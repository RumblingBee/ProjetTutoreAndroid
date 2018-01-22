

package com.example.cassa.entrainementprojettut;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements AppCompatCallback,
        TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider {
    
    Toast toast;

    public GameActivity() {
        dialog = null;
    }


    public float getScreenWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
    public float getScreenHeight(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    final Handler handler = new Handler();
    float playerImagePosition;
    protected ImageView playerImage;
    protected ImageView IAImage;
    protected Runnable looseActivity;



    protected  void showText(String text){

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        toast = Toast.makeText(context, text, duration);
        toast.show();

        Handler toastStop = new Handler();
        toastStop.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 500);
    }

    protected int levelChosen = 0;
    protected MediaPlayer bgPlayer;

    protected AlertDialog dialog;



    protected void initializeGame(){

        levelChosen = 0;


    }


    protected void displayLevelChoice(final Activity activite, String typeDeNiveau, int idLevel) {



        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activite);


        View lvlChoiceView = getLayoutInflater().inflate(R.layout.level_choice_popup, null);

        String classTab[] = {"CP", "CE1", "CE2", "CM1", "CM2"};
        String lvlTab[] = {"Niveau 1", "Niveau 2", "Niveau 3", "Niveau 4", "Niveau 5"};
        String colorsTab[] = {"#77dd6c", "#eebf38", "#ee3838", "#c847ea", "#47aaea"};

        LinearLayout container = (LinearLayout)lvlChoiceView.findViewById(R.id.level_popup_activity_linearlayout);
        for(int i = 0; i < idLevel; i++){
            final Button lvlButton = (Button)this.getLayoutInflater().inflate(R.layout.level_choice_button, container, false);
            if(typeDeNiveau.equalsIgnoreCase( "listeClasse")){
                lvlButton.setText(classTab[i]);
            }
            else{
                lvlButton.setText(lvlTab[i]);
            }
            lvlButton.setTag(i + 1);
            lvlButton.getBackground().setColorFilter(Color.parseColor(colorsTab[i]), PorterDuff.Mode.MULTIPLY);
            lvlButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    levelChosen = (int)view.getTag();
                    dialog.dismiss();
                }
            });
            if(container != null) {
                container.addView(lvlButton);
            }
        }

        TextView message = (TextView) lvlChoiceView.findViewById(R.id.level_popup_message_textView);

        mBuilder.setView(lvlChoiceView);
        dialog = mBuilder.create();
        dialog.show();

        //On prend les caracs de l'écran
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());

        //On l'applique au dialogue
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {

                Context c = getApplicationContext();


                Intent ecranMenu = new Intent(c, MainActivity.class);
                startActivity(ecranMenu);
            }
        });
    }



    protected void showResultScreen(final Activity activity, boolean win, boolean hasAScore, int score){
        levelChosen = 0;
        final boolean[] canLeave = {false};
        if(looseActivity != null) {
            handler.removeCallbacks(looseActivity);


        }

        levelChosen = 0;

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);


        View resultView = getLayoutInflater().inflate(R.layout.resultat_popup, null);

        Button mButtonRejouer = (Button) resultView.findViewById(R.id.resultat_popup_rejouer_btn);
        Button mButtonMenu = (Button) resultView.findViewById(R.id.resultat_popup_menu_btn);
        TextView mTextViewMessage = (TextView)resultView.findViewById(R.id.resultat_popup_messace_textView);

        mBuilder.setView(resultView);
        dialog = mBuilder.create();
        dialog.show();

        //On prend les caracs de l'écran
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());

        //On l'applique au dialogue
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);



        //Affichage du message
         if(hasAScore == false) {
            if (win == true) {
                mTextViewMessage.setText("Bravo, tu as gagné!");
            } else {
                mTextViewMessage.setText("Dommage, tu as perdu.");
            }
        }
        else{
            mTextViewMessage.setText("Ton score est de " +score);
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (canLeave[0] == true) {
                    dialog.dismiss();

                }
                else{
                    dialog.show();
                }

            }
        });
                mButtonRejouer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        canLeave[0] = true;
                        activity.recreate();
                        dialog.dismiss();

                    }
                });
                mButtonMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        canLeave[0] = true;
                        Intent additionIntent = new Intent(activity, MainActivity.class);
                        startActivity(additionIntent);
                        activity.finish();

                        }
                });

    }

    protected void moveImage(ImageView pImage, float pDestination, int pDuration, float pPosDepart){


        TranslateAnimation animationTranslation=new TranslateAnimation(pPosDepart,pDestination,0,0);
        animationTranslation.setFillAfter(true);
        animationTranslation.setDuration(pDuration);
        pImage.startAnimation(animationTranslation);

    }

    protected int music = 0;


    protected void startBackgroundMusic(Activity selfActivity, int idMusic){


        if(bgPlayer != null){ bgPlayer.stop();}

        bgPlayer = MediaPlayer.create(selfActivity,idMusic);
        bgPlayer.start();

    }


    protected void launchTimer(final Activity srcActivity, int arrivalTime, int playerImage, int IAImage){


        startChrono(srcActivity,arrivalTime);

        this.playerImage = (ImageView)findViewById(playerImage);
        this.IAImage = (ImageView)findViewById(IAImage);
        //On récupère la taille de l'écran


        float screenWidth = getScreenWidth();
        int IApictureWidth = this.IAImage.getDrawable().getIntrinsicWidth();




        //On lance le chrono, l'enfant perd s'il arrive au bout

        playerImagePosition = this.playerImage.getX();



        // On anime l'image représentant l'ordinateur

        moveImage(this.IAImage,screenWidth-IApictureWidth,arrivalTime,0);

    }


    protected void startChrono(final Activity srcActivity, int temps){
        looseActivity = new Runnable() {
            @Override
            public void run() {

                showResultScreen(srcActivity,false,false,0);


            }
        };


        handler.postDelayed(looseActivity,temps);
    }


    @Override
    protected void onPause() {
        super.onPause();
        bgPlayer.stop();
    }

    @Override
    public void onBackPressed(){
        bgPlayer.stop();
        Intent back = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        bgPlayer.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bgPlayer.start();
    }

    @Override
    protected void onResume() {
        if(bgPlayer != null){


            startBackgroundMusic(GameActivity.this, music);


        }
        super.onResume();
    }
}
