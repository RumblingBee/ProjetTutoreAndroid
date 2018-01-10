package com.example.cassa.projetAndroid.jeuDeCalcul;

import com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation.I_SetOperation;
import com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation.SetOperationCE1;
import com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation.SetOperationCE2;
import com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation.SetOperationCM1;
import com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation.SetOperationCM2;
import com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation.SetOperationCP;

/**
 * Created by clement on 09/01/18.
 */

public class FactorySetOperation {

    public I_SetOperation createSetOperation(int difficulte) {
        I_SetOperation setOperation;
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
        return setOperation;
    }
}
