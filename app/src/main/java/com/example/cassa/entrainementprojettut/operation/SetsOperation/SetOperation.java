package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Ioperation;

import java.util.List;

/**
 * Created by clement on 02/01/18.
 */

public interface SetOperation {

    List<Ioperation> genererSetOperation();

    List<Ioperation> getOperations();

    Ioperation genererUneOperation();

}
