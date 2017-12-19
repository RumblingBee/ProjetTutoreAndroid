package com.example.cassa.entrainementprojettut;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by prax on 10/11/17.
 */

public class Operation {


    protected int terme1;
    protected int terme2;
    protected int reponse;
    protected char signe;

    public char getSigne() {
        return signe;
    }

    public int getReponse() {
        return reponse;
    }

    public int getTerme1() {
        return terme1;
    }

    public int getTerme2() {
        return terme2;
    }

    public Operation(int difficulte){
        signe = genererSigne(difficulte);
        switch (difficulte) {
            //CP
            case 1:
                terme1 = genererNombre(10, 1);
                terme2 = genererNombre(4, 1);
                break;

            //CE1
            case 2:
                switch (signe) {
                    case '+':
                        terme1 = genererNombre(10, 1);
                        terme2 = genererNombre(10, 1);
                        break;
                    case '-':
                        terme1 = genererNombre(10, 4);
                        terme2 = genererNombre(terme1-3, 1);
                        break;
                }
                break;

            //CE2
            case 3:
                switch (signe) {
                    case '+':
                        terme1 = genererNombre(10, 1);
                        terme2 = genererNombre(10, 1);
                        break;
                    case '-':
                        terme1 = genererNombre(10, 4);
                        terme2 = genererNombre(terme1-3, 1);
                        break;
                    case 'x':
                        //On souhaite générer des multiplications qu'avec 2,5,10

                        int tabProduit[] = new int[3];
                        tabProduit[0] = 2;
                        tabProduit[1] = 5;
                        tabProduit[2] = 10;

                       int indiceTabProduit = genererNombre(2,0);

                        terme1 = tabProduit[indiceTabProduit];
                        terme2 = genererNombre(10, 1);
                        break;
                }
                break;

            //CM1
            case 4:
                switch (signe) {
                    case '+':
                        terme1 = genererNombre(12, 1);
                        terme2 = genererNombre(12, 1);
                        break;
                    case '-':
                        terme1 = genererNombre(10, 4);
                        terme2 = genererNombre(terme1-3, 1);
                        break;
                    case 'x':
                        terme1 = genererNombre(10, 1);
                        terme2 = genererNombre(10, 1);
                        break;
                }
                break;

            //CM2
            case 5:
                switch (signe) {
                    case '+':
                        terme1 = genererNombre(15, 1);
                        terme2 = genererNombre(15, 1);
                        break;
                    case '-':
                        terme1 = genererNombre(15, 4);
                        terme2 = genererNombre(terme1-3, 1);
                        break;
                    case 'x':
                        terme1 = genererNombre(10, 1);
                        terme2 = genererNombre(10, 1);
                        break;
                }
                break;
        }
        reponse=calculerResultat(signe);

    }


    protected int genererNombre(int borneSup,int borneInf){

        int nombre = (int)(Math.random() * (borneSup) + borneInf);

        return nombre;
    }

    protected int calculerResultat(char signe) {

        int res=0;

        switch (signe) {
            case '+':
                res= terme1 + terme2;
            break;
            case '-':
                res=terme1 - terme2;
            break;
            case 'x':
                res=terme1*terme2;
            break;
        }
        return res;
    }

    protected char genererSigne(int diff){
        char signe;
        int cas=(int)(Math.random() * (diff) + 1);
        switch (cas){
            case 1:
                signe='+';
                break;
            case 2:
                signe='-';
                break;
            case 3:
                signe='x';
                break;
            case 4:
                signe='x';
                break;
            default:
                signe='x';
                break;
        }
        return signe;
    }

}