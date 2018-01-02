package com.example.cassa.entrainementprojettut.operation;

/**
 * Created by clement on 02/01/18.
 */

public class Multiplication extends Operation {

    private int terme1;
    private int terme2;

    public Multiplication(int borneSup,int borneInf) {
        this.terme1=genererNombre(borneSup,borneInf);
        this.terme2=genererNombre(borneSup,borneInf);
    }

    @Override
    public int afficherResultat() {
        return terme1*terme2;
    }
}
