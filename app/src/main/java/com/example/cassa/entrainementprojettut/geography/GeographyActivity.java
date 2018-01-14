package com.example.cassa.entrainementprojettut.geography;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.cassa.entrainementprojettut.GameActivity;
import com.example.cassa.entrainementprojettut.R;

import java.util.List;

/**
 * Created by clement on 07/12/17.
 */

public class GeographyActivity extends GameActivity {

    private ViewGroup mainLayout;
    private List<Tag> tagList;

    private int rightAnswerCounter;
    private int deltaX;
    private int deltaY;

    private MediaPlayer mediaPlayer;
    private  TextView[] textViewTab;
    private Controler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        music = R.raw.geography_music;
        fullScreenMode();
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.geographyTag_relativeLayout);

        displayLevelChoice(GeographyActivity.this,"",3);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialogInterface) {
                                            if (levelChosen != 0) {

                                                controler = new Controler(levelChosen);
                                                tagList = controler.getTagList();

                                                setBackgroundImage();
                                                setRectanglesOnMap();

                                                generateTextView();
                                                setNameTextView();
                                                setTagTextView();
                                            } else {
                                                displayLevelChoice(GeographyActivity.this, "", 3);
                                            }
                                        }
                                    });

        rightAnswerCounter =0;

        startBackgroundMusic(GeographyActivity.this, R.raw.geography_music);
        mediaPlayer =MediaPlayer.create(GeographyActivity.this,R.raw.envent_sound);


    }

    private View.OnTouchListener onTouchListener(){
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

            final int x=(int) motionEvent.getRawX();
            final int y=(int) motionEvent.getRawY();

            switch(motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams=(RelativeLayout.LayoutParams)view.getLayoutParams();
                    deltaX =x-lParams.leftMargin;
                    deltaY =y-lParams.topMargin;

                    break;

                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin=x- deltaX;
                    layoutParams.topMargin=y- deltaY;
                    view.setLayoutParams(layoutParams);
                    break;

                case MotionEvent.ACTION_UP:
                   /*
                   TO SHOW TAG LOCATION ON SCREEN

                    Toast toast;
                    toast=Toast.makeText(getApplicationContext(),"x="+((x- deltaX)*12)/retourTailleEcran()+"/12 y="+((y- deltaY)*12)/getHauteurEcran()+"/12",Toast.LENGTH_SHORT);
                    toast.show();*/

                    int tagCoord[] = new int[2];
                    view.getLocationOnScreen(tagCoord);
                    float leftSide = tagCoord[0];
                    float rightSide = leftSide + view.getWidth();
                    float upperSide = tagCoord[1];
                    float downSide = upperSide + view.getHeight();

                    if (checkVictoryBox((float[])view.getTag(),leftSide, rightSide, upperSide, downSide)){
                       view.setEnabled(false);

                       view.setBackgroundColor(Color.GREEN);
                       mediaPlayer.start();

                    }

            }
            mainLayout.invalidate();
            return true;
            }
        };
    }

    private boolean checkVictoryBox(float[]victoryBox, float leftSideTextView, float rightSideTextView,
                                    float upperSideTextView, float lowerSideTextView){


        if( leftSideTextView >= victoryBox[0] && leftSideTextView <= victoryBox[1]
                && rightSideTextView >= victoryBox[0] && rightSideTextView <= victoryBox[1]
                && upperSideTextView >= victoryBox[2] && upperSideTextView <= victoryBox[3]
                && lowerSideTextView >= victoryBox[2] && lowerSideTextView <= victoryBox[3]){
         showText("Bravo!");
            rightAnswerCounter++;
            if(rightAnswerCounter == tagList.size()){
                showResultScreen(GeographyActivity.this,true,false,0);
            }
            return true;
        }

        return false;
    }

    private void generateTextView(){
        int maxTagInAColumn = getMaxTagInAColumn();
        int columnNumber = 0;

        textViewTab = new TextView[tagList.size()];

        for(int i = 0; i< tagList.size(); i++){

            RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            textViewTab[i] = new TextView(this);
            textViewTab[i].setPadding(10,10,10,10);

            if (((i+1) % maxTagInAColumn) == 0 ) {
                columnNumber +=1;
            }
            textViewParams.setMargins((columnNumber*350)+10, ((i-(columnNumber*(maxTagInAColumn-1))) * 100) + 10, 0, 0);

            textViewTab[i].setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
            textViewTab[i].setLayoutParams(textViewParams);
            textViewTab[i].setBackgroundColor(Color.BLUE);
            textViewTab[i].setOnTouchListener(onTouchListener());

            mainLayout.addView(textViewTab[i],textViewParams);
        }
    }

    private void setTagTextView() {
        for(int i = 0; i< tagList.size(); i++){
            textViewTab[i].setTag(tagList.get(i).getVictoryBox());
        }
    }

    private void setNameTextView() {
        for(int i = 0; i< tagList.size(); i++){
            textViewTab[i].setText(tagList.get(i).getName());
        }
    }

    private void setBackgroundImage() {

            mainLayout.setBackgroundResource(controler.getBackgroundImage());

    }

    private void setRectanglesOnMap(){
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(85);

        Bitmap b = Bitmap.createBitmap((int)getScreenWidth(), (int)getScreenHeight(), Bitmap.Config.ARGB_8888);

        View conteneurRect = getLayoutInflater().inflate(R.layout.geographytag_conteneur_rect, mainLayout, false);
        mainLayout.addView(conteneurRect);

        ImageView drawRectangle = findViewById(R.id.geography_conteneur_rect);
        drawRectangle.setImageBitmap(b);

        Canvas canvas = new Canvas(b);
        RectF rect;
        float[] tagCoords;

        for(Tag tag : tagList){

            tagCoords = resizeVictoryBox(tag.getVictoryBox());

            rect = new RectF(tagCoords[0], tagCoords[2], tagCoords[1],
                    tagCoords[3]);

            canvas.drawRect(rect, paint);

        }
    }

    private float[] resizeVictoryBox(float[] victoryBox) {

        victoryBox[0] = victoryBox[0] * getScreenWidth();
        victoryBox[1] = victoryBox[1] * getScreenWidth();
        victoryBox[2]  = victoryBox[2] * getScreenHeight();
        victoryBox[3]  = victoryBox[3] * getScreenHeight();


        return  victoryBox;


    }

    private int getMaxTagInAColumn(){
        int maxTagInACol = 0;
        while(maxTagInACol*100<= getScreenHeight() * 0.8){
            maxTagInACol++;
        }

        return maxTagInACol;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullScreenMode();
    }

    private void fullScreenMode(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
