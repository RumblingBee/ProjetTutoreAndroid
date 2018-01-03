package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.I_Operation;
import com.example.cassa.entrainementprojettut.operation.Soustraction;

/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCE1 implements I_SetOperation {

    private I_Operation operation;

    public SetOperationCE1() {
        this.operation =genererUneOperation();
    }

    public I_Operation getOperation() {
        return operation;
    }

    @Override
    public I_Operation genererUneOperation() {
        int operateur=(int)(Math.random() * (2) + 1);
        if(operateur==1){
            return genererAddition();
        }else{
            return genererSoustraction();
        }
    }

    private I_Operation genererAddition() {
        Addition addition=new Addition();
        addition.genererOperation(10,1,10,1);
        return addition;
    }

    private I_Operation genererSoustraction() {
        Soustraction soustraction=new Soustraction();
        soustraction.genererOperation(10,1,10,1);
        return soustraction;
    }
}
