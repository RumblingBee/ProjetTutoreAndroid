package com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations;

/**
 * Created by clement on 02/01/18.
 */

public class Soustraction implements I_Operation {

    private int terme1;
    private int terme2;
    private char signe='-';

    @Override
    public char getSigne() {
        return signe;
    }

    @Override
    public int getTerme1() {
        return terme1;
    }

    @Override
    public int getTerme2() {
        return terme2;
    }

    @Override
    public void genererOperation(int borneSupTerme1, int borneInfTerme1, int borneSupTerme2, int borneInfTerme2) {
        do {
            this.terme1 = genererNombre(borneSupTerme1, borneInfTerme1);
            this.terme2 = genererNombre(borneSupTerme2, borneInfTerme2);
        }while (terme1-terme2<=0);
    }

    @Override
    public int afficherResultat() {
        return terme1-terme2;
    }

    @Override
    public int genererNombre(int borneSup, int borneInf){

        int nombre = (int)(Math.random() * (borneSup) + borneInf);

        return nombre;
    }

}
