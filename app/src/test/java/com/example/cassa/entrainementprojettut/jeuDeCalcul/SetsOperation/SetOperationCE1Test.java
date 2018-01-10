package com.example.cassa.entrainementprojettut.jeuDeCalcul.SetsOperation;

import com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations.I_Operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class SetOperationCE1Test {

    private I_SetOperation setOperation;

    @Before
    public void setUp() throws Exception {
        setOperation=new SetOperationCE1();
    }

    @Test
    public void testValiditeSoustraction() throws Exception {
        for (int i = 0; i <1000; i++) {
            I_Operation operation=setOperation.genererUneOperation();
            assertEquals(true,operation.afficherResultat()>=0);
        }
    }
}