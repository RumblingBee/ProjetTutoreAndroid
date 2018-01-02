package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import android.support.annotation.NonNull;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.Ioperation;
import com.example.cassa.entrainementprojettut.operation.Multiplication;
import com.example.cassa.entrainementprojettut.operation.Soustraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCE2 implements SetOperation {

    private List<Ioperation> operations;

    public SetOperationCE2() {
        this.operations = genererSetOperation();
    }

    @Override
    public List<Ioperation> getOperations() {
        return operations;
    }

    @Override
    public List<Ioperation> genererSetOperation() {
        List<Ioperation> resultat = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Ioperation ioperation =genererUneOperation();
            resultat.add(ioperation);
        }
        return resultat;
    }

    @Override
    public Ioperation genererUneOperation() {
        Ioperation ioperation;
        int operateur=(int)(Math.random() * (3) + 1);
        switch (operateur){
            case 1:
                ioperation= genererAddition();
                break;
            case 2:
                ioperation= genererSoustraction();
                break;
            case 3:
                ioperation = genererMultiplicationSimple();
                break;
            default:
                ioperation=genererAddition();
                break;
        }

        return ioperation;
    }

    @NonNull
    private Ioperation genererMultiplicationSimple() {
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
    public Ioperation genererSoustraction() {
        Soustraction soustraction=new Soustraction();
        soustraction.genererOperation(10,1,10,1);
        return soustraction;
    }

    @NonNull
    public Ioperation genererAddition() {
        Addition addition=new Addition();
        addition.genererOperation(10,1,10,1);
        return addition;
    }
}