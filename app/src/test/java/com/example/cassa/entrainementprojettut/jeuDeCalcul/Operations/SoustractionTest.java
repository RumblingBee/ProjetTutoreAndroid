package com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations;

import com.example.cassa.entrainementprojettut.jeuDeCalcul.Operations.Soustraction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class SoustractionTest {

    private Soustraction soustraction;

    @Before
    public void setUp() throws Exception {
        soustraction=new Soustraction();
    }

    @Test
    public void testPasDeNegatif() throws Exception {
        for(int i=0;i<1000;i++){
            soustraction.genererOperation(10,1,10,1);
            System.out.println(soustraction.afficherResultat());
            assertEquals(true, soustraction.afficherResultat() > 0);
        }
    }

    @Test
    public void testStandardSoustraction() throws Exception {
        for (int i = 0; i <1000; i++) {
            soustraction.genererOperation(10,1,10,1);
            assertEquals(soustraction.afficherResultat(),soustraction.getTerme1()-soustraction.getTerme2());
        }
    }
}