package com.example.cassa.entrainementprojettut.operation.SetsOperation;

import com.example.cassa.entrainementprojettut.operation.Ioperation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class SetIoperationCPTest {
    @Test
    public void testTailleSet() throws Exception {
        for (int i = 0; i <100; i++) {
            SetOperationCP setOperationCP=new SetOperationCP();
            assertEquals(setOperationCP.getOperations().size(), 10);
        }
    }

    @Test
    public void testEtendueOperation() throws Exception {
        for (int i = 0; i <100; i++) {
            SetOperationCP setOperationCP=new SetOperationCP();
            for (Ioperation ioperation :setOperationCP.getOperations()) {
                int res= ioperation.afficherResultat();
                assertEquals(true, 2<=res && res<=14);
            }
        }
    }

}