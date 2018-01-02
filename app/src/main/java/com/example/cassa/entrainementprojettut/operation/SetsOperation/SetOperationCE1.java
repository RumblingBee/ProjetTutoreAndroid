package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.I_operation;
import com.example.cassa.entrainementprojettut.operation.Soustraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCE1 implements I_SetOperation {

    private List<I_operation> operations;

    public List<I_operation> getOperations() {
        return operations;
    }

    public SetOperationCE1() {
        this.operations=genererSetOperation();
    }

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
    public I_operation genererUneOperation() {
        int operateur=(int)(Math.random() * (2) + 1);
        if(operateur==1){
            return genererAddition();
        }else{
            return genererSoustraction();
        }
    }

    public I_operation genererAddition() {
        Addition addition=new Addition();
        addition.genererOperation(10,1,10,1);
        return addition;
    }

    public I_operation genererSoustraction() {
        Soustraction soustraction=new Soustraction();
        soustraction.genererOperation(10,1,10,1);
        return soustraction;
    }
}
