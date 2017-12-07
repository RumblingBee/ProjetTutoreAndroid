package com.example.cassa.entrainementprojettut;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ReverseFlagActivity extends GameActivity {

    private ImageView mFlag;
    private TextView mScore;

    private TextView mNomPays1;
    private TextView mNomPays2;
    private TextView mNomPays3;
    private TextView mNomPays4;

    private int diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_flag);

        mFlag = (ImageView) findViewById(R.id.activity_reverse_flag_drapeau);
        mScore = (TextView) findViewById(R.id.activity_reverse_flag_score);

        mNomPays1 = (TextView) findViewById(R.id.activity_reverse_flag_name1);
        mNomPays2 = (TextView) findViewById(R.id.activity_reverse_flag_name2);
        mNomPays3 = (TextView) findViewById(R.id.activity_reverse_flag_name3);
        mNomPays4 = (TextView) findViewById(R.id.activity_reverse_flag_name4);

        diff = getIntent().getIntExtra("diff", 1);

        mScore.setText("0");



    }

    protected void genererPartie(){
        //FlagBank flagBank = new FlagBank();
    }
}
