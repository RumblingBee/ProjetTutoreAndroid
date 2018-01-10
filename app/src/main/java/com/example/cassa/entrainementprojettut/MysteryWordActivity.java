package com.example.cassa.entrainementprojettut;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cassa.entrainementprojettut.mysteryWord.ControleurWordBank;
import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;

public class MysteryWordActivity extends GameActivity {
    private TextView gTxtOrder;
    private TextView gTxtAnswer;
    private ImageView gImgPlayer;
    private LinearLayout gBtnLayout;

    private ToggleButton gKeyboard[];
    float gPositionImageJoueur;

    private ControleurWordBank controleurWordBank;
    private I_Word motEnCour;

    private char gSelectedCharaAnswer;
    private int gNbReponsesCorrectes = 0;
    private int gNbLettreOk;
    private ToggleButton gSelectedLetter;

    final Handler gHandler = new Handler();


    protected Runnable gDisplayWord = new Runnable() {
        @Override
        public void run() {
            viderLayout();
            gTxtAnswer.setText("");
            displayWord(motEnCour);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_word);

        mMusique = R.raw.bensound_cute;
        launchBackgroundMusic(MysteryWordActivity.this, mMusique);

        gKeyboard= new ToggleButton[26];
        controleurWordBank =new ControleurWordBank(niveauChoisi);


        displayLevelChoice(MysteryWordActivity.this, "listeClasse", 5);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialogInterface){
                if (niveauChoisi != 0) {
                    lancerPartie();
                }
                else {
                    MysteryWordActivity.this.onStop();
                    dialog.show();
                }
            }
        });

        gKeyboard[0] = (ToggleButton) findViewById(R.id.activity_mysteryWord_A_button);
        gKeyboard[1] = (ToggleButton) findViewById(R.id.activity_mysteryWord_Z_button);
        gKeyboard[2] = (ToggleButton) findViewById(R.id.activity_mysteryWord_E_button);
        gKeyboard[3] = (ToggleButton) findViewById(R.id.activity_mysteryWord_R_button);
        gKeyboard[4] = (ToggleButton) findViewById(R.id.activity_mysteryWord_T_button);
        gKeyboard[5] = (ToggleButton) findViewById(R.id.activity_mysteryWord_Y_button);
        gKeyboard[6] = (ToggleButton) findViewById(R.id.activity_mysteryWord_U_button);
        gKeyboard[7] = (ToggleButton) findViewById(R.id.activity_mysteryWord_I_button);
        gKeyboard[8] = (ToggleButton) findViewById(R.id.activity_mysteryWord_O_button);
        gKeyboard[9] = (ToggleButton) findViewById(R.id.activity_mysteryWord_P_button);
        gKeyboard[10] = (ToggleButton) findViewById(R.id.activity_mysteryWord_Q_button);
        gKeyboard[11] = (ToggleButton) findViewById(R.id.activity_mysteryWord_S_button);
        gKeyboard[12] = (ToggleButton) findViewById(R.id.activity_mysteryWord_D_button);
        gKeyboard[13] = (ToggleButton) findViewById(R.id.activity_mysteryWord_F_button);
        gKeyboard[14] = (ToggleButton) findViewById(R.id.activity_mysteryWord_G_button);
        gKeyboard[15] = (ToggleButton) findViewById(R.id.activity_mysteryWord_H_button);
        gKeyboard[16] = (ToggleButton) findViewById(R.id.activity_mysteryWord_J_button);
        gKeyboard[17] = (ToggleButton) findViewById(R.id.activity_mysteryWord_K_button);
        gKeyboard[18] = (ToggleButton) findViewById(R.id.activity_mysteryWord_L_button);
        gKeyboard[19] = (ToggleButton) findViewById(R.id.activity_mysteryWord_M_button);
        gKeyboard[20] = (ToggleButton) findViewById(R.id.activity_mysteryWord_W_button);
        gKeyboard[21] = (ToggleButton) findViewById(R.id.activity_mysteryWord_X_button);
        gKeyboard[22] = (ToggleButton) findViewById(R.id.activity_mysteryWord_C_button);
        gKeyboard[23] = (ToggleButton) findViewById(R.id.activity_mysteryWord_V_button);
        gKeyboard[24] = (ToggleButton) findViewById(R.id.activity_mysteryWord_B_button);
        gKeyboard[25] = (ToggleButton) findViewById(R.id.activity_mysteryWord_N_button);

        gTxtAnswer = (TextView) findViewById(R.id.activity_mysteryWord_answer_textview);
        gTxtOrder = (TextView) findViewById(R.id.activity_mysteryWord_order_textview);

        gImgPlayer = (ImageView) findViewById(R.id.activity_mysteryWord_pos1_img);

        gBtnLayout = (LinearLayout) findViewById(R.id.activity_mysteryWord_word_linearlayout);

        for (int i = 0; i < gKeyboard.length; i++) {
            final int tmp = i;
            gKeyboard[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validRep(gKeyboard[tmp], gSelectedLetter);
                    System.out.println(gSelectedCharaAnswer);
                }
            });
        }
    }

    private void displayWord(I_Word pWord) {

        int i = 0;
        final int wordLength = pWord.getMotCode().length();
        for (char c : pWord.getMotCode().toCharArray()) {
            final int tmp = i;
            final ToggleButton button = (ToggleButton) this.getLayoutInflater().inflate(R.layout.mystery_word_button, gBtnLayout, false);
            button.setText(String.valueOf(c));
            button.setTextOff(String.valueOf(c));
            button.setTextOn(String.valueOf(c));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View pView) {
                    reinitClavier();
                    gTxtAnswer.setText("");
                    gSelectedCharaAnswer = motEnCour.getMot().charAt(tmp);
                    gSelectedLetter = (ToggleButton)pView;
                    pView.setClickable(false);
                    for (int j = 0; j < wordLength; j++) {
                        ToggleButton letter = (ToggleButton) gBtnLayout.getChildAt(j);
                        if (letter.isChecked() && letter.isEnabled() && letter != pView) {
                            letter.setChecked(false);
                            letter.setClickable(true);
                        }
                    }
                }
            });
            gBtnLayout.addView(button);
            ToggleButton firstLetter = (ToggleButton) gBtnLayout.getChildAt(0);
            firstLetter.setChecked(true);
            gSelectedCharaAnswer = motEnCour.getMot().charAt(0);
            gSelectedLetter = firstLetter;
            i++;
        }
    }

    private void viderLayout() {
        gBtnLayout.removeAllViews();
    }

    private boolean checkAnswer(String pString) {
        boolean res = false;
        if (pString.equalsIgnoreCase(String.valueOf(gSelectedCharaAnswer))) {
            res = true;
            gTxtAnswer.setText("Bonne réponse, continue !");
        } else {
            gTxtAnswer.setText("Essaye encore !");
        }
        return res;
    }

    public boolean motFini(I_Word sMotActuel, int i) {
        return (sMotActuel.getMot().length() == i);
    }

    public void reinitClavier() {
        for (ToggleButton button : gKeyboard) {
            button.setEnabled(true);
            button.setChecked(false);
        }
    }

    protected void onDestroy(){
        super.onDestroy();

        bgPlayer.stop();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    //TODO Placer en classe mère
    public void desactiverBouton(Button pBtn, String pString) {
        pBtn.setText(pString);
        pBtn.setEnabled(false);
        pBtn.setTextColor(Color.rgb(60, 60, 60));
    }

    public void validRep(Button pBtn, Button pBtnSelec) {
        String s = pBtn.getText().toString();
        if (checkAnswer(s)) {
            gNbLettreOk++;
            desactiverBouton(pBtnSelec, s);
            reinitClavier();
            validMot(motEnCour, gNbLettreOk, gTxtAnswer);
        } else {
            pBtn.setEnabled(false);
        }
    }

    public void validMot(I_Word pWord, int pInt, TextView pReponse) {
        if (motFini(pWord, pInt)) {
            pReponse.setText("Bravo !");
            gNbReponsesCorrectes++;
            float largeurEcran = getScreenSize();
            movePicture(gImgPlayer, gPositionImageJoueur + (largeurEcran / 5), 600, gPositionImageJoueur);
            gPositionImageJoueur = gPositionImageJoueur + (largeurEcran / 5);
            partieFinie(5);
        } else {
            int indexCurrentLetter = gBtnLayout.indexOfChild(gSelectedLetter);
            int indexNextLetter;
            if (indexCurrentLetter == motEnCour.getMotCode().length() - 1 ||
                    !gBtnLayout.getChildAt(indexCurrentLetter + 1).isEnabled()) {
                int j = 0;
                while (!gBtnLayout.getChildAt(j).isEnabled()) {
                    j++;
                }
                indexNextLetter = j;
            } else {
                indexNextLetter = indexCurrentLetter + 1;
            }
            ToggleButton nextLetter = (ToggleButton) gBtnLayout.getChildAt(indexNextLetter);
            nextLetter.setChecked(true);
            gSelectedCharaAnswer = motEnCour.getMot().charAt(indexNextLetter);
            gSelectedLetter = nextLetter;
            nextLetter.setClickable(false);
        }
    }

    public void partieFinie(int pNbMot) {
        if (gNbReponsesCorrectes == pNbMot) {
            displayEndScreen(MysteryWordActivity.this, true, false, 0);
        }
        else {
            motEnCour = motSuivant();
        }
    }

    public void lancerPartie() {
        //On récupère le mot et on l'affiche, ainsi que la consigne associée
        motEnCour = controleurWordBank.getUnMot(0);
        gNbLettreOk = 0;
        displayWord(motEnCour);
        gTxtOrder.setText(motEnCour.getConsigne());

        int duree;
        switch(niveauChoisi)
        {
            case 1: case 2: case 3:
                duree = 120000;
                break;
            case 4: case 5:
                duree = 180000;
                break;
            default:
                duree = 120000;
                break;
        }
        lancerCourse(MysteryWordActivity.this,
                duree, R.id.activity_mysteryWord_pos1_img, R.id.activity_mysteryWord_ordi_img);

    }

    /**
     * @return I_Word motSuivant
     */

    public I_Word motSuivant() {
        I_Word motSuivant = controleurWordBank.getUnMot(gNbReponsesCorrectes);
        gNbLettreOk = 0;
        gHandler.postDelayed(gDisplayWord, 1000);
        gTxtOrder.setText(motSuivant.getConsigne());
        return motSuivant;
    }
}