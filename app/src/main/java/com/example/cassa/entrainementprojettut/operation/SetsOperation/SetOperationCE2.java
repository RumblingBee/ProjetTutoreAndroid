package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import android.support.annotation.NonNull;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.I_Operation;
import com.example.cassa.entrainementprojettut.operation.Multiplication;
import com.example.cassa.entrainementprojettut.operation.Soustraction;

/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCE2 implements I_SetOperation {

    private I_Operation operation;

    public SetOperationCE2() {
        this.operation = genererUneOperation();
    }

    public I_Operation getOperation() {
        return operation;
    }


    @Override
    public I_Operation genererUneOperation() {
        I_Operation operation;
        int operateur=(int)(Math.random() * (3) + 1);
        switch (operateur){
            case 1:
                operation= genererAddition();
                break;
            case 2:
                operation= genererSoustraction();
                break;
            case 3:
                operation = genererMultiplicationSimple();
                break;
            default:
                operation=genererAddition();
                break;
        }

        return operation;
    }

    @NonNull
    private I_Operation genererMultiplicationSimple() {
        int terme1=genererMultipleSimple();
        Multiplication multiplication=new Multiplication();
        multiplication.genererOperation(terme1,10,1);
        return multiplication;
    }

    private int genererMultipleSimple() {
        int tabProduit[] = new int[4];
        tabProduit[1] = 2;
        tabProduit[2] = 5;
        tabProduit[3] = 10;

        return tabProduit[(int)(Math.random() * (3) + 1)];
    }

    @NonNull
    public I_Operation genererSoustraction() {
        Soustraction soustraction=new Soustraction();
        soustraction.genererOperation(10,1,10,1);
        return soustraction;
    }

    @NonNull
    public I_Operation genererAddition() {
        Addition addition=new Addition();
        addition.genererOperation(10,1,10,1);
        return addition;
    }
}