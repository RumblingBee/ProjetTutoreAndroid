package com.example.cassa.entrainementprojettut.geographie;

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


    public Etiquette(String nom, float xMin, float yMin, float tailleCote) {
        this.nom = nom;
        this.zoneVictoire[0] = xMin;
        this.zoneVictoire[1] = xMin + tailleCote;
        this.zoneVictoire[2] = yMin;
        this.zoneVictoire[3] = yMin + tailleCote;
    }

}
