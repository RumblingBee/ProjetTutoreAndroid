package com.example.cassa.entrainementprojettut.geographie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by clement on 12/12/17.
 */

public class Etiquette {

    private String nom;
    private float[] zoneVictoire=new float[4];


    public float[] getZoneVictoire() {
        return zoneVictoire;
    }

    public String getNom() {
        return nom;
    }

    public Etiquette(String nom, float xMin, float xMax, float yMin, float yMax) {
        this.nom = nom;
        this.zoneVictoire[0] = xMin;
        this.zoneVictoire[1] = xMax;
        this.zoneVictoire[2] = yMin;
        this.zoneVictoire[3] = yMax;
    }
    public Etiquette(String nom, float xMin, float yMin, float tailleCote) {
        this.nom = nom;
        this.zoneVictoire[0] = xMin;
        this.zoneVictoire[1] = xMin + tailleCote;
        this.zoneVictoire[2] = yMin;
        this.zoneVictoire[3] = yMin + tailleCote;
    }

}
