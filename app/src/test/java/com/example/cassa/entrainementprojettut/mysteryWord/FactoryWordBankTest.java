package com.example.cassa.entrainementprojettut.mysteryWord;

import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCE1;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCE2;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCM1;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCM2;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCP;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by clement on 09/01/18.
 */
public class FactoryWordBankTest {

    private FactoryWordBank factoryWordBank;

    @Before
    public void setUp() throws Exception {
        factoryWordBank=new FactoryWordBank();
    }

    @Test
    public void testGenerationCP() throws Exception {
        factoryWordBank.createI_WordBank(1);
        assertEquals(factoryWordBank.getUnMot(0).getClass(), WordCP.class);
    }
    @Test
    public void testGenerationCE1() throws Exception {
        factoryWordBank.createI_WordBank(2);
        assertEquals(factoryWordBank.getUnMot(0).getClass(), WordCE1.class);
    }
    @Test
    public void testGenerationCE2() throws Exception {
        factoryWordBank.createI_WordBank(3);
        assertEquals(factoryWordBank.getUnMot(0).getClass(), WordCE2.class);
    }
    @Test
    public void testGenerationCM1() throws Exception {
        factoryWordBank.createI_WordBank(4);
        assertEquals(factoryWordBank.getUnMot(0).getClass(), WordCM1.class);
    }
    @Test
    public void testGenerationCM2() throws Exception {
        factoryWordBank.createI_WordBank(5);
        assertEquals(factoryWordBank.getUnMot(0).getClass(), WordCM2.class);
    }

}