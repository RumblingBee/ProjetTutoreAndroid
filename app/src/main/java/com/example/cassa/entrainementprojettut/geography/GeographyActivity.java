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
import java.util.Collections;
import java.util.List;


/**
 * Created by clement on 07/12/17.
 */

public class GeographyActivity extends GameActivity {

    private ViewGroup       mainLayout;
    private List<Tag> tagList;

    private int rightAnswerCounter;
    private int xDelta;
    private int yDelta;

    private MediaPlayer playerEvent;
    private  TextView[] tabTextView;
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
                                                setRectangleOnMap();

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
        playerEvent=MediaPlayer.create(GeographyActivity.this,R.raw.envent_sound);


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
                    xDelta=x-lParams.leftMargin;
                    yDelta=y-lParams.topMargin;

                    break;

                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin=x-xDelta;
                    layoutParams.topMargin=y-yDelta;
                    view.setLayoutParams(layoutParams);
                    break;

                case MotionEvent.ACTION_UP:
                    /*Toast toast;
                    toast=Toast.makeText(getApplicationContext(),"x="+((x-xDelta)*12)/retourTailleEcran()+"/12 y="+((y-yDelta)*12)/getHauteurEcran()+"/12",Toast.LENGTH_SHORT);
                    toast.show();*/

                    int tagCoords[] = new int[2];
                    view.getLocationOnScreen(tagCoords);
                    float leftSide = tagCoords[0];
                    float rightSide = leftSide + view.getWidth();
                    float upperSide = tagCoords[1];
                    float downSide = upperSide + view.getHeight();

                    if (checkVictoryBox((float[])view.getTag(),leftSide, rightSide, upperSide, downSide)){
                       view.setEnabled(false);

                       view.setBackgroundColor(Color.GREEN);
                       playerEvent.start();

                    }

            }
            mainLayout.invalidate();
            return true;
            }
        };
    }

    private void generateTextView(){
        int maxTagInAColumn = getMaxTagInAColumn();
        int columnID = 0;

        Collections.shuffle(tagList);

        tabTextView = new TextView[tagList.size()];

        for(int i = 0; i< tagList.size(); i++){

            RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            tabTextView[i] = new TextView(this);



            if (isPlacedInTheNextCol(maxTagInAColumn, columnID, i) == true){
                columnID +=1;
            }
            textViewParams.setMargins((columnID*150)+10, ((i-(columnID*(maxTagInAColumn-1))) * 100) + 10, 0, 0);

            tabTextView[i].setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
            tabTextView[i].setLayoutParams(textViewParams);
            tabTextView[i].setBackgroundColor(Color.parseColor("#f3f0e8"));

            //TODO: Changer la largeur des étiquettes
            tabTextView[i].setTextSize(getScreenHeight()*0.02F);

            tabTextView[i].setOnTouchListener(onTouchListener());

            mainLayout.addView(tabTextView[i],textViewParams);
        }
    }

    private Boolean isPlacedInTheNextCol(int maxTagInAColum, int columnID, int idTag) {
        return (((((idTag + 1) + columnID) % (maxTagInAColum)) == 0));

    }

    private void setTagTextView() {
        for(int i = 0; i< tagList.size(); i++){
            tabTextView[i].setTag(tagList.get(i).getVictoryBox());
        }
    }

    private void setNameTextView() {
        for(int i = 0; i< tagList.size(); i++){
            tabTextView[i].setText(tagList.get(i).getName());
        }
    }

    private void setBackgroundImage() {

            mainLayout.setBackgroundResource(controler.getBackgroundImage());

    }

    private void setRectangleOnMap(){
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

        for(Tag etiquette : tagList){

            tagCoords = resizeVictoryBow(etiquette.getVictoryBox());

            rect = new RectF(tagCoords[0], tagCoords[2], tagCoords[1],
                    tagCoords[3]);

            canvas.drawRect(rect, paint);

        }
    }

    private boolean checkVictoryBox(float[]victoryBox, float leftSideTxtView, float rightSideTxtView,
                                    float upperSideTxtView, float downSideTxtView){

        victoryBox = vicotryBoxHitBoxTolerance(victoryBox); //victoryBox

        if( leftSideTxtView >= victoryBox[0] && leftSideTxtView <= victoryBox[1]
                && rightSideTxtView >= victoryBox[0] && rightSideTxtView <= victoryBox[1]
                && upperSideTxtView >= victoryBox[2] && upperSideTxtView <= victoryBox[3]
                && downSideTxtView >= victoryBox[2] && downSideTxtView <= victoryBox[3]){
            showText("Bravo!");
            rightAnswerCounter++;
            if(rightAnswerCounter == tagList.size()){
                showResultScreen(GeographyActivity.this,true,false,0);
            }
            return true;
        }

        return false;
    }

    private float[] vicotryBoxHitBoxTolerance(float[] zoneVictoireEtiquette) {
        zoneVictoireEtiquette[0] -= 8;
        zoneVictoireEtiquette[1] += 8;
        zoneVictoireEtiquette[2] -= 8;
        zoneVictoireEtiquette[3] += 8;

        return zoneVictoireEtiquette;
    }

    private float[] resizeVictoryBow(float[] victoryBox) {



        victoryBox[0] = victoryBox[0] * getScreenWidth();
        victoryBox[1] = victoryBox[1] * getScreenWidth();
        victoryBox[2]  = victoryBox[2] * getScreenHeight();
        victoryBox[3]  = victoryBox[3] * getScreenHeight();


        return  victoryBox;


    }

    private int getMaxTagInAColumn(){
        int maxTagInAColumn = 0;
        while(maxTagInAColumn*100<= getScreenHeight() * 0.9){
            maxTagInAColumn++;
        }

        return maxTagInAColumn;
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
