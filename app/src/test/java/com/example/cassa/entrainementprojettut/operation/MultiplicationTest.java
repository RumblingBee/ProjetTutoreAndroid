package com.example.cassa.entrainementprojettut.operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class MultiplicationTest {

    private Multiplication multiplication;

    @Before
    public void setUp() throws Exception {
        multiplication=new Multiplication();
    }

    @Test
    public void testStandardMultiplication() throws Exception {
        for (int i = 0; i <1000; i++) {
            multiplication.genererOperation(5,1,5,1);
            assertEquals(multiplication.afficherResultat(), (multiplication.getTerme1() * multiplication.getTerme2()));
        }
    }
}