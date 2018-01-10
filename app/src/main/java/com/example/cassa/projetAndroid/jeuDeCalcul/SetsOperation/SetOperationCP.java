package com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation;

import com.example.cassa.projetAndroid.jeuDeCalcul.Operations.Addition;
import com.example.cassa.projetAndroid.jeuDeCalcul.Operations.I_Operation;


/**
 * Created by clement on 02/01/18.
 */

public class SetOperationCP implements I_SetOperation {

    private I_Operation operation;

    public SetOperationCP() {
        this.operation = genererUneOperation();
    }

    public I_Operation getOperation() {
        return operation;
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

    public I_Operation genererUneOperation() {
        return genererAddition();
    }

    public I_Operation genererAddition() {
        I_Operation ioperation =new Addition();
        ioperation.genererOperation(10,1,4,1);
        return ioperation;
    }

}
