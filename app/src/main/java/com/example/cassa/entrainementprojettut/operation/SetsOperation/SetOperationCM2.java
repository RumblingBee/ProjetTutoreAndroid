package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import android.support.annotation.NonNull;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.I_operation;
import com.example.cassa.entrainementprojettut.operation.Multiplication;
import com.example.cassa.entrainementprojettut.operation.Soustraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 03/01/18.
 */

public class SetOperationCM2 implements I_SetOperation {

    private List<I_operation> operations;

    @Override
    public List<I_operation> genererSetOperation() {
        List<I_operation> resultat = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            I_operation ioperation =genererUneOperation();
            resultat.add(ioperation);
        }
        return resultat;
    }

    @Override
    public I_operation getUneOperation(int i) {
        return operations.get(i);
    }

    @Override
    public List<I_operation> getOperations() {
        return this.operations;
    }

    @Override
    public I_operation genererUneOperation() {
        I_operation operation;
        int operateur=(int)(Math.random() * (5) + 1);
        if(operateur>2){
            operation=genererMultiplication();
        }else operation = genererOperationAleatoireHorsMultiplication(operateur);
        return operation;
    }

    @NonNull
    private I_operation genererOperationAleatoireHorsMultiplication(int operateur) {
        I_operation operation;
        if(operateur==2){
            operation=genererSoustraction();
        }else{
            operation=genererAddition();
        }
        return operation;
    }

    private I_operation genererMultiplication() {
        Multiplication multiplication=new Multiplication();
        multiplication.genererOperation(10,1,10,1);
        return multiplication;
    }


    @NonNull
    public I_operation genererSoustraction() {
        Soustraction soustraction=new Soustraction();
        soustraction.genererOperation(10,1,10,1);
        return soustraction;
    }

    @NonNull
    public I_operation genererAddition() {
        Addition addition=new Addition();
        addition.genererOperation(10,1,10,1);
        return addition;
    }
}
