package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.I_operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCP implements I_SetOperation {

    private List<I_operation> operations;

    public List<I_operation> getOperations() {
        return operations;
    }

    @Override
    public I_operation getUneOperation(int i) {
        return operations.get(i);
    }

    public SetOperationCP() {
        this.operations = genererSetOperation();
    }

    public List<I_operation> genererSetOperation() {
        List<I_operation> resultat = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            I_operation ioperation =genererUneOperation();
            resultat.add(ioperation);
        }
        return resultat;
    }

    public I_operation genererUneOperation() {
        return genererAddition();
    }

    public I_operation genererAddition() {
        I_operation ioperation =new Addition();
        ioperation.genererOperation(10,1,4,1);
        return ioperation;
    }

}
