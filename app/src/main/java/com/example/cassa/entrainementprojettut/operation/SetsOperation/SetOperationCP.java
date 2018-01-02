package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.Ioperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCP implements SetOperation {

    private List<Ioperation> operations;

    public List<Ioperation> getOperations() {
        return operations;
    }

    public SetOperationCP() {
        this.operations = genererSetOperation();
    }

    public List<Ioperation> genererSetOperation() {
        List<Ioperation> resultat = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Ioperation ioperation =genererUneOperation();
            resultat.add(ioperation);
        }
        return resultat;
    }

    public Ioperation genererUneOperation() {
        return genererAddition();
    }

    @Override
    public Ioperation genererAddition() {
        Ioperation ioperation =new Addition();
        ioperation.genererOperation(10,1,4,1);
        return ioperation;
    }

    @Override
    public Ioperation genererSoustraction() {
        return null;
    }
}
