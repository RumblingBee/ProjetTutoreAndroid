package com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation;

import android.support.annotation.NonNull;

import com.example.cassa.projetAndroid.jeuDeCalcul.Operations.Addition;
import com.example.cassa.projetAndroid.jeuDeCalcul.Operations.I_Operation;
import com.example.cassa.projetAndroid.jeuDeCalcul.Operations.Multiplication;
import com.example.cassa.projetAndroid.jeuDeCalcul.Operations.Soustraction;



/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCM1 implements I_SetOperation {

    private I_Operation operation;

    public SetOperationCM1() {
        this.operation = genererUneOperation();
    }

    public I_Operation getOperation() {
        return this.operation;
    }

    @Override
    public int getTerme1Operation() {
        return operation.getTerme1();
    }

    @Override
    public int getTerme2Operation() {
        return operation.getTerme2();
    }

    @Override
    public char getSigneOperation() {
        return operation.getSigne();
    }

    @Override
    public I_Operation genererUneOperation() {
        I_Operation operation;
        int operateur=(int)(Math.random() * (4) + 1);
        if(operateur>2){
            operation=genererMultiplication();
        }else operation = genererOperationAleatoireHorsMultiplication(operateur);
        return operation;
    }

    private I_Operation genererMultiplication() {
        Multiplication multiplication=new Multiplication();
        multiplication.genererOperation(10,1,10,1);
        return multiplication;
    }

    @NonNull
    private I_Operation genererOperationAleatoireHorsMultiplication(int operateur) {
        I_Operation operation;
        if(operateur==2){
            operation=genererSoustraction();
        }else{
            operation=genererAddition();
        }
        return operation;
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
        addition.genererOperation(12,1,12,1);
        return addition;
    }
}
