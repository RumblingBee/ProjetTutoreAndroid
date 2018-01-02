package com.example.cassa.entrainementprojettut.operation;

/**
 * Created by clement on 02/01/18.
 */

public abstract class Operation {

    public abstract int afficherResultat();

    public int genererNombre(int borneSup, int borneInf){
        return (int)(Math.random() * borneSup + borneInf);
    }

}
