package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.Operation;
import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.Ioperation;
import com.example.cassa.entrainementprojettut.operation.Soustraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCE1 implements SetOperation {

    private List<Ioperation> operations;

    public List<Ioperation> getOperations() {
        return operations;
    }

    SetOperationCE1() {
        this.operations=genererSetOperation();
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
        int operateur=(int)(Math.random() * (2) + 1);
        if(operateur==1){
            return genererAddition();
        }else{
            return genererSoustraction();
        }
    }

    @Override
    public Ioperation genererAddition() {
        Addition addition=new Addition();
        addition.genererOperation(10,1,10,1);
        return addition;
    }

    @Override
    public Ioperation genererSoustraction() {
        Soustraction soustraction=new Soustraction();
        soustraction.genererOperation(10,1,10,1);
        return soustraction;
    }
}
