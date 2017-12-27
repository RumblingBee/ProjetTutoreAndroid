/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cassa.entrainementprojettut;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements AppCompatCallback,
        TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider {

    private AppCompatDelegate mDelegate;
    private int mThemeId = 0;
    private Resources mResources;
    Toast toast;

    public GameActivity() {
        dialog = null;
    }


    public float retourTailleEcran(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
    public float getHauteurEcran(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    final Handler handler = new Handler();
    float positionImageJoueur;
    protected ImageView mImagePos1;
    protected ImageView mImageOrdi;
    protected Runnable perdreActivite;


    protected  void afficherTexte(String text){


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

    protected int niveauChoisi = 0;
    protected MediaPlayer bgPlayer;

    protected AlertDialog dialog;

    protected void initialiserPartie(){
        niveauChoisi = 0;

    }


    protected void afficherChoixNiveaux(final Activity activite,String typeDeNiveau,int nombreNiveau) {


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activite);


        View lvlChoiceView = getLayoutInflater().inflate(R.layout.level_choice_popup, null);

        String tabClasses[] = {"CP", "CE1", "CE2", "CM1", "CM2"};
        String tabNiveaux[] = {"Niveau 1", "Niveau 2", "Niveau 3", "Niveau 4", "Niveau 5"};
        String tabCouleurs[] = {"#77dd6c", "#eebf38", "#ee3838", "#c847ea", "#47aaea"};

        LinearLayout conteneur = (LinearLayout)lvlChoiceView.findViewById(R.id.level_popup_activity_linearlayout);
        for(int i = 0; i < nombreNiveau; i++){
            final Button lvlButton = (Button)this.getLayoutInflater().inflate(R.layout.level_choice_button, conteneur, false);
            if(typeDeNiveau.equalsIgnoreCase( "listeClasse")){
                lvlButton.setText(tabClasses[i]);
            }
            else{
                lvlButton.setText(tabNiveaux[i]);
            }
            lvlButton.setTag(i + 1);
            lvlButton.getBackground().setColorFilter(Color.parseColor(tabCouleurs[i]), PorterDuff.Mode.MULTIPLY);
            lvlButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    niveauChoisi = (int)view.getTag();
                    dialog.dismiss();
                }
            });
            if(conteneur != null) {
                conteneur.addView(lvlButton);
            }
        }

        TextView mMessage = (TextView) lvlChoiceView.findViewById(R.id.level_popup_message_textView);

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

    protected void afficherEcranFin(final Activity activite,boolean gagne,boolean aUnScore,int score){
        niveauChoisi = 0;
        final boolean[] peutQuitter = {false};
        if(perdreActivite != null) {
            handler.removeCallbacks(perdreActivite);
        }

        niveauChoisi = 0;

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activite);


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
         if(aUnScore == false) {
            if (gagne == true) {
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
                if (peutQuitter[0] == true) {
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
                        peutQuitter[0] = true;
                        activite.recreate();
                        dialog.dismiss();

                    }
                });
                mButtonMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        peutQuitter[0] = true;
                        Intent additionIntent = new Intent(activite, MainActivity.class);
                        startActivity(additionIntent);
                        activite.finish();

                        }
                });

    }

    protected void bougerImage(ImageView pImage, float pDestination, int pDuration, float pPosDepart){

        TranslateAnimation animationTranslation=new TranslateAnimation(pPosDepart,pDestination,0,0);
        animationTranslation.setFillAfter(true);
        animationTranslation.setDuration(pDuration);
        pImage.startAnimation(animationTranslation);

    }


    protected void lancerBgMusique(Activity selfActivity, int idMusic){

        if(bgPlayer != null){ bgPlayer.stop();}

        bgPlayer = MediaPlayer.create(selfActivity,idMusic);
        bgPlayer.start();

    }


    protected void lancerCourse(final Activity srcActivity,int dureeArriveeRobot,int imageJoueur,int imageOrdinateur){


        lancerDecompteFinPartie(srcActivity,dureeArriveeRobot);

        mImagePos1 = (ImageView)findViewById(imageJoueur);
        mImageOrdi = (ImageView)findViewById(imageOrdinateur);
        //On récupère la taille de l'écran

        float largeurEcran = retourTailleEcran();
        int largeurImageOrdi = mImageOrdi.getDrawable().getIntrinsicWidth();



        //On lance le chrono, l'enfant perd s'il arrive au bout

        positionImageJoueur = mImagePos1.getX();



        // On anime l'image représentant l'ordinateur

        bougerImage(mImageOrdi,largeurEcran-largeurImageOrdi,dureeArriveeRobot,0);
    }


    protected void lancerDecompteFinPartie(final Activity srcActivity,int temps){
        perdreActivite = new Runnable() {
            @Override
            public void run() {
                afficherEcranFin(srcActivity,false,false,0);

            }
        };


        handler.postDelayed(perdreActivite,temps);
    }





}
