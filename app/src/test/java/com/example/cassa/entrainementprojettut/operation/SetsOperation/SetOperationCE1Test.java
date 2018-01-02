package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Ioperation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class SetOperationCE1Test {

    @Test
    public void testValiditeOperation() throws Exception {
        for (int i = 0; i <1000; i++) {
            SetOperation setOperation=new SetOperationCE1();
            int taille=setOperation.getOperations().size();
            assertEquals(true,taille==10);
        }
    }

    @Test
    public void testValiditeSoustraction() throws Exception {
        for (int i = 0; i <1000; i++) {
            SetOperation setOperation=new SetOperationCE1();
            for (Ioperation ioperation:setOperation.getOperations()) {
                assertEquals(true,ioperation.afficherResultat()>=0);
            }
        }
    }
}