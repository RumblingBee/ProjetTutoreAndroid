package com.example.cassa.projetAndroid.jeuDeCalcul;

import com.example.cassa.projetAndroid.jeuDeCalcul.Operations.I_Operation;
import com.example.cassa.projetAndroid.jeuDeCalcul.SetsOperation.I_SetOperation;

/**
 * Created by clement on 03/01/18.
 */

public class ControleurOperation {

    private I_SetOperation setOperation;

    public ControleurOperation(int diff) {
        FactorySetOperation factorySetOperation =new FactorySetOperation();
        setOperation=factorySetOperation.createSetOperation(diff);
    }

    public char getSigneOperation(){
        return setOperation.getSigneOperation();
    }

    public int[] getTermesOperation(){
        int[] termes=new int[2];
        termes[0]=setOperation.getTerme1Operation();
        termes[1]=setOperation.getTerme2Operation();
        return termes;
    }

    public boolean verifierReponse(int reponse){
        I_Operation operation=setOperation.getOperation();
        return reponse==operation.afficherResultat();
    }

    public int getReponse(){
        I_Operation operation=setOperation.getOperation();
        return operation.afficherResultat();
    }

}
