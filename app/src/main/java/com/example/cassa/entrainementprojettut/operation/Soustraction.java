package com.example.cassa.entrainementprojettut.operation;

/**
 * Created by clement on 02/01/18.
 */

public class Soustraction extends Operation {

    private int terme1;
    private int terme2;

    public Soustraction(int borneSup,int borneInf) {
        this.terme1 = genererNombre(borneSup,borneInf);
        do {
            this.terme2 = genererNombre(borneSup, borneInf);
        }while (terme1<terme2);
    }

    @Override
    public int afficherResultat() {
        return terme1-terme2;
    }

}
