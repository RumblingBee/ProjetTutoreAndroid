package com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation;

import com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations.Addition;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations.I_Operation;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations.Multiplication;
import com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations.Soustraction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class SetOperationCE2Test {

    private I_SetOperation setOperation;
    private I_Operation operation;

    @Before
    public void setUp() throws Exception {
        setOperation=new SetOperationCE2();
    }

    @Test
    public void testMultiplicationValides() throws Exception {
        for (int i = 0; i <1000; i++) {
            operation=setOperation.genererUneOperation();
            if (operation.getClass()==Multiplication.class){
                assertEquals(true,operation.getTerme1()==10 || operation.getTerme1()==2 ||operation.getTerme1()==5);
            }
        }
    }

    @Test
    public void testPresenceDesTroisOperations() throws Exception {
        int nbAddition=0,nbSoustraction=0,nbMultiplication=0;
        for (int i = 0; i <10000 ; i++) {
            operation=setOperation.genererUneOperation();
            if(operation.getClass()== Addition.class){
                nbAddition++;
            }else if(operation.getClass()== Soustraction.class){
                nbSoustraction++;
            }else{
                nbMultiplication++;
            }
        }
        assertEquals(true,nbAddition>0 && nbSoustraction>0 && nbMultiplication>0);
    }
}