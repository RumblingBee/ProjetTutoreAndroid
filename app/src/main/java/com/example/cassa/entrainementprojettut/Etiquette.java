package com.example.cassa.entrainementprojettut;

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
    private float x;
    private float y;
    private int[] zoneVictoireX=new int[2];
    private int[] zoneVictoireY=new int[2];
    private int idTextView;

    public Etiquette(String nom, int x, int y, int[] zoneVictoireX, int[] zoneVictoireY, int idTextView) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.zoneVictoireX = zoneVictoireX;
        this.zoneVictoireY = zoneVictoireY;
        this.idTextView = idTextView;
    }

    public int getIdTextView() {
        return idTextView;
    }

    public void setIdTextView(int idTextView) {
        this.idTextView = idTextView;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int[] getZoneVictoireX() {
        return zoneVictoireX;
    }

    public void setZoneVictoireX(int[] zoneVictoireX) {
        this.zoneVictoireX = zoneVictoireX;
    }

    public int[] getZoneVictoireY() {
        return zoneVictoireY;
    }

    public void setZoneVictoireY(int[] zoneVictoireY) {
        this.zoneVictoireY = zoneVictoireY;
    }

    //TODO a tester
    public boolean estDansLaZone(){
        return((x>zoneVictoireX[0] && x<zoneVictoireX[1])&&(y>zoneVictoireY[0]&&y<zoneVictoireY[1]));
    }


}
