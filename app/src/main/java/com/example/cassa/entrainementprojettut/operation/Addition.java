package com.example.cassa.entrainementprojettut.operation;

/**
 * Created by clement on 02/01/18.
 */

public class Addition extends Operation {

    private int terme1;
    private int terme2;

    public Addition(int borneSup, int borneInf) {
        this.terme1=genererNombre(borneSup, borneInf);
        this.terme2=genererNombre(borneSup, borneInf);
    }

    public int afficherResultat(){
        return terme1+terme1;
    }

}
