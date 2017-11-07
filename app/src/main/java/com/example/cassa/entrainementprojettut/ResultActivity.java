package com.example.cassa.entrainementprojettut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Button mButtonRejouer;
    private Button mButtonMenu;
    private TextView mTextResultat;


    public final static String mMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_gagner);

        mButtonMenu = (Button)findViewById(R.id.activity_win_menu_btn);
        mButtonRejouer = (Button)findViewById(R.id.activity_win_replay_btn);
        mTextResultat = (TextView)findViewById(R.id.activity_win_resultat_textView) ;

        Intent intent = getIntent();
        String mMessage = intent.getStringExtra("resultat");
        mTextResultat.setText(mMessage);


        mButtonRejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent additionIntent = new Intent(ResultActivity.this, AdditionActivity.class);
                startActivity(additionIntent);
            }
        });
        mButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent additionIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(additionIntent);
            }
        });


    }


}
