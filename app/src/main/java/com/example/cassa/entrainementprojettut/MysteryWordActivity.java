package com.example.cassa.entrainementprojettut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MysteryWordActivity extends AppCompatActivity
{
    private Button button[] = new Button[26];

    private TextView txtOrder;
    private TextView txtSelect;
    private TextView txtAnswer;

    private ImageView imgPlayer;
    private ImageView imgIA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_word);

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

        for(int i=0; i<button.length; i++){
            final int tmp = i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = button[tmp].getText().toString();
                    txtSelect.setText(s);
                }
            });
        }

    }

}
