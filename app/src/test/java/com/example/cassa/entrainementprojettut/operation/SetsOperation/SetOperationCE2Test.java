package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Ioperation;
import com.example.cassa.entrainementprojettut.operation.Multiplication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class SetOperationCE2Test {

    @Test
    public void testNombreOperation() throws Exception {
        for (int i = 0; i <1000; i++) {
            SetOperationCE2 setOperationCE2=new SetOperationCE2();
            assertEquals(true,setOperationCE2.getOperations().size()==10);
        }
    }

    @Test
    public void testMultiplicationValides() throws Exception {
        for (int i = 0; i <1000; i++) {
            SetOperationCE2 setOperationCE2=new SetOperationCE2();
            for (Ioperation ioperation: setOperationCE2.getOperations()) {
                if (ioperation.getClass()==Multiplication.class){
                    assertEquals(true,ioperation.getTerme1()==10 || ioperation.getTerme1()==2 ||ioperation.getTerme1()==5);
                }
            }

        }
    }
}