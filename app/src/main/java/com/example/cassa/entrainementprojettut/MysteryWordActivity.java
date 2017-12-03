package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ToggleButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MysteryWordActivity extends AppCompatActivity
{
    private TextView txtOrder;
    private TextView txtAnswer;
    private ImageView imgPlayer;
    private ImageView imgIA;
    private LinearLayout btnLayout;

    private ToggleButton keyboard[] = new ToggleButton[26];
    float positionImageJoueur;
    private WordBank wordBank;
    private Word currentWord;
    private char selectedCharaAnswer;
    private int gNbReponsesCorrectes = 0;
    private int gNbLettreOk;
    private ToggleButton gselectedLetter;

    final Handler handler = new Handler();

    protected Runnable displayWord = new Runnable() {
        @Override
        public void run() {
            viderLayout();
            txtAnswer.setText("");
            displayWord(currentWord);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_word);

        Intent intent = getIntent();
        int level = intent.getIntExtra("diff", 1);

        keyboard[0] = (ToggleButton) findViewById(R.id.activity_mysteryWord_A_button);
        keyboard[1] = (ToggleButton) findViewById(R.id.activity_mysteryWord_Z_button);
        keyboard[2] = (ToggleButton) findViewById(R.id.activity_mysteryWord_E_button);
        keyboard[3] = (ToggleButton) findViewById(R.id.activity_mysteryWord_R_button);
        keyboard[4] = (ToggleButton) findViewById(R.id.activity_mysteryWord_T_button);
        keyboard[5] = (ToggleButton) findViewById(R.id.activity_mysteryWord_Y_button);
        keyboard[6] = (ToggleButton) findViewById(R.id.activity_mysteryWord_U_button);
        keyboard[7] = (ToggleButton) findViewById(R.id.activity_mysteryWord_I_button);
        keyboard[8] = (ToggleButton) findViewById(R.id.activity_mysteryWord_O_button);
        keyboard[9] = (ToggleButton) findViewById(R.id.activity_mysteryWord_P_button);
        keyboard[10] = (ToggleButton) findViewById(R.id.activity_mysteryWord_Q_button);
        keyboard[11] = (ToggleButton) findViewById(R.id.activity_mysteryWord_S_button);
        keyboard[12] = (ToggleButton) findViewById(R.id.activity_mysteryWord_D_button);
        keyboard[13] = (ToggleButton) findViewById(R.id.activity_mysteryWord_F_button);
        keyboard[14] = (ToggleButton) findViewById(R.id.activity_mysteryWord_G_button);
        keyboard[15] = (ToggleButton) findViewById(R.id.activity_mysteryWord_H_button);
        keyboard[16] = (ToggleButton) findViewById(R.id.activity_mysteryWord_J_button);
        keyboard[17] = (ToggleButton) findViewById(R.id.activity_mysteryWord_K_button);
        keyboard[18] = (ToggleButton) findViewById(R.id.activity_mysteryWord_L_button);
        keyboard[19] = (ToggleButton) findViewById(R.id.activity_mysteryWord_M_button);
        keyboard[20] = (ToggleButton) findViewById(R.id.activity_mysteryWord_W_button);
        keyboard[21] = (ToggleButton) findViewById(R.id.activity_mysteryWord_X_button);
        keyboard[22] = (ToggleButton) findViewById(R.id.activity_mysteryWord_C_button);
        keyboard[23] = (ToggleButton) findViewById(R.id.activity_mysteryWord_V_button);
        keyboard[24] = (ToggleButton) findViewById(R.id.activity_mysteryWord_B_button);
        keyboard[25] = (ToggleButton) findViewById(R.id.activity_mysteryWord_N_button);

        txtAnswer = (TextView) findViewById(R.id.activity_mysteryWord_answer_textview);
        txtOrder = (TextView) findViewById(R.id.activity_mysteryWord_order_textview);

        imgIA = (ImageView) findViewById(R.id.activity_mysteryWord_ordi_img);
        imgPlayer = (ImageView) findViewById(R.id.acivity_mysteryWord_pos1_img);

        btnLayout = (LinearLayout)findViewById(R.id.activity_mysteryWord_word_linearlayout);

        for(int i = 0; i < keyboard.length; i++){
            final int tmp = i;
            keyboard[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = keyboard[tmp].getText().toString();
                    if(checkAnswer(s)){
                        gNbLettreOk++;
                        gselectedLetter.setText(s);
                        gselectedLetter.setEnabled(false);
                        gselectedLetter.setTextColor(Color.rgb(60,60,60));
                        reinitClavier();
                        if(motFini(currentWord, gNbLettreOk)){
                            txtAnswer.setText("Bravo !");
                            gNbReponsesCorrectes++;
                            if(gNbReponsesCorrectes == 5){
                                terminerActivite(1);
                            }
                            else{
                                currentWord = wordBank.getWord(gNbReponsesCorrectes);
                                gNbLettreOk = 0;
                                handler.postDelayed(displayWord,1000);
                                txtOrder.setText(currentWord.get_order());
                            }
                        }
                        else{
                            int indexCurrentLetter = btnLayout.indexOfChild(gselectedLetter);
                            int indexNextLetter;
                            if(indexCurrentLetter == currentWord.get_codedWord().length() - 1 ||
                                    !btnLayout.getChildAt(indexCurrentLetter + 1).isEnabled()){
                                int j = 0;
                                while(!btnLayout.getChildAt(j).isEnabled()){
                                    j++;
                                }
                                indexNextLetter = j;
                            }
                            else{
                                indexNextLetter = indexCurrentLetter + 1;
                            }
                            ToggleButton nextLetter = (ToggleButton)btnLayout.getChildAt(indexNextLetter);
                            nextLetter.setChecked(true);
                            selectedCharaAnswer = currentWord.get_answer().charAt(indexNextLetter);
                            gselectedLetter = nextLetter;
                        }
                    }
                    else{
                        keyboard[tmp].setEnabled(false);
                    }
                }
            });
        }

        //On génère une collection de 5 mots codés
        wordBank = new WordBank(level);

        //On récupère le mot et on l'affiche, ainsi que la consigne associée
        currentWord = wordBank.getWord(gNbReponsesCorrectes);
        gNbLettreOk = 0;
        displayWord(currentWord);
        txtOrder.setText(currentWord.get_order());

        //On récupère la taille de l'écran
        float largeurEcran = retourTailleEcran();
        int largeurImageOrdi = imgIA.getDrawable().getIntrinsicWidth();

        //On lance le chrono, l'enfant perd s'il arrive au bout
        positionImageJoueur = imgPlayer.getX();
        handler.postDelayed(terminerActivite,60000);

        //On anime l'image représentant l'ordinateur
        bougerImage(imgIA, largeurEcran - largeurImageOrdi, 60000, 0);
    }

    private void displayWord(Word word)
    {
        int i = 0;
        final int wordLength = word.get_codedWord().length();
        for(char c : word.get_codedWord().toCharArray())
        {
            final int tmp = i;
            final ToggleButton button = (ToggleButton)this.getLayoutInflater().inflate(R.layout.mystery_word_button, btnLayout, false);
            button.setText(String.valueOf(c));
            button.setTextOff(String.valueOf(c));
            button.setTextOn(String.valueOf(c));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txtAnswer.setText("");
                    selectedCharaAnswer = currentWord.get_answer().charAt(tmp);
                    gselectedLetter = button;
                    for(int j = 0; j < wordLength; j++){
                        ToggleButton letter = (ToggleButton)btnLayout.getChildAt(j);
                        if(letter.isChecked() && letter.isEnabled() && letter != view){
                            letter.setChecked(false);
                        }
                    }
                }
            });
            btnLayout.addView(button);
            ToggleButton firstLetter = (ToggleButton)btnLayout.getChildAt(0);
            firstLetter.setChecked(true);
            selectedCharaAnswer = currentWord.get_answer().charAt(0);
            gselectedLetter = firstLetter;
            i++;
        }
    }
    private void viderLayout(){
        btnLayout.removeAllViews();
    }

    private boolean checkAnswer(String s)
    {
        boolean res = false;
        if(s.equalsIgnoreCase(String.valueOf(selectedCharaAnswer))){
            res = true;
            txtAnswer.setText("Bonne réponse, continue !");
        }
        else{
            txtAnswer.setText("Essaye encore !");
        }
        return res;
    }

    public boolean motFini(Word motActuel, int i){
        return(motActuel.get_answer().length() == i);
    }

    public void reinitClavier(){
        for(ToggleButton button : keyboard){
            button.setEnabled(true);
            button.setChecked(false);
        }
    }

    public float retourTailleEcran()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    protected void bougerImage(ImageView pImage, float pDestination, int pDuration, float pPosDepart)
    {
        TranslateAnimation animationTranslation = new TranslateAnimation(pPosDepart,pDestination,0,0);
        animationTranslation.setFillAfter(true);
        animationTranslation.setDuration(pDuration);
        pImage.startAnimation(animationTranslation);
    }

    protected Runnable terminerActivite = new Runnable() {
        @Override
        public void run() {
            terminerActivite(0);
        }
    };

    public void terminerActivite(int status)
    {
        MysteryWordActivity.this.finish();
        overridePendingTransition(0,0);

        Intent ecranFin = new Intent(MysteryWordActivity.this, ResultActivity.class);
        ecranFin.putExtra("Activity","MysteryWord");

        handler.removeCallbacks(terminerActivite);
        ecranFin.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        if(status != 0)
            ecranFin.putExtra("resultat", "Gagné!");
        else
            ecranFin.putExtra("resultat", "Perdu!");

        startActivity(ecranFin);
    }

    @Override
    public void onBackPressed()
    {
        Intent ecranMenu = new Intent(MysteryWordActivity.this, MainActivity.class);
        startActivity(ecranMenu);
        handler.removeCallbacks(terminerActivite);
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}