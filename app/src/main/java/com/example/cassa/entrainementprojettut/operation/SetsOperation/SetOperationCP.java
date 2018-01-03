package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Addition;
import com.example.cassa.entrainementprojettut.operation.I_Operation;


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

    public I_Operation genererUneOperation() {
        return genererAddition();
    }

    public I_Operation genererAddition() {
        I_Operation ioperation =new Addition();
        ioperation.genererOperation(10,1,4,1);
        return ioperation;
    }

}
