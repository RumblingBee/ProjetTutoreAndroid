package com.example.cassa.entrainementprojettut;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by clement on 07/12/17.
 */

public class GeographyTag extends GameActivity {

    private ViewGroup mainLayout;
    private TextView image;
    private TextView mEtiquette2;

    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.main);

        image = (TextView) findViewById(R.id.image);
        mEtiquette2 = (TextView) findViewById(R.id.activity_geographytag_etiquette2_textView);

        image.setText("Amerique du Nord");

        //On crée les étiquettes
        Etiquette etiquette2 = new Etiquette("Afrique",retourTailleEcran()/3,retourTailleEcran()/2,getHauteurEcran()/3,getHauteurEcran());


        //On attribue la zone de victoire à la textView
        mEtiquette2.setTag(etiquette2.getZoneVictoire());

        //On lui met le nom de l'étiquette
        mEtiquette2.setText(etiquette2.getNom());

        //On rend la textView "dragable"
        mEtiquette2.setOnTouchListener(onTouchListener());


        float[] tabZoneVictoire = new float[4];
        tabZoneVictoire[0] = 0; // xMin
        tabZoneVictoire[1] = retourTailleEcran()/4;  //xMax
        tabZoneVictoire[2] = 0;
        tabZoneVictoire[3] = getHauteurEcran()/3;

        image.setTag(tabZoneVictoire);




        if(image!=null) {

            image.setOnTouchListener(onTouchListener());
        }else{
            System.out.println("C'est la merde");
        }
    }

    private View.OnTouchListener onTouchListener(){
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int x=(int) motionEvent.getRawX();
                final int y=(int) motionEvent.getRawY();

                switch(motionEvent.getAction()& MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams=(RelativeLayout.LayoutParams)view.getLayoutParams();

                        xDelta=x-lParams.leftMargin;
                        yDelta=y-lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin=x-xDelta;
                        layoutParams.topMargin=y-yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;

                    case MotionEvent.ACTION_UP:
                        Toast toast;
                        toast=Toast.makeText(getApplicationContext(),"x="+(x-xDelta)+" y="+(y-yDelta),Toast.LENGTH_SHORT);
                        toast.show();

                       if (verifierZone((float[])view.getTag(),(x-xDelta),(y-yDelta))){
                           view.setEnabled(false);
                           view.setBackgroundColor(Color.argb(150,200,200,200));
                       }

                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

    private boolean verifierZone(float[]zoneVictoireEtiquette,int positionX, int positionY){
        if( positionX >= zoneVictoireEtiquette[0] && positionX <= zoneVictoireEtiquette[1] && positionY>= zoneVictoireEtiquette[2] && positionY <= zoneVictoireEtiquette[3]){
         afficherTexte("Victoire!");
            return true;
        }


        return false;
    }

    public float[] getPosition(MotionEvent me){
        float[] pos=new float[2];
        pos[0]=me.getRawX()-xDelta;
        pos[1]=me.getRawY()-yDelta;
        return pos;
    }
}
