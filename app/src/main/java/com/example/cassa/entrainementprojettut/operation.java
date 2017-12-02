package com.example.cassa.entrainementprojettut;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by prax on 10/11/17.
 */

public class operation {


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

    public operation(int difficulte){
        signe=genererSigne(difficulte);
        switch(signe){
            case '+':
                terme1=genererNombre(10,1);
                terme2=genererNombre(10,1);
                break;
            case '-':
                terme1=genererNombre(10,2);
                terme2=genererNombre(terme1-1,1);
                break;
            case 'x':
                terme1=genererNombre(10,1);
                terme2=genererNombre(10,1);
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
            default:
                signe='+';
                break;
        }
        return signe;
    }



    }


