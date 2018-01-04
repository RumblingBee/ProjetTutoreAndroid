package com.example.cassa.entrainementprojettut.jeuDeCalcul;

import com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations.I_Operation;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation.I_SetOperation;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation.SetOperationCE1;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation.SetOperationCE2;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation.SetOperationCM1;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation.SetOperationCM2;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation.SetOperationCP;

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
