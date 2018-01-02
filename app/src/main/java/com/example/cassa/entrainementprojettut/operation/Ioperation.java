package com.example.cassa.entrainementprojettut.operation;

/**
 * Created by clement on 02/01/18.
 */

public interface Ioperation {

    int getTerme1();
    int getTerme2();

    void genererOperation(int borneSupTerme1, int borneInfTerme1, int borneSupTerme2, int borneInfTerme2);

    int afficherResultat();

    int genererNombre(int borneSup, int borneInf);

}
