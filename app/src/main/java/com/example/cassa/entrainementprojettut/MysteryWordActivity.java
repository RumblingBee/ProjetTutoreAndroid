package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Display;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MysteryWordActivity extends AppCompatActivity
{
    private Button button[] = new Button[26];

    private TextView txtOrder;
    private TextView txtSelect;
    private TextView txtAnswer;
    private ImageView imgPlayer;
    private ImageView imgIA;
    private LinearLayout btnLayout;

    final Handler handler = new Handler();
    float positionImageJoueur;

    private WordBank wordBank;
    private Word currentWord;
    private char selectedCharaAnswer;
    private int gNbReponsesCorrectes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_word);

        Intent intent = getIntent();
        int level = intent.getIntExtra("diff", 1);

        button[0] = (Button) findViewById(R.id.activity_mysteryWord_A_button);
        button[1] = (Button) findViewById(R.id.activity_mysteryWord_Z_button);
        button[2] = (Button) findViewById(R.id.activity_mysteryWord_E_button);
        button[3] = (Button) findViewById(R.id.activity_mysteryWord_R_button);
        button[4] = (Button) findViewById(R.id.activity_mysteryWord_T_button);
        button[5] = (Button) findViewById(R.id.activity_mysteryWord_Y_button);
        button[6] = (Button) findViewById(R.id.activity_mysteryWord_U_button);
        button[7] = (Button) findViewById(R.id.activity_mysteryWord_I_button);
        button[8] = (Button) findViewById(R.id.activity_mysteryWord_O_button);
        button[9] = (Button) findViewById(R.id.activity_mysteryWord_P_button);
        button[10] = (Button) findViewById(R.id.activity_mysteryWord_Q_button);
        button[11] = (Button) findViewById(R.id.activity_mysteryWord_S_button);
        button[12] = (Button) findViewById(R.id.activity_mysteryWord_D_button);
        button[13] = (Button) findViewById(R.id.activity_mysteryWord_F_button);
        button[14] = (Button) findViewById(R.id.activity_mysteryWord_G_button);
        button[15] = (Button) findViewById(R.id.activity_mysteryWord_H_button);
        button[16] = (Button) findViewById(R.id.activity_mysteryWord_J_button);
        button[17] = (Button) findViewById(R.id.activity_mysteryWord_K_button);
        button[18] = (Button) findViewById(R.id.activity_mysteryWord_L_button);
        button[19] = (Button) findViewById(R.id.activity_mysteryWord_M_button);
        button[20] = (Button) findViewById(R.id.activity_mysteryWord_W_button);
        button[21] = (Button) findViewById(R.id.activity_mysteryWord_X_button);
        button[22] = (Button) findViewById(R.id.activity_mysteryWord_C_button);
        button[23] = (Button) findViewById(R.id.activity_mysteryWord_V_button);
        button[24] = (Button) findViewById(R.id.activity_mysteryWord_B_button);
        button[25] = (Button) findViewById(R.id.activity_mysteryWord_N_button);

        txtAnswer = (TextView) findViewById(R.id.activity_mysteryWord_answer_textview);
        txtOrder = (TextView) findViewById(R.id.activity_mysteryWord_order_textview);
        txtSelect = (TextView) findViewById(R.id.activity_mysteryWord_selectedLetter_textview);

        imgIA = (ImageView) findViewById(R.id.activity_mysteryWord_ordi_img);
        imgPlayer = (ImageView) findViewById(R.id.acivity_mysteryWord_pos1_img);

        btnLayout = (LinearLayout)findViewById(R.id.activity_mysteryWord_word_linearlayout);

        for(int i=0; i<button.length; i++){
            final int tmp = i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = button[tmp].getText().toString();
                    txtSelect.setText(s);
                    checkAnswer(s);
                }
            });
        }

        //On génère une collection de 5 mots codés
        wordBank = new WordBank(level);

        //On récupère le mot et on l'affiche, ainsi que la consigne associée
        currentWord = wordBank.getWord();
        displayWord(currentWord);
        txtOrder.setText(currentWord.get_order());

        //On récupère la taille de l'écran
        float largeurEcran = retourTailleEcran();
        int largeurImageOrdi = imgIA.getDrawable().getIntrinsicWidth();

        //On lance le chrono, l'enfant perd s'il arrive au bout
        positionImageJoueur = imgPlayer.getX();
        handler.postDelayed(terminerActivite,27000);

        //On anime l'image représentant l'ordinateur
        bougerImage(imgIA, largeurEcran - largeurImageOrdi, 27000, 0);
    }

    private void displayWord(Word word)
    {
        int i = 0;
        for(char c : word.get_codedWord().toCharArray())
        {
            final int tmp = i;
            Button button = (Button)this.getLayoutInflater().inflate(R.layout.mystery_word_button, btnLayout, false);
            button.setText(String.valueOf(c));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCharaAnswer = currentWord.get_answer().charAt(tmp);
                }
            });
            btnLayout.addView(button);
            i++;
        }
    }

    private void checkAnswer(String s)
    {
        if(s.equalsIgnoreCase(String.valueOf(selectedCharaAnswer)))
            txtAnswer.setText("Bonne réponse !");
        else
            txtAnswer.setText("Essaye encore");
    }

    public float retourTailleEcran()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    protected Runnable terminerActivite = new Runnable() {
        @Override
        public void run() {
            terminerActivite();
        }
    };

    protected void bougerImage(ImageView pImage, float pDestination, int pDuration, float pPosDepart)
    {
        TranslateAnimation animationTranslation = new TranslateAnimation(pPosDepart,pDestination,0,0);
        animationTranslation.setFillAfter(true);
        animationTranslation.setDuration(pDuration);
        pImage.startAnimation(animationTranslation);
    }

    public void terminerActivite()
    {
        MysteryWordActivity.this.finish();
        overridePendingTransition(0,0);

        Intent ecranFin = new Intent(MysteryWordActivity.this, ResultActivity.class);

        handler.removeCallbacks(terminerActivite);
        ecranFin.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        if(gNbReponsesCorrectes == 5)
            ecranFin.putExtra("resultat", "Gagné!");
        else
            ecranFin.putExtra("resultat", "Perdu!");

        startActivity(ecranFin);
    }
}