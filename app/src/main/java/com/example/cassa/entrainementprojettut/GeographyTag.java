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

    private Etiquette e;
    private ViewGroup mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographytag);
        mainLayout=findViewById(R.id.geographyTag_Layout);
        int[] vx=new int[2];
        vx[0]=10;
        vx[1]=100;
        int[] vy=new int[2];
        vy[0]=10;
        vy[1]=100;

        TextView etiquette=(TextView)findViewById(R.id.Etiquette);
        e=new Etiquette("Etiquette",(int)etiquette.getX(),(int)etiquette.getY(),vx,vy,R.id.Etiquette);
        etiquette.setOnTouchListener(onTouchListener());
    }

    private View.OnTouchListener onTouchListener(){
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()& MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        view.setX(motionEvent.getX());
                        view.setY(motionEvent.getY());

                    case MotionEvent.ACTION_MOVE:

                        view.setX(motionEvent.getX());
                        view.setY(motionEvent.getY());

                    case MotionEvent.ACTION_UP:
                        Toast toast;
                        toast=Toast.makeText(getApplicationContext(),"x="+e.getX()+" y="+e.getY(),Toast.LENGTH_SHORT);
                        toast.show();
                }
                return true;
            }
        };
    }
}
