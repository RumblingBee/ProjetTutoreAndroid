package com.example.cassa.entrainementprojettut.operation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 02/01/18.
 */
public class SoustractionTest {
    @Test
    public void afficherResultat() throws Exception {
        for(int i=0;i<10000;i++){
            Soustraction soustraction=new Soustraction(10,1);
            assertTrue(soustraction.afficherResultat()>=0);
        }
    }

}