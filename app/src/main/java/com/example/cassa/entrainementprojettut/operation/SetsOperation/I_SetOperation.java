package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.I_operation;

import java.util.List;

/**
 * Created by clement on 02/01/18.
 */

public interface I_SetOperation {

    List<I_operation> genererSetOperation();

    List<I_operation> getOperations();

    I_operation getUneOperation(int i);

    I_operation genererUneOperation();

}
