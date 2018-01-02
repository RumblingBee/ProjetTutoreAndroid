package com.example.cassa.entrainementprojettut.operation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class OperationTest {
    @Test
    public void genererNombre() throws Exception {
        for (int i = 0; i <10000 ; i++) {
            Multiplication  multiplication=new Multiplication(10,1);
            int test = multiplication.genererNombre(10, 1);
            assertTrue(test <=10 && test >=1);
        }
    }

}