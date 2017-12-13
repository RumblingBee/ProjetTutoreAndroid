package com.example.cassa.entrainementprojettut;

import android.annotation.SuppressLint;
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

/**
 * Created by clement on 07/12/17.
 */

public class GeographyTag extends GameActivity {

    private ViewGroup mainLayout;
    private TextView image;

    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographytag);
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        image = (TextView) findViewById(R.id.image);

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
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

    public float[] getPosition(MotionEvent me){
        float[] pos=new float[2];
        pos[0]=me.getRawX()-xDelta;
        pos[1]=me.getRawY()-yDelta;
        return pos;
    }
}
