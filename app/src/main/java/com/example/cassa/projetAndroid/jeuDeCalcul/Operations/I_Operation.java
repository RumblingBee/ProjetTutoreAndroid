package com.example.cassa.projetAndroid.jeuDeCalcul.Operations;

/**
 * Created by clement on 02/01/18.
 */

public interface I_Operation {

    int getTerme1();
    int getTerme2();
    char getSigne();

    void genererOperation(int borneSupTerme1, int borneInfTerme1, int borneSupTerme2, int borneInfTerme2);

    int afficherResultat();

    int genererNombre(int borneSup, int borneInf);

}
