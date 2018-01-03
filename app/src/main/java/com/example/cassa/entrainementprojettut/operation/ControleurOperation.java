package com.example.cassa.entrainementprojettut.operation;

import com.example.cassa.entrainementprojettut.operation.SetsOperation.I_SetOperation;
import com.example.cassa.entrainementprojettut.operation.SetsOperation.SetOperationCE1;
import com.example.cassa.entrainementprojettut.operation.SetsOperation.SetOperationCE2;
import com.example.cassa.entrainementprojettut.operation.SetsOperation.SetOperationCM1;
import com.example.cassa.entrainementprojettut.operation.SetsOperation.SetOperationCM2;
import com.example.cassa.entrainementprojettut.operation.SetsOperation.SetOperationCP;

/**
 * Created by clement on 03/01/18.
 */

public class ControleurOperation {

    private I_SetOperation setOperation;

    public ControleurOperation(int difficulte) {

        switch (difficulte){
            case 1:
                setOperation=new SetOperationCP();
                break;
            case 2:
                setOperation=new SetOperationCE1();
                break;
            case 3:
                setOperation=new SetOperationCE2();
                break;
            case 4:
                setOperation=new SetOperationCM1();
                break;
            default:
                setOperation=new SetOperationCM2();
                break;
        }
    }

}
