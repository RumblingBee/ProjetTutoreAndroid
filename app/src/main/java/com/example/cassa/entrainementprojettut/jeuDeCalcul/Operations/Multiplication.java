package com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations;

/**
 * Created by clement on 02/01/18.
 */

public class Multiplication implements I_Operation {

    private int terme1;
    private int terme2;
    private char signe='x';

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
        this.terme1=genererNombre(borneSupTerme1, borneInfTerme1);
        this.terme2=genererNombre(borneSupTerme2, borneInfTerme2);
    }

    public void genererOperation(int terme1,int terme2) {
        this.terme1=terme1;
        this.terme2=terme2;
    }

    public void genererOperation(int terme1,int borneSupTerme2, int borneInfTerme2) {
        this.terme1=terme1;
        this.terme2=genererNombre(borneSupTerme2,borneInfTerme2);
    }


    @Override
    public int afficherResultat() {
        return terme1*terme2;
    }

    @Override
    public int genererNombre(int borneSup, int borneInf){

        int nombre = (int)(Math.random() * (borneSup) + borneInf);

        return nombre;
    }
}
